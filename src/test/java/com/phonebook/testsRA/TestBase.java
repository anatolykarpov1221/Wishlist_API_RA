package com.phonebook.testsRA;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    public static final String TOKEN="eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoic3R1ZGVudDE3QHdlYi5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTcxNDYzODgxOSwiaWF0IjoxNzE0MDM4ODE5fQ.Goh6Is8l7UwP8F5MXOXz0fLiC4CdiW2ulkrG9IQ-lx0";
    public static final String AUTH ="Authorization";
    //private Object Parser;

    @BeforeMethod
    public void init(){
        RestAssured.baseURI="https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath="v1";
        //RestAssured.defaultParser(Parser.JSON);

    }
}
