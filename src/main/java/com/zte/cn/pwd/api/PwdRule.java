package com.zte.cn.pwd.api;

public interface PwdRule {

    /**
     * 返回满足自己的规则需要修改的次数
     *
     * @param pwd
     * @return
     */
    int rule(String pwd);

}
