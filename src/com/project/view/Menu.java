package com.project.view;

import java.io.IOException;

import com.project.services.Service;
import com.project.services.ServiceImplements;
import com.project.util.Input;

public class Menu {

     private Input input = new Input();
     private String option;

     /*
      * membuat fungsi untuk user apakah ingin
      * melanjutkan program atau tidak
      */
     private boolean isContinue = true;

     public void showMenu() throws IOException {

          Service service = new ServiceImplements();

          while (isContinue) {
               /*
                * membuat clear console agar console terhapus otomatis
                * saat user memasukkan opsi
                */
               clearConsole();
               /*
                * Membuat menu mana pilihan yang di inginkan user
                * terdiri dari show all movie. search, add, update, dan delete
                */
                System.out.println("-------------------------------------------------");
               System.out.println("|\t\tMovie List Management\t\t|");
               System.out.println("-------------------------------------------------");
               System.out.println("|\t\t1. Show All Movie\t\t|");
               System.out.println("|\t\t2. Search Movie\t\t\t|");
               System.out.println("|\t\t3. Add Movie\t\t\t|");
               System.out.println("|\t\t4. Update Movie\t\t\t|");
               System.out.println("|\t\t5. Delete Movie\t\t\t|");
               System.out.println("|\t\t6. Exit\t\t\t\t|");
               System.out.println("-------------------------------------------------");
               
               System.out.print("\nInsert Option : ");
               option = input.consoleInput();

               switch (option) {
                    case "1":
                         System.out.println("\n=============== LIST ALL MOVIE ===============");
                         service.showAllMovieList();
                         break;
                    case "2":
                         System.out.println("\n=============== SEARCH LIST MOVIE ===============");
                         service.searchListMovie();
                         break;
                    case "3":
                         System.out.println("\n=============== ADD MOVIE LIST ===============");
                         service.addMovieList();
                         break;
                    case "4":
                         System.out.println("\n=============== UPDATE DATA MOVIE ===============");
                         service.updateList();
                         break;
                    case "5":
                         System.out.println("\n=============== DELETE DATA MOVIE ===============");
                         service.deleteList();
                         break;
                    case "6":
                         System.out.println("\n=============== EXIT ===============");
                         service.exitApp();
                         break;
                    default:
                         System.err.println("WARNING : Option not found ! Please insert [1-6]!");
               }

               isContinue = getYes_or_No("\nDo You Want To Continue This Program ? [y/n] : ");
          }
     }

     /*
      * clear console
      * UNIX/Linux Only
      */
     private void clearConsole() {
          System.out.print("\033[H\033[2J");
          System.out.flush();
     }

     /*
      * fungsi untuk [yes/no]
      */
     public boolean getYes_or_No(String message) {
          System.out.print(message);
          String option = input.consoleInput();

          /* opsi yes/no */
          while (!option.equalsIgnoreCase("y") && !option.equalsIgnoreCase("n")) {
               System.err.println("Warning : Insert is not [y/n]");
               System.out.print("Please Insert [y/n]! : ");
               option = input.consoleInput();

          }
          return option.equalsIgnoreCase("y");
     }
}
