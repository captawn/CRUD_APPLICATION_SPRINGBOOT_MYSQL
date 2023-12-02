package com.example.crudapplication.services;

import com.example.crudapplication.dto.BookDTO;

import java.util.List;

public interface BookService {
    List<BookDTO> getAllBooks();

    BookDTO getBookById(Long id);

    BookDTO saveBook(BookDTO bookDTO);

    BookDTO updateBook(Long id, BookDTO bookDTO);

    void deleteBook(Long id);
}
