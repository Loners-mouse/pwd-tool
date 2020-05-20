package com.zte.cn.test;

import com.zte.cn.pwd.api.PwdRuleFactory;
import com.zte.cn.pwd.app.App;
import com.zte.cn.pwd.count.PwdObject;
import org.junit.Assert;
import org.junit.Test;

public class TestCase {


    /*---------------------------密码修改次数计算处理---------------------------------*/
    /*
     *PWD获取需要修改次数算法测试
     *
     */
    @Test//密码没有数字测试
    public void noDigit() {
        String pwd ="asDasd";
        int size=PwdRuleFactory.getPwdRule("contain").rule(pwd);
        Assert.assertSame(size,1);
    }

    @Test//密码没有大写字母测试
    public void noUp() {
        String pwd ="as1asd";
        int size=PwdRuleFactory.getPwdRule("contain").rule(pwd);
        Assert.assertSame(size,1);
    }


    @Test//密码没有小写字母测试
    public void noLow() {
        String pwd ="AS1ASD";
        int size=PwdRuleFactory.getPwdRule("contain").rule(pwd);
        Assert.assertSame(size,1);
    }

    @Test//密码全数字
    public void allDigit() {
        String pwd ="111111";
        int size=PwdRuleFactory.getPwdRule("contain").rule(pwd);
        Assert.assertSame(size,2);
    }
    @Test//密码全小写字母
    public void allLow() {
        String pwd ="abcdef";
        int size=PwdRuleFactory.getPwdRule("contain").rule(pwd);
        Assert.assertSame(size,2);
    }

    @Test//密码全大写字母
    public void allUp() {
        String pwd ="ABCEDS";
        int size=PwdRuleFactory.getPwdRule("contain").rule(pwd);
        Assert.assertSame(size,2);
    }

    @Test//密码全特殊字符
    public void allSpecial() {
        String pwd ="!@#$%^";
        int size=PwdRuleFactory.getPwdRule("contain").rule(pwd);
        Assert.assertSame(size,3);
    }

    @Test//密码3个重复次数,由连续3个计数一次
    public void repeat3() {
        String pwd ="aaaaaaaaaa";
        int size=PwdRuleFactory.getPwdRule("repeat").rule(pwd);
        Assert.assertSame(size,3);
        pwd ="aaasaasaaa";
        size=PwdRuleFactory.getPwdRule("repeat").rule(pwd);
        Assert.assertSame(size,2);
    }

    @Test//密码长度，需要增加删除多少个
    public void len() {
        String pwd ="aas";
        int size=PwdRuleFactory.getPwdRule("len").rule(pwd);
        Assert.assertSame(size,3);//需要增加3个

        pwd ="aaasaasaaa";
        size=PwdRuleFactory.getPwdRule("len").rule(pwd);
        Assert.assertSame(size,0);//在6-20范围内不需要增删

        pwd ="aaasaasaaaasasasasasasa";
        size=PwdRuleFactory.getPwdRule("len").rule(pwd);
        Assert.assertSame(size,3);//需要删除3个
    }

    @Test//密码长度大于20和3个字母重复次数组合处理
    public void lenRep() {
        String pwd ="aasaaaaaaaaaaaaaaaaa";//长度=20
        int size=PwdRuleFactory.getPwdRule("replen").rule(pwd);
        Assert.assertSame(size,0);//密码长度不大于20

        pwd ="aaasaasaaaaaaaasasaaaddddaaa";
        size=PwdRuleFactory.getPwdRule("replen").rule(pwd);
        Assert.assertSame(size,2);//密码长度大于20，重复的次数大于多余的数量

        pwd ="aaasaasaaaaaaaasasaaaddddaaasadsasdasd";
        size=PwdRuleFactory.getPwdRule("replen").rule(pwd);
        Assert.assertSame(size,1);//密码长度大于20，重复的次数小于多余的数量
    }

    /*---------------------------单个密码封装得分和成本处理---------------------------------*/
    /*
     * 测试各个算法组合后单个密码，得分和成本等
     *
     */
    @Test//密码长度小于6,空密码
    public void pwdNull() {
        String pwd="";
        int c=5;
        int ms=1;
        PwdObject pwdObject= new PwdObject(pwd,c,ms);
        int s=pwdObject.getS();
        Assert.assertSame(s,6);
        ms=pwdObject.getMs();
        Assert.assertSame(ms,-1);
    }

    @Test//密码长度小于6，1个字符
    public void pwd1() {
        String pwd="1";
        int c=5;
        int ms=1;
        PwdObject pwdObject= new PwdObject(pwd,c,ms);
        int s=pwdObject.getS();
        Assert.assertSame(s,5);
        ms=pwdObject.getMs();
        Assert.assertSame(ms,0);
    }

    @Test//密码长度小于6，5个字符
    public void pwd5() {
        String pwd="12aSD";
        int c=5;
        int ms=1;
        PwdObject pwdObject= new PwdObject(pwd,c,ms);
        int s=pwdObject.getS();
        Assert.assertSame(s,1);
        ms=pwdObject.getMs();
        Assert.assertSame(ms,4);
    }

    @Test//密码长度等于6
    public void pwd6() {
        String pwd="12aSDa";//自身是强密码
        int c=5;
        int ms=1;
        PwdObject pwdObject= new PwdObject(pwd,c,ms);
        int s=pwdObject.getS();
        Assert.assertSame(s,0);
        ms=pwdObject.getMs();
        Assert.assertSame(ms,0);

        pwd="111111";//单一字符
        c=5;
        ms=1;
        pwdObject= new PwdObject(pwd,c,ms);
        s=pwdObject.getS();
        Assert.assertSame(s,2);
        ms=pwdObject.getMs();
        Assert.assertSame(ms,3);
    }

    @Test//密码长度大于6小于20
    public void pwd20() {
        String pwd="aaSaa1aad";//自身是强密码
        int c=5;
        int ms=1;
        PwdObject pwdObject= new PwdObject(pwd,c,ms);
        int s=pwdObject.getS();
        Assert.assertSame(s,0);
        ms=pwdObject.getMs();
        Assert.assertSame(ms,0);

        pwd="aaaaa1aad";//自身是强密码
        c=5;
        ms=1;
        pwdObject= new PwdObject(pwd,c,ms);
        s=pwdObject.getS();
        Assert.assertSame(s,1);
        ms=pwdObject.getMs();
        Assert.assertSame(ms,4);

        pwd="aaaaaaaaaaaaaaaaaaaa";//单一20字符
        c=5;
        ms=1;
        pwdObject= new PwdObject(pwd,c,ms);
        s=pwdObject.getS();
        Assert.assertSame(s,6);
        ms=pwdObject.getMs();
        Assert.assertSame(ms,-1);
    }

    @Test//密码长度大于20，3字符联系重复的比多出的字符多
    public void pwd2MR() {
        String pwd="aaaaaaaaaaaa11111sssss";//
        int c=5;
        int ms=1;
        PwdObject pwdObject= new PwdObject(pwd,c,ms);
        int s=pwdObject.getS();
        Assert.assertSame(s,7);
        ms=pwdObject.getMs();
        Assert.assertSame(ms,-2);
    }

    @Test//密码长度大于20，3字符联系重复的比多出的字符少
    public void pwd2RM() {
        String pwd="aaaaaaaaaaaa11111sssssasasasa";//
        int c=12;
        int ms=1;
        PwdObject pwdObject= new PwdObject(pwd,c,ms);
        int s=pwdObject.getS();
        Assert.assertSame(s,12);
        ms=pwdObject.getMs();
        Assert.assertSame(ms,0);
    }

    /*---------------------------积分处理---------------------------------*/
    /*
     * 输入输出积分和修改次数测试
     *
     */
    @Test//密码正常信息
    public void appT1() {
        String input="{\"P\":[\"123456\",\"abcdEf\"],\"C\":[3,2],\"N\":2,\"MS\":1}";
        String ms=App.outPut(input);
        Assert.assertEquals(ms,"3-2");
    }

    @Test//当单个密码修改成本大于当前MS则无法修改
    public void appT2() {
        String input="{\"P\":[\"123456\",\"abcdef\"],\"C\":[3,2],\"N\":2,\"MS\":0}";
        String ms=App.outPut(input);
        Assert.assertEquals(ms,"0-0");
    }

    @Test//当MS相同时，使修改次数最少
    public void appT3() {
        String input="{\"P\":[\"123456\",\"abcdEf\",\"abcdEf\"],\"C\":[4,1,1],\"N\":2,\"MS\":2}";
        String ms=App.outPut(input);
        Assert.assertEquals(ms,"4-1");
    }

    @Test//重复密码情况
    public void appT4() {
        String input="{\"P\":[\"111111\",\"abcdEf\",\"abcdEf\"],\"C\":[4,2,2],\"N\":2,\"MS\":2}";
        String ms=App.outPut(input);
        Assert.assertEquals(ms,"5-2");
    }

    @Test//当最终盈利小于0时，则不去修改
    public void appT5() {
        String input="{\"P\":[\"111111\",\"abcdEf\",\"abcdEf\"],\"C\":[1,1,1],\"N\":2,\"MS\":2}";
        String ms=App.outPut(input);
        Assert.assertEquals(ms,"2-0");
    }
}
