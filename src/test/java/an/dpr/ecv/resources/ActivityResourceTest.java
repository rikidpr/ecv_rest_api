package an.dpr.ecv.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import java.time.LocalDate;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import an.dpr.ecv.model.ActivityType;
import an.dpr.ecv.resources.dto.ActivityDTO;
import an.dpr.ecv.resources.dto.ActivityDTO.ActivityDTOBuilder;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
@TestMethodOrder(OrderAnnotation.class)
public class ActivityResourceTest {

    private static final long ACTIVITY_ID = 2l;

    @Test
    @Order(10)
    public void postActivityTest() {
        given()
        .contentType(ContentType.JSON)
        .body(getPayload("ecv", null))
        .post("/activity")
        .then().assertThat()
        .statusCode(200);
    }

    @Test
    @Order(15)
    public void postActivityTestShould409() {
        given()
        .contentType(ContentType.JSON)
        .body(getPayload("ecv", ACTIVITY_ID))
        .post("/activity")
        .then().assertThat()
        .statusCode(409);
    }

    private String getPayload(String organizer, Long id) {
        ActivityDTOBuilder builder = ActivityDTO.builder()
        .date(LocalDate.now()).location("zgz")
        .organizer(organizer).type(ActivityType.CX);
        if (id != null)
            builder.id(id);
        ActivityDTO dto = builder.build();
        Jsonb jsonb = JsonbBuilder.create();
        return jsonb.toJson(dto);
    }

    @Test
    @Order(20)
    public void getActivityTest() {
        given()
        .contentType(ContentType.JSON)
        .get("/activity")
        .then().assertThat()
        .statusCode(200).body(containsString(getPayload("ecv",ACTIVITY_ID)));
    }
    
    @Test
    @Order(30)
    public void putActivityTest() {
        given()
        .contentType(ContentType.JSON)
        .body(getPayload("bomberos", ACTIVITY_ID))
        .put("/activity")
        .then().assertThat()
        .statusCode(200);
    }

    @Test
    @Order(40)
    public void getActivityTestPut() {
        given()
        .contentType(ContentType.JSON)
        .get("/activity")
        .then().assertThat()
        .statusCode(200).body(containsString(getPayload("bomberos",ACTIVITY_ID)));
    }
}