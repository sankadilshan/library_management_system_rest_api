package com.libraryManagement.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Result {
    private long totalResult;
    private int totalPage;
    private int resultPerPage;
    private int pageNumber;
    private List<BookDto> bookDto;
}
