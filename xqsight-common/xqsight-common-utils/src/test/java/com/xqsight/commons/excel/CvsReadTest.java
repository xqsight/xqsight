package com.xqsight.commons.excel;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2016/11/4.
 */
public class CvsReadTest {

    @Test
    public void  paseDate(){
        List dataList = readCvs();
        for(int i = 0;i <dataList.size();i++){
            Map map = (Map) dataList.get(i);
            String[] datas = getParentName(dataList,MapUtils.getString(map,"pid"));
            map.put("parent_cate_ids",datas[0] + MapUtils.getString(map,"id"));
            map.put("parent_cate_names",datas[1] + MapUtils.getString(map,"cat"));
            System.out.println("read data -> " + map);
        }

        int cateSize = dataList.size();
        File file = new File("D:\\prod_cates.sql");
        FileWriter fw = null;
        BufferedWriter writer = null;
        try {
            fw = new FileWriter(file);
            writer = new BufferedWriter(fw);
            for(int j =0;j < cateSize;j++){
                Map map = (Map) dataList.get(j);
                StringBuffer insertSql = new StringBuffer("INSERT INTO prod_cate(");
                insertSql.append("id,parent_cate_id,parent_cate_ids,parent_cate_names,cate_chinese_name,cate_english_name,");
                insertSql.append("is_leaf_cate,`status`,create_time,create_oper_id,create_oper_name,update_time,update_oper_id,update_oper_name)");
                insertSql.append("VALUES(");
                insertSql.append(MapUtils.getString(map,"id"));
                insertSql.append(",");
                insertSql.append( MapUtils.getString(map,"pid"));
                insertSql.append(",");
                insertSql.append("'" + MapUtils.getString(map,"parent_cate_ids") + "'");
                insertSql.append(",");
                insertSql.append("'" + MapUtils.getString(map,"parent_cate_names") + "'");
                insertSql.append(",");
                insertSql.append("'" + MapUtils.getString(map,"cat") + "'");
                insertSql.append(",");
                insertSql.append("'" + MapUtils.getString(map,"cat_en") + "'");
                insertSql.append(",");
                insertSql.append((StringUtils.split(MapUtils.getString(map,"parent_cate_ids"),",").length > 2 ? "1" : "0"));
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

    public List generateData(List list, String rootId) {
       List lists = new ArrayList<>();
        int dataSize = list.size();
        for(int i=0;i<dataSize;i++){
            Map cateMap = new HashMap();
            Map dataMap = (Map) list.get(i);
            if(StringUtils.equals(rootId,"0")) {
                cateMap.put("id", MapUtils.getString(dataMap, "id"));
                cateMap.put("parent_cate_id", MapUtils.getString(dataMap, "pid"));
                cateMap.put("cate_chinese_name", MapUtils.getString(dataMap, "cat"));
                cateMap.put("cate_english_name", MapUtils.getString(dataMap, "cat_en"));
                cateMap.put("status", "1");
            }else{

            }
        }
        return lists;
    }

    public String[] getParentName(List list, String parentId) {
        String parentCateIds = "", parentCateNames = "";
        for(int i =0 ;i < list.size();i++){
            Map map = (Map) list.get(i);
            String pid = MapUtils.getString(map,"pid");
            String id = MapUtils.getString(map,"id");
            String name = MapUtils.getString(map,"cat");

            if(!StringUtils.equals(id,parentId))
                continue;

            if(StringUtils.equals(pid,"0")){
                parentCateIds += id +",";
                parentCateNames += name + ",";
            }else{
                String[] retDatas = getParentName(list,pid);
                parentCateIds += (retDatas[0] + id +",");
                parentCateNames  += (retDatas[1] + name + ",");
            }
        }
        return new String[]{parentCateIds,parentCateNames};
    }


    public List readCvs(){
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null; //用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
        try {
            String str = "";
            fis = new FileInputStream("D:\\ep_smtcategory.csv");// FileInputStream
            // 从文件系统中的某个文件中获取字节
            isr = new InputStreamReader(fis);// InputStreamReader 是字节流通向字符流的桥梁,
            br = new BufferedReader(isr);// 从字符输入流中读取文件中的内容,封装了一个new InputStreamReader的对象
            boolean isFirst = true;
            List list = new ArrayList();
            String[] mapKey = null ;
            while ((str = br.readLine()) != null) {
                if(isFirst){
                    mapKey = StringUtils.split(str,"#");
                    isFirst = false;
                    continue;
                }
                String[] datas = StringUtils.split(str,"#");
                int dataLength = datas.length;
                if(dataLength>4){
                    System.out.println(datas);
                }
                Map map = new HashMap();
                for(int i =0;i < dataLength;i++){
                    map.put(mapKey[i],datas[i]);
                }
                list.add(map);
            }
            return list;
            // 当读取的一行不为空时,把读到的str的值赋给str1
        } catch (FileNotFoundException e) {
            System.out.println("找不到指定文件");
        } catch (IOException e) {
            System.out.println("读取文件失败");
        } finally {
            try {
                br.close();
                isr.close();
                fis.close();
                // 关闭的时候最好按照先后顺序关闭最后开的先关闭所以先关s,再关n,最后关m
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
