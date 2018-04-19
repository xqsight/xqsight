package com.xqsight.sign.test;

public class SetTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        String str[] = { "A", "B", "C"};
        int nCnt = str.length;
        //int nBit = (0xFFFFFFFF >>> (32 - nCnt));
        int nBit = 1<<nCnt;
        for (int i = 1; i <= nBit; i++) {
            for (int j = 0; j < nCnt; j++) {
                if ((i << (31 - j)) >> 31 == -1) {
                    System.out.print(str[j]);
                }
            }
            System.out.println("");
        }
    }
}  