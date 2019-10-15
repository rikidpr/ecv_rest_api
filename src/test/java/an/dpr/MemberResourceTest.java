package an.dpr;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;

@QuarkusTest
public class MemberResourceTest {

    @Test
    public void testGetMemberEndpoint() {
        given().when().get("/member").then().statusCode(200);
    }
    
}