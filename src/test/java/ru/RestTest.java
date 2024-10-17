package ru;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import io.restassured.path.json.JsonPath;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.internal.matchers.Null;

public class RestTest {
    @Test
    public void test2__NoStudent(){
        RestAssured.given()
                .baseUri("http://localhost:8080/student/1")
                .when()
                .get()
                .then()
                .statusCode(404); // еще нет ни одного студента
    }
    @Test
    public void test3__PostStudent(){ // с оценками можно
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
    public void test3_1_PostStudent2(){ // без оценок разрешает, но без массива проваливает, хотя в документации сказано, что можно
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
    public void test3_2_PostStudent3(){ // без оценок проваливает, хотя в документации сказано, что можно -баг
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
                .statusCode(201); // Баг
    }
    @Test
    public void test4_PostStudent4(){ // пишет постоянно новых студентов - не перезаписывает по номеру
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
    @Test
    public void test5_PostStudent4(){ // не работает по описанию
        RestAssured.given()
                .baseUri("http://localhost:8080/student")
                .contentType(ContentType.JSON)
                .body("""
                        {
                        "Id": Null,
                        "name": "Tretyak",
                        "marks": [3,4,5]
                        }
                """)
                .when()
                .post()
                .then()
                .statusCode(201)
                .body(Matchers.notNullValue());
    }
    @Test
    public void test6__PostStudent4() { // все ок
        RestAssured.given()
                .baseUri("http://localhost:8080/student")
                .contentType(ContentType.JSON)
                .body("""
                                {
                                "Id": 5,
                                "name": Null,
                                "marks": [3,4,5]
                                }
                        """)
                .when()
                .post()
                .then()
                .statusCode(400)
                .body(Matchers.notNullValue());
    }
    @Test
    public void test7__DeleteExistStudent(){ // с этим - ок
        RestAssured.given()
                .baseUri("http://localhost:8080/student/2")
                .contentType(ContentType.JSON)
                .body("""
                        {
                        "Id": 8,
                        "name": "Tretyak",
                        "marks": [3,4,5]
                        }
                """)
                .when()
                .delete()
                .then()
                .statusCode(200);
    }
    @Test
    public void test8__DeleteStudentNotExist(){ // с этим - ок
        RestAssured.given()
                .baseUri("http://localhost:8080/student/118")
                /*
                .contentType(ContentType.JSON)
                .body("""
                        {
                        "Id": 8,
                        "name": "Tretyak",
                        "marks": [3,4,5]
                        }
                """)

                 */
                .when()
                .delete()
                .then()
                .statusCode(404);
    }
    @Test
    public void test1__GetStudent() {
        RestAssured.given()
                .baseUri("http://localhost:8080/student/7")
                .when()
                .get()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("name", Matchers.equalTo("Tretyak"));
    }
    @Test
    public void test9__GetTopStudent() {
            //Student st = new Student("Petya");
            RestAssured.given()
                .baseUri("http://localhost:8080/topStudent")
                .contentType(ContentType.JSON)
                .when()
                .get()
                    .getBody().print();
                //.then()
                //.statusCode(200)
                //.contentType(ContentType.JSON) // он отдает не джейсон - я не понял, как с этим быть
                //.body("name", Matchers.notNullValue()) // пока массив не пустой, он возвращает ContentType не джейсон
                //.body("marks", Matchers.notNullValue())

                //.extract().as(Student.class).


       // JsonPath jsonPath = new JsonPath("http://localhost:8080/topStudent");
       // int id = jsonPath.getInt("Id");
       // System.out.println(id);
    }
    /*
    @Test
    public void test10 fromSlava(){
        RestAssured.given()
                .baseUri("http://localhost:8080/topStudent")
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON) // он отдает не джейсон - я не понял, как с этим быть
                .body("name[0]", equalTo("sdkfj")).body("marks[0]", hasItems(5, 5, 5))
                .body("name[1]", equalTo("sdkfj")).body("marks[1]", hasItems(5, 5, 5));
    }

     */

}
