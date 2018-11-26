package com.example.dynamodbcrud.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.example.dynamodbcrud.models.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Override
    public List<Book> findBooksPricedLessThanSpecifiedValue(String value)  throws Exception {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val1", new AttributeValue().withN(value));
        eav.put(":val2", new AttributeValue().withS("Book"));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
            .withFilterExpression("Price < :val1 and ProductCategory = :val2").withExpressionAttributeValues(eav);

        List<Book> scanResult = dynamoDBMapper.scan(Book.class, scanExpression);

        return scanResult; 
	}
}