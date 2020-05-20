package com.zte.cn.pwd.api.impl;

import com.zte.cn.pwd.api.PwdRule;
import com.zte.cn.pwd.api.PwdRuleFactory;

import java.util.ArrayList;
import java.util.List;

public class PwdRLSRule implements PwdRule {

    @Override
    public int rule(String pwd) {
        int size= getSize(pwd);
        return size;
    }

    private int getSize(String pwd) {
        int size=0;
        int len = pwd.length();
        if (len > 20) {
            int delSize = len - 20;
            int repeSize = PwdRuleFactory.getPwdRule("repeat").rule(pwd);
            List<Integer> nums = parseNum(pwd);
            for (Integer num : nums) {
                if (num < 3) {
                    continue;
                }
                int i = num % 3;
                if (i == 0) {
                    if (delSize > 0 && repeSize > 0) {
                        delSize--;
                        num = num - 1;
                        repeSize--;
                    } else {
                        break;
                    }
                }
            }
            for (Integer num : nums) {
                if (num < 3) {
                    continue;
                }
                int i = num % 3;
                if (i == 1) {
                    if (delSize > 1 && repeSize > 0) {
                        delSize--;
                        delSize--;
                        num = num - 2;
                        repeSize--;
                    } else {
                        break;
                    }
                }
            }
            if (delSize > 0 && repeSize > 0) {
                int reMain = delSize / 3;
                int reY=delSize % 3;
                if (reMain >= repeSize) {
                    size=1;
                } else if(reY>0){
                    size=2+repeSize-reMain;
                } else{
                    size=2;
                }
            } else if (delSize == 0 && repeSize == 0) {
                size=1;
            } else if (delSize > 0 && repeSize == 0) {
                size=1;
            } else if (delSize == 0 && repeSize > 0) {
                size=2;
            }
        }
        return size;
    }

    public List<Integer> parseNum(String pwd) {
        List<Integer> num = new ArrayList<Integer>();
        int len = pwd.length() - 1;
        for (int i = 0; i < len; i++) {
            int length = 0;
            for (int j = i; j < len; j++) {
                char a = pwd.charAt(j);
                char b = pwd.charAt(j + 1);
                if (a == b) {
                    length++;
                    continue;
                }
                break;
            }
            if (length > 1) {
                num.add(length + 1);
            }
            i = i + length;
        }
        return num;
    }

}
