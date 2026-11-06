package com.example.chest_book.service;

import com.example.chest_book.DTO.BookRequestDTO;
import com.example.chest_book.model.book.Book;
import com.example.chest_book.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public Book createBook(BookRequestDTO dto) {

        if (dto.externalApiId() != null && !dto.externalApiId().isBlank()) {
            var existingBook = bookRepository.findByExternalApiId(dto.externalApiId());
            if (existingBook.isPresent()) {
                return existingBook.get();
            }
        }

        Book newBook = Book.builder()
                .externalApiId(dto.externalApiId())
                .title(dto.title())
                .author(dto.author())
                .synopsis(dto.synopsis())
                .coverUrl(dto.coverUrl())
                .pageCount(dto.pageCount())
                .publishedYear(dto.publishedYear())
                .build();

        return bookRepository.save(newBook);
    }

    @Transactional(readOnly = true)
    public List<Book> searchBooks(String query) {
        if (query == null || query.isBlank()) {
            return bookRepository.findAll();
        }
        return bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(query, query);
    }

    @Transactional(readOnly = true)
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado com o ID: " + id));
    }
}