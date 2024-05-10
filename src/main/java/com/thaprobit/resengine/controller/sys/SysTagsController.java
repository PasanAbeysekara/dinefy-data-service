package com.thaprobit.resengine.controller.sys;

import com.thaprobit.resengine.controller.service.sys.SysTagsService;
import com.thaprobit.resengine.dao.sys.Tags;
import com.thaprobit.resengine.facade.dto.TagsModel;
import com.thaprobit.util.ResponseWrapper;
import com.thaprobit.util.URLProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tharinda Wickramaarachchi
 */
@RestController
@RequestMapping(URLProvider.SERVICE_DATA)
@Slf4j
public class SysTagsController {
    @Autowired
    private SysTagsService sysTagsService;


    public SysTagsController(SysTagsService sysTagsService) {
        this.sysTagsService = sysTagsService;
    }

    /**
     * Get All Tags
     *
     * @param pageable Pageable
     * @return all sys Tags
     */
    @GetMapping("/tags")
    public ResponseEntity<ResponseWrapper<PagedModel<TagsModel>>> getTags(Pageable pageable) {
        return sysTagsService.getTags(pageable);
    }

    /**
     * Get Single Tag
     *
     * @param id Tag ID
     * @return The Tag
     */
    @GetMapping("/tags/{id}")
    public ResponseEntity<ResponseWrapper<Tags>> getTag(@PathVariable("id") int id) {
        return sysTagsService.getTag(id);
    }

    /**
     * Create a Tag
     *
     * @param tag The Tag
     * @return Saved Tag response
     */
    @PostMapping("/tags")
    public ResponseEntity<ResponseWrapper<Tags>> createTag(@RequestBody Tags tag) {
        return sysTagsService.createTag(tag);
    }


    /**
     * Update a Tag
     *
     * @param id   The Tag ID
     * @param tags The Tag
     * @return Updated Tag response
     */
    @PutMapping("/tags/{id}")
    public ResponseEntity<ResponseWrapper<Tags>> updateTag(@PathVariable("id") int id, @RequestBody Tags tags) {
        return sysTagsService.updateTag(id, tags);
    }

    /**
     * Delete a Tag
     *
     * @param id The Tag ID
     * @return Delete response
     */
    @DeleteMapping("/tags/{id}")
    public ResponseEntity<ResponseWrapper<Tags>> deleteTag(@PathVariable("id") int id) {
        return sysTagsService.deleteTag(id);
    }


}
