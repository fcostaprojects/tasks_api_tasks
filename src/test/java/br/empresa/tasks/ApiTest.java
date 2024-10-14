package br.empresa.tasks;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ApiTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8181";
    }

    @Test
    public void teste() {
        RestAssured.given()
                .log().all()
        .when()
                .get("/tasks")
        .then()
                .statusCode(200);
    }

    @Test
    public void deveAdicionarTarefaComSucesso() {
        var tarefa = "{ \"detail\": \"tarefa\", \"date\": \"14/10/2024\" };";
        RestAssured.given()
                .body(tarefa)
                .contentType(ContentType.JSON)
            .when()
                .post("/tasks")
            .then()
                .log().all()
                .statusCode(201);
    }

    @Test
    public void naoDeveCadastrarTarefaComDataPassada() {
        var tarefa = "{ \"detail\": \"\", \"date\": \"\" };";
        RestAssured.given()
                .body(tarefa)
                .contentType(ContentType.JSON)
            .when()
                .post("/tasks")
            .then()
                .log().all()
                .statusCode(400);
    }
}
