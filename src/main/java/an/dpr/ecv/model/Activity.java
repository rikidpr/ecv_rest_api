package an.dpr.ecv.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Data;

@Entity
@Data
public class Activity extends PanacheEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	public LocalDate date;
	public String location;
	public String organizer;
//	public List<Participant> participants;
//	public List<SubActivity> subevents;
	public ActivityType type;
	
	public static List<Activity> findByLocation(String location) {
		return list("location", location);
	}
}