package com.solution.x.data.controller.sys;

import com.solution.x.dao.sys.Tags;
import com.solution.x.data.controller.service.sys.SysTagsService;
import com.solution.x.data.facade.dto.TagsModel;
import com.solution.x.data.util.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tharinda Wickramaarachchi
 */
@RestController
public class SysTagsController
{
	@Autowired
	private SysTagsService sysTagsService;

	/**
	 * Get All Tags
	 *
	 * @return all sys Tags
	 */
	@GetMapping("/tags")
	public ResponseEntity<ResponseWrapper<PagedModel<TagsModel>>> getTags( Pageable pageable )
	{
		return sysTagsService.getTags( pageable );
	}

	/**
	 * Get Single Tag
	 *
	 * @param id Tag ID
	 * @return The Tag
	 */
	@GetMapping("/tags/{id}")
	public ResponseEntity<ResponseWrapper<Tags>> getTag( @PathVariable("id") int id )
	{
		return sysTagsService.getTag( id );
	}

	/**
	 * Create a Tag
	 *
	 * @param tag The Tag
	 * @return Saved Tag response
	 */
	@PostMapping("/tags")
	public ResponseEntity<ResponseWrapper<Tags>> createTag( @RequestBody Tags tag )
	{
		return sysTagsService.createTag( tag );
	}


	/**
	 * Update a Tag
	 *
	 * @param id   The Tag ID
	 * @param tags The Tag
	 * @return Updated Tag response
	 */
	@PutMapping("/tags/{id}")
	public ResponseEntity<ResponseWrapper<Tags>> updateTag( @PathVariable("id") int id, @RequestBody Tags tags )
	{
		return sysTagsService.updateTag( id, tags );
	}

	/**
	 * Delete a Tag
	 *
	 * @param id The Tag ID
	 * @return Delete response
	 */
	@DeleteMapping("/tags/{id}")
	public ResponseEntity<ResponseWrapper<Tags>> deleteTag( @PathVariable("id") int id )
	{
		return sysTagsService.deleteTag( id );
	}


}
