package an.dpr.ecv.services;

import java.time.LocalDate;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import an.dpr.ecv.model.Category;
import an.dpr.ecv.model.Member;

@ApplicationScoped
public class MemberService {

	public Member getMember(String id) {
		return Member.builder().id(id).name("fulano").category(Category.ALEVIN)
				.info("member extra info, how telefphone, fahters name...")
				.entryDate(LocalDate.of(1980, 6, 7)).build();
	}
	
	public void saveMember(Member member) {
		return;
//		throw nXew UnsupportedOperationException("not implemented yet");
	}
	
	public boolean deleteMember(String id) {
		throw new UnsupportedOperationException("not implemented yet");
	}
	
	public List<Member> findMembers() {
		return List.of(getMember("1"), getMember("2"));
	}
}
