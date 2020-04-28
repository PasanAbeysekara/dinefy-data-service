package com.solution.x.dao.sys;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.solution.x.dao.PropFacilities;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * @author Tharinda Wickramaarachchi
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sys_facilities")
public class Facilities extends RepresentationModel<Facilities>
{
	@Id
	@SequenceGenerator(
			name = "facilities_gen",
			sequenceName = "facilities_facility_id_seq",
			initialValue = 100
	)
	private int facility_id;

	@NotBlank
	@Size(max = 10)
	@Column(name = "code")
	private String code;

	@Size(max = 100)
	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@JsonBackReference // Could not write JSON: Infinite recursion (StackOverflowError)
	@OneToMany(mappedBy = "sysFacility", fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private Set<PropFacilities> propFacilities;

}
