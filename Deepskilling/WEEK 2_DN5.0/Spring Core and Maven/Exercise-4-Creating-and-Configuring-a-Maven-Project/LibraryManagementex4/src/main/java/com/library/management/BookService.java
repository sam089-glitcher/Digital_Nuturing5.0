package com.library.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public void displayService() {

        System.out.println("========================================");
        System.out.println(" LIBRARY MANAGEMENT SYSTEM ");
        System.out.println("========================================");

        System.out.println("BookService Bean Created using @Service");
        System.out.println("BookRepository injected using @Autowired");
        System.out.println();

        bookRepository.displayRepository();

        System.out.println();
        System.out.println("Annotation-based configuration successful.");
        System.out.println("Spring IoC Container is managing all beans.");
    }
}