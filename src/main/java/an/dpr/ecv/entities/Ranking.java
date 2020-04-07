package an.dpr.ecv.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import an.dpr.ecv.model.SubActivityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ranking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	@ManyToOne(fetch=FetchType.LAZY)
	public Participant participant;
	public Integer position;
	public SubActivityType type;
}
