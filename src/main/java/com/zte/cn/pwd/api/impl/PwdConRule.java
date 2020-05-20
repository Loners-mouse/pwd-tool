package com.zte.cn.pwd.api.impl;

import com.zte.cn.pwd.api.PwdRule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PwdConRule implements PwdRule {

    @Override
    public int rule(String pwd) {
        int modifySize = 0;
        if (!hasDigit(pwd)) {
            modifySize++;
        }
        if (!hasLow(pwd)) {
            modifySize++;
        }
        if (!hasUp(pwd)) {
            modifySize++;
        }
        return modifySize;
    }

    // 判断一个字符串是否含有数字
    private boolean hasDigit(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(content);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }

    // 判断一个字符串是否含有小写
    private boolean hasLow(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*[a-z]+.*");
        Matcher m = p.matcher(content);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }

    // 判断一个字符串是否含有大写
    private boolean hasUp(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*[A-Z]+.*");
        Matcher m = p.matcher(content);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }

}
