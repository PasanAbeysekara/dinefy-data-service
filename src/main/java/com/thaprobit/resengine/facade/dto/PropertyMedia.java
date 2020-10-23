package com.thaprobit.resengine.facade.dto;

import com.thaprobit.resengine.dao.PropMedia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyMedia
{

	private List<String> bannerImages;
	private List<String> coverImages;
	private List<String> otherImages;

	public void processPropertyMedia( Set<PropMedia> propMedia )
	{

		bannerImages = new ArrayList<>();
		coverImages = new ArrayList<>();
		otherImages = new ArrayList<>();

		propMedia.forEach( i -> addUrl( i.getCategory(), i.getURL() ) );
	}

	private void addUrl( String category, String url )
	{

		switch( category )
		{
			case "banner":
				bannerImages.add( url );
				break;

			case "cover":
				coverImages.add( url );
				break;

			case "other":
				otherImages.add( url );
				break;

			default:
				break;
		}
	}
}
