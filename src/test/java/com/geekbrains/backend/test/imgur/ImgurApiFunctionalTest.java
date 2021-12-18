package com.geekbrains.backend.test.imgur;


import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ImgurApiFunctionalTest extends ImgurApiAbstractTest {


    private static Properties properties;
    private static String TOKEN;
    private static String image_ID = "";


    @BeforeAll
    static void beforeAll() throws Exception {
        properties = readProperties();
        RestAssured.baseURI = properties.getProperty("imgur-api-url");
        TOKEN = properties.getProperty("imgur-api-token");
    }

    @Test
    @Order(1)
    void getAccountBase() {
        String userName = "redcappy";

        ResponseSpecification resp = new ResponseSpecBuilder()
                .build()
                .statusCode(200)
                .body("data.id", is(157917785));

        given()
                .spec(requestSpecification)
                .log()
                .all()
                .expect()
                .spec(resp)
                .log()
                .all()
                .when()
                .get("account/" + userName);
    }

    @Test
    @Order(2)
    void postImageTestNew() {
        image_ID = given()
                .spec(requestSpecification)
                .multiPart("image", getFileResource("Picture.jpg"))
                .formParam("name", "Image")
                .formParam("title", "Beautiful")
                .log()
                .all()
                .expect()
                .spec(resp_1())
                .log()
                .all()
                .when()
                .post("upload")
                .then()
                .extract()
                .response()
                .jsonPath()
                .getString("data.id");
    }

    @Test
    @Order(3)
    void getImageTestNew() {
        given()
                .spec(requestSpecification)
                .log()
                .all()
                .when()
                .get("image/" + image_ID)
                .then()
                .spec(resp_1())
                .log()
                .all();
    }


    @Test
    @Order(4)
    void deleteImageTestNew() {

        ResponseSpecification resp_del = new ResponseSpecBuilder()
                .build()
                .statusCode(200)
                .body("success", is(true));

        given()
                .spec(requestSpecification)
                .log()
                .all()
                .when()
                .delete("image/" + image_ID)
                .then()
                .spec(resp_del)
                .log()
                .all();
    }

    @Test
    @Order(5)
    void postTextTest() {

        ResponseSpecification resp_code1 = new ResponseSpecBuilder()
                .build()
                .statusCode(400);

        given()
                .spec(requestSpecification)
                .multiPart("text", getFileResource("text.txt"))
                .formParam("name", "Text")
                .formParam("title", "TXT")
                .log()
                .all()
                .expect()
                .spec(resp_code1)
                .log()
                .all();
    }

    @Test
    @Order(6)
    void postExeTest() {

        ResponseSpecification resp_code2 = new ResponseSpecBuilder()
                .build()
                .statusCode(417);

        given()
                .spec(requestSpecification)
                .multiPart("exe", getFileResource("uninstall.exe"))
                .formParam("name", "Exe")
                .formParam("title", "EXE")
                .log()
                .all()
                .expect()
                .spec(resp_code2)
                .log()
                .all();
    }
}
