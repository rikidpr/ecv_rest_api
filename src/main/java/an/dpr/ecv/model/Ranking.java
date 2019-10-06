package an.dpr.ecv.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ranking {

	private Integer position;
	private SubActivityType type;
}
