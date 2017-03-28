package com.xqsight.sign.test;

import com.xqsight.model.Result;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

/**
 * @author wangganggang
 * @date 2017/03/28
 */
public class XmlTest {

    public static void main(String[] args) throws JAXBException {
        String xml="<Result> <Success>1</Success><Code>0000</Code><Message>打卡成功！</Message></Result>";

        JAXBContext jaxbContext = JAXBContext.newInstance(Result.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Result result = (Result) jaxbUnmarshaller.unmarshal(new StringReader(xml));

        System.out.println(result.getCode());
    }
}
