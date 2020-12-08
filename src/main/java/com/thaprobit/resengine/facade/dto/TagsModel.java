package com.thaprobit.resengine.facade.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

/**
 * @author Tharinda Wickramaarachchi
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TagsModel extends RepresentationModel<TagsModel>
{
	private int tagId;
	private String code;
	private String name;
	private String icon;
	private String description;
}
