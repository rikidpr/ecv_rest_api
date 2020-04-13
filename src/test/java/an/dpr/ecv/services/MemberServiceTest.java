package an.dpr.ecv.services;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class MemberServiceTest {

    @Inject
    MemberService memberService;

    @Test
    public void existShouldReturnTrue() {
        boolean condition = memberService.existsMember(1);
        assertTrue(condition, "Member 1 should exist");
    }

}