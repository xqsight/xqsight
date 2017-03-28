package com.xqsight.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author wangganggang
 * @date 2017/03/28
 */
@Data
@XmlRootElement(name = "Result")
public class Result {

    @XmlElement
    private String Success;
    @XmlElement
    private String Code;
    @XmlElement
    private String Message;

}
