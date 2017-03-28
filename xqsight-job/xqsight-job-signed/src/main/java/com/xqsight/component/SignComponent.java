package com.xqsight.component;

import com.alibaba.fastjson.JSON;
import com.xqsight.config.BaseInfoProperties;
import com.xqsight.data.ehcache.core.CacheTemplate;
import com.xqsight.model.LoginRet;
import com.xqsight.model.Result;
import com.xqsight.utils.PasswordHelp;
import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.net.ssl.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

/**
 * @author wangganggang
 * @date 2017/03/28
 */
@Component
public class SignComponent {
    protected Logger logger = LogManager.getLogger(SignComponent.class);

    private static final String CASH_KEY="sign_cookie_";

    @Autowired
    private BaseInfoProperties baseInfoProperties;

    @Autowired
    private CacheTemplate cacheTemplate;

    private OkHttpClient httpClient;

    public SignComponent() throws Exception {
        TrustManager[] managers = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                }
        };
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, managers, new SecureRandom());

        httpClient = new OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) (managers[0]))
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String s, SSLSession sslSession) {
                        return true;
                    }
                })
                .build();
    }

    public LoginRet login(String userName, String password) throws Exception {
        StringBuffer stringBuffer = new StringBuffer(baseInfoProperties.getLoginUrl())
                .append("?appid=").append(baseInfoProperties.getAppId()).append("&userid=")
                .append(userName).append("&password=").append(PasswordHelp.getMd5Str(password).toUpperCase());
        Request build = new Request.Builder()
                .url(stringBuffer.toString())
                .build();
        Response response = null;
        try {
            response = httpClient.newCall(build).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String ret = getResponseString(response.body().byteStream());
        logger.debug("login response={}", ret);
        return JSON.parseObject(ret, LoginRet.class);
    }

    public String auth(String userName, String password) throws Exception {
        LoginRet loginRet = login(userName, password);

        StringBuffer stringBuffer = new StringBuffer(baseInfoProperties.getAuthUrl())
                .append("?appid=").append(baseInfoProperties.getAppId()).append("&sid=")
                .append(loginRet.getSid()).append("&ticket=").append(loginRet.getTicket()).append("&sign=")
                .append(PasswordHelp.getMd5Str("appid=" + baseInfoProperties.getAppId() + "&sid=" + loginRet.getSid()+ "&ticket=" + loginRet.getTicket() + loginRet.getKey()));
        Request build = new Request.Builder()
                .url(stringBuffer.toString())
                .build();
        Response response = null;
        try {
            response = httpClient.newCall(build).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String ret = getResponseString(response.body().byteStream());
        logger.debug("response={}", ret);
        return response.header("Set-Cookie");
    }

    public void signJob(String userName, String password) throws Exception {
        String cookie =  (String)cacheTemplate.get(CASH_KEY + userName);
        if(cookie == null || "".equals(cookie)){
            cookie = auth(userName, password);
            cacheTemplate.put(CASH_KEY + userName,cookie);
        }

        Result result = sign(userName,cookie);
        logger.info("{}签到结果:{}",userName,result.getMessage());
        if(!result.getCode().equals("0000")){
            cookie = auth(userName, password);
            cacheTemplate.put(CASH_KEY + userName,cookie);
            result = sign(userName,cookie);
            logger.info("{}重新签到结果:{}",userName,result.getMessage());
        }
    }

    public Result sign(String userName, String cookie)throws Exception {
        StringBuffer stringBuffer = new StringBuffer(baseInfoProperties.getSignUrl())
                .append("?user=").append(userName);
        Request build = new Request.Builder()
                .url(stringBuffer.toString())
                .addHeader("Cookie", cookie)
                .build();
        Response response = null;
        try {
            response = httpClient.newCall(build).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String ret = getResponseString(response.body().byteStream());
        logger.debug("login response={}", ret);
        JAXBContext jaxbContext = JAXBContext.newInstance(Result.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Result result = (Result) jaxbUnmarshaller.unmarshal(new StringReader(ret));

        return result;
    }

    private String getResponseString(InputStream input) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        byte[] buffer = new byte[512];
        int count = 0;
        while(-1 != (count = input.read(buffer))) {
            baos.write(buffer, 0, count);
            baos.flush();
        }
        input.close();
        baos.close();
        String ret = new String(baos.toByteArray());
        return new String(baos.toByteArray());
    }
}
