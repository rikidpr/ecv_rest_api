package an.dpr.ecv.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import an.dpr.ecv.entities.Member;
import an.dpr.ecv.resources.dto.MemberDTO;

@ApplicationScoped
public class MemberService {
	
	private static final Logger log = LoggerFactory.getLogger(MemberService.class);
	@Inject
    EntityManager entityManager; 

	@Transactional
	public Member getMember(Integer id) {
		try{
			return entityManager.find(Member.class, id);
		} catch(Exception e) {
			log.error(e.getMessage(), e.getCause());
			return null;
		}
	}
	
	public boolean existsMember(Integer id) {
		return getMember(id) != null;
	}
	
	@Transactional
	public void saveMember(Member member) {
		entityManager.persist(member);
	}
	
	@Transactional
	public boolean deleteMember(Integer id) {
		throw new UnsupportedOperationException("not implemented yet");
	}
	
	@Transactional
	public List<Member> findMembers() {
		try{
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Member> criteriaQuery = cb.createQuery(Member.class);
			Root<Member> m = criteriaQuery.from(Member.class);
			criteriaQuery.select(m);
			TypedQuery<Member> query = entityManager.createQuery(criteriaQuery);
			return query.getResultList();
		} catch(Exception e) {
			log.error(e.getMessage(), e.getCause());
			return null;
		}
    }
    
}
