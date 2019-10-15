package an.dpr.ecv.resources.dto;

import java.time.LocalDate;
import java.util.List;

import an.dpr.ecv.model.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityDTO {

	private Long id;
	private LocalDate date;
	private String location;
	private String organizer;
	private List<ParticipantDTO> participants;
	private List<SubActivityDTO> subevents;
	private ActivityType type;
	
}