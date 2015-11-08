/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jayway.restassured.parsing.Parser;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.http.ContentType;
import entity.User;
import static org.junit.Assert.*;


/**
 *
 * @author Lenovo
 */
public class RestAssuredTest {
    Gson gson;
    
    
    public RestAssuredTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        baseURI = "http://localhost:8080/";
        defaultParser = Parser.JSON;
        basePath = "";
        
    }
    
    @Test
    public void addUser() {
        User u = new User();
        JsonObject json = new JsonObject();
        json.addProperty("userName", "RestTest");
        json.addProperty("password", "RestTest");
        
        u = gson.fromJson(json, User.class);
        
        given()
                .body(u)
        .when()
                .post("/api/newuser/adduser")
        .then()
                .statusCode(200);
    }
    
    @Test
    public void deleteUser() {
        given()
        .when()
                .delete("api/demoadmin/user/RestTest")
        .then()
                .statusCode(200);
    }
    
    @Test
    public void getUser() {
        given()
                .accept(ContentType.JSON).
        when()
                .get("api/demouser/user/john").
        then()
                .statusCode(200);
    }
}
