import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ApiTest {
    private Connection connection;
    Long id = null;

    @BeforeEach
    public void connect() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost/app-db",
                "app-db-admin",
                "P@ssw0rd"
        );
        id = addValue();
    }

    @BeforeAll
    public static void setUpAuth() {
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("admin");
        authScheme.setPassword("admin");
        RestAssured.authentication = authScheme;
    }

    @AfterEach
    public void disconnect() throws SQLException {
        if (id != null) deleteValue(id);
        id = null;
        connection.close();
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
                .body("", hasItem(hasEntry("countryName", "99")
                ));
    }

//    @Test
//    public void shouldCreateCountry() {
//        given()
//                .contentType("application/json")
//                .body("{\n" +
//                        "  \"countryName\": \"99\"\n" +
//                        "}")
//                .when()
//                .post("/api/countries/")
//                .then()
//                .statusCode(201)
//                .body("id", not(empty()));
//    }

    @Test
    public void shouldDeleteCountry() {
        given()
                .when()
                .delete("/api/countries/" + id)
                .then()
                .statusCode(204);
    }

    @Test
    public void shouldUpdateCountry() throws SQLException {
        String countryName = "PP";
        given()
                .contentType("application/json")
                .body(String.format("{\n" +
                        "  \"id\": %d,\n" +
                        "  \"countryName\": \"%s\"\n" +
                        "}", id, countryName))
                .when()
                .put(String.format("/api/countries/%d", id))
                .then()
                .statusCode(200);
        assertThat(selectById(id), equalTo(countryName));
    }

    public Long addValue() throws SQLException {
        PreparedStatement insert = connection.prepareStatement(
                "INSERT INTO country(country_name) VALUES(?)",
                Statement.RETURN_GENERATED_KEYS
        );
        insert.setString(1, "99");
        insert.executeUpdate();
        ResultSet keys = insert.getGeneratedKeys();
        keys.next();
        return keys.getLong(1);
    }

    public String selectById(Long id) throws SQLException {
        PreparedStatement select = connection.prepareStatement("SELECT * from country where id = ?");
        select.setLong(1, id);
        ResultSet row = select.executeQuery();
        if (row.next()) return row.getString(2);
        else return null;
    }

    public void deleteValue(Long id) throws SQLException {
        PreparedStatement delete = connection.prepareStatement("DELETE from country where id = ?");
        delete.setLong(1, id);
        delete.executeUpdate();
    }
}






