package com.project.services;

import java.io.IOException;

public interface Service {

     public void showAllMovieList() throws IOException;

     public void searchListMovie() throws IOException;

     public void addMovieList() throws IOException;

     public void deleteList() throws IOException;

     public void updateList() throws IOException;

     public void exitApp();
}
