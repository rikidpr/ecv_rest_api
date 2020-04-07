package an.dpr.ecv.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import an.dpr.ecv.model.ActivityType;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Activity extends PanacheEntity{

	public LocalDate date;
	public String location;
	public String organizer;
	@OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Participant> participants;
//	public List<SubActivity> subevents;
	public ActivityType type;
	
	public static List<Activity> findByLocation(String location) {
		return list("location", location);
	}

    public void addParticipant(Participant participant) {
        if (participants == null) participants = new ArrayList<Participant>();
        participant.setActivity(this);
        participants.add(participant);
	}
	
	public static class Builder {
		private Long id;
		private LocalDate date;
		private String location;
		private String organizer;
		private List<Participant> participants;
		private ActivityType type;

		public Builder id(Long id) {
			this.id = id;
			return this;
		}
		
		public Builder date(LocalDate date) {
			this.date = date;
			return this;
		}
		
		public Builder location(String location) {
			this.location = location;
			return this;
		}
		
		public Builder organizer(String organizer) {
			this.organizer = organizer;
			return this;
		}
		
		public Builder type(ActivityType type) {
			this.type = type;
			return this;
		}

		public Activity build() {
			Activity activity = new Activity();
			activity.id = id;
			activity.date = date;
			activity.location = location;
			activity.organizer = organizer;
			activity.type = type;
			return activity;
		}
	}
}