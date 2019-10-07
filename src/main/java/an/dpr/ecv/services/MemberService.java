package an.dpr.ecv.services;

import java.time.LocalDate;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import an.dpr.ecv.model.Category;
import an.dpr.ecv.model.Member;

@RequestScoped
public class MemberService {
	
	@PersistenceContext(name = "ecv-jpa-unit")
	private EntityManager entityManager;
	private Logger log = LogManager.getLogger(MemberService.class);

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
		log.info("find members jpa criteria implementation");
		try{
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Member> criteriaQuery = cb.createQuery(Member.class);
			Root<Member> m = criteriaQuery.from(Member.class);
			criteriaQuery.select(m);
			TypedQuery<Member> query = entityManager.createQuery(criteriaQuery);
			return query.getResultList();
		} catch(Exception e) {
			e.printStackTrace();
			log.error(e.getCause());
			return List.of(getMember("1"), getMember("2"));
		}
//		log.info("find members mock implementation");
	}
}
