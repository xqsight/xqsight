package com.xqsight.sign.test;

import com.xqsight.Application;
import com.xqsight.component.SignComponent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

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
            signComponent.signJob("122792","4430Abcd");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
