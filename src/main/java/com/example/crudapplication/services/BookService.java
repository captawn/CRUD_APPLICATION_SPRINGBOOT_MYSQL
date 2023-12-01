package com.example.crudapplication.services;

import com.example.crudapplication.repository.BookRepository;
import com.example.crudapplication.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {


    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Create operation
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    // Read operation
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(long id) {
        return bookRepository.findById(id);
    }

    // Update operation
    public Book updateBook(Book book) {
        // Check if the book exists
        if (bookRepository.existsById(book.getId())) {
            return bookRepository.save(book);
        } else {
            // Handle not found scenario
            throw new IllegalArgumentException("Book with ID " + book.getId() + " not found");
        }
    }

    // Delete operation
    public void deleteBookById(long id) {
        bookRepository.deleteById(id);
    }
}
