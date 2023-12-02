package com.example.crudapplication.services;

import com.example.crudapplication.dto.BookDTO;
import com.example.crudapplication.entity.Book;
import com.example.crudapplication.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        return (book != null) ? convertToDTO(book) : null;
    }

    @Override
    public BookDTO saveBook(BookDTO bookDTO) {
        Book book = convertToEntity(bookDTO);
        book = bookRepository.save(book);
        return convertToDTO(book);
    }

    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book existingBook = bookRepository.findById(id).orElse(null);

        if (existingBook != null) {
            BeanUtils.copyProperties(bookDTO, existingBook, "id");
            existingBook = bookRepository.save(existingBook);
            return convertToDTO(existingBook);
        }

        return null;
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    private BookDTO convertToDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        BeanUtils.copyProperties(book, bookDTO);
        return bookDTO;
    }

    private Book convertToEntity(BookDTO bookDTO) {
        Book book = new Book();
        BeanUtils.copyProperties(bookDTO, book);
        return book;
    }
}