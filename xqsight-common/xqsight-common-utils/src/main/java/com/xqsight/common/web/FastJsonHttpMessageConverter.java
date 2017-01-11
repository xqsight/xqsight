/**
 * Company:新启信息技术有限责任公司
 * Copyright: Copyright (c) 2011 
 */
package com.xqsight.common.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 自定义的FastJsonHttpMessageConverter，支持返回JSONP形式的对象
  * @Description: this is use for 
  * @author xqsight-jerry
  * @date 2016年1月8日 下午7:51:41
 */
public class FastJsonHttpMessageConverter extends AbstractHttpMessageConverter<Object> {
    
    public final static Charset UTF8     = Charset.forName("UTF-8");

    private Charset             charset  = UTF8;

    private SerializerFeature[] features = new SerializerFeature[0];

    public FastJsonHttpMessageConverter(){
        super(new MediaType("application", "json", UTF8), new MediaType("application", "*+json", UTF8),new MediaType("text", "javascript", UTF8), new MediaType("application", "javascript", UTF8));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return true;
    }

    public Charset getCharset() {
        return this.charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public SerializerFeature[] getFeatures() {
        return features;
    }

    public void setFeatures(SerializerFeature... features) {
        this.features = features;
    }

    @Override
    protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage) throws IOException,
                                                                                               HttpMessageNotReadableException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        InputStream in = inputMessage.getBody();

        byte[] buf = new byte[1024];
        for (;;) {
            int len = in.read(buf);
            if (len == -1) {
                break;
            }

            if (len > 0) {
                baos.write(buf, 0, len);
            }
        }

        byte[] bytes = baos.toByteArray();
        return JSON.parseObject(bytes, 0, bytes.length, charset.newDecoder(), clazz);
    }

    @Override
    protected void writeInternal(Object obj, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        if (obj != null && obj instanceof JSONPObject) {
            JSONPObject jsonpObj = (JSONPObject) obj;
            if (StringUtils.hasLength(jsonpObj.getCallBackFunction())) {
                String respStr = jsonpObj.getCallBackFunction() + "(" + JSON.toJSONString(jsonpObj.getJsonObject(),
                    ArrayUtils.isEmpty(jsonpObj.getFeature()) ? getFeatures() : jsonpObj.getFeature()) + ")";
                OutputStream out = outputMessage.getBody();
                byte[] bytes = respStr.getBytes(getCharset());
                out.write(bytes);
            } else {
                writeNormalInternal(jsonpObj.getJsonObject(), outputMessage);
            }
        } else {
            writeNormalInternal(obj, outputMessage);
        }
    }
    
    /**
     * 不是JSONP
     * @param obj
     * @param outputMessage
     * @throws IOException
     * @throws HttpMessageNotWritableException
     */
    private void writeNormalInternal(Object obj, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        OutputStream out = outputMessage.getBody();
        String text = JSON.toJSONString(obj, features);
        byte[] bytes = text.getBytes(charset);
        out.write(bytes);
    }

}
