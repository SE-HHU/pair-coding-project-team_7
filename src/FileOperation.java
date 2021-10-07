import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class FileOperation {

    public void FileW(File file,String s){                              //文件写入函数
        try {
            FileWriter  out=new FileWriter (file,true);
            BufferedWriter bw= new BufferedWriter(out);
            bw.write(s);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void FileC(File file1,File file2,File file3){                 //比较两个答案的文件，将结果写入另一个文件
        List<String> correct =new ArrayList<String>();
        List<String> wrong =new ArrayList<String>();
        try{
            BufferedReader br1 = new BufferedReader(new FileReader(file1));
            BufferedReader br2 = new BufferedReader(new FileReader(file2));
            int i=1;
            String s = null;
            while((s = br1.readLine())!=null){
                if(s.equals(br2.readLine())){
                    correct.add(Integer.toString(i));
                }else{
                    wrong.add(Integer.toString(i));
                }
                i++;
            }

            this.FileW(file3, "Correct:"+correct.size()+"(");
            for(int j=0;j<correct.size();j++){
                if(j==correct.size()-1){
                    this.FileW(file3, correct.get(j));
                }else{
                    this.FileW(file3, correct.get(j)+",");
                }
            }
            this.FileW(file3, ")"+"\r\n");

            this.FileW(file3, "Wrong:"+wrong.size()+"(");
            for(int j=0;j<wrong.size();j++){
                if(j==wrong.size()-1){
                    this.FileW(file3, wrong.get(j));
                }else{
                    this.FileW(file3, wrong.get(j)+",");
                }
            }
            this.FileW(file3, ")"+"\r\n");

            CheckWeight cw=new CheckWeight();
            List<String> find=new ArrayList<String>();
            find=cw.checkAnswer();
            this.FileW(file3,"Repeat:"+find.size()+"\r\n");
            this.FileW(file3,"RepeatDetail:"+"\r\n");
            for(int k=1;k<=find.size();k++){
                this.FileW(file3,"("+k+") "+find.get(k-1)+"\r\n");
            }

            br1.close();
            br2.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}