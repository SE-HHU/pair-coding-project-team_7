import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Expression ex =new Expression();               //实例化表达式类
        InfixToSuffix its=new InfixToSuffix();         //实例化表达式计算类
        FileOperation fo=new FileOperation();          //实例化文件操作类

        File file1 = new File("Exercises.txt");
        File file2 = new File("Answers.txt");
        File file3 = new File("MyAnswers.txt");
        File file4 = new File("Grade.txt");

        int n=1,m=10;                                   //参数默认值

        System.out.println("---------------四则运算程序---------------");
        System.out.println("n：生成题目个数");
        System.out.println("r：参数数值范围");
        System.out.println("g：查看测试结果");
        System.out.println("do：执行程序");
        System.out.println("请输入指令：");

        Scanner in =new Scanner(System.in);
        while(in.hasNext()){
            switch(in.next()){
                case "n" :
                    System.out.println("请输入要生成的题目个数：");
                    n=in.nextInt();
                    break;
                case "r":
                    System.out.println("请输入运算数的数值范围：");
                    m=in.nextInt();
                    break;
                case "g":
                    fo.FileC(file2, file3, file4);        //答案和做题文档对比，结果写入Grade文档
                    break;
                case "do":
                    for(int i=0;i<n;i++){
                        String s=ex.CreatExp(n,m),fstr;   //生成随机表达式并求解
                        String rus=its.suffixToArithmetic(its.infixToSuffix(s));

                        fstr=i+1+":"+s+"\r\n";
                        fo.FileW(file1, fstr);            //表达式写入文档

                        fstr=i+1+":"+rus+"\r\n";
                        fo.FileW(file2, fstr);			  //答案写入文档
                    }
                    break;
                default:
                    System.out.println("无效指令！");
                    break;
            }
            System.out.println("请输入指令：");
        }
    }
}