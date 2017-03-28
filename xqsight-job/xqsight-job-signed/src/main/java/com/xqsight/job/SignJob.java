package com.xqsight.job;

import com.xqsight.component.SignComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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
        /** 洪福 **/
        map.put("123123", "4030Abcd");
        /** 钜鳌 **/
        map.put("122894", "3028Abcd");
        /** 张俊 **/
        map.put("117585", "3028Abcd");
        /** 卢俊 **/
        map.put("14485", "0411Abcd");

        mExecutor = Executors.newFixedThreadPool(map.size() / 2 + 1);
    }


    @Scheduled(cron = "0 45 8 * * ?")
    public void morningSign() {
        sign();
    }


    @Scheduled(cron = "0 47 18 * * ?")
    public void nightSign() {
        sign();
    }

    private void sign(){
        map.forEach((k,v)->{
            mExecutor.execute(()->{
                Random random = new Random(System.currentTimeMillis());
                long milliSeconds = random.nextLong();
                milliSeconds = Math.abs(milliSeconds);
                milliSeconds %= 30 * 60 * 1000 / 2;

                try {
                    Thread.sleep(milliSeconds);
                } catch (InterruptedException ignored) {}

                try {
                    signComponent.signJob(k,v);
                } catch (Exception e) {
                    logger.error(e);
                    e.printStackTrace();
                }
            });
        });
    }
}
