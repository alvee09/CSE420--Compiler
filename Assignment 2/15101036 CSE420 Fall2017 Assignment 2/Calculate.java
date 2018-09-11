/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package pkg420.assignment.pkg2;

/**
 *
 * @author alvee
 */
import java.util.*;
import java.io.*;
import java.util.regex.*;
import java.util.Arrays;
public class Calculate {
  private final FileInputStream file;
  private final String[][] array;
  private int index= 0; 
  private final Stack st= new Stack();
  private int eqs= 0;
  private final Stack<Integer> st2 = new Stack<Integer>();
  private String equation= "";
  private boolean found= true;
  public List<String> mathops = new ArrayList<String>(Arrays.asList("+", "-", "/", "*"));
  public int var =0;
  public List<String> variable = new ArrayList<String>();
  
  public Calculate(FileInputStream file)
  {
    this.file= file;
    Scanner sc= new Scanner(file);
    var= Integer.parseInt(sc.nextLine());
    String [][] array = new String[var][2];
    this.array= array;
    for(int i= 0; i<var; i++)
    {
      String temp= sc.nextLine();
      inputVariable(temp);
    }
    eqs= Integer.parseInt(sc.nextLine());
    for(int i= 0; i<eqs; i++)
    {
      equation= "";
      String temp= sc.nextLine();
      inputEquation(temp);
      //System.out.println(equation);  //for printing equation 
      getAnswer(equation);
      
    }
    
    
  }
  
  public void inputVariable(String s)
  {    
    String [] stringArray = s.split(" ");
    array[index][0]= stringArray[0];
    array[index][1]= stringArray[stringArray.length -1];
    index++;
    variable.add(stringArray[0]);
    return;
    
  }
  public void inputEquation(String s)
  {
    String [] stringArray = s.split(" ");
    int i=0;
    while(i<stringArray.length)
    {
      if(stringArray[i].matches("[a-xA-z][a-zA-Z0-9_#]*"))
      {
        equation= equation +stringArray[i];
        i++;   
      }
      else if(stringArray[i].matches("[\\(]"))
      {
        System.out.println("open (");
        
        st.push(stringArray[i]);
        i++; 
      }
      else if(mathops.contains(stringArray[i]))
      {
        operator(stringArray[i]);
        i++;
      }
      else if(stringArray[i].matches("[\\)]"))
      {
        System.out.println("if loop");
        for(;;)
        {
          String sa= (String) st.peek();
          System.out.println("in for");
          if(sa.equals("("))
          {
            System.out.println("open (");
            String temp= (String) st.pop();
            System.out.println("end");
            break;
          }
          else
          {
            System.out.println(sa);
            equation = equation +st.pop();
          }
        }
        i++;
      }
    }
    for(;;)
    {
      if(st.empty())
      {
        break;
      }
      else
      {
        equation = equation +st.pop();
      }
    }
  }
  public void operator(String s)
  {
    if(st.empty())
    {
      st.push(s);
      return;
    }
    String temp = (String) st.peek();
    if(s.equals("+") || s.equals("-"))
    {
      if(temp.equals("+") || temp.equals("-") || temp.equals("*") || temp.equals("/"))
      {
        equation = equation +st.pop();
        operator(s);
        return; //new line
      }
      else
      {
        st.push(s);
        return;
      }
    }
    else if(s.equals("*") || s.equals("/"))
    {
      if(temp.equals("+") || temp.equals("-"))
      {
        st.push(s);
        return;
      }
      else 
      {
        if(temp.equals("*") || temp.equals("/"))
        {
          equation = equation +st.pop();
          operator(s);
        }
        else
        {
          st.push(s);
          return;
        }
      }
    }
    
  }
  public void getAnswer(String s)
  {
    char [] ch = s.toCharArray();
    int i1 = 0;
    int i2 = 0;
    int x=0;
    for(int i=0 ; i<ch.length; i++)
    {
      
      String temp = Character.toString(ch[i]);
      if(mathops.contains(temp))
      {
        i1=st2.pop();
        i2=st2.pop();
        if(temp.equals("+"))
        {
          x= i2 + i1;
          st2.push(x);
        }
        else if(temp.equals("*"))
        {
          x= i2 * i1;
          st2.push(x);
        }
        else if(temp.equals("-"))
        {
          x= i2 - i1;
          st2.push(x);
        }
        else if(temp.equals("/"))
        {
          x= i2 / i1;
          st2.push(x);
        }
        
      }
      else
      {
        if(variable.contains(temp))
        {
        }
        else
        {
          found = false;
          System.out.println("Compilation error");
          return;
        }
        for(int y=0; y<index; y++)
        {
          if(temp.equals(array[y][0]))
          {
            
            int t= Integer.parseInt(array[y][1]);
            st2.push(t);
          }
          else
          {
          }
        }
      }
    }
    System.out.println(st2.pop());       
  }
}
