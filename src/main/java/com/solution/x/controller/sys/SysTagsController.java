package com.solution.x.controller.sys;

import com.solution.x.controller.HngoutAbstractController;
import com.solution.x.dao.sys.Tags;
import com.solution.x.repo.sys.TagsRepository;
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
public class SysTagsController extends HngoutAbstractController<Tags>
{
	@Autowired
	private TagsRepository tagsRepository;

	@GetMapping("/tags")
	public ResponseEntity<List<Tags>> getTags()
	{
		return ResponseEntity.ok()
				.headers( addCommonHeaders( new HttpHeaders() ) )
				.body( tagsRepository.findAll() );
	}

	/**
	 * Get Single Facility
	 *
	 * @param id Facility ID
	 * @return The Facility
	 */
	@GetMapping("/tags/{id}")
	public ResponseEntity<Tags> getTag( @PathVariable("id") int id )
	{
		Optional<Tags> optionalTags = tagsRepository.findById( id );

		return optionalTags.map( tag -> ResponseEntity.ok()
				.headers( addCommonHeaders( new HttpHeaders() ) )
				.body( tag ) ).orElseGet( this::buildNotFoundResponse );
	}
}
