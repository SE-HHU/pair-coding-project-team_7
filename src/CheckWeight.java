import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CheckWeight {
    List<String> exp = new ArrayList<>();
    List<String> ans = new ArrayList<>();

    public List checkAnswer(){                             //第一步比较答案，答案相同再进行下一步判断，提高效率
        File file1 = new File("Exercises.txt");
        File file2 = new File("Answers.txt");

        List<String> find= new ArrayList<>();         //用于存取重复表达式的信息

        try{                                               //将题目和答案扫描存入list表中
            BufferedReader br1 = new BufferedReader(new FileReader(file1));
            String s;
            while((s = br1.readLine())!=null){
                s=s.substring(s.indexOf(":")+1,s.length());
                exp.add(s);
            }

            BufferedReader br2 = new BufferedReader(new FileReader(file2));
            while((s = br2.readLine())!=null){
                s=s.substring(s.indexOf(":")+1);
                ans.add(s);
            }

        }catch (IOException e) {
            e.printStackTrace();
        }

        for(int i=0;i<ans.size()-1;i++){                    //冒泡排序查找相同答案，再进行下一步判断
            StringBuilder s= new StringBuilder();
            for(int j=i+1;j<ans.size();j++){
                if(ans.get(i).equals(ans.get(j))){
                    if(checkExp(exp.get(i),exp.get(j)))
                        s.append(i + 1).append(",").append(exp.get(i)).append(" Repeat ").append(j + 1).append(",").append(exp.get(j)).append("  ");
                }
            }
            if(s.length()>0)
                find.add(s.toString());
        }
        return find;
    }

    public boolean checkExp(String exp1,String exp2){       //中缀转后缀，通过比较两表达式字符判断
        InfixToSuffix its=new InfixToSuffix();
        exp1=its.infixToSuffix(exp1);
        exp2=its.infixToSuffix(exp2);

        String[] strings = exp1.split(" ");

        for (String string : strings) {
            if (!exp2.contains(string))
                return false;
        }
        return true;
    }

}