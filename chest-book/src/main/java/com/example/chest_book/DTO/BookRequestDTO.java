package com.example.chest_book.DTO;

import jakarta.validation.constraints.NotBlank;

public record BookRequestDTO(
        String externalApiId,
        @NotBlank(message = "O título é obrigatório") String title,
        @NotBlank(message = "O autor é obrigatório") String author,
        String synopsis,
        String coverUrl,
        Integer pageCount,
        Integer publishedYear
) {}