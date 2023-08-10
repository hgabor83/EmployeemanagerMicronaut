package com.example.employeemanager;

import com.example.employeemanager.repository.EmployeeRepository;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import jakarta.inject.Inject;

import static org.hamcrest.Matchers.*;

@MicronautTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeemanagerMicronautTest {

    @Inject
    EmbeddedApplication<?> application;

    @Test
    @Order(1)
    void testItWorks() {
        Assertions.assertTrue(application.isRunning());
    }


    @Test
    @Order(2)
    void getAllEmployees(RequestSpecification spec) {
        spec
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .body(is("[]"));
    }

    @Test
    @Order(3)
    void addEmployee(RequestSpecification spec) {
        //add an employee
        spec
                .given()
                .header("Content-Type", "application/json")
                .body(
                        """
                                {
                                    "name" : "John Doo",
                                    "email" :"jd@gmail.com",
                                    "phone" : "123",
                                    "jobTitle": "Java Developer",
                                    "imageUrl": "https://cdn-icons-png.flaticon.com/512/2202/2202112.png"
                                }
                                """
                )
                .when()
                .post("/employees")
                .then()
                .statusCode(201);

        //find the employee
        spec
                .when()
                .get("/employees/1")
                .then()
                .statusCode(200)

                .body("id", is(1))
                .body("name",is("John Doo"))
                .body("email",is("jd@gmail.com"))
                .body("phone",is("123"))
                .body("jobTitle",is("Java Developer"))
                .body("imageUrl",is("https://cdn-icons-png.flaticon.com/512/2202/2202112.png"));
    }

    @Test
    @Order(4)
    void deleteEmployee(RequestSpecification spec) {
        //delete employee
        spec
                .when()
                .delete("/employees/1")
                .then()
                .statusCode(200);

        //find 0 employee
        spec
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .body(is("[]"));
    }

    @Test
    @Order(5)
    void modifyEmployee(RequestSpecification spec) {
        //add an employee
        spec
                .given()
                .header("Content-Type", "application/json")
                .body(
                        """
                                {
                                    "name" : "John Doo",
                                    "email" :"jd@gmail.com",
                                    "phone" : "123",
                                    "jobTitle": "Java Developer",
                                    "imageUrl": "https://cdn-icons-png.flaticon.com/512/2202/2202112.png"
                                }
                                """
                )
                .when()
                .post("/employees")
                .then()
                .statusCode(201);

        //modify the employee
        spec
                .given()
                .header("Content-Type", "application/json")
                .body(
                        """
                                {
                                    "name" : "John Doo Mod",
                                    "email" :"jd@gmail.com",
                                    "phone" : "123",
                                    "jobTitle": "Java Developer",
                                    "imageUrl": "https://cdn-icons-png.flaticon.com/512/2202/2202112.png"
                                }
                                """
                )
                .when()
                .put("/employees/2")
                .then()
                .statusCode(200)
                .body("id", is(2))
                .body("name",is("John Doo Mod"))
                .body("email",is("jd@gmail.com"))
                .body("phone",is("123"))
                .body("jobTitle",is("Java Developer"))
                .body("imageUrl",is("https://cdn-icons-png.flaticon.com/512/2202/2202112.png"));
    }
}
