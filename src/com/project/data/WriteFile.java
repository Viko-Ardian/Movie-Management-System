package com.project.data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class WriteFile {

     File file;
     FileWriter fileWriter;
     BufferedWriter bufferedWriter;

     public WriteFile() throws IOException {
          this.fileWriter = new FileWriter("database.txt", true);
          this.bufferedWriter = new BufferedWriter(fileWriter);
     }

     public WriteFile(String file) throws IOException {
          this.file = new File(file);
          this.fileWriter = new FileWriter(this.file);
          this.bufferedWriter = new BufferedWriter(fileWriter);
     }

     public File getFile() {
          return file;
     }

     public void setFile(File file) {
          this.file = file;
     }

     public FileWriter getFileWriter() {
          return fileWriter;
     }

     public void setFileWriter(FileWriter fileWriter) {
          this.fileWriter = fileWriter;
     }

     public BufferedWriter getBufferedWriter() {
          return bufferedWriter;
     }

     public void setBufferedWriter(BufferedWriter bufferedWriter) {
          this.bufferedWriter = bufferedWriter;
     }
}
