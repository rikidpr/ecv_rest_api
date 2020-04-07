package an.dpr.ecv.services;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.test.Mock;

/**
 * MemberServiceMock
 */
@Mock
@ApplicationScoped 
public class MemberServiceMock extends MemberService{

    @Override
    public boolean existsMember(Integer id) {
        return true;
    }
    
}