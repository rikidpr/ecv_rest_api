package an.dpr.ecv.resources.dto;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import an.dpr.ecv.entities.Member;

@ApplicationScoped
public class MemberMapper {

    private Mapper mapper;
    
    @PostConstruct
    void init() {
        mapper = DozerBeanMapperBuilder.buildDefault();
    }
    
    public Member map(MemberDTO dto)  {
        return mapper.map(dto, Member.class);
    }

    public MemberDTO map(Member member) {
        return mapper.map(member, MemberDTO.class);
    }

	public List<MemberDTO> map(List<Member> members) {
        List<MemberDTO> dtos = new ArrayList<MemberDTO>();
        mapper.map(members, dtos);
        return dtos;
	}
}