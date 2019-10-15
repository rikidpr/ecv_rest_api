package an.dpr.ecv.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Participant extends PanacheEntity {

	@ManyToOne(fetch=FetchType.LAZY)
	public Member rider;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="activity_id")
	public Activity activity;
	@OneToMany(mappedBy = "participant")
	public List<Ranking> ranking;
	public Integer startPosition;
	
	public static List<Participant> getActivityParticipants(Activity activity) {
		return list("activity", activity);
	}
}
