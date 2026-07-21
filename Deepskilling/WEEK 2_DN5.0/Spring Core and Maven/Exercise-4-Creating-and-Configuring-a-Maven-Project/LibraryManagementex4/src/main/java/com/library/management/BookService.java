package com.library.management;

public class BookService {

    private BookRepository bookRepository;

    // Setter Injection
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void displayService() {

        System.out.println("========================================");
        System.out.println("     LIBRARY MANAGEMENT SYSTEM");
        System.out.println("========================================");

        System.out.println("BookService Bean Created Successfully.");
        System.out.println("BookRepository injected using Setter Injection.");
        System.out.println();

        bookRepository.displayRepository();

        System.out.println();
        System.out.println("Spring IoC Container configured successfully.");
        System.out.println("Application executed successfully.");
    }
}