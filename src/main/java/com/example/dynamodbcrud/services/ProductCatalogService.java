package com.example.dynamodbcrud.services;

import com.example.dynamodbcrud.models.CatalogItem;

public interface ProductCatalogService {
    CatalogItem loadCatalogItem(Integer id);
    void saveCatalogItem(CatalogItem item); 
    void deleteCatalogItem(CatalogItem item);
}