package com.solution.x.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solution.x.controller.sys.SysFacilityController;
import com.solution.x.dao.sys.Facilities;
import com.solution.x.facade.ResponseWrapper;
import com.solution.x.facade.SystemMessages;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

	@Autowired
	private ObjectMapper objectMapper;

	private Facilities facility = new Facilities( null, "ABC1", "Name ABC1", "Description ABC1", null );

	@Before
	public void before()
	{

		Facilities facilities2 = new Facilities( 2, "ABC2", "Name ABC2", "Description ABC2", null );

		ResponseEntity<ResponseWrapper<Facilities>> responseEntity = ResponseEntity.ok().body( new ResponseWrapper<>( "OK", facility ) );

		BDDMockito.given( controller.getFacility( 1 ) ).willReturn( responseEntity );

		ResponseEntity<ResponseWrapper<Facilities>> response = ResponseEntity.status( HttpStatus.CREATED )
				.body( new ResponseWrapper<>( "CREATED", SystemMessages.FACILITY_CREATE_SUCCESS.getReasonPhrase(), facility ) );

		BDDMockito.given( controller.createFacility( facility ) ).willReturn( response );

		System.out.println( " Before Finished" );
	}

	@Test
	public void createFacility() throws Exception
	{
		mvc.perform( post( "/facilities" )
				.content( objectMapper.writeValueAsString( facility ) )
				.contentType( MediaType.APPLICATION_JSON ) )
				.andDo( print() )
				.andExpect( status().isCreated() )
				.andExpect( jsonPath( "$.code", Matchers.is( "CREATED" ) ) )
				.andExpect( jsonPath( "$.prettyMessage", Matchers.is( SystemMessages.FACILITY_CREATE_SUCCESS.getReasonPhrase() ) ) )
				.andExpect( jsonPath( "$.data.code", Matchers.is( facility.getCode() ) ) )
				.andExpect( jsonPath( "$.data.name", Matchers.is( facility.getName() ) ) )
				.andExpect( jsonPath( "$.data.description", Matchers.is( facility.getDescription() ) ) );
	}

	@Test
	public void getFacility() throws Exception
	{
		mvc.perform( get( "/facilities/1" )
				.contentType( MediaType.APPLICATION_JSON ) )
				.andDo( print() )
				.andExpect( status().isOk() )
				.andExpect( jsonPath( "$.code", Matchers.is( "OK" ) ) )
				.andExpect( jsonPath( "$.data.code", Matchers.is( facility.getCode() ) ) )
				.andExpect( jsonPath( "$.data.name", Matchers.is( facility.getName() ) ) )
				.andExpect( jsonPath( "$.data.description", Matchers.is( facility.getDescription() ) ) );
	}


}
