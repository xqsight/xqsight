package com.xqsight.data.elasticsearch.model;

import lombok.Data;

/**
 * @author wangganggang
 * @date 2017年10月25日 17:57
 */
@Data
public class User {

    private String userId;

    private String userName;

    private boolean userSex;

    private int userAge;

}
