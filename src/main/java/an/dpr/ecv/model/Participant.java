package an.dpr.ecv.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Participant {
	
	private Member rider;
	private List<Ranking> ranking;
	private Integer startPosition;
}
