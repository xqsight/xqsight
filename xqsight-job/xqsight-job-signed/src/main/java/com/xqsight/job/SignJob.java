package com.xqsight.job;

import com.xqsight.component.SignComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wangganggang
 * @date 2017/03/28
 */
@Service
public class SignJob {

    protected Logger logger = LogManager.getLogger(SignComponent.class);

    @Autowired
    private SignComponent signComponent;

    private ExecutorService mExecutor;
    Map<String,String> map = new HashMap();

    public SignJob(){
        /**王刚刚 **/
        map.put("122792", "4430Abcd");
        /**小胖 **/
        map.put("110595", "1018Abcd");
        /**帆帆 **/
        map.put("122894", "3028Abcd");
        /** 张俊 **/
        map.put("117585", "0916Abcd");
        /** 卢俊 **/
        map.put("14485", "0411Abcd");
        /** 王子腾 **/
        map.put("107626", "Eju13559O");
        /** 刘书理 **/
        map.put("111780", "2011Abcd");
        /** 施澍 **/
        map.put("115715", "shishu123!");

        mExecutor = Executors.newFixedThreadPool(map.size() / 2 + 1);
    }


    @Scheduled(cron = "0 45 8 * * ?")
    public void morningSign() {
        sign();
    }


    @Scheduled(cron = "0 55 18 * * ?")
    public void nightSign() {
        sign();
    }

    private void sign(){
        Random random = new Random();
        for(Map.Entry<String, String> entry : map.entrySet()) {
            long milliSeconds = random.nextLong();
            milliSeconds = Math.abs(milliSeconds);
            milliSeconds %= 30 *  60 * 1000 / 2;

            final long milli = milliSeconds;
            final String username = entry.getKey();
            final String password = entry.getValue();
            mExecutor.execute(()->{
                logger.debug("休眠{}分钟之后{}开始打卡",milli/1000/60,username);
                try {
                    Thread.sleep(milli);
                } catch (InterruptedException ignored) {}

                logger.info("{}开始打卡",username);
                try {
                    signComponent.signJob(username,password);
                } catch (Exception e) {
                    logger.error(e);
//                    e.printStackTrace();
                }
            });
        }
    }
}
