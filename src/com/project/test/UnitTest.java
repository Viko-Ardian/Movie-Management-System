package com.project.test;

import java.io.IOException;

import com.project.services.Service;
import com.project.services.ServiceImplements;
import com.project.view.Menu;

public class UnitTest {
     public static void main(String[] args) throws IOException {
          showMenuTest();
          // serviceTest();
          // searchListMovieTest();
          // addMovieListTest();
          // deleteListTest();
          // updateListTest();
     }
     
     public static void showMenuTest() throws IOException {
          Menu menu = new Menu();
          menu.showMenu();
     }

     public static void serviceTest() throws IOException {
          Service service = new ServiceImplements();
          service.showAllMovieList();
     }

     public static void searchListMovieTest() throws IOException {
          Service service = new ServiceImplements();
          service.searchListMovie();
     }

     public static void addMovieListTest() throws IOException {
          Service service = new ServiceImplements();
          service.addMovieList();
     }

     public static void deleteListTest() throws IOException {
          Service service = new ServiceImplements();
          service.deleteList();
     }

     public static void updateListTest() throws IOException {
          Service service = new ServiceImplements();
          service.updateList();
     }
}
