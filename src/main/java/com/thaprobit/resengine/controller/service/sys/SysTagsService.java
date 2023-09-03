package com.thaprobit.resengine.controller.service.sys;

import com.thaprobit.global.SystemOperation;
import com.thaprobit.resengine.controller.assembler.TagsModelAssembler;
import com.thaprobit.resengine.controller.service.HATEOASProvider;
import com.thaprobit.resengine.dao.sys.Tags;
import com.thaprobit.resengine.facade.dto.TagsModel;
import com.thaprobit.resengine.repo.sys.TagsRepository;
import com.thaprobit.service.AbstractService;
import com.thaprobit.util.ResponseWrapper;
import com.thaprobit.util.SystemMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Tharinda Wickramaarachchi
 */
@Service
@Slf4j
public class SysTagsService extends AbstractService<Tags> {
    @Autowired
    private TagsRepository tagsRepository;

    @Autowired
    private PagedResourcesAssembler<Tags> pagedResourcesAssembler;

    @Autowired
    private TagsModelAssembler tagsModelAssembler;

    /**
     * Get All Tags
     *
     * @param pageable Pageable
     * @return all sys Tags
     */
    public ResponseEntity<ResponseWrapper<PagedModel<TagsModel>>> getTags(Pageable pageable) {
        Page<Tags> tagsPaged = tagsRepository.findAll(pageable);
        PagedModel<TagsModel> collModel = pagedResourcesAssembler.toModel(tagsPaged, tagsModelAssembler);

        return ResponseEntity.ok()
                .headers(addCommonHeaders(new HttpHeaders()))
                .body(new ResponseWrapper<>(SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, collModel));
    }

    /**
     * Get Single Tag
     *
     * @param id Tag ID
     * @return The Tag
     */
    public ResponseEntity<ResponseWrapper<Tags>> getTag(int id) {
        Optional<Tags> optionalTags = tagsRepository.findById(id);

        ResponseEntity<ResponseWrapper<Tags>> response;

        if (optionalTags.isPresent()) {
            Tags tags = optionalTags.get();
            Link selfRel = HATEOASProvider.sysTagsSelfLinkProvider(tags.getTagId());
            tags.add(selfRel);

            response = ResponseEntity.ok()
                    .headers(addCommonHeaders(new HttpHeaders()))
                    .body(new ResponseWrapper<>(SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, tags));
        } else {
            response = buildNotFoundResponseWrapped();
        }

        return response;

    }

    /**
     * Create a Tag
     *
     * @param tag The Tag
     * @return Saved Tag response
     */
    public ResponseEntity<ResponseWrapper<Tags>> createTag(Tags tag) {
        ResponseEntity<ResponseWrapper<Tags>> response;

        try {
            Tags savedTag = tagsRepository.save(tag);

            Link selfRel = HATEOASProvider.sysTagsSelfLinkProvider(savedTag.getTagId());
            savedTag.add(selfRel);

            response = ResponseEntity.status(HttpStatus.CREATED)
                    .headers(addCommonHeaders(new HttpHeaders()))
                    .body(new ResponseWrapper<>(SystemOperation.CREATE.withSuccess(), SystemMessages.TAG_CREATE_SUCCESS, savedTag));
        } catch (Exception e) {
            e.printStackTrace();
            response = buildExceptionErrorResponse(SystemOperation.CREATE, SystemMessages.TAG_CREATE_FAILED, e);
        }

        return response;
    }


    /**
     * Update a Tag
     *
     * @param id   The Tag ID
     * @param tags The Tag
     * @return Updated Tag response
     */
    public ResponseEntity<ResponseWrapper<Tags>> updateTag(int id, Tags tags) {
        ResponseEntity<ResponseWrapper<Tags>> response;

        try {
            tags.setTagId(id);
            Tags savedTag = tagsRepository.save(tags);

            Link selfRel = HATEOASProvider.sysTagsSelfLinkProvider(savedTag.getTagId());
            savedTag.add(selfRel);

            response = ResponseEntity.ok()
                    .headers(addCommonHeaders(new HttpHeaders()))
                    .body(new ResponseWrapper<>(SystemOperation.MODIFY.withSuccess(), SystemMessages.TAG_UPDATE_SUCCESS, savedTag));
        } catch (Exception e) {
            e.printStackTrace();
            response = buildExceptionErrorResponse(SystemOperation.MODIFY, SystemMessages.TAG_UPDATE_FAILED, e);
        }

        return response;
    }

    /**
     * Delete a Tag
     *
     * @param id The Tag ID
     * @return Delete response
     */
    public ResponseEntity<ResponseWrapper<Tags>> deleteTag(int id) {
        ResponseEntity<ResponseWrapper<Tags>> response;

        try {
            tagsRepository.deleteById(id);

            response = ResponseEntity.ok()
                    .headers(addCommonHeaders(new HttpHeaders()))
                    .body(new ResponseWrapper<>(SystemOperation.DELETE.withSuccess(), SystemMessages.TAG_DELETE_SUCCESS, ""));
        } catch (Exception e) {
            e.printStackTrace();
            response = buildExceptionErrorResponse(SystemOperation.DELETE, SystemMessages.TAG_DELETE_FAILED, e);
        }

        return response;
    }


}
