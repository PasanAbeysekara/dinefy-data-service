package com.solution.hangouts.controller.sys;

import com.solution.hangouts.controller.HngoutAbstractController;
import com.solution.hangouts.dao.sys.WeekDefinition;
import com.solution.hangouts.repo.sys.WeekDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author Tharinda Wickramaarachchi
 */
@RestController
public class WeekDefinitionController extends HngoutAbstractController<WeekDefinition>
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
