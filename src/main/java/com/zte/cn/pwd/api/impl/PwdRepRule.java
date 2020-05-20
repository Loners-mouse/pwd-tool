package com.zte.cn.pwd.api.impl;

import com.zte.cn.pwd.api.PwdRule;

public class PwdRepRule implements PwdRule {

    @Override
    public int rule(String pwd) {
        return parseChar(pwd);
    }

    private int parseChar(String pwd) {
        int modifySize = 0;
        int len = pwd.length() - 2;
        for (int i = 0; i < len; i++) {
            char a = pwd.charAt(i);
            char b = pwd.charAt(i + 1);
            char c = pwd.charAt(i + 2);
            if (a == b && b == c) {
                modifySize++;
                i = i + 2;
                if (i < len) {
                    continue;
                } else {
                    break;
                }
            }
        }
        return modifySize;
    }

}