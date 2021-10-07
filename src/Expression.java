import java.util.*;

public class Expression {

    /*随机生成表达式*/
    public String CreatExp(int n,int m){
        StringBuilder exp= new StringBuilder(CreatNum(m));                          //随机操作数
        Random rd=new Random();
        int t=rd.nextInt(2);
        boolean flag=false;                              //是否生成括号
        if(t>0)
            flag=Creatkh();
        for(int i=0;i<=t;i++){                           //生成String类型中缀表达式
            if(flag){
                if(i==0){
                    exp.append(CreatChar()).append("(").append(CreatNum(m));
                }else
                {
                    exp.append(CreatChar()).append(CreatNum(m)).append(")");
                }
            }else{
                exp.append(CreatChar()).append(CreatNum(m));
            }
        }
        return exp.toString();
    }

    /*随机生成操作数*/
    public String CreatNum(int m){
        String s="";
        Random rd=new Random();
        switch (rd.nextInt(2)) {                            //随机类型：整数，分数
            case 0 -> s = Integer.toString(rd.nextInt(m - 1) + 1);    //整数
            case 1 -> {                                       //分数
                int a, b;
                a = rd.nextInt(m - 1) + 1;
                b = rd.nextInt(m - 2) + 2;
                s = Dating(a, b);                            //分数约分处理
            }
        }
        return s;
    }

    /*随机生成运算符*/
    public String CreatChar(){
        String s="";
        Random rd=new Random();
        switch (rd.nextInt(4)) {
            case 0 -> s = "+";
            case 1 -> s = "-";
            case 2 -> s = "*";
            case 3 -> s = "÷";
        }
        return s;
    }

    /*分数进行约分*/
    public String Dating(int a,int b){
        String s;
        int gongyinshu=1,c;
        c=a/b;
        a=a%b;
        if(c<0){                                   //若带分数已为负数，这分数不用带负号
            a=a*-1;
        }
        for (int i = 1; i <= a; i++) {             //求最小公约数
            if (a % i == 0 && b % i == 0) {
                gongyinshu = i;
            }
        }
        a=a/gongyinshu;                            //生成最简分数
        b=b/gongyinshu;
        if(a==0){
            s=Integer.toString(c);
        }else if(c==0){
            s= a +"/"+ b;
        }else{
            s= "%d'%s/%d".formatted(c, Integer.toString(a), b);
        }
        return s;
    }

    /*随机是否生成括号*/
    public boolean Creatkh(){
        boolean flag=false;
        Random rd=new Random();
        if(rd.nextInt(3)<1)
            flag=true;
        return flag;
    }
}