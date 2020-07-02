package com.solution.x.data.controller.sys;

import com.solution.x.dao.sys.WeekDefinition;
import com.solution.x.data.controller.service.AbstractService;
import com.solution.x.repo.sys.WeekDefinitionRepository;
import com.solution.x.util.URLProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author Tharinda Wickramaarachchi
 */
@RestController
@RequestMapping(URLProvider.SERVICE_DATA)
public class WeekDefinitionController extends AbstractService<WeekDefinition>
{
	@Autowired
	private WeekDefinitionRepository weekDefinitionRepository;

	@GetMapping("/week-definitions")
	public ResponseEntity<List<WeekDefinition>> getWeekDefinition()
	{
		return ResponseEntity.ok()
				.headers( addCommonHeaders( new HttpHeaders() ) )
				.body( weekDefinitionRepository.findAll() );
	}

	/**
	 * Get Single WeekDefinition
	 *
	 * @param id WeekDefinition ID
	 * @return The WeekDefinition
	 */
	@GetMapping("/week-definitions/{id}")
	public ResponseEntity<WeekDefinition> getTag( @PathVariable("id") Short id )
	{
		Optional<WeekDefinition> optionalTags = weekDefinitionRepository.findById( id );

		return optionalTags.map( wd -> ResponseEntity.ok()
				.headers( addCommonHeaders( new HttpHeaders() ) )
				.body( wd ) ).orElseGet( this::buildNotFoundResponse );
	}
}
