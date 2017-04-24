/**
 * Copyright: Copyright (c) 2011 
 * Company:新启信息技术有限责任公司
 */
package com.xqsight.common.model.constants;

import java.math.BigDecimal;

/**
 * @author xqsight-jerry
 * @date 2016年1月8日 下午7:39:21
 */
public class Constants {
    public static final String TOTAL_SIZE = "totalSize";
    public static final String PAGE_SIZE = "pageSize";
    public static final String PAGE_NUM = "pageNum";
    public static final String PAGE_RESULT = "data";

    public static final String CURRENT_USER = "user";

    public final static BigDecimal THOUSAND_BIGDECIMAL = new BigDecimal("1000");

    /**
     * , 符号
     */
    public static final String COMMA_SIGN_SPLIT_NAME = ",";

    /**
     * 身份识别COOKIE名称
     */
    public static final String IDENTITY_COOKIE_NAME = "_xqsight_cookie";

    /**
     * 使用http访问网络资源时使用的USER_ANGENT
     */
    public static final String USER_ANGENT = "Mozilla/5.0";

    /**
     * 模版存储路径。
     *
     * 模版路径在应用内，以/开头。如：/template。
     *
     * 模版路径在应用外，以file:开头。可以实现程序与模版分开部署（配合上传文件发布点和全文索引位置fsDirectory.location）。 如：file:d:\\jspxcms\\template 或
     * file:/home/mysite/template。 因模版内含有图片，该路径应能通过同一域名访问，通常单独作为一个应用部署。
     */
    public static String TEMPLATE_STORE_PATH = "/template";


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
