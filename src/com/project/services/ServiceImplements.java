package com.project.services;

import java.io.IOException;

import java.util.Arrays;
import java.util.StringTokenizer;

import com.project.data.ReadFile;
import com.project.data.WriteFile;
import com.project.util.Input;
import com.project.view.Menu;

public class ServiceImplements implements Service {

     public void showAllMovieList() throws IOException {
          ReadFile readFile = new ReadFile();

          String data = readFile.getBufferedReader().readLine();

          int number = 0;

          System.out.println("\n| no\t| year\t| Main Character\t| Studios\t\t| Title");
          System.out.println("--------------------------------------------------------------------------------");
          while (data != null) {
               number++;
               StringTokenizer stringTokenizer = new StringTokenizer(data, ",");

               System.out.printf("|%2d \t", number);
               System.out.printf("| %s \t", stringTokenizer.nextToken());
               System.out.printf("| %-15s \t", stringTokenizer.nextToken());
               System.out.printf("| %-20s \t", stringTokenizer.nextToken());
               System.out.printf("| %s \t", stringTokenizer.nextToken());
               System.out.println();

               data = readFile.getBufferedReader().readLine();
          }
          System.out.println("--------------------------------------------------------------------------------\n");
     }

     @Override
     public void searchListMovie() throws IOException {

          Input input = new Input();

          System.out.print("Insert Movie Keywords : ");
          String consoleInput = input.consoleInput();

          String[] keywords = consoleInput.split("\\s+");
          System.out.print("Serach for : ");
          System.out.println(Arrays.toString(keywords));

          dataBaseCheck(keywords, true);
     }

     private boolean dataBaseCheck(String[] keywords, boolean isDisplay) throws IOException {

          ReadFile readFile = new ReadFile();

          String data = readFile.getBufferedReader().readLine();
          int number = 0;
          boolean isAlreadyExist = false;
          if (isDisplay) {
               System.out.println("\n| no\t| year\t| Main Character\t| Studios\t\t| Title");
               System.out.println("--------------------------------------------------------------------------------");
          }

          while (data != null) {

               isAlreadyExist = true;

               /*
                * looping keywords apakah keyword terdapat
                * kata/string yang cocok dengan keywords yang di masukkan
                */
               // System.out.println(data);
               for (String dataBase : keywords) {
                    isAlreadyExist = isAlreadyExist && data.toLowerCase().contains(dataBase.toLowerCase());
               }

               /* jika data cocok maka tampilkan */
               if (isAlreadyExist) {
                    if (isDisplay) {
                         number++;
                         StringTokenizer stringTokenizer = new StringTokenizer(data, ",");

                         System.out.printf("|%2d \t", number);
                         System.out.printf("| %s \t", stringTokenizer.nextToken());
                         System.out.printf("| %-20s \t", stringTokenizer.nextToken());
                         System.out.printf("| %-20s \t", stringTokenizer.nextToken());
                         System.out.printf("| %s \t", stringTokenizer.nextToken());
                         System.out.print("\n");
                    } else {
                         break;
                    }
               }

               data = readFile.getBufferedReader().readLine();
          }

          if (isDisplay) {
               System.out.println("--------------------------------------------------------------------------------");
          }

          return isAlreadyExist;
     }

     @Override
     public void addMovieList() throws IOException {

          WriteFile writeFile = new WriteFile();
          Menu menu = new Menu();

          Input input = new Input();

          /* input Data Movie */
          String year, actor, studios, title;
          System.out.println("\n----------------------------------------");
          System.out.print("Year\t: ");
          year = input.consoleInput();
          System.out.print("Actor\t: ");
          actor = input.consoleInput();
          System.out.print("Studios\t: ");
          studios = input.consoleInput();
          System.out.print("Title\t: ");
          title = input.consoleInput();
          System.out.println("----------------------------------------");
          String[] keywords = { year + "," + actor + "," + studios + "," + title };
          System.out.print("Data for : ");
          System.out.println(Arrays.toString(keywords));

          /*
           * akan menjadi masalah jika data di tampilkan
           * user ingin jika data sudah tersedia data tidak di tampikan
           * maka pada fungsi cek database akan di tambah dengan data bertipe boolean
           * bernama Display berfunsi untuk pilihan apakan user ingin menampilkan data
           * atau tidak
           */
          boolean isAlreadyExist = dataBaseCheck(keywords, false);

          if (!isAlreadyExist) {

               System.out.println("\nData Want to Add is : ");
               System.out.println("-----------------------------------");
               System.out.println("Year\t: " + year);
               System.out.println("Actor\t: " + actor);
               System.out.println("Studios\t: " + studios);
               System.out.println("Title\t: " + title);
               System.out.println("-----------------------------------");
               System.out.println();

               boolean isAdd = menu.getYes_or_No("Do you want to add data ? [y/n] : ");

               if (isAdd) {
                    writeFile.getBufferedWriter().write(year + "," + actor + "," + studios + "," + title);
                    writeFile.getBufferedWriter().newLine();
                    writeFile.getBufferedWriter().flush();
               }
          } else {
               System.out.println("Your Data is Already Exist !");
               dataBaseCheck(keywords, true);
          }

          writeFile.getBufferedWriter().close();
     }

     @Override
     public void deleteList() throws IOException {
          Menu menu = new Menu();

          /* ambil original database */
          ReadFile readFile = new ReadFile("database.txt");

          /* database sementara */
          WriteFile writeFile = new WriteFile("TempDataBase");

          showAllMovieList();

          /* mengambil user input */
          Input input = new Input();
          int numberList;
          int number = 0;

          System.out.print("Insert Number Want To Delete : ");
          numberList = input.consoleInputInt();

          /* boleean untuk memberi tahu bahwa number tidak tersedia */
          boolean isFoundNumber = false;

          String data = readFile.getBufferedReader().readLine();

          while (data != null) {

               number++;
               Boolean isDeleteList = false;

               /* jika inputuser sama dengan nomor data yang di looping maka tampilkan */
               if (numberList == number) {
                    System.out.println("\n| no\t| year\t| Main Character\t| Studios\t\t| Title");
                    System.out.println(
                              "--------------------------------------------------------------------------------");
                    StringTokenizer stringTokenizer = new StringTokenizer(data, ",");

                    System.out.printf("|%2d \t", number);
                    System.out.printf("| %s \t", stringTokenizer.nextToken());
                    System.out.printf("| %-20s \t", stringTokenizer.nextToken());
                    System.out.printf("| %-20s \t", stringTokenizer.nextToken());
                    System.out.printf("| %s \t", stringTokenizer.nextToken());
                    System.out.println();
                    isFoundNumber = true;
                    System.out.println(
                              "--------------------------------------------------------------------------------");
                    isDeleteList = menu.getYes_or_No("\nAre you sure want to delete this data ? [y/n] : ");
               }

               /* jika is delete yes maka */
               if (isDeleteList) {
                    System.out.println("Delete Data Succes !");
               } else {
                    /* pindahkan data ke temporary database */
                    writeFile.getBufferedWriter().write(data);
                    writeFile.getBufferedWriter().newLine();
               }

               data = readFile.getBufferedReader().readLine();
          }

          if (!isFoundNumber) {
               System.err.println("List Not Found !");
          }

          writeFile.getBufferedWriter().flush();
          /*
           * delete data original kemudian
           * rename TempDataBase.txt to database.txt
           */
          readFile.getFile().delete();
          writeFile.getFile().renameTo(readFile.getFile());
     }

     @Override
     public void updateList() throws IOException {

          Menu menu = new Menu();

          /* ambil original database */
          ReadFile readFile = new ReadFile("database.txt");

          // File file = new File("database.txt");
          // FileReader fileReader = new FileReader(file);
          // BufferedReader bufferedReader = new BufferedReader(fileReader);

          /* membuat database sementara */
          WriteFile writeFile = new WriteFile("TempDataBase.txt");

          showAllMovieList();

          /* mengambil user input */
          Input input = new Input();
          int numberList;
          int number = 0;

          System.out.print("Insert Number Want To Update : ");
          numberList = input.consoleInputInt();

          String data = readFile.getBufferedReader().readLine();

          while (data != null) {

               number++;

               /* jika inputuser sama dengan nomor data yang di looping maka tampilkan */
               if (numberList == number) {
                    StringTokenizer stringTokenizer = new StringTokenizer(data, ",");
                    System.out.println("\nData Did You Want To Upgrade is : ");
                    System.out.println("------------------------------------");
                    System.out.println("Year\t\t: " + stringTokenizer.nextToken());
                    System.out.println("Character\t: " + stringTokenizer.nextToken());
                    System.out.println("Studios\t\t: " + stringTokenizer.nextToken());
                    System.out.println("Title\t\t: " + stringTokenizer.nextToken());
                    System.out.println();

                    /*
                     * Update Data Dengan User Input
                     * dengan menggunakan temporary data
                     * maka data yang baru akan di pindahkan ke temporary data
                     */

                    String[] listData = { "Year", "Character", "Studios", "Title" };
                    String[] temporaryData = new String[4];

                    /*
                     * string tokenizer di gunakan untuk membaca original data
                     * yang di looping
                     */
                    stringTokenizer = new StringTokenizer(data, ",");

                    /* looping data yang ingin di update 1 per 1 */
                    for (int i = 0; i < listData.length; i++) {
                         boolean update = menu.getYes_or_No("Did You Want to Update " + listData[i] + " [y/n] ? : ");

                         String originalData = stringTokenizer.nextToken();

                         if (update) {
                              input = new Input();
                              System.out.print("Input New " + listData[i] + " : ");
                              temporaryData[i] = input.consoleInput();
                         } else {
                              temporaryData[i] = originalData;
                         }
                    }

                    /*
                     * menapilkan data yang baru ke console
                     * perlu di ingatstring tokenizer harus terus di update jika ingin menampilkan
                     * data yang baru
                     */

                    stringTokenizer = new StringTokenizer(data, ",");
                    System.out.println("\nData Want to Update : ");
                    System.out.println("------------------------------------");
                    System.out.println("Year\t\t: " + stringTokenizer.nextToken() + " ----> " + temporaryData[0]);
                    System.out.println("Character\t: " + stringTokenizer.nextToken() + " ----> " + temporaryData[1]);
                    System.out.println("Studios\t\t: " + stringTokenizer.nextToken() + " ----> " + temporaryData[2]);
                    System.out.println("Title\t\t: " + stringTokenizer.nextToken() + " ----> " + temporaryData[3]);

                    Boolean isUpdateList = menu.getYes_or_No("\nAre you sure want to update this data ? [y/n] : ");

                    /*
                     * apakah user yakin ingin menghapus data
                     * jika ya maka data akan di timpa
                     * 
                     * jika tidak maka hanya akan di copy
                     */
                    if (isUpdateList) {
                         /*
                          * cek apakah data yang di update sudah tersedia di database
                          * jika belum maka data akan di tambahkan
                          */
                         boolean isAlreadyExist = dataBaseCheck(temporaryData, false);

                         if (isAlreadyExist) {
                              System.out.println("Your Data is Already Exist !\nPlease insert new data");
                              writeFile.getBufferedWriter().write(data);
                         } else {
                              /*
                               * format baru ke Database
                               */
                              String newYear = temporaryData[0];
                              String newCharacter = temporaryData[1];
                              String newStudios = temporaryData[2];
                              String newTitle = temporaryData[3];

                              /*
                               * menulis ke database
                               */
                              writeFile.getBufferedWriter()
                                        .write(newYear + "," + newCharacter + "," + newStudios + "," + newTitle);
                         }

                    } else {
                         writeFile.getBufferedWriter().write(data);
                    }

               } else {
                    // copy data
                    writeFile.getBufferedWriter().write(data);
               }

               writeFile.getBufferedWriter().newLine();
               data = readFile.getBufferedReader().readLine();
          }

          writeFile.getBufferedWriter().flush();
          /*
           * delete data original kemudian
           * rename TempDataBase.txt to database.txt
           */
          // readFile.getFile().delete();
          // writeFile.getFile().renameTo(readFile.getFile());
     }

     @Override
     public void exitApp() {
          System.out.println("\n--------------------------------------");
          System.out.println("Thank You For Using This Software :) ");
          System.out.println("--------------------------------------");
          System.exit(0);
     }
}
