package com.xqsight.sign.test;

import com.xqsight.common.utils.StringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wangganggang
 * @date 2017年12月08日 下午9:05
 */
public class HouseNoCombine {

    private static final String RULES_AND = "&";
    private static final String OUT_AND = "&&";
    private static final String COMMON_AND = ",";

    public Map<String, String> rulesToMap(String combineRules) {
        return Arrays.stream(combineRules.split(COMMON_AND))
                .collect(Collectors.toMap(s -> s.split(RULES_AND)[0], v -> v));
    }


    public String getCombineNo(final Map<String,String> rules,String houseNos,String houseNo){
        StringBuffer stringBuffer = new StringBuffer(houseNo);
        String rule = rules.get(houseNo);
        if(StringUtils.isNoneBlank(rule)){
            String endRule = StringUtils.split(rule,RULES_AND)[1];
            if(StringUtils.contains(houseNos,endRule)){
                stringBuffer.append(COMMON_AND);
                stringBuffer.append(getCombineNo(rules,houseNos,endRule));
            }
        }
        return stringBuffer.toString();
    }


    public static void comb(String[] str) {
        StringBuffer sb = new StringBuffer();
        int nCnt = str.length;
        //int nBit = (0xFFFFFFFF >>> (32 - nCnt));
        int nBit = 1<<nCnt;
        for (int i = 1; i <= nBit; i++) {
            for (int j = 0; j < nCnt; j++) {
                if ((i << (31 - j)) >> 31 == -1) {
                    String out = str[j] + OUT_AND;
                    System.out.print(StringUtils.substringBeforeLast(out,OUT_AND));
                }
            }

            System.out.println("");
        }
    }

    @Test
    public void outRules() {
        String combineRules = "101&102,102&103";
        String houseNos = "101,103                                                                                               ss44                                                ";

        final Map<String,String> rulesMap = rulesToMap(combineRules);

        String combineHouseNo = getCombineNo(rulesMap,houseNos,"101");
        comb(StringUtils.split(combineHouseNo,COMMON_AND));
    }

}
