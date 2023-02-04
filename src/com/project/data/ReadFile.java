package com.project.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {

     File file;
     FileReader fileReader;
     BufferedReader bufferedReader;

     public ReadFile() {
          try {
               this.fileReader = new FileReader("database.txt");
               this.bufferedReader = new BufferedReader(fileReader);
          } catch (Exception e) {
               System.err.println("WARNING : Database Tidak Ditemukan");
               System.err.println("Silahkan Buat Database");
               return;
          }
     }

     public ReadFile(String file) throws IOException {
          try {
               this.file = new File(file);
               this.fileReader = new FileReader(this.file);
               this.bufferedReader = new BufferedReader(fileReader);
               
          } catch (Exception e) {
               System.err.println("WARNING : Database Tidak Ditemukan");
               System.err.println("Silahkan Buat Database");
               return;
          }
     }

     public File getFile() {
          return file;
     }

     public void setFile(File file) {
          this.file = file;
     }

     public FileReader getFileReader() {
          return fileReader;
     }

     public void setFileReader(FileReader fileReader) {
          this.fileReader = fileReader;
     }

     public BufferedReader getBufferedReader() {
          return bufferedReader;
     }

     public void setBufferedReader(BufferedReader bufferedReader) {
          this.bufferedReader = bufferedReader;
     }
}
