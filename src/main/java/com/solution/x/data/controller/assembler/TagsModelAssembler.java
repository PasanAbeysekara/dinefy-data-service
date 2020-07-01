package com.solution.x.data.controller.assembler;

import com.solution.x.dao.sys.Tags;
import com.solution.x.data.controller.sys.SysTagsController;
import com.solution.x.data.facade.dto.TagsModel;
import com.solution.x.data.util.HATEOASProvider;
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
