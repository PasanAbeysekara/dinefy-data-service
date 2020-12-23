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
public class PropertyMediaWrapper
{

	private List<PropMediaModel> bannerImages;
	private List<PropMediaModel> coverImages;
	private List<PropMediaModel> otherMedia;

	public void processPropertyMedia( Set<PropMedia> propMedia )
	{

		bannerImages = new ArrayList<>();
		coverImages = new ArrayList<>();
		otherMedia = new ArrayList<>();

		propMedia.forEach( i -> setPropMediaModel( i ) );
	}

	private void setPropMediaModel( PropMedia propMedia )
	{
		PropMediaModel propMediaModel = new PropMediaModel();

		propMediaModel.setMediaUrl( propMedia.getMediaUrl() );
		propMediaModel.setMediaType( propMedia.getType() );
		propMediaModel.setTitle( propMedia.getTitle() );
		propMediaModel.setThumbnail( propMedia.getThumbnail() );

		switch( propMedia.getCategory() )
		{
			case "banner":
				bannerImages.add( propMediaModel );
				break;

			case "cover":
				coverImages.add( propMediaModel );
				break;

			case "other":
				otherMedia.add( propMediaModel );
				break;

			default:
				break;
		}
	}
}
