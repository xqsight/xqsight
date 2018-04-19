package com.test;

import com.xqsight.common.utils.StringUtils;
import org.apache.poi.util.StringUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wangganggang
 * @date 2017年12月08日 下午9:05
 */
public class HouseNoCombine {

    private static final String RULES_AND = "&";
    private static final String COMMON_AND = ",";

    public Map<String, String> rulesToMap(String combineRules) {
        return Arrays.stream(combineRules.split(COMMON_AND))
                .collect(Collectors.toMap(s -> s.split(RULES_AND)[0], v -> v));
    }


    public String getCombineNo(final Map<String,String> rules,String houseNos,String houseNo){
        StringBuffer stringBuffer = new StringBuffer(houseNo);
        String rule = rules.get(houseNo);
        if(StringUtils.isNoneBlank(rule)){
            String endRule = StringUtils.split(rule,COMMON_AND)[1];
            if(StringUtils.contains(houseNos,endRule)){
                stringBuffer.append(COMMON_AND);
                stringBuffer.append(getCombineNo(rules,houseNos,endRule));
            }
        }
        return stringBuffer.toString();
    }


    public static void comb(String[] chs) {
        int len = chs.length;
        int nbits = 1 << len;
        for (int i = 0; i < nbits; ++i) {
            int t;
            for (int j = 0; j < len; j++) {
                t = 1 << j;
                if ((t & i) != 0) { // 与运算，同为1时才会是1
                    System.out.print(chs[j]);
                }
            }
            System.out.println();
        }
    }

    @Test
    public void outRules() {
        String combineRules = "101&102,102&103";
        String houseNos = "101,102,103                                                                                               ss44                                                ";

        final Map<String,String> rulesMap = rulesToMap(combineRules);
        Arrays.stream(houseNos.split(COMMON_AND)).forEach(n ->{
            String combineHouseNo = getCombineNo(rulesMap,houseNos,n);
            comb(StringUtils.split(combineHouseNo,COMMON_AND));

        });
    }

}
