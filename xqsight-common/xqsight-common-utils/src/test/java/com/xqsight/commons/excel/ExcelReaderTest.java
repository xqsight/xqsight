package com.xqsight.commons.excel;

import jxl.read.biff.BiffException;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2016/11/4.
 */
public class ExcelReaderTest {

    private String pMetaData = "id,classOne,classTwo,classThree,classFour,chinese";

    @Test
    public void readTest() throws IOException, BiffException {
        List list = readExcel();

        List cateList = new ArrayList();

        int size = list.size();
        System.out.println("read data size -> " + size);
        String[] metaDatas = StringUtils.split(pMetaData,",");

        String classOneId="",classOneIds="",
                classTwoId="",classTwoIds="",
                classThreeId="",classThreeIds="",
                classFourId="",classFourIds="";

        for(int i = 0 ; i < size;i++){
            Map map = (Map) list.get(i);
            String id = MapUtils.getString(map,metaDatas[0]);
            String classOne = MapUtils.getString(map,metaDatas[1]);
            String classTwo = MapUtils.getString(map,metaDatas[2]);
            String classThree = MapUtils.getString(map,metaDatas[3]);
            String classFour = MapUtils.getString(map,metaDatas[4]);
            String chinese = MapUtils.getString(map,metaDatas[5]);
            Map cateMap = new HashedMap();
            cateMap.put("id",id);
            cateMap.put("cate_chinese_name",chinese);
            cateMap.put("status","1");


            if(StringUtils.isNoneBlank(classOne) && StringUtils.isBlank(classTwo)){ //one class
                classOneId = id;
                classOneIds = id + ",";
                cateMap.put("cate_english_name",classOne);
                cateMap.put("parent_cate_id",0);
                cateMap.put("parent_cate_ids",classOneIds);
                cateMap.put("is_leaf_cate",0);
            }else if(StringUtils.isNoneBlank(classTwo) && StringUtils.isBlank(classThree)){ // two class
                classTwoId = id;
                classTwoIds = classOneIds + id + ",";

                cateMap.put("cate_english_name",classTwo);
                cateMap.put("parent_cate_id",classOneId);
                cateMap.put("parent_cate_ids",classTwoIds);
                cateMap.put("is_leaf_cate",0);
            }else if(StringUtils.isNoneBlank(classThree) && StringUtils.isBlank(classFour)){ // three class
                classThreeId=id;
                classThreeIds= classTwoIds + id + ",";
                cateMap.put("cate_english_name",classThree);
                cateMap.put("parent_cate_id",classTwoId);
                cateMap.put("parent_cate_ids",classThreeIds);
                cateMap.put("is_leaf_cate",0);

            } else if(StringUtils.isNoneBlank(classFour)){ // four class
                classFourId=id;
                classFourIds= classThreeIds + id + ",";
                cateMap.put("cate_english_name",classFour);
                classThreeIds= classTwoIds + id + ",";
                cateMap.put("cate_english_name",classThree);
                cateMap.put("parent_cate_id",classThreeId);
                cateMap.put("parent_cate_ids",classFourIds);
                cateMap.put("is_leaf_cate",1);
            }
            cateList.add(cateMap);
        }

        int cateSize = cateList.size();

        File file = new File("D:\\prod_cates.sql");
        FileWriter fw = null;
        BufferedWriter writer = null;
        try {
            fw = new FileWriter(file);
            writer = new BufferedWriter(fw);
            for(int j =0;j < cateSize;j++){
                Map map = (Map) cateList.get(j);
                StringBuffer insertSql = new StringBuffer("INSERT INTO prod_cate(");
                insertSql.append("id,parent_cate_id,parent_cate_ids,cate_chinese_name,cate_english_name,");
                insertSql.append("is_leaf_cate,`status`,create_time,create_oper_id,create_oper_name,update_time,update_oper_id,update_oper_name)");
                insertSql.append("VALUES(");
                insertSql.append(MapUtils.getString(map,"id"));
                insertSql.append(",");
                insertSql.append( MapUtils.getString(map,"parent_cate_id"));
                insertSql.append(",");
                insertSql.append("'" + MapUtils.getString(map,"parent_cate_ids") + "'");
                insertSql.append(",");
                insertSql.append("'" + MapUtils.getString(map,"cate_chinese_name") + "'");
                insertSql.append(",");
                insertSql.append("'" + MapUtils.getString(map,"cate_english_name") + "'");
                insertSql.append(",");
                insertSql.append("'" + MapUtils.getString(map,"is_leaf_cate") + "'");
                insertSql.append(",");
                insertSql.append("1");  insertSql.append(",");
                insertSql.append("now()");  insertSql.append(",");
                insertSql.append("1");  insertSql.append(",");
                insertSql.append("'admin'");  insertSql.append(",");
                insertSql.append("now()");  insertSql.append(",");
                insertSql.append("1");  insertSql.append(",");
                insertSql.append("'admin'");  insertSql.append(")");
                System.out.println(insertSql.toString());
                writer.write(insertSql.toString());
                writer.newLine();//换行
            }
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                writer.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




    private List readExcel() throws IOException, BiffException {
        InputStream is = new FileInputStream("D:\\prod_cates1.xls");
        ExcelReader excelReader = new ExcelReader(pMetaData,is);
        List list = excelReader.read(1);
        return list;
    }
}
