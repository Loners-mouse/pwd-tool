package com.zte.cn.pwd.api.impl;

import com.zte.cn.pwd.api.PwdRule;

public class PwdLenRule implements PwdRule {

    @Override
    public int rule(String pwd) {
        int len = pwd.length();
        if (len < 6) {
            return 6 - len;
        } else if (len > 20) {
            return len - 20;
        } else {
            return 0;
        }
    }

}
