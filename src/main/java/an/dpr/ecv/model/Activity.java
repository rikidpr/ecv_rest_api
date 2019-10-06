package an.dpr.ecv.model;

import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class Activity {

	private Integer id;
	private Date date;
	private String location;
	private String organizer;
	private List<Participant> participants;
	private List<SubActivity> subevents;
	private ActivityType type;
}
