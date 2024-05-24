package com.wishlist.testsRA;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    //RestAssured.defaultParser(Parser.JSON);
    public static final String TOKEN ="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyaW5nb0B3ZWIuY29tIiwiZXhwIjoxNzE3MDczODMyLCJyb2xlcyI6W3siaWQiOjIsInRpdGxlIjoiUk9MRV9VU0VSIiwiYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dLCJuYW1lIjoiUmluZ28gU3RhcnIifQ.rw5vUNpok2iT3aJC45VI-d8FN4mgYwKsKxt1Z_Vm-PY";
    public static final String AUTH ="Authorization";
    //private Object Parser;

    @BeforeMethod
    public void init(){
        RestAssured.baseURI="http://localhost:8080";  //http://localhost:8080/swagger-ui/**
        RestAssured.basePath="/api";


    }
}
