package com.solution.x.repo;

import com.solution.x.dao.sys.Facilities;
import com.solution.x.repo.sys.FacilitiesRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Tharinda Wickramaarachchi
 * @since 5/2/2020 2:37 PM
 */

@RunWith(SpringRunner.class)
@DataJpaTest(properties = {"classpath:application.properties"})
public class SysFacilityRepoTest
{
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private FacilitiesRepository facilitiesRepository;

	@Before
	public void before()
	{
	}

	@Test
	public void testRepo()
	{
		Facilities facilities1 = new Facilities( 1, "ABC1", "Name ABC1", "Description ABC1", null );
		Facilities facilities2 = new Facilities( 2, "ABC2", "Name ABC2", "Description ABC2", null );

		Facilities facilities3 = new Facilities( 3, "ABC2", "Name ABC2", "Description ABC2", null );

		Facilities savedFacilities1 = facilitiesRepository.save( facilities1 );
		Facilities savedFacilities2 = facilitiesRepository.save( facilities2 );

		Assert.assertEquals( facilities1, savedFacilities1 );
		Assert.assertEquals( facilities2, savedFacilities2 );

		facilitiesRepository.save( facilities3 );

		assertThrows( DataIntegrityViolationException.class, () -> {
			facilitiesRepository.findAll();
		} );

	}
}
