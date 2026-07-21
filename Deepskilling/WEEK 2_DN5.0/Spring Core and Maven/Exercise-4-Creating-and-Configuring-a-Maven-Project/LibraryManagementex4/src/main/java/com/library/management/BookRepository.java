package com.library.management;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

    public void displayRepository() {

        System.out.println("Book Repository Bean Created Successfully.");
        System.out.println("Accessing Library Database...");
    }
}