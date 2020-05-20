package com.zte.cn.pwd.app;

import java.util.List;

public class InputModel {

    private List<String> P;

    private List<Integer> C;

    private Integer N;

    private Integer MS;

    public List<String> getP() {
        return P;
    }

    public void setP(List<String> p) {
        P = p;
    }

    public List<Integer> getC() {
        return C;
    }

    public void setC(List<Integer> c) {
        C = c;
    }

    public Integer getN() {
        return N;
    }

    public void setN(Integer n) {
        N = n;
    }

    public Integer getMS() {
        return MS;
    }

    public void setMS(Integer MS) {
        this.MS = MS;
    }

    @Override
    public String toString() {
        return "InputModel{" + "P=" + P + ", C=" + C + ", N=" + N + ", MS=" + MS + '}';
    }

}
