package com.xqsight.tess4j;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * @author wangganggang
 * @date 2017年08月22日 8:29
 */
public class Tess4jTest {

    public static final String APP_ID = "10034803";
    public static final String API_KEY = "qz2D1oDnDW4P4ye2V4FGYvOE";
    public static final String SECRET_KEY = "y35V7RRTlZmzvWWGYlCfnlLpce3meCOR ";

    public static void main(String[] args) {
        // 初始化一个OcrClient
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 调用身份证识别接口
       /* String idFilePath = "test.jpg";
        JSONObject idcardRes = client.idcard(idFilePath, true);
        System.out.println(idcardRes.toString(2));
*/
        // 调用银行卡识别接口
        /*String bankFilePath = "test_bank.jpg";
        JSONObject bankRes = client.bankcard(bankFilePath);
        System.out.println(bankRes.toString(2));*/

        // 调用通用识别接口
        String genFilePath = "e:/20170822084715.png";
        JSONObject genRes = client.accurateGeneral(genFilePath, new HashMap<String, String>());
        System.out.println(genRes.toString(2));

        // 调用通用识别（含位置信息）接口
       /* String genFilePath = "test_general.jpg";
        JSONObject genRes2 = client.general(genFilePath, new HashMap<String, String>());
        System.out.println(genRes2.toString(2));*/
    }
}