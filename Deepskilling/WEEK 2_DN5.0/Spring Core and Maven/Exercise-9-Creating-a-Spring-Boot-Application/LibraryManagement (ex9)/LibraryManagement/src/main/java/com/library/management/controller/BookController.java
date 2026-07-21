package com.library.management.controller;

import com.library.management.entity.Book;
import com.library.management.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository repository;

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return repository.save(book);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Book> getBook(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id,
                           @RequestBody Book book) {

        book.setId(id);
        return repository.save(book);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {

        repository.deleteById(id);
        return "Book deleted successfully.";
    }
}