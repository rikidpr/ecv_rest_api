package an.dpr.ecv.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;

import an.dpr.ecv.entities.Activity;
import an.dpr.ecv.model.ActivityType;
import an.dpr.ecv.resources.dto.ActivityDTO;

@RequestScoped
public class ActivityService {

	private void persist(Activity activity) {
		if (activity.id != null) {
			Activity actToPersist = Activity.findById(activity.id);
			if (actToPersist != null) {
				actToPersist.persist();
			}
		} else {
			activity.persist();
		}
	}

	public List<ActivityDTO> listAll() {
		return buildActivityDTOList(Activity.listAll());
	}

	public Optional<Activity> findById(Long id) {
		if (id == null)
			return Optional.empty();
		Activity activity = Activity.findById(id);
	// Activity activity = getMock(id);
//		activity.participants = new ArrayList<Participant>();
//		activity.participants
//				.add(Participant.builder().id(1l).startPosition(3).rider(Member.builder().name("vega").build())
//						.activity(Activity.builder().location("caspe").build()).build());
		return Optional.ofNullable(activity);
	}

	private Activity getMock(Long id) {
		return new Activity.Builder().id(id).date(LocalDate.now()).location("Valdesparetera")
		.organizer("ECV").type(ActivityType.CX).build();
	}
	
    public Optional<ActivityDTO> getActivityDTO(Optional<Activity> activityParam) {
		Activity activity = activityParam.orElse(new Activity());
		return Optional.of(ActivityDTO.builder().date(activity.date).location(activity.location)
				.organizer(activity.organizer).type(activity.type).id(activity.id).build());
	}

	public List<ActivityDTO> findByLocation(String location) {
		return buildActivityDTOList(Activity.findByLocation(location));
		
	}
	private List<ActivityDTO> buildActivityDTOList(List<Activity> list) {
		return list.stream()
				.map(activity -> ActivityDTO.builder().date(activity.date).location(activity.location)
						.organizer(activity.organizer).type(activity.type).id(activity.id).build())
				.collect(Collectors.toList());
	}

	@Transactional
	public void save(Activity activity) {
		persist(activity);
	}

	@Transactional
	public boolean delete(Long id) {
		return Activity.delete("id", id) > 0;
	}

	public Activity convertToActivity(ActivityDTO dto) {
		return new Activity.Builder().date(dto.getDate()).location(dto.getLocation())
				.organizer(dto.getOrganizer()).type(dto.getType()).id(dto.getId()).build();
	}
}
