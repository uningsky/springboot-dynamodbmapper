package com.example.dynamodbcrud.services;

import java.util.List;

import com.example.dynamodbcrud.models.Bicycle;

public interface BicycleService {
    List<Bicycle> findBicyclesOfSpecificTypeWithMultipleThreads(int numberOfThreads, String bicycleType) throws Exception;
}