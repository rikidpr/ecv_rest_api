package an.dpr.ecv.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;

import java.time.LocalDate;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import an.dpr.ecv.model.Category;
import an.dpr.ecv.resources.dto.MemberDTO;
import an.dpr.ecv.resources.dto.MemberDTO.MemberDTOBuilder;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
@TestMethodOrder(OrderAnnotation.class)
public class MemberResourceTest {
    
    @Test
    @Order(10)
    public void testPostMemberEndpoint() {
		given()
        .contentType(ContentType.JSON)
        .body(getPayload(null, Category.PROMESA_FEM))
        .post("/member")
        .then()
        .statusCode(200);
    }

    @Test
    @Order(15)
    public void testPostMemberEndpointShould409() {
		given()
        .contentType(ContentType.JSON)
        .body(getPayload(1, Category.PROMESA_FEM))
        .post("/member")
        .then()
        .statusCode(409);
    }

    private String getPayload(Integer id, Category category) {
        MemberDTOBuilder builder = MemberDTO.builder().category(category)
        .code("code").entryDate(LocalDate.now()).info("info")
        .name("name");
        if (id != null) {
            builder = builder.id(id);
        }
        Jsonb jsonb = JsonbBuilder.create();
        return jsonb.toJson(builder.build());
    }
    
    @Test
    @Order(20)
    public void testGetMemberEndpoint() {
        given().when().get("/member").then().statusCode(200)
        .body("category", hasItem(Category.PROMESA_FEM.toString()));
    }
    
    @Test
    @Order(30)
    public void testPutMemberEndpoint() {
		given()
        .contentType(ContentType.JSON)
        .body(getPayload(1, Category.PRINCIPIANTE_FEM))
        .put("/member")
        .then()
        .assertThat()
        .statusCode(200);
    }

    @Test
    @Order(35)
    public void testPutMemberEndpointShould404() {
		given()
        .contentType(ContentType.JSON)
        .body(getPayload(null, Category.PRINCIPIANTE_FEM))
        .put("/member")
        .then()
        .assertThat()
        .statusCode(404);
    }
    
    @Test
    @Order(40)
    public void testGetMemberEndpointAfterPut() {
        given().when().get("/member").then().statusCode(200)
        .body("category", hasItem(Category.PRINCIPIANTE_FEM.toString()));
    }
}
