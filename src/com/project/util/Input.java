package com.project.util;

import java.util.Scanner;


public class Input {

     private int number;
     private String console;
     private Scanner userInput = new Scanner(System.in);
     boolean isInteger = false;


     public String consoleInput() {
          console = userInput.nextLine();
          return console;
     }

     public int consoleInputInt() {
          console = userInput.next();
          try {
               number = Integer.parseInt(console);
          } catch (Exception e) {
               System.err.println("\nInput is Not Number, Please Insert Number !");
          }

          return number;
     }
}