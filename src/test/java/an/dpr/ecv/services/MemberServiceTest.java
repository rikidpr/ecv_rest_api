package an.dpr.ecv.services;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import io.quarkus.test.Mock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class MemberServiceTest {

    @Mock
    MemberService memberService;

    @Test
    public void existShouldReturnTrue() {
        boolean condition = memberService.existsMember(1);
        assertTrue(condition, "Member 1 should exist");
    }
}