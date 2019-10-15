package an.dpr.ecv.resources.dto;

import an.dpr.ecv.model.SubActivityType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubActivityDTO {
	private SubActivityType type;
	private String text;//for classifications or whatever
}
