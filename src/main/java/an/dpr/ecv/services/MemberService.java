package an.dpr.ecv.services;

import java.time.LocalDate;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import an.dpr.ecv.model.Category;
import an.dpr.ecv.model.Member;

@ApplicationScoped
public class MemberService {
	
	@PersistenceContext(name = "ecv-jpa-unit")
	private EntityManager entityManager;

	public Member getMember(String id) {
		return Member.builder().id(id).name("fulano").category(Category.ALEVIN)
				.info("member extra info, how telefphone, fahters name...")
				.entryDate(LocalDate.of(1980, 6, 7)).build();
	}
	
	public void saveMember(Member member) {
		entityManager.persist(member);
	}
	
	public boolean deleteMember(String id) {
		throw new UnsupportedOperationException("not implemented yet");
	}
	
	public List<Member> findMembers() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Member> criteriaQuery = cb.createQuery(Member.class);
		Root<Member> m = criteriaQuery.from(Member.class);
		criteriaQuery.select(m);
		TypedQuery<Member> query = entityManager.createQuery(criteriaQuery);
		return query.getResultList();
//		return List.of(getMember("1"), getMember("2"));
	}
}
