package an.dpr.ecv.resources.dto;

import java.time.LocalDate;
import java.util.List;

import an.dpr.ecv.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MemberDTO {

	private Integer id;
	private String code;
	private String name;
	private Category category;
	private LocalDate entryDate;
	private LocalDate leavingDate;
	private String info;
	private List<ParticipantDTO> participants;
	

}
