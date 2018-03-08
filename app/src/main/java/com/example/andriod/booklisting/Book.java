package com.example.andriod.booklisting;

/**
 * Created by akhilamadari on 3/4/18.
 */

public class Book {

        private String tTitle;
        private String[] tAuthor;
        String s = "Authors: ";

        public Book(String title, String[] authors){
            tTitle= title;
            tAuthor =authors;
        }
        public String getTitle(){
            return "Title: "+tTitle;

        }
        public String[]  getAuthor(){
/*
        for(int i = 0; i < tAuthor.length ; i++){
            s = s+tAuthor[i]+" ";
        }*/
           // tAuthor =null;
        return tAuthor;
        }





}
