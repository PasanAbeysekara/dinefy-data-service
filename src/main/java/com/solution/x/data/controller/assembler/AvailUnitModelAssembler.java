package com.solution.x.data.controller.assembler;

import com.solution.x.dao.sys.AvailabilityUnit;
import com.solution.x.data.controller.service.HATEOASProvider;
import com.solution.x.data.controller.sys.SysAvailabilityUnitController;
import com.solution.x.data.facade.dto.AvailabilityUnitModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * @author Tharinda Wickramaarachchi
 * @since 5/4/2020 1:27 AM
 */
@Component
public class
AvailUnitModelAssembler extends RepresentationModelAssemblerSupport<AvailabilityUnit, AvailabilityUnitModel>
{

	public AvailUnitModelAssembler()
	{
		super( SysAvailabilityUnitController.class, AvailabilityUnitModel.class );
	}

	@Override
	public AvailabilityUnitModel toModel( AvailabilityUnit entity )
	{
		AvailabilityUnitModel availabilityUnitModel = new AvailabilityUnitModel();
		availabilityUnitModel.setUnitId( entity.getUnitId() );
		availabilityUnitModel.setCode( entity.getCode() );
		availabilityUnitModel.setName( entity.getName() );
		availabilityUnitModel.setMinCapacity( entity.getMinCapacity() );
		availabilityUnitModel.setMaxCapacity( entity.getMaxCapacity() );

		availabilityUnitModel.add( HATEOASProvider.sysAvailabilityUnitSelfLinkProvider( availabilityUnitModel.getUnitId() ) );

		return availabilityUnitModel;
	}

	@Override
	public CollectionModel<AvailabilityUnitModel> toCollectionModel( Iterable<? extends AvailabilityUnit> entities )
	{
		CollectionModel<AvailabilityUnitModel> facilitiesModel = super.toCollectionModel( entities );

		facilitiesModel.add( HATEOASProvider.sysFacilitySelfLinkProvider( 10 ) ); // TODO change

		return facilitiesModel;
	}
}
