package com.wishlist.testsRA;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    //RestAssured.defaultParser(Parser.JSON);
    public static final String TOKEN ="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkdWRraW5hQHdlYi5kZSIsImV4cCI6MTcxNjk4MTAzNCwicm9sZXMiOlt7ImlkIjoyLCJ0aXRsZSI6IlJPTEVfVVNFUiIsImF1dGhvcml0eSI6IlJPTEVfVVNFUiJ9XSwibmFtZSI6IkxlbmEgRHVka2luYSJ9.ORu2YmPnhfVKpQVRJhazYQAk1T0qal_NNLToJMiKngU";
    public static final String AUTH ="Authorization";
    //private Object Parser;

    @BeforeMethod
    public void init(){
        RestAssured.baseURI="http://localhost:8080";  //http://localhost:8080/swagger-ui/**
        RestAssured.basePath="/api";


    }
}
