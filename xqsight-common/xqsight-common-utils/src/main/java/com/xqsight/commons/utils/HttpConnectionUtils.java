/**
 * 
 */
package com.xqsight.commons.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Http连接实用类
 * @author xqsight-jerry
 * 
 */
public class HttpConnectionUtils {

    private static Log logger = LogFactory.getLog(HttpConnectionUtils.class);

    /**
     *  Open a normal https connection to the specified url.
     * @param urlPath
     * @param charset
     * @param connectTimeout
     * @param readTimeout
     * @return
     * @throws Exception
     */
    public static String openHttpsConnection(final String urlPath, String charset, int connectTimeout, int readTimeout, Map<String, String> headerMap) throws Exception {
        HttpURLConnection conn = null;
        URL url = null;
        try {
            url = new URL(urlPath);
            conn = (HttpURLConnection) url.openConnection();
            if (headerMap != null) {
                for (String key : headerMap.keySet()) {
                    conn.setRequestProperty(key, headerMap.get(key));
                }
            }
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; " + "SailBrowser; Maxthon; Alexa Toolbar; .NET CLR 2.0.50727)");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);
            conn.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), Charset.forName(charset)));
            String str = null;
            StringBuffer returnStr = new StringBuffer(200);
            while ((str = reader.readLine()) != null) {
                returnStr.append(str);
            }
            String result = returnStr.toString();
            conn.disconnect();
            return result;
        } catch (Exception e) {
            logger.error(e);
            throw e;
        } finally {
            conn = null;
            url = null;
        }
    }

    /**
     *  Open a normal https connection to the specified url.
     *  修改以支持中文 by xqsight-jerry 201401222
     * @param urlPath
     * @param postData
     * @param charset
     * @param connectTimeout
     * @param readTimeout
     * @return
     * @throws Exception
     */
    public static String openHttpsConnection(final String urlPath, final String postData, String charset, int connectTimeout, int readTimeout, Map<String, String> headerMap) throws Exception {
        HttpURLConnection conn = null;
        URL url = null;
        OutputStream os = null;
        InputStreamReader isr = null;
        try {
            url = new URL(urlPath);
            conn = (HttpURLConnection) url.openConnection();
            if (headerMap != null) {
                for (String key : headerMap.keySet()) {
                    conn.setRequestProperty(key, headerMap.get(key));
                }
            }
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            os = conn.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            dos.write(postData.getBytes(charset));
            dos.flush();
            dos.close();
            isr = new InputStreamReader(conn.getInputStream(), charset);
            BufferedReader reader = new BufferedReader(isr);
            String str = null;
            StringBuffer returnStr = new StringBuffer(200);
            while ((str = reader.readLine()) != null) {
                returnStr.append(str);
            }
            String result = returnStr.toString();
            conn.disconnect();
            return result;
        } catch (Exception e) {
            logger.error(e);
            throw e;
        } finally {
            conn = null;
            url = null;
        }
    }

    /**
     *  Open a normal https connection to the specified url.
     */
    public static String openHttpsConnection(final String urlPath, String charset, int connectTimeout, int readTimeout) throws Exception {
        HttpURLConnection conn = null;
        URL url = null;
        try {
            url = new URL(urlPath);
            logger.info("url:" + url);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; " + "SailBrowser; Maxthon; Alexa Toolbar; .NET CLR 2.0.50727)");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);
            conn.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), Charset.forName(charset)));
            String str = null;
            StringBuffer returnStr = new StringBuffer(200);
            while ((str = reader.readLine()) != null) {
                returnStr.append(str);
            }
            conn.disconnect();
            return returnStr.toString();
        } catch (Exception e) {
            logger.error(e);
            throw e;
        } finally {
            conn = null;
            url = null;
        }
    }

    /**
     *  Open a normal https connection to the specified url.
     * @param urlPath
     * @param postData
     * @param charset
     * @param connectTimeout
     * @param readTimeout
     * @return
     * @throws Exception
     */
    public static String openHttpsConnection(final String urlPath, final String postData, String charset, int connectTimeout, int readTimeout) throws Exception {
        HttpURLConnection conn = null;
        URL url = null;
        try {
            url = new URL(urlPath);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoOutput(true);
            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
            dos.writeBytes(postData);
            dos.flush();
            dos.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
            String str = null;
            StringBuffer returnStr = new StringBuffer(200);
            while ((str = reader.readLine()) != null) {
                returnStr.append(str);
            }
            String result = returnStr.toString();
            conn.disconnect();
            return result;
        } catch (Exception e) {
            logger.error(e);
            throw e;
        } finally {
            conn = null;
            url = null;
        }
    }

    /**
     *  Open a normal https connection to the specified url.
     * @param urlPath
     * @param postData
     * @param charset
     * @param connectTimeout
     * @param readTimeout
     * @return
     * @throws Exception
     */
    public static String openHttpsConnectionWithUrlEncodeParams(final String urlPath, final Map<String, String> params, String charset, int connectTimeout, int readTimeout) throws Exception {
        HttpURLConnection conn = null;
        URL url = null;
        OutputStream os = null;
        InputStreamReader isr = null;
        try {
            url = new URL(urlPath);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoOutput(true);
            os = conn.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            StringBuffer postData = new StringBuffer();
            boolean notFirst = false;
            for (String key : params.keySet()) {
                if (notFirst) {
                    postData.append('&');
                } else {
                    notFirst = true;
                }
                postData.append(key).append('=');
                String val = params.get(key);
                if (val != null) {
                    postData.append(URLEncoder.encode(val, charset));
                }
            }
            dos.writeBytes(postData.toString());
            dos.flush();
            dos.close();
            isr = new InputStreamReader(conn.getInputStream(), charset);
            BufferedReader reader = new BufferedReader(isr);
            String str = null;
            StringBuffer returnStr = new StringBuffer(200);
            while ((str = reader.readLine()) != null) {
                returnStr.append(str);
            }
            String result = returnStr.toString();
            conn.disconnect();
            return result;
        } catch (Exception e) {
            logger.error(e);
            throw e;
        } finally {
            conn = null;
            url = null;
        }
    }

    /**
     * Open a https connection with specified pfx file and password.
     * 
     * @param urlPath
     *            String
     * @param password
     *            The password to pfx file.
     * @param pfxPath
     *            Pfx file path.
     * @return String
     * @throws Exception
     */
    public static String openHttpsConnection(final String urlPath, final String password, final String pfxPath, String charset) throws Exception {
        HttpsURLConnection conn = null;
        try {
            conn = createHttpsConnection(urlPath, password, pfxPath);
            conn.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
            String str = null;
            StringBuffer returnStr = new StringBuffer(200);
            while ((str = reader.readLine()) != null) {
                returnStr.append(str);
            }
            String result = returnStr.toString();
            conn.disconnect();
            return result;
        } catch (Exception e) {
            logger.error(e);
            throw e;
        } finally {
            conn = null;
        }
    }

    /**
     * Open a https connection with specified pfx file and password via post method.
     * 
     * @param urlPath
     * @param password
     * @param pfxPath
     * @param postData
     * @return
     * @throws Exception
     */
    public static String openHttpsConnection(final String urlPath, final String password, final String pfxPath, final String postData, String charset) throws Exception {
        OutputStream os = null;
        InputStreamReader isr = null;
        HttpsURLConnection conn = null;
        try {
            conn = createHttpsConnection(urlPath, password, pfxPath);
            conn.setRequestMethod("POST");
            os = conn.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeBytes(postData);
            dos.flush();
            dos.close();
            isr = new InputStreamReader(conn.getInputStream());
            BufferedReader reader = new BufferedReader(isr);
            String str = null;
            StringBuffer returnStr = new StringBuffer(200);
            while ((str = reader.readLine()) != null) {
                returnStr.append(str);
            }
            String result = returnStr.toString();
            conn.disconnect();
            return result;
        } catch (Exception e) {
            logger.error(e);
            throw e;
        } finally {
            if (null != os) {
                try {
                    os.close();
                    os = null;
                } catch (IOException e) {
                    logger.error(e);
                }
            }
            if (null != isr) {
                try {
                    isr.close();
                    isr = null;
                } catch (IOException e) {
                    logger.error(e);
                }
            }
            conn = null;
        }
    }
    
    public static String openHttpsConnection(final String urlPath, final String password, final String pfxPath, final String postData, String reqCharset, String respCharset) throws Exception {
        OutputStream os = null;
        InputStream is = null;
        HttpsURLConnection conn = null;
        try {
            conn = createHttpsConnection(urlPath, password, pfxPath);
            conn.setRequestMethod("POST");
            os = conn.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            dos.write(postData.getBytes(reqCharset));
            dos.flush();
            dos.close();
            
            is = conn.getInputStream();
            byte[] bytes = new byte[2048];
            int len = 0;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while((len = is.read(bytes)) > 0) {
                baos.write(bytes, 0, len);
            }
            String result = baos.toString(respCharset);
            is.close();
            conn.disconnect();
            return result;
        } catch (Exception e) {
            logger.error(e);
            throw e;
        } finally {
            if (null != os) {
                try {
                    os.close();
                    os = null;
                } catch (IOException e) {
                    logger.error(e);
                }
            }
            if (null != is) {
                try {
                    is.close();
                    is = null;
                } catch (IOException e) {
                    logger.error(e);
                }
            }
            conn = null;
        }
    }

    /**
     *  Open a normal https connection to the specified url.
     * @param urlPath
     * @param postData
     * @param charset
     * @param connectTimeout
     * @param readTimeout
     * @return
     * @throws Exception
     */
    public static String openHttpsConnectionBufferedOutput(final String urlPath, final String postData, String charset, int connectTimeout, int readTimeout, Map<String, String> headerMap)
                                                                                                                                                                                           throws Exception {
        HttpURLConnection conn = null;
        URL url = null;
        OutputStream os = null;
        InputStreamReader isr = null;
        BufferedWriter bw = null;
        try {
            url = new URL(urlPath);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            if (headerMap != null) {
                for (String key : headerMap.keySet()) {
                    conn.setRequestProperty(key, headerMap.get(key));
                }
            }
            conn.setRequestProperty("Content-Type", "text/json");
            conn.setDoOutput(true);
            os = conn.getOutputStream();
            bw = new BufferedWriter(new OutputStreamWriter(os, charset));
            bw.write(postData);
            bw.flush();
            isr = new InputStreamReader(conn.getInputStream(), charset);
            BufferedReader reader = new BufferedReader(isr);
            String str = null;
            StringBuffer returnStr = new StringBuffer(200);
            while ((str = reader.readLine()) != null) {
                returnStr.append(str);
            }
            String result = returnStr.toString();
            return result;
        } catch (Exception e) {
            logger.error(e);
            throw e;
        } finally {
            if (conn != null) {
                conn.disconnect();
                conn = null;
            }
            url = null;
            if (bw != null) {
                bw.close();
                bw = null;
            }
        }
    }

    /**
     * 创建https连接.
     * 
     * @param urlPath
     * @param password
     * @param pfxPath
     * @return
     * @throws Exception
     */
    @SuppressWarnings("restriction")
    public static HttpsURLConnection createHttpsConnection(final String urlPath, final String password, final String pfxPath) throws Exception {
        HttpsURLConnection conn = null;
        String pswd = password;
        javax.net.ssl.SSLSocketFactory factory = null;
        X509TrustManager[] xtmArray = null;
        try {

            X509TrustManager xtm = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };

            SSLContext ctx = SSLContext.getInstance("SSL");
            // 证书类型可以根据实际情况填写
            KeyManagerFactory kmf = null;
            if (null != password && null != pfxPath) {
                char[] passphrase = pswd.toCharArray();
                kmf = KeyManagerFactory.getInstance("SunX509");
                // 证书类型可以根据实际情况填写
                TrustManagerFactory.getInstance("SunX509");
                // ks_type是证书库的类型
                kmf.init(getKeyStore(pfxPath, pswd), passphrase);
                // tmf.init(ks);
                xtmArray = new X509TrustManager[] { xtm };
                ctx.init(kmf.getKeyManagers(), xtmArray, null);
                factory = ctx.getSocketFactory();
            }
            // tmf.init(ks);
            xtmArray = new X509TrustManager[] { xtm };
            ctx.init(null == kmf ? null : kmf.getKeyManagers(), xtmArray, null);
            factory = ctx.getSocketFactory();

            sun.net.www.protocol.https.Handler handler = new sun.net.www.protocol.https.Handler();
            URL url = new URL(null, urlPath, handler);
            conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; " + "SailBrowser; Maxthon; Alexa Toolbar; .NET CLR 2.0.50727)");
            conn.setSSLSocketFactory(factory);
            conn.setHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
        } catch (Exception e) {
            logger.error(e);
            throw e;
        } finally {
            factory = null;
            xtmArray = null;
        }
        return conn;
    }

    /**
     * Generate a key store.
     * 
     * @param pfxPath
     *            Pfx file path.
     * @param password
     *            The password to the pfx file.
     * @return KeyStore
     * @throws Exception
     */
    public static KeyStore getKeyStore(final String pfxPath, final String password) throws Exception {
        KeyStore ks = null;
        if (null == ks) {
            try {
                String pswd = password;
                String keystore = pfxPath;
                char[] passphrase = pswd.toCharArray();
                ks = KeyStore.getInstance("PKCS12");
                if (!(new File(keystore)).isFile()) {
                    throw new FileNotFoundException("key file is not exist! path is " + keystore);
                }
                ks.load(new FileInputStream(keystore), passphrase);
            } catch (Exception e) {
                logger.error(e);
                throw e;
            }
        }
        return ks;
    }

}
