package an.dpr.ecv.services;

import java.util.List;

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

@ApplicationScoped
public class MemberService {
	
	private static final Logger log = LoggerFactory.getLogger(MemberService.class);
	@Inject
    EntityManager entityManager; 

	@Transactional
	public Member getMember(final Integer id) {
		try{
			return entityManager.find(Member.class, id);
		} catch(final Exception e) {
			log.error(e.getMessage(), e.getCause());
			return null;
		}
	}
	
	public boolean existsMember(final Integer id) {
		return getMember(id) != null;
	}
	
	@Transactional
	public void saveMember(final Member param) {
		final Member member;
		if (param.getId() != null) {
			member = getMember(param.getId());
			member.setCategory(param.getCategory());
			member.setCode(param.getCode());
			member.setEntryDate(param.getEntryDate());
			member.setInfo(param.getInfo());
			member.setLeavingDate(param.getLeavingDate());
			member.setName(param.getName());
			// member.setParticipants(participants);
		} else { 
			member = param;
		}
		entityManager.persist(member);
	}
	
	@Transactional
	public boolean deleteMember(final Integer id) {
		throw new UnsupportedOperationException("not implemented yet");
	}
	
	@Transactional
	public List<Member> findMembers() {
		try{
			final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			final CriteriaQuery<Member> criteriaQuery = cb.createQuery(Member.class);
			final Root<Member> m = criteriaQuery.from(Member.class);
			criteriaQuery.select(m);
			final TypedQuery<Member> query = entityManager.createQuery(criteriaQuery);
			return query.getResultList();
		} catch(final Exception e) {
			log.error(e.getMessage(), e.getCause());
			return null;
		}
    }
    
}
