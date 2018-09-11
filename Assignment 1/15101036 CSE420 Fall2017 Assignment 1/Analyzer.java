import java.util.*;
import java.io.*;
import java.util.Arrays;
public class Analyzer
{
  private FileInputStream file;
  List<String> keywords= new ArrayList<String>();
  List<String> identifier= new ArrayList<String>();
  List<String> mathOp= new ArrayList<String>();
  List<String> logicOp= new ArrayList<String>();
  List<Object> numeric= new ArrayList<Object>();
  List<String> others= new ArrayList<String>();
  
  public List<String> keys = new ArrayList<String>(Arrays.asList("auto", "break", "case", "char", "const", "continue", "default", "do", "double", "else", "enum", "extern",
                                                                 "float", "for", "goto", "if", "int", "long", "register", "return", "short", "signed", "sizeof", "static",
                                                                 "struct", "switch", "typedef", "union", "unsigned", "void", "volatile", "while"));
  
  public Analyzer(FileInputStream file)
  {
    this.file= file;
    try{
      Scanner sc= new Scanner(file);
      while(sc.hasNextLine())
      {
        String s= sc.next();
//        System.out.println(s);
        this.isKeyword(s);
        this.isIdentifier(s);
        this.ismathOp(s);
        this.islogicOp(s);
        this.isnum(s);
        this.isothers(s);
      }
      getPrint();
    }
    
    catch(Exception e)
    {}
  }
  public void isKeyword(String i)
  {
    String[] keys = {"auto", "break", "case", "char", "const", "continue", "default", "do", "double", "else", "enum", "extern",
      "float", "for", "goto", "if", "int", "long", "register", "return", "short", "signed", "sizeof", "static",
      "struct", "switch", "typedef", "union", "unsigned", "void", "volatile", "while"};
    
    for(int x=0; x<keys.length; x++)
    {
      if(keys[x].equals(i))
      {
        if(keywords.contains(i))
        {        }
        else
        {
          keywords.add(i);
        } 
      }
    }
  }
  public void isIdentifier(String i)
  {
    char [] ch= i.toCharArray();      
    if(ch[0]>= 65 && ch[0]<=122)
    {
      if(i.contains("="))
      {
        int temp= i.indexOf("=");
        if(identifier.contains(i.substring(0,temp)) || keys.contains(i.substring(0,temp)) )
        {}
        else{ 
          identifier.add(i.substring(0, temp));
        }
      }
      else if(i.contains(";"))
      {
        //System.out.print(i);
        int temp= i.indexOf(";");
        //new code
        String temps =i.substring(0,temp);
        if(temps.contains(","))
        {
          isIdentifier(temps);
        }
        
        
        else if(identifier.contains(i.substring(0,temp)) || keys.contains(i.substring(0,temp)))
        {}
        else{ 
          identifier.add(i.substring(0, temp));
        }
      }
      else if(i.contains(","))
      {
        //System.out.print(i);
        int temp= i.indexOf(",");
        int end=i.length();
        isIdentifier(i.substring(0,temp));
        if(temp<end)
        {
          //System.out.println(i.substring((temp), end));
          isIdentifier(i.substring((temp), end));
        }
        
        if(identifier.contains(i.substring(0,temp)) || keys.contains(i.substring(0,temp)))
        {}
        else{ 
          identifier.add(i.substring(0, temp));
        }
      }
      else
      {
        int last=  (int) ch[ch.length-1];
        if((last>=65 && last<=122) || (last>=48 && last <=57))
        {
          if(identifier.contains(i) || keys.contains(i))
          {
            
          }
          else{
            identifier.add(i);
          }
        }
        
      }
    }
    
    
  }
  public void ismathOp(String i){
    String[] mathOps= {"=", "+", "-", "/", "++", "%", "--"};
    for(int x=0; x<mathOps.length; x++)
    {
      if(mathOps[x].equals(i))
      {
        if(mathOp.contains(i))
        {        }
        else
        {
          mathOp.add(i);
        }
      }
    }
  }
  
  public void islogicOp(String i)
  {
    String[] logicOps= {"==", "<=", ">=", "<", ">"};
    for(int x=0; x<logicOps.length; x++)
    {
      if(logicOps[x].equals(i))
      {
        if(logicOp.contains(i))
        {        }
        else
        {
          logicOp.add(i);
        }
      }
    }
  }
  
  public void isnum(String i)
  {
    String [] nums = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
    //System.out.println("line 1");
    int temp=0;
    Double tempd= 0.0;
    for(int a=0; a<nums.length; a++)
    {
      if(i.contains(nums[a]))
      {
        if(i.endsWith(";"))
        {
          //System.out.println("1st if");
          if(i.contains("."))
          {
            //System.out.println("2nd if");
            int z= i.indexOf(";");
            tempd = Double.parseDouble(i.substring(0,z));
            if(numeric.contains(tempd))
            {}
            else{
              numeric.add(tempd);
            }
          }
          else
          {
            int z= i.indexOf(";");
            temp = Integer.parseInt(i.substring(0,z));
            //System.out.println(temp);
            if(numeric.contains(temp))   {}
            else{
              numeric.add(temp);
            }
          }
        }
        else{  
        }
      }
    }
  }
  
  
  public void isothers(String i)
  {
    String [] ots = {"(", ")", "{", "}", ",", ";"} ;
    for(int x=0; x<ots.length; x++)
    {
      if(i.contains(ots[x]))
      {
        if(others.contains(ots[x]))
        {        }
        else
        {
          int temp= i.indexOf(ots[x]);
          //System.out.println(ots[x]);
          others.add(ots[x]);
        }
      }
    }
  }
  public void getPrint()
  {
    printKey();
    printID();
    printMathop();
    printlogicop();
    printnum();
    printothers();
  }
  
  public void printKey()
  {
    System.out.print("Keywords : ");
    for(int x=0; x<keywords.size(); x++)
    {
      System.out.print(keywords.get(x)+ " ");
    }
    System.out.println("");
  }
  public void printID()
  {
    System.out.print("Identifiers : ");
    for(int x=0; x<identifier.size(); x++)
    {
      System.out.print(identifier.get(x)+ " ");
    }
    System.out.println("");
  }
  public void printMathop()
  {
    System.out.print("Math Operators : ");
    for(int x=0; x<mathOp.size(); x++)
    {
      System.out.print(mathOp.get(x)+ " ");
    }
    System.out.println("");
  }
  public void printlogicop()
  {
    System.out.print("Logical Operators : ");
    for(int x=0; x<logicOp.size(); x++)
    {
      System.out.print(logicOp.get(x)+ " ");
    }
    System.out.println("");
  }
  public void printnum()
  {
    System.out.print("Numerical Values : ");
    for(int x=0; x<numeric.size(); x++)
    {
      System.out.print(numeric.get(x)+ " ");
    }
    System.out.println("");
  }
  public void printothers()
  {
    System.out.print("Others : ");
    for(int x=0; x<others.size(); x++)
    {
      System.out.print(others.get(x)+ " ");
    }
    System.out.println("");
  }
}