package com.xqsight.data.elasticsearch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xqsight.data.elasticsearch.model.User;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * @author wangganggang
 * @date 2017年10月26日 14:40
 */
public class ElasticsearchIndex {

    public static String jsonIndex() {
        String json = "{" +
                "\"userId\":\"1001\"," +
                "\"userName\":\"test\"," +
                "\"userSex\":true" +
                "\"userAge\":\"28\"" +
                "}";
        return json;
    }

    public static Map mapIndex() {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("userId", "1001");
        json.put("userName", "test");
        json.put("userSex", true);
        json.put("userAge", "28");
        return json;
    }


    public static byte[] objIndex() {
        ObjectMapper mapper = new ObjectMapper(); // create once, reuse
        User user = new User();
        user.setUserId("1001");
        user.setUserName("test");
        user.setUserAge(28);
        user.setUserSex(true);
        byte[] json = new byte[0];
        try {
            json = mapper.writeValueAsBytes(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static String buildIndex() {
        XContentBuilder builder = null;
        try {
            builder = jsonBuilder()
                    .startObject()
                    .field("userId", "1001")
                    .field("userName", "test")
                    .field("userSex", true)
                    .field("userAge", 28)
                    .endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            return builder.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
