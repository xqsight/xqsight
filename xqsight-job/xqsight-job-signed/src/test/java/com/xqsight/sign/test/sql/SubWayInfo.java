package com.xqsight.sign.test.sql;


import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "sw")
public class SubWayInfo {

    @XmlAttribute(name = "c")
    private String cityName;

    @XmlElement(name = "l")
    protected List<SubLine> subLines;

    @XmlTransient
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @XmlTransient
    public List<SubLine> getSubLines() {
        return subLines;
    }

    public void setSubLines(List<SubLine> subLines) {
        this.subLines = subLines;
    }
}

@XmlType(name = "l")
@XmlRootElement(name = "l")
class SubLine{

    @XmlAttribute(name = "lid")
    private String subwayLine;

    @XmlElement(name = "p")
    private List<SubLineStation> subLineStations;

    @XmlTransient
    public String getSubwayLine() {
        return subwayLine;
    }

    public void setSubwayLine(String subwayLine) {
        this.subwayLine = subwayLine;
    }

    @XmlTransient
    public List<SubLineStation> getSubLineStations() {
        return subLineStations;
    }

    public void setSubLineStations(List<SubLineStation> subLineStations) {
        this.subLineStations = subLineStations;
    }
}

@XmlType(name = "p")
@XmlRootElement(name = "p")
class SubLineStation {

    @XmlAttribute(name = "sid")
    private String subwayStation;

    @XmlAttribute(name = "px")
    private String X;

    @XmlAttribute(name = "py")
    private String Y;

    @XmlTransient
    public String getSubwayStation() {
        return subwayStation;
    }

    public void setSubwayStation(String subwayStation) {
        this.subwayStation = subwayStation;
    }


    @XmlTransient
    public String getX() {
        return X;
    }

    public void setX(String x) {
        X = x;
    }

    @XmlTransient
    public String getY() {
        return Y;
    }

    public void setY(String y) {
        Y = y;
    }
}
