package com.example.dynamodbcrud.services;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.example.dynamodbcrud.models.CatalogItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCatalogServiceImpl implements ProductCatalogService {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Override
    public CatalogItem loadCatalogItem(Integer id) {
        
        CatalogItem itemRetrieved = dynamoDBMapper.load(CatalogItem.class, id);
        
        return itemRetrieved;
	}

    @Override
    public void saveCatalogItem(CatalogItem item) {

        dynamoDBMapper.save(item);

    }

    @Override
    public void deleteCatalogItem(CatalogItem item) {
        // CatalogItem itemRetrieved = dynamoDBMapper.load(CatalogItem.class, item.getId());
        // dynamoDBMapper.delete(itemRetrieved);
        dynamoDBMapper.delete(item);
    }
}