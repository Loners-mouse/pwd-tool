package com.zte.cn.pwd.app;

import com.zte.cn.pwd.sum.PwdsSum;
import com.zte.cn.pwd.util.JsonUtils;

import java.util.Scanner;

public
class App {

    public static void main(String[] args) {

        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入原始数据Json:");
            String inputInfo = sc.nextLine();

            System.out.println(outPut(inputInfo));

            System.out.println("如果退出请输入0,继续请输入1:");
            int finish = sc.nextInt();
            if (finish == 1) {
                continue;
            } else if (finish == 0) {
                break;
            }
        }
    }

    public static String outPut(String inputInfo){
        InputModel inputModel = JsonUtils.toObject(inputInfo, InputModel.class);
        PwdsSum pwdsSum = new PwdsSum();
        return pwdsSum.execute(inputModel);
    }
}
