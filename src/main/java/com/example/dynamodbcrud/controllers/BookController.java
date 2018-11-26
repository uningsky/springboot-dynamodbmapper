package com.example.dynamodbcrud.controllers;

import java.util.List;

import com.example.dynamodbcrud.models.Book;
import com.example.dynamodbcrud.services.BookService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService; 

    @GetMapping("/findBooksPricedLessThanSpecifiedValue")
    public List<Book> findBooksPricedLessThanSpecifiedValue(@RequestParam(value="price", defaultValue="1000") String value)
            throws Exception {
        return bookService.findBooksPricedLessThanSpecifiedValue(value);
    }
}