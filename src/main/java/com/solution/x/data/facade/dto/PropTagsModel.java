package com.solution.x.data.facade.dto;

import com.solution.x.dao.key.PropTagID;
import com.solution.x.dao.sys.Tags;
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
	private Tags sysTags;
}
