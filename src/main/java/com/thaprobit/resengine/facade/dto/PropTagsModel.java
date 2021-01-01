package com.thaprobit.resengine.facade.dto;

import com.thaprobit.resengine.dao.key.PropTagID;
import com.thaprobit.resengine.dao.sys.Tags;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Tharindu Aththanayake
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PropTagsModel
{
	private PropTagID propTagID;
	private String name;
	private String description;
	private int order;
	private TagsModel sysTags;
}
