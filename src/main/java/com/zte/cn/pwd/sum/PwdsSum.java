package com.zte.cn.pwd.sum;

import com.zte.cn.pwd.count.PwdObject;
import com.zte.cn.pwd.app.InputModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PwdsSum {

    public String execute(InputModel inputModel) {
        //输入非json判断
        if (inputModel == null) {
            return "No Json input";
        }

        //密码长度和数组长度不大于100
        List<String> pwds = inputModel.getP();
        if (pwds == null) {
            return "P Array is null";
        }
        int pwdSize = pwds.size();
        if (pwdSize > 100) {
            return "P Array size >100";
        }

        for (String p : pwds) {
            int len = p.length();
            if (len > 100) {
                return "P Array exist pwd length >100; pwd =" + p;
            }
        }

        //保证P和C数组长度相同
        List<Integer> pwdCs = inputModel.getC();
        if (pwdCs == null) {
            return "C Array is null";
        }
        int cSize = pwdCs.size();
        if (pwdSize != cSize) {
            return "P size != C size";
        }

        int ms = inputModel.getMS();
        int n = inputModel.getN();

        String result=getResult(ms, n, sortPwds(ms, pwds, pwdCs));

        return result;
    }

    private String getResult(int ms, int n, List<PwdObject> pwdObjects) {
        int size = 0;
        for (int i = 0; i < n; i++) {
            Iterator<PwdObject> its = pwdObjects.iterator();
            while (its.hasNext()) {
                PwdObject pwdObject = its.next();
                int s = pwdObject.getS();
                int Ms = pwdObject.getMs();
                if (ms >= s && Ms > 0) {
                    ms += pwdObject.getMs();
                    size++;
                    its.remove();
                    break;
                }
            }
        }

        return ms + "-" + size;
    }

    private List<PwdObject> sortPwds(int ms, List<String> pwds, List<Integer> pwdCs) {
        //封装Pwd对象实体，对每一个进行计算
        List<PwdObject> pwdObjects = new ArrayList<PwdObject>();
        for (int i = 0; i < pwds.size(); i++) {
            PwdObject pwdObject = new PwdObject(pwds.get(i), pwdCs.get(i), ms);
            pwdObjects.add(pwdObject);
        }
        Collections.sort(pwdObjects);
        return pwdObjects;
    }

}
