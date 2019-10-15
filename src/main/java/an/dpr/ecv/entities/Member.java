package an.dpr.ecv.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
@Entity
/**
 * Panaché repository option
 */
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String code;
	private String name;
	private Category category;
	private LocalDate entryDate;
	private LocalDate leavingDate;
	private String info;
	@OneToMany(mappedBy = "rider", fetch=FetchType.EAGER)
	public List<Participant> participants;
	

}
