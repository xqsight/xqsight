/**
 * Copyright: Copyright (c) 2011 
 * Company:新启信息技术有限责任公司
 */
package com.xqsight.common.constants;

import java.math.BigDecimal;

/**
 * @Description: this is use for 
 * @author xqsight-jerry
 * @date 2016年1月8日 下午7:39:21
 */
public class Constants {
public final static BigDecimal THOUSAND_BIGDECIMAL = new BigDecimal("1000");


    public static final String ACS = "ACS";
    public static final String DESC = "DESC";

    public static final String MYSQL_LONG_TIME_FORMAT    =  "%Y-%m-%d %H:%i:%s";

    /**
     * , 符号
     */
    public static final String COMMA_SIGN_SPLIT_NAME = ",";


	public final static int YES 			= 0;
	public final static int NO 				= -1;
	
	public final static int ENABLE 			= 0;
    public final static int DISABLE 		= -1;
    
    public final static int ACTIVE 			= 0;
    public final static int INACTIVE 		= -1;
    
    public final static int SUCCESS 		= 0;
    public final static int FAILURE 		= -1;
    
	public static final String KEY_STATUS 	= "status";

	public static final String KEY_MESSAGE	= "msg";

}
