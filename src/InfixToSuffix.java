import java.util.*;

public class InfixToSuffix {


    /*中缀表达式转后缀表达式*/
    public String infixToSuffix(String exp) {
        Stack<Character> s = new Stack<Character>();                // 创建操作符堆栈
        String suffix = "";                                         // 要输出的后缀表达式字符串
        int length = exp.length();                                  // 输入的中缀表达式的长度
        for (int i = 0; i < length; i++) {
            char temp;
            char ch = exp.charAt(i);                               // 获取该中缀表达式的每一个字符并进行判断
            switch (ch) {
                case '(':
                    s.push(ch);
                    break;
                case '+':                              // 碰到'+' '-'，将栈中的所有运算符全部弹出去，直至碰到左括号为止，输出到队列中去
                case '-':
                    suffix += " ";
                    while (s.size() != 0) {
                        temp = s.pop();
                        if (temp == '(') {
                            s.push('(');
                            break;
                        }
                        suffix += temp;
                        suffix += " ";
                    }
                    s.push(ch);
                    break;
                case '*':                               // 如果是乘号或者除号，则弹出所有序列，直到碰到加好、减号、左括号为止，最后将该操作符压入堆栈
                case '÷':
                    suffix += " ";
                    while (s.size() != 0) {
                        temp = s.pop();
                        if (temp == '+' || temp == '-' || temp == '(') {
                            s.push(temp);
                            break;
                        } else {
                            suffix += temp;
                            suffix += " ";
                        }
                    }
                    s.push(ch);
                    break;
                case ')':
                    while (!s.isEmpty()) {
                        temp = s.pop();
                        if (temp == '(') {
                            break;
                        } else {
                            suffix += " ";
                            suffix += temp;
                        }
                    }
                    break;
                default:
                    suffix += ch;
                    break;
            }
        }
        while (s.size() != 0) {                            // 如果堆栈不为空，则把剩余运算符一次弹出，送至输出序列
            suffix += " ";
            suffix += s.pop();
        }
        return suffix;
    }

    /*计算后缀表达式*/
    public String suffixToArithmetic(String exp) {
        String[] strings = exp.split(" ");                  //按空格分解字符串
        Stack<String> stack = new Stack<String>();          //操作数栈
        for (int i = 0; i < strings.length; i++) {
            if(strings[i].equals("+")||strings[i].equals("-")||strings[i].equals("*")||strings[i].equals("÷")){
                String y=stack.pop();                        //读取到运算符，提取栈顶的两个操作数，先出的操作数为运算符后的数
                String x=stack.pop();
                String rus=calculate(x, y, strings[i]);      //调用自定义的四则运算法则
                stack.push(rus);
                if(rus.equals("无解"))                       //除数为0返回无解
                    return rus;
            }else{
                stack.push(strings[i]);
            }
        }
        return stack.pop();
    }

    /*自定义四则运算法则*/
    public String calculate(String x, String y, String ch) {
        String rus="";

        boolean flag1=false,flag2=false;              //判断两个参数是否为分数，标记
        if(x.indexOf("/")!=-1)
            flag1=true;
        if(y.indexOf("/")!=-1)
            flag2=true;

        int a=1,b=1,c=1,d=1,f1=0,f2=0;                //对两个参数的数字进行转换，String转int,x=f1'a/c,y=f2'b/d

        if(flag1==false&&flag2==false){               //类型1，都为整数
            a=Integer.parseInt(x);
            b=Integer.parseInt(y);
        }

        if(flag1==false&&flag2==true){                //类型2，x为整数，y为分数
            a=Integer.parseInt(x);

            int lenf2=y.indexOf("'");                          //判断是否带分数，是则提取所带整数
            if(lenf2!=-1){
                f2=Integer.parseInt(y.substring(0,lenf2));
            }

            int len=y.indexOf("/");                            //提取分号前后的整数
            b=Integer.parseInt(y.substring(lenf2+1, len));
            d=Integer.parseInt(y.substring(len+1, y.length()));
            if(f2<0){                                          //判断带分数是否负数，处理方式不同
                b=f2*d-b;
            }else{
                b=f2*d+b;
            }
        }

        if(flag1==true&&flag2==false){                        //类型3，x为分数，y为整数
            int lenf1=x.indexOf("'");                         //判断是否带分数，是则提取所带整数
            if(lenf1!=-1){
                f1=Integer.parseInt(x.substring(0,lenf1));
            }

            int len=x.indexOf("/");                           //提取分号前后的整数
            a=Integer.parseInt(x.substring(lenf1+1, len));
            c=Integer.parseInt(x.substring(len+1, x.length()));
            if(f1<0){                                         //判断带分数是否负数，处理方式不同
                a=f1*c-a;
            }else{
                a=f1*c+a;
            }

            b=Integer.parseInt(y);
        }

        if(flag1==true&&flag2==true){                          //类型4，x为分数，y为分数
            int lenf1=x.indexOf("'");                          //判断是否带分数，是则提取所带整数
            if(lenf1!=-1){
                f1=Integer.parseInt(x.substring(0,lenf1));
            }
            int len1=x.indexOf("/");                           //提取分号前后的整数
            a=Integer.parseInt(x.substring(lenf1+1, len1));
            c=Integer.parseInt(x.substring(len1+1, x.length()));
            if(f1<0){                                          //判断带分数是否负数，处理方式不同
                a=f1*c-a;
            }else{
                a=f1*c+a;
            }

            int lenf2=y.indexOf("'");                          //判断是否带分数，是则提取所带整数
            if(lenf2!=-1){
                f2=Integer.parseInt(y.substring(0,lenf2));
            }
            int len2=y.indexOf("/");                           //提取分号前后的整数
            b=Integer.parseInt(y.substring(lenf2+1, len2));
            d=Integer.parseInt(y.substring(len2+1, y.length()));
            if(f2<0){                                          //判断带分数是否负数，处理方式不同
                b=f2*d-b;
            }else{
                b=f2*d+b;
            }
        }

        Expression ex=new Expression();                //调用分数约分函数

        switch(ch){                                    //使用上述提取的四个整数进行运算并约分
            case "+":
                rus=ex.Dating(a*d+c*b, c*d);
                break;
            case "-":
                rus=ex.Dating(a*d-c*b, c*d);
                break;
            case "*":
                rus=ex.Dating(a*b, c*d);
                break;
            case "÷":
                if(c*b==0){
                    rus="无解";
                    break;
                }else{
                    rus=ex.Dating(a*d, c*b);
                }
                break;
        }
        return rus;
    }
}