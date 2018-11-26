package com.example.dynamodbcrud.services;

import java.util.List;

import com.example.dynamodbcrud.models.Book;

public interface BookService {
    List<Book> findBooksPricedLessThanSpecifiedValue(String value)  throws Exception;
}