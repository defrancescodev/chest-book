package com.example.chest_book.model.booklist;

import com.example.chest_book.model.book.Book;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "book_list_items",
        uniqueConstraints = @UniqueConstraint(columnNames = {"list_id", "book_id"})
)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class BookListItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "list_id", nullable = false)
    private BookList bookList;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(nullable = false)
    private Integer positionOrder;

    @Column(length = 300)
    private String notes;
}