package com.thaprobit.resengine.repo;

import com.thaprobit.resengine.dao.sys.Facilities;
import com.thaprobit.resengine.repo.sys.FacilitiesRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Tharinda Wickramaarachchi
 * @since 5/2/2020 2:37 PM
 */

@RunWith(SpringRunner.class)
@DataJpaTest(properties = {"classpath:application.properties"})
//@TestPropertySource( locations = "classpath:application-integrationtest.properties")
public class SysFacilityRepoTest
{
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private FacilitiesRepository facilitiesRepository;

	private Facilities facilities1;
	private Facilities facilities2;
	private Facilities facilities3;

	@Before
	public void before()
	{

		facilities1 = new Facilities( null, "ABC1", "Name ABC1", "Description ABC1", "http://images/1.jpg", null );
		facilities2 = new Facilities( null, "ABC2", "Name ABC2", "Description ABC2","http://images/1.jpg" ,null );
		facilities3 = new Facilities( null, "ABC2", "Name ABC2", "Description ABC2", "http://images/1.jpg",null );
	}

	@Test
	@Order(1)
	public void testDataIntegrity()
	{
		Facilities savedFacilities1 = facilitiesRepository.save( facilities1 );
		Facilities savedFacilities2 = facilitiesRepository.save( facilities2 );

		List<Facilities> all = facilitiesRepository.findAll();

		Assert.assertEquals( 2, all.size() );

		Assert.assertEquals( facilities1, savedFacilities1 );
		Assert.assertEquals( facilities2, savedFacilities2 );

		Facilities save = facilitiesRepository.save( facilities3 );

		assertThrows( DataIntegrityViolationException.class, () -> {
			facilitiesRepository.findAll();
		} );

	}


	@Test
	@Order(2)
	public void testRepoCreateAndRead()
	{
		Facilities savedFacilities = facilitiesRepository.save( facilities1 );

		Assert.assertEquals( facilities1, savedFacilities );

		Optional<Facilities> facilitiesOptional = facilitiesRepository.findById( facilities1.getFacilityId() );
		Assert.assertTrue( facilitiesOptional.isPresent() );

		Facilities searchedFacilities = facilitiesOptional.get();
		Assert.assertEquals( facilities1, searchedFacilities );

	}

	@Test
	@Order(3)
	public void testRepoSaveUpdateAndRead()
	{
		facilitiesRepository.save( facilities1 );

		String originalName = facilities1.getName();

		facilities1.setName( "Changed Name" );
		Facilities save = facilitiesRepository.save( facilities1 );

		Assert.assertNotEquals( originalName, save.getName() );
		Assert.assertEquals( "Changed Name", save.getName() );


		Optional<Facilities> facilitiesOptional = facilitiesRepository.findById( facilities1.getFacilityId() );
		Assert.assertTrue( facilitiesOptional.isPresent() );

		Facilities searchFacilities = facilitiesOptional.get();

		Assert.assertNotEquals( originalName, searchFacilities.getName() );
		Assert.assertEquals( "Changed Name", searchFacilities.getName() );

	}
}
