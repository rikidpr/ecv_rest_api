package an.dpr.ecv.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
@Entity
public class Member {

	private String id;
	private String name;
	private Category category;
	private LocalDate entryDate;
	private LocalDate leavingDate;
	private String info;
	

	/**
	 * Test method to create instances based on id
	 * 
	 * @param id
	 * @return
	 */
	public static Member getInstance(String id) {
		return Member.builder().id(id).name("socio" + id).category(Category.random()).build();
	}
}
