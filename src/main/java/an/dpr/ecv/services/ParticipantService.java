package an.dpr.ecv.services;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import an.dpr.ecv.entities.Activity;
import an.dpr.ecv.entities.Member;
import an.dpr.ecv.entities.Participant;
import an.dpr.ecv.resources.dto.ParticipantDTO;
import an.dpr.exception.EcvException;

@RequestScoped
public class ParticipantService {

	private static Logger log = LoggerFactory.getLogger(ParticipantService.class);
	@Inject
	ActivityService activityService;
	@Inject
	MemberService memberService;

	public void persist(Participant participantParam) throws EcvException {
		try {
			Participant participant;
			if (participantParam.id != null) {
				participant = Participant.findById(participantParam.id);
				participant.setActivity(Activity.findById(participantParam.activity.id));
				participant.setRider(memberService.getMember(participantParam.rider.getId()));
				participant.setStartPosition(participantParam.startPosition);
			} else {
				participant = participantParam;
			}
			// participant.setActivity(activityService.findById(participant.activity.id));
			participant.persist();
		} catch (NullPointerException e) {
			log.debug("activity is null", e);
			throw new EcvException("Parameters are not valid");
		}
	}

	public List<Participant> listAll() {
		List<Participant> list =  Participant.listAll();
		return list;
	}
	
	@Transactional
	public void create(ParticipantDTO participantDTO) throws EcvException {
		Participant participant = Participant.builder()
				.rider(Member.builder().id(participantDTO.getRider().getId()).build())
				.startPosition(participantDTO.getStartPosition())
				.activity(new Activity.Builder().id(participantDTO.getActivity().getId()).build()).build();
		persist(participant);
	}

}
