package com.thaprobit.resengine.controller.assembler;

import com.thaprobit.resengine.controller.service.HATEOASProvider;
import com.thaprobit.resengine.controller.sys.SysFacilityController;
import com.thaprobit.resengine.dao.sys.Facilities;
import com.thaprobit.resengine.facade.dto.FacilitiesModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * @author Tharinda Wickramaarachchi
 * @since 5/4/2020 1:27 AM
 */
@Component
public class FacilitiesModelAssembler extends RepresentationModelAssemblerSupport<Facilities, FacilitiesModel>
{

	public FacilitiesModelAssembler()
	{
		super( SysFacilityController.class, FacilitiesModel.class );
	}

	@Override
	public FacilitiesModel toModel( Facilities entity )
	{
		FacilitiesModel facilitiesModel = new FacilitiesModel();
		facilitiesModel.setFacilityId( entity.getFacilityId() );
		facilitiesModel.setCode( entity.getCode() );
		facilitiesModel.setName( entity.getName() );
		facilitiesModel.setDescription( entity.getDescription() );

		facilitiesModel.add( HATEOASProvider.sysFacilitySelfLinkProvider( facilitiesModel.getFacilityId() ) );

		return facilitiesModel;
	}

	@Override
	public CollectionModel<FacilitiesModel> toCollectionModel( Iterable<? extends Facilities> entities )
	{
		CollectionModel<FacilitiesModel> facilitiesModel = super.toCollectionModel( entities );

		facilitiesModel.add( HATEOASProvider.sysFacilitySelfLinkProvider( 10 ) ); // TODO change

		return facilitiesModel;
	}
}
