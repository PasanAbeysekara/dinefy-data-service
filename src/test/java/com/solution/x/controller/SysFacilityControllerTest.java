package com.solution.x.controller;

import com.solution.x.controller.sys.SysFacilityController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author Tharinda Wickramaarachchi
 * @since 5/2/2020 12:18 AM
 */
@RunWith(SpringRunner.class)
@WebMvcTest(SysFacilityController.class)
public class SysFacilityControllerTest
{
	@Autowired
	private MockMvc mvc;

	@MockBean
	private SysFacilityController controller;

	@Test
	public void createFacility() throws Exception
	{
		//		Facilities facilities1 = new Facilities( 1, "ABC1", "Name ABC1", "Description ABC1", null );
		//		Facilities facilities2 = new Facilities( 2, "ABC2", "Name ABC2", "Description ABC2", null );
		//
		//		List<Facilities> facilitiesList = Arrays.asList( facilities1, facilities2 );
		//
		//		ResponseEntity<ResponseWrapper<List<Facilities>>> responseEntity = ResponseEntity.ok().body( new ResponseWrapper<>( "OK", facilitiesList ) );
		//
		//		BDDMockito.given( controller.getFacilities() ).willReturn( responseEntity );
		//
		//		mvc.perform( get( "/facilities" )
		//				.contentType( MediaType.APPLICATION_JSON ) )
		//				.andExpect( status().isOk() )
		//				.andExpect( jsonPath( "$.code", Matchers.is( "OK" ) ) )
		//				.andExpect( jsonPath( "$.data", Matchers.hasSize( 2 ) ) )
		//				.andExpect( jsonPath( "$.data[0].code", Matchers.is( "ABC1" ) ) )
		//				.andExpect( jsonPath( "$.data[0].name", Matchers.is( "Name ABC1" ) ) )
		//				.andExpect( jsonPath( "$.data[0].description", Matchers.is( "Description ABC1" ) ) )
		//				.andExpect( jsonPath( "$.data[1].code", Matchers.is( "ABC2" ) ) )
		//				.andExpect( jsonPath( "$.data[1].name", Matchers.is( "Name ABC2" ) ) )
		//				.andExpect( jsonPath( "$.data[1].description", Matchers.is( "Description ABC2" ) ) );
	}
}
