import java.util.*;
import java.io.*;
import java.io.FileInputStream;
public class Main {

 public static void main(String[] args) {
  try
  {
    File file= new File("input.txt");
    //Scanner sc= new Scanner(file);
    FileInputStream stream = new FileInputStream(file);
    Analyzer analyze = new Analyzer(stream);
    //String s= sc.next();
    //System.out.println(s);
  }
  catch (Exception e)
  {
    System.out.println("File Not Found");
  }
 }

}
