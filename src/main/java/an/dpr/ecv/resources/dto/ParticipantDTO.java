package an.dpr.ecv.resources.dto;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantDTO extends PanacheEntity {

	private Long id;
	private MemberDTO rider;
	private ActivityDTO activity;
	private List<RankingDTO> ranking;
	private Integer startPosition;
	
}
