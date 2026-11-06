package com.example.chest_book.controller;

import com.example.chest_book.DTO.BookRequestDTO;
import com.example.chest_book.model.book.Book;
import com.example.chest_book.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class BookController {

    private final BookService bookService;


    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody BookRequestDTO dto) {
        Book savedBook = bookService.createBook(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }


    @GetMapping
    public ResponseEntity<List<Book>> searchBooks(@RequestParam(name = "q", required = false) String query) {
        List<Book> books = bookService.searchBooks(query);
        return ResponseEntity.ok(books);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }
}