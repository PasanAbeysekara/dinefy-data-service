package com.thaprobit.resengine.controller.assembler;

import com.thaprobit.resengine.controller.service.HATEOASProvider;
import com.thaprobit.resengine.controller.sys.SysTagsController;
import com.thaprobit.resengine.dao.sys.Tags;
import com.thaprobit.resengine.facade.dto.TagsModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * @author Tharinda Wickramaarachchi
 * @since 5/4/2020 1:27 AM
 */
@Component
public class TagsModelAssembler extends RepresentationModelAssemblerSupport<Tags, TagsModel>
{

	public TagsModelAssembler()
	{
		super( SysTagsController.class, TagsModel.class );
	}

	@Override
	public TagsModel toModel( Tags entity )
	{
		TagsModel tagModel = new TagsModel();
		tagModel.setTagId( entity.getTagId() );
		tagModel.setCode( entity.getCode() );
		tagModel.setName( entity.getName() );
		tagModel.setIcon( entity.getIcon() );
		tagModel.setDescription( entity.getDescription() );

		tagModel.add( HATEOASProvider.sysFacilitySelfLinkProvider( tagModel.getTagId() ) );

		return tagModel;
	}

	@Override
	public CollectionModel<TagsModel> toCollectionModel( Iterable<? extends Tags> entities )
	{
		CollectionModel<TagsModel> facilitiesModel = super.toCollectionModel( entities );

		facilitiesModel.add( HATEOASProvider.sysFacilitySelfLinkProvider( 10 ) ); // TODO change

		return facilitiesModel;
	}
}
