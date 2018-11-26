package com.example.dynamodbcrud.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.example.dynamodbcrud.models.Bicycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BicycleServiceImpl implements BicycleService {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Override
    public List<Bicycle> findBicyclesOfSpecificTypeWithMultipleThreads(int numberOfThreads, String bicycleType) throws Exception {

		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val1", new AttributeValue().withS("Bicycle"));
        eav.put(":val2", new AttributeValue().withS(bicycleType));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
            .withFilterExpression("ProductCategory = :val1 and BicycleType = :val2").withExpressionAttributeValues(eav);

        List<Bicycle> scanResult = dynamoDBMapper.parallelScan(Bicycle.class, scanExpression, numberOfThreads);

        return scanResult;
	}
}