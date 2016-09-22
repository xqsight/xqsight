package com.saicfc.themis.data.neo4j;

import com.alibaba.fastjson.JSONObject;
import com.saicfc.common.lite.utils.http.HttpConfig;
import com.saicfc.common.lite.utils.http.HttpURLConnectionsUtils;
import com.saicfc.themis.api.constants.CertType;
import com.saicfc.themis.api.models.PersonalInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GTaurus on 2016/4/5.
 */
public class TestRun {

    public final static String URL = "http://10.116.1.98:8080/person/save";

    public final static Map<String, String> HEADER = new HashMap<String, String>() {{
        put("Accept", "application/json");
        put("Content-Type", "application/json");
    }};

    public static void main(String... args) throws Exception {
        PersonalInfo info = new PersonalInfo();
        info.setName("person1");
        info.setCertNo("person1CertNo");
        info.setCertType(CertType.NATIONAL_ID.type());
        info.setBankCardNo("323223");

        PersonalInfo father = new PersonalInfo();
        father.setBankCardNo("342");
        father.setName("father");
        father.setCertNo("father4");
        father.setCertType(CertType.NATIONAL_ID.type());

        PersonalInfo mother = new PersonalInfo();
        mother.setBankCardNo("3443432");
        mother.setName("mother");
        mother.setCertNo("mother3323");
        mother.setCertType(CertType.NATIONAL_ID.type());

        PersonalInfo son = new PersonalInfo();
        son.setBankCardNo("32424242");
        son.setName("son");
        son.setCertNo("son2332");
        son.setCertType(CertType.NATIONAL_ID.type());

        PersonalInfo fatherinlaw = new PersonalInfo();
        fatherinlaw.setBankCardNo("342");
        fatherinlaw.setName("father");
        fatherinlaw.setCertNo("father4");
        fatherinlaw.setCertType(CertType.NATIONAL_ID.type());

        PersonalInfo spouse = new PersonalInfo();
        spouse.setBankCardNo("23533");
        spouse.setName("spouse");
        spouse.setCertNo("spouse323");
        spouse.setCertType(CertType.NATIONAL_ID.type());
        spouse.setFather(fatherinlaw);


        PersonalInfo relative = new PersonalInfo();
        relative.setBankCardNo("34432211");
        relative.setName("relative");
        relative.setCertNo("relative323");
        relative.setCertType(CertType.NATIONAL_ID.type());

        PersonalInfo knows = new PersonalInfo();
        knows.setBankCardNo("87534534");
        knows.setName("knows");
        knows.setCertNo("knows7676");
        knows.setCertType(CertType.NATIONAL_ID.type());

        info.setFather(father);
        info.setMother(mother);
        info.setChild(son);
        info.setSpouse(spouse);
        info.setOtherRelative(relative);
        info.setOther(knows);

        info.setRegAddress("It is Reg Address");
        info.setHomeAddress("It is Home Address");
        info.setCompanyAddress("It is Work Address");
        info.setContactAddress("It is Conract Address");

        info.setQq("123456789");
        info.setWechat("GGGGGGG");
        info.setEmail("s22@fsfsf.com");

        String content = JSONObject.toJSONString(info);
        System.out.println(JSONObject.toJSONString(info, true));
        String resp = HttpURLConnectionsUtils.sendStringForString(URL, content, HEADER, HttpConfig.getDefaultPostConf());
        System.out.println(resp);
    }
}
