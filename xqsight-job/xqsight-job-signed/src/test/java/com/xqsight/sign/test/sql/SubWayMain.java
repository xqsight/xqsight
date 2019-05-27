package com.xqsight.sign.test.sql;

import com.xqsight.common.utils.HttpConnectionUtils;
import com.xqsight.common.utils.StringUtils;
import org.junit.Test;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import java.io.StringReader;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class SubWayMain {

    @Test
    public void generateSql(){
        String url = "http://map.baidu.com/fwmap/upload/subway_data/sw_xian.xml?date=" + new Date().getTime();
        try {
            String data = HttpConnectionUtils.openHttpsConnection(url,"utf-8",500000,500000);
            JAXBContext jc = JAXBContext.newInstance(SubWayInfo.class);
            Unmarshaller unmar = jc.createUnmarshaller();
            SubWayInfo subWayInfo = (SubWayInfo) unmar.unmarshal(new StringReader(data));
            createSql(subWayInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createSql(SubWayInfo subWayInfo){
        String judgeSql = "cityName = '%s' AND subwayLine = '%s' AND subwayStation = '%s'";
        String sql = "('%s','%s','%s',%s,%s,null)\n";
        String blank = "    ";
        if(Objects.nonNull(subWayInfo)){
            List<SubLine> subLineList  =subWayInfo.getSubLines();
            if(null != subLineList && subLineList.size() > 0){
                for (SubLine subLine : subLineList){
                    List<SubLineStation> subLineStations = subLine.getSubLineStations();
                    if(subLineStations != null && subLineStations.size() > 0){
                       for(SubLineStation subLineStation : subLineStations){
                           if(StringUtils.isBlank(subLineStation.getSubwayStation())){
                               continue;
                           }
                           StringBuffer sbSql = new StringBuffer("IF NOT EXISTS (SELECT TOP 1 1 FROM sys_subway WHERE ");
                           sbSql.append(String.format(judgeSql,subWayInfo.getCityName(),subLine.getSubwayLine(),subLineStation.getSubwayStation()));
                           sbSql.append(") \n");
                           sbSql.append(blank).append("BEGIN \n");
                           sbSql.append(blank).append("INSERT INTO sys_subway");
                           sbSql.append("(cityName, subwayLine, subwayStation, X, Y, DirectionNo)\n");
                           sbSql.append(blank).append("VALUES");
                           sbSql.append(String.format(sql,subWayInfo.getCityName(),subLine.getSubwayLine(),subLineStation.getSubwayStation(),subLineStation.getX(),subLineStation.getY()));
                           sbSql.append(blank).append("END\n").append("GO\n");
                           System.out.println(sbSql.toString());
                       }
                    }
                }
            }
        }
    }
}
