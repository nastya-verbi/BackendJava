package com.geekbrains.backend.test.imgur;

import java.util.Properties;

import com.geekbrains.backend.test.FunctionalTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ImgurApiFunctionalTest extends FunctionalTest {


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
        given()
                .auth()
                .oauth2(TOKEN)
                .log()
                .all()
                .expect()
                .body("data.id", is(157917785))
                .log()
                .all()
                .when()
                .get("account/" + userName);
    }

    @Test
    @Order(2)
    void postImageTest() {
        given()
                .auth()
                .oauth2(TOKEN)
                .multiPart("image", getFileResource("img.jpg"))
                .formParam("name", "Picture")
                .formParam("title", "The best picture!")
                .log()
                .all()
                .expect()
                .body("data.size", is(46314))
                .body("data.type", is("image/jpeg"))
                .body("data.name", is("Picture"))
                .body("data.title", is("The best picture!"))
                .log()
                .all()
                .when()
                .post("upload");
    }

    @Test
    @Order(3)
    void postImageTestNew() {
        image_ID = given()
                .auth()
                .oauth2(TOKEN)
                .multiPart("image", getFileResource("Picture.jpg"))
                .formParam("name", "Image")
                .formParam("title", "Beautiful")
                .log()
                .all()
                .expect()
                .body("data.size", is(139228))
                .body("data.type", is("image/jpeg"))
                .body("data.name", is("Image"))
                .body("data.title", is("Beautiful"))
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
    @Order(4)
    void getImageTestNew() {
        given()
                .auth()
                .oauth2(TOKEN)
                .log()
                .all()
                .when()
                .get("image/" + image_ID)
                .then()
                .body("data.size", is(139228))
                .body("data.type", is("image/jpeg"))
                .body("data.name", is("Image"))
                .body("data.title", is("Beautiful"))
                .log()
                .all();
    }


    @Test
    @Order(5)
    void deleteImageTestNew() {
        given()
                .auth()
                .oauth2(TOKEN)
                .log()
                .all()
                .when()
                .delete("image/" + image_ID)
                .then()
                .statusCode(200)
                .body("success", is(true))
                .log()
                .all();
    }

    @Test
    @Order(6)
    void postTextTest() {
        given()
                .auth()
                .oauth2(TOKEN)
                .multiPart("text", getFileResource("text.txt"))
                .formParam("name", "Text")
                .formParam("title", "TXT")
                .log()
                .all()
                .expect()
                .statusCode(400)
                .log()
                .all();
    }

    @Test
    @Order(7)
    void postExeTest() {
        given()
                .auth()
                .oauth2(TOKEN)
                .multiPart("exe", getFileResource("uninstall.exe"))
                .formParam("name", "Exe")
                .formParam("title", "EXE")
                .log()
                .all()
                .expect()
                .statusCode(417)
                .log()
                .all();
    }

    // TODO: 08.12.2021 Домашка протестировать через RA минимум 5 различных end point-ов
}
