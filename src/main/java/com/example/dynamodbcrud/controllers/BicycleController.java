package com.example.dynamodbcrud.controllers;

import java.util.List;

import com.example.dynamodbcrud.models.Bicycle;
import com.example.dynamodbcrud.services.BicycleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bicycles")
class BicycleController {

    private static final Logger logger = LoggerFactory.getLogger(BicycleController.class);

    @Autowired
    private BicycleService bicycleService; 

    @GetMapping("/findBicyclesOfSpecificTypeWithMultipleThreads")
    public List<Bicycle> findBicyclesOfSpecificTypeWithMultipleThreads(@RequestParam(value="bicycleType", defaultValue="Road") String bicycleType)
            throws Exception {
        return bicycleService.findBicyclesOfSpecificTypeWithMultipleThreads(1, bicycleType);
    }
}