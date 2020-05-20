package com.zte.cn.pwd.api;

import com.zte.cn.pwd.api.impl.PwdConRule;
import com.zte.cn.pwd.api.impl.PwdLenRule;
import com.zte.cn.pwd.api.impl.PwdRLSRule;
import com.zte.cn.pwd.api.impl.PwdRepRule;

public class PwdRuleFactory {

    public static PwdRule getPwdRule(String rule) {

        if ("contain".equals(rule)) {
            return new PwdConRule();
        } else if ("len".equals(rule)) {
            return new PwdLenRule();
        } else if ("repeat".equals(rule)) {
            return new PwdRepRule();
        } else if("replen".equals(rule)){
            return new PwdRLSRule();
        }else{
            return null;
        }
    }

}
