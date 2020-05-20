package com.zte.cn.pwd.count;

import com.zte.cn.pwd.api.PwdRule;
import com.zte.cn.pwd.api.PwdRuleFactory;

public class PwdObject implements Comparable<PwdObject> {

    private String pwd;
    private int c;
    private int s;
    private int ms;

    public PwdObject(String pwd, int c, int ms) {
        this.pwd = pwd;
        this.c = c;
        this.ms = ms;
        this.setS2Ms();
    }

    public int getMs() {
        return ms;
    }

    public int getS() {
        return s;
    }


    private void setS2Ms() {
        PwdRule pwdRule = PwdRuleFactory.getPwdRule("len");
        int len = pwdRule.rule(pwd);
        pwdRule = PwdRuleFactory.getPwdRule("contain");
        int con = pwdRule.rule(pwd);
        pwdRule = PwdRuleFactory.getPwdRule("repeat");
        int rep = pwdRule.rule(pwd);
        pwdRule = PwdRuleFactory.getPwdRule("replen");
        int rl  = pwdRule.rule(pwd);
        handlerS(len, con, rep, rl);
        if (s == 0) {
            ms = 0;
        } else {
            ms = c - s;
        }
    }

    private void handlerS(int len, int con, int rep, int rl) {
        if (len == 0) {
            s = con >= rep ? con : rep;
        } else {
            if (pwd.length() < 6) {
                int value = len >= con ? len : con;
                s = value >= rep ? value : rep;
            } else if (pwd.length() > 20) {
                if (rl == 1) {
                    s = len + con;
                } else if (rl == 2) {
                    s = rep > con ? rep : con;
                } else if(rl>2){
                    rl-=2;
                    s = len+rl > con ? len+rl : con;
                }
            }
        }
    }

    @Override
    public int compareTo(PwdObject o) {
        if (this.getMs() < o.getMs()) {
            return 1;
        } else if (this.getMs() > o.getMs()) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "PwdObject{" + "pwd='" + pwd + '\'' + ", c=" + c + ", s=" + s + ", ms=" + ms + '}';
    }

}
