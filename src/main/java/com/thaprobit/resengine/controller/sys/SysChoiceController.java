package com.thaprobit.resengine.controller.sys;

import com.thaprobit.resengine.controller.service.sys.SysChoiceService;
import com.thaprobit.resengine.dao.sys.Choices;
import com.thaprobit.resengine.facade.dto.ChoiceModel;
import com.thaprobit.util.ResponseWrapper;
import com.thaprobit.util.URLProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tharindu Aththanayake
 */
@RestController
@RequestMapping(URLProvider.SERVICE_DATA)
@Slf4j
public class SysChoiceController {
    @Autowired
    private SysChoiceService choiceService;

    /**
     * Get All Choices
     *
     * @return all sys choices
     */
    @GetMapping("/choices")
    public ResponseEntity<ResponseWrapper<PagedModel<ChoiceModel>>> getChoices(Pageable pageable) {
        return choiceService.getChoices(pageable);
    }

    /**
     * Get Single Choice
     *
     * @param id Choice ID
     * @return The Choice
     */
    @GetMapping("/choices/{id}")
    public ResponseEntity<ResponseWrapper<Choices>> getChoice(@PathVariable("id") int id) {
        return choiceService.getChoice(id);
    }

    /**
     * Create a Choice
     *
     * @param choice The Choice
     * @return Saved Choice response
     */
    @PostMapping("/choices")
    public ResponseEntity<ResponseWrapper<Choices>> createChoice(@RequestBody Choices choice) {
        return choiceService.createChoice(choice);
    }

    /**
     * Update a Choice
     *
     * @param id     The Choice ID
     * @param choice The Choice
     * @return Updated Choice response
     */
    @PutMapping("/choices/{id}")
    public ResponseEntity<ResponseWrapper<Choices>> updateChoice(@PathVariable("id") int id, @RequestBody Choices choice) {
        return choiceService.updateChoice(id, choice);
    }

    /**
     * Delete a Choice
     *
     * @param id The Choice ID
     * @return Delete Choice Response
     */
    @DeleteMapping("/choices/{id}")
    public ResponseEntity<ResponseWrapper<Choices>> deleteChoice(@PathVariable("id") int id) {
        return choiceService.deleteChoice(id);
    }
}
