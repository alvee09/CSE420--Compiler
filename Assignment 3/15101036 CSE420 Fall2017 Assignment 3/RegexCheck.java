/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package Assignment;
import java.util.*;
import java.io.FileInputStream;
//import java.io.*;
/**
 *
 * @author alvee
 */
public class RegexCheck {
  public FileInputStream file;
  private int regex;
  public String[] expression;
  private int text;
  public String[] textarray;
  public char [] ex;
  public char [] te;
  public int excount;
  public int tecount;
  public int [] matchfound;
  public int textcount;
  public int matchcount;
  
  public RegexCheck(FileInputStream file)
  {
    this.file = file;
    Scanner input = new Scanner(file);
    regex= Integer.parseInt(input.nextLine());
    System.out.println(regex);
    expression = new String[regex];
    for(int i = 0; i<regex; i++)
    {
      expression[i]= input.nextLine();
    }
    text= Integer.parseInt(input.nextLine());
    //this.textarray= textarray;
    textarray = new String[text];
    System.out.println(text);
    matchfound= new int[text];
    for(int y=0; y<text; y++)
    {
      
      textarray[y]= input.nextLine();
      matchfound[y]=0;
      //System.out.println(textarray[y]);
      //System.out.println(1234);
    }
    //System.out.println("123");
    
    for(matchcount =0; matchcount<text; matchcount++)
    {
      //System.out.println("123");
      textcheck(textarray[matchcount]);
      //System.out.println("after method call "+ matchcount);
      //textcount++;
    }
    printall();
    
  }
  
  /**
   *
   * @param s
   */
  public final void textcheck(String s)
  {
    
    int i= 0;
    //textcount= 0;
    //System.out.println("123");
    while (textcount<text)
    {
      while(i<regex)
      {
        //System.out.println("first while");
        //System.out.println(i);
        String r= expression[i];
        //String s= textarray[textcount];
        
        ex = new char[r.length()];
        te = new char[s.length()];
        System.out.println(s);
        
        ex= r.toCharArray();
        te= s.toCharArray();
        tecount =0;
        while(tecount<s.length())
        {
          //System.out.println(r);
          r= expression[i];
          ex= r.toCharArray();
          excount= 0;
          
          while(excount<r.length())
          {
            //System.out.println("te while");
            
            if(ex[excount] == te[tecount])
            {
              excount++;
              tecount++;
            }
            else if(ex[excount]=='(')
            {
              int temp= r.indexOf(')');
              String temp2 = r.substring(excount+1, temp);
              if(ex[temp+1]=='*')
              {
                starfound(temp2);
              }
              if(ex[temp+1]=='+')
              {
                int qw= tecount;
                plusfound(temp2);
                if(qw== tecount)
                {
                  excount=excount+3;
                  break;
                }
              }
              if(ex[temp+1]=='?')
              {
                questionfound(temp2);
              }
              excount=excount+3;
              //int x= temp2.length();
              //for (int q = 0; q<x; q++)
              //{
              //  excount++;
              //}
              
            }
            else if(ex[excount]== '*')
            {
              starfound(r.substring(excount-1, excount));
            }
            else if(ex[excount]== '+')
            {
              plusfound(r.substring(excount, excount+1));
              excount++;
            }
            
            else if(ex[excount]== '?')
            {
              questionfound(r.substring(excount-1, excount));
              
            }
            else 
            {
              nomatchfound();
              //System.out.println("end of else"+excount);
            }
            if(tecount==s.length() || excount==r.length())
            {
              
              if(tecount==s.length() && excount==r.length())
              {
                int c= i+1;
                System.out.println("matched");
                matchfound[matchcount]=c;
                //System.out.println(c);
                //textcount++;
                return;
              }
              else
              {
                System.out.println("not mathced");
                if(i<regex-1)
                {
                  i++;
                  tecount=0;
                }
                else
                {
                  //System.out.println("end return");
                  textcount++;
                  return;
                }
                
                //System.out.println(i);
                //break;
              }
            }
          }
        }
        
      }
    }
    
  }
  
  public void nomatchfound()
  {
    excount++;
    return;
  }
  public void starfound(String s)
  {
    //System.out.println(s);
    int temp= s.length();
    for(int i =0; i<temp; i++)
    {
      excount++;
    }
    char[] ch= s.toCharArray();
    for(;;)
    {
      for(int i=0; i<s.length(); i++)
      {
        if(te[tecount]==ch[i])
        {
          tecount++;
        }
        else
        {
          return;
        }
        String e=textarray[textcount];
        int w=e.length();
        if(tecount== w)
        {
          return;
          
        }
      }
      
    }
  }
  public void plusfound(String s)
  {
    //System.out.println(s);
    int temp= s.length();
    for(int i =0; i<temp; i++)
    {
      excount++;
    }
    char[] ch= s.toCharArray();
    for(int i=0; i<s.length(); i++)
    {
      if(te[tecount]==ch[i])
      {
        tecount++;
      }
      else
      {
        //i++;
        return;
      }
    }
    for(;;)
    {
      for(int i=0; i<s.length(); i++)
      {
        if(te[tecount]==ch[i])
        {
          tecount++;
        }
        else
        {
          return;
        }
      }
    }
  }
  
  public void questionfound(String s)
  {
    int temp= s.length();
    for(int i =0; i<temp; i++)
    {
      excount++;
    }
    char[] ch= s.toCharArray();
    for(int q= 0; q<2; q++)
    {
      for(int i=0; i<s.length(); i++)
      {
        if(te[tecount]==ch[i])
        {
          tecount++;
        }
        else
        {
          //i++;
          //System.out.println("return from ?");
          //      System.out.println(tecount);
          return;
        }
      }
    }
    return;
  }
  
  public void printall()
  {
    for(int i=0; i<text; i++)
    {
      if(matchfound[i]!=0)
      {
        System.out.println("yes "+matchfound[i]);
      }
      else
      {
        System.out.println("no "+matchfound[i]);
      }
    }
  }
}
