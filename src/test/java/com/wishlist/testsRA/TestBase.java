package com.wishlist.testsRA;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    //public static final String accessTOKEN="eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoic3R1ZGVudDE3QHdlYi5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTcxNjIwNDA2MiwiaWF0IjoxNzE1NjA0MDYyfQ.mpKTz1dADlb8H6B5glMfd3k8Fq6i9JDM1aaXIo_kKT0";
    //public static final String AUTH ="Authorization";
    //private Object Parser;

    @BeforeMethod
    public void init(){
        RestAssured.baseURI="http://localhost:8080";  //http://localhost:8080/swagger-ui/**
        RestAssured.basePath="/api";    //  API
        //RestAssured.defaultParser(Parser.JSON);

    }
}
