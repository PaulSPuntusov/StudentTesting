package ru;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.internal.matchers.Null;

public class RestTest {
    @Test
    public void testNoStudent(){
        RestAssured.given()
                .baseUri("http://localhost:8080/student/1")
                .when()
                .get()
                .then()
                .statusCode(404); // еще нет ни одного студента
    }
    @Test
    public void testPostStudent(){ // с оценками можно
        RestAssured.given()
                .baseUri("http://localhost:8080/student")
                .contentType(ContentType.JSON)
                .body("""
                        {
                        "Id": 1,
                        "name": "Pervak",
                        "marks": [2,3,4]
                        }
                """)
                .when()
                .post()
                .then()
                .statusCode(201);
    }
    @Test
    public void testPostStudent2(){ // без оценок проваливает, хотя в документации сказано, что можно
        RestAssured.given()
                .baseUri("http://localhost:8080/student")
                .contentType(ContentType.JSON)
                .body("""
                        {
                        "Id": 2,
                        "name": "Vtoryak",
                        "marks": []
                        }
                """)
                .when()
                .post()
                .then()
                .statusCode(201); // это второй
    }
    @Test
    public void testPostStudent3(){ // без оценок проваливает, хотя в документации сказано, что можно -баг
        RestAssured.given()
                .baseUri("http://localhost:8080/student")
                .contentType(ContentType.JSON)
                .body("""
                        {
                        "Id": 2,
                        "name": "Vtoryak",
                        "marks": Null
                        }
                """)
                .when()
                .post()
                .then()
                .statusCode(400); // Баг
    }
    @Test
    public void testPostStudent4(){ // пишет постоянно новых студентов - не перезаписывает по номеру
        RestAssured.given()
                .baseUri("http://localhost:8080/student")
                .contentType(ContentType.JSON)
                .body("""
                        {
                        "Id": 3,
                        "name": "Tretyak",
                        "marks": [3,4,5]
                        }
                """)
                .when()
                .post()
                .then()
                .statusCode(201)
                .body(Matchers.hasToString(String.valueOf(3)));
    }
}
