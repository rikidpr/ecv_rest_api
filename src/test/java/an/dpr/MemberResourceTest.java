package an.dpr;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
@TestMethodOrder(OrderAnnotation.class)
public class MemberResourceTest {
    
    @Test
    @Order(1)
    public void testPostMemberEndpoint() {
        String payload = "{\"cod\": \"code\", \"name\":\"fulanita\"," +
        " \"category\":\"PROMESA_FEM\", \"info\":\"fulanita de tal\"}";
		given()
        .contentType(ContentType.JSON)
        .body(payload)
        .post("/member")
        .then()
        .assertThat()
        .statusCode(200);
    }
    
    @Test
    @Order(2)
    public void testGetMemberEndpoint() {
        given().when().get("/member").then().statusCode(200)
        .body(containsString("PROMESA_FEM"));
    }
    
    @Test
    @Order(3)
    public void testPutMemberEndpoint() {
        String payload = "{\"id\":\"1\", \"cod\": \"code\", \"name\":\"fulanita\"," +
        " \"category\":\"PRINCIPIANTE_FEM\", \"info\":\"fulanita de tal\"}";
		given()
        .contentType(ContentType.JSON)
        .body(payload)
        .put("/member")
        .then()
        .assertThat()
        .statusCode(200);
    }
    
    @Test
    @Order(4)
    public void testGetMemberEndpointAfterPut() {
        given().when().get("/member").then().statusCode(200)
        .body(containsString("PRINCIPIANTE_FEM"));
    }
}
