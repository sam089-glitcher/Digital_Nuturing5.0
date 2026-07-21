package com.library.management;

public class BookService {

    private BookRepository bookRepository;

    // Constructor Injection
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Setter Injection
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void displayService() {

        System.out.println();
        System.out.println("========================================");
        System.out.println("     LIBRARY MANAGEMENT SYSTEM");
        System.out.println("========================================");

        System.out.println("BookService Started Successfully.");

        bookRepository.displayRepository();

        System.out.println();
        System.out.println("Library Service Executed Successfully.");
    }
}