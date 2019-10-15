package an.dpr.ecv.resources.dto;

import an.dpr.ecv.model.SubActivityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RankingDTO {

	private Long id;
	private ParticipantDTO participant;
	private Integer position;
	private SubActivityType type;
}
