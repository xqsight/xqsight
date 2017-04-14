package com.xqsight.sign.test;

import com.xqsight.Application;
import com.xqsight.component.SignComponent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangganggang
 * @date 2017/03/28
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
public class SignTest {

    @Autowired
    private SignComponent signComponent;

    @Test
    public void signTest(){
        try {
            Map<String,String> map = new HashMap();
            /**王刚刚 **/
            map.put("122792", "4430Abcd");
            /**小胖 **/
            map.put("110595", "1018Abcd");
            /**帆帆 **/
            map.put("122894", "3028Abcd");
            /** 钜鳌 **/
            map.put("123123", "0110Abcd");
            /** 张俊 **/
            map.put("117585", "0916Abcd");
            /** 卢俊 **/
            map.put("14485", "0411Abcd");
            /** 虞倩倩 **/
            map.put("117572", "0222Abcd");
            /** 洪福 **/
            map.put("112690", "4030Abcd");
            /** 王子腾 **/
            map.put("107626", "Eju13559O");
            /** 刘书理 **/
            map.put("111780", "2011Abcd");
            /** 施澍 **/
            map.put("115715", "shishu123!");
            map.forEach((k,v)-> {
                try {
                    signComponent.signJob(k, v);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
