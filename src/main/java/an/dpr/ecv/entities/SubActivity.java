package an.dpr.ecv.entities;

import an.dpr.ecv.model.SubActivityType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubActivity {
	private SubActivityType type;
	private String text;//for classifications or whatever
}
