package com.solution.x.dao.sys;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.solution.x.dao.PropTags;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "sys_tags")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "tagId")
public class Tags extends RepresentationModel<Tags>
{
	@Id
	@SequenceGenerator(
			name = "tags_gen",
			sequenceName = "tags_tag_id_seq",
			initialValue = 100,
			allocationSize = 1
	)
	@GeneratedValue(generator = "tags_gen", strategy = GenerationType.SEQUENCE)
	@Column(name = "tag_id", updatable = false, nullable = false)
	private int tagId;

	@NotBlank
	@Size(max = 10, message = "Tag code cannot exceed 10 characters")
	@Column(name = "code", unique = true)
	private String code;

	@Size(max = 100, message = "Tag name cannot exceed 100 characters")
	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@JsonBackReference // Could not write JSON: Infinite recursion (StackOverflowError)
	@OneToMany(mappedBy = "sysTags", fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private Set<PropTags> propTags;
}
