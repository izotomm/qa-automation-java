import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

public class ApiTest {
    @BeforeAll
    public static void setUpAuth() {
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("admin");
        authScheme.setPassword("admin");
        RestAssured.authentication = authScheme;
    }

    @BeforeAll
    public static void setUpErrorLogging() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void shouldGetCountry() {
        when()
                .get("/api/countries")
                .then()
                .statusCode(200)
                .body(
                        "[0].countryName", is("vo"),
                        "[1].countryName", is("vi"),
                        "[2].countryName", is("St")
                );
    }

    @Test
    public void shouldCreateCountry() {
        given()
                .contentType("application/json")
                .body("{\n" +
                        "  \"countryName\": \"QZ\"\n" +
                        "}")
                .when()
                .post("/api/countries/")
                .then()
                .statusCode(201)
                .body("id", not(empty()));
    }

    @Test
    public void shouldDeleteCountry() {
        given()
                .when()
                .delete("/api/countries/1005")
                .then()
                .statusCode(204);
    }

    @Test
    public void shouldUpdateCountry() {
        given()
                .contentType("application/json")
                .body("{\n" +
                        "  \"id\": \"8\",\n" +
                        "  \"countryName\": \"RR\"\n" +
                        "}")
                .when()
                .put("/api/countries/8")
                .then()
                .statusCode(200);
    }


}