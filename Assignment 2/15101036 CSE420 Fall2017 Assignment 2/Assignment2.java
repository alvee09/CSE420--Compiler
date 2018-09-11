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
import java.io.FileInputStream;
public class Assignment2 {
  
  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    try
    {
      File file= new File("input1.txt");
      File file2 = new File("input2.txt");
      System.out.println("Input File 1");
      FileInputStream stream = new FileInputStream(file);
      Calculate calculate = new Calculate(stream);
      
      System.out.println("Input File 2");
      FileInputStream stream2 = new FileInputStream(file2);
      Calculate calculate2 = new Calculate(stream2);
    }
    catch (Exception e)
    {
      System.out.println("File Not Found");
    }
  }
}


