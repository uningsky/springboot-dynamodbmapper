package com.example.dynamodbcrud.controllers;

import java.util.List;

import com.example.dynamodbcrud.models.CatalogItem;
import com.example.dynamodbcrud.models.Mything;
import com.example.dynamodbcrud.services.ProductCatalogService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(value = "ProductCatalogController", description = "ProductCatalog api")
@RestController
@RequestMapping("/catalogItems")
class ProductCatalogController {

    private static final Logger logger = LoggerFactory.getLogger(ProductCatalogController.class);

    @Autowired
    private ProductCatalogService productCatalogService; 

    @GetMapping(path = "/", produces = "application/json;charset=UTF-8")
    public List<CatalogItem> getCatalogItems(@RequestParam(value="title", defaultValue="") String title) {
        return null;
    }

    @GetMapping(path = "/{id}", produces = "application/json;charset=UTF-8")
    public CatalogItem getCatalogItem(@RequestParam(value="id", defaultValue="101") Integer id) {
        
        CatalogItem catalogItem = productCatalogService.loadCatalogItem(id);
        return catalogItem; 
    }

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json;charset=UTF-8")
    public void add(@RequestBody CatalogItem catalogItem) {
        productCatalogService.saveCatalogItem(catalogItem);
    }

    @DeleteMapping(path = "/", consumes = "application/json", produces = "application/json;charset=UTF-8")
    public void delete(@RequestBody CatalogItem catalogItem) {
        productCatalogService.deleteCatalogItem(catalogItem);
    }

}