import java.io.Console;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author xue
 * @Description
 * @Date:14:34 2017/8/27
 */
public class Test01 {
//    StringBuilder sb = new StringBuilder();
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.print("What is you name ?");
        String name = in.nextLine();

        System.out.print("How old are you");
        int age = in.nextInt();
        System.out.println("Hello,"+name+".Next year,you will be "+(age+1));
        Scanner content = null;
        try {
             content = new Scanner(Paths.get("D:\\upload\\a.txt"),"UTF-8");
             System.out.print("=====================");
             System.out.print(content);
             System.out.print("=====================");
        } catch (IOException e) {
            e.printStackTrace();
        }


        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");

    }

}
