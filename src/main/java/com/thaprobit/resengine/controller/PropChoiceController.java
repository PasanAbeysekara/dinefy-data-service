package com.thaprobit.resengine.controller;

import com.thaprobit.resengine.dao.Organization;
import com.thaprobit.resengine.dao.PropChoices;
import com.thaprobit.resengine.dao.key.PropChoiceID;
import com.thaprobit.resengine.repo.PropChoicesRepository;
import com.thaprobit.service.AbstractService;
import com.thaprobit.util.URLProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(URLProvider.SERVICE_DATA)
public class PropChoiceController extends AbstractService<PropChoices> {

    @Autowired
    private PropChoicesRepository propChoicesRepository;

    @GetMapping("/prop-choices")
    public ResponseEntity<List<PropChoices>> getProperty() {
        List<PropChoices> orgList = propChoicesRepository.findAll();

        ResponseEntity<List<PropChoices>> responseEntity = null;
        if (orgList.isEmpty()) {
            responseEntity = ResponseEntity.notFound().headers(addCommonHeaders(new HttpHeaders())).build();
        } else {
            responseEntity = ResponseEntity.ok().headers(addCommonHeaders(new HttpHeaders())).body(orgList);
        }

        return responseEntity;
    }

    @GetMapping("/prop-choices/{propId}/{choiceId}")
    public ResponseEntity<PropChoices> getPropChoiceById(@PathVariable("propId") Integer propId, @PathVariable("choiceId") Long choiceId) {
        PropChoiceID propChoiceId = new PropChoiceID(propId, choiceId);
        return propChoicesRepository.findByPropChoiceId(propChoiceId)
                .map(propChoice -> ResponseEntity.ok().headers(addCommonHeaders(new HttpHeaders())).body(propChoice))
                .orElseGet(() -> ResponseEntity.notFound().headers(addCommonHeaders(new HttpHeaders())).build());
    }

//    @GetMapping("/prop-choices/by-property/{propId}")
//    public ResponseEntity<List<PropChoices>> getChoicesByProperty(@PathVariable("propId") Long propId) {
//        List<PropChoices> choicesList = propChoicesRepository.findByPropertyId(propId);
//
//        if (choicesList.isEmpty()) {
//            return ResponseEntity.notFound().headers(addCommonHeaders(new HttpHeaders())).build();
//        } else {
//            return ResponseEntity.ok().headers(addCommonHeaders(new HttpHeaders())).body(choicesList);
//        }
//    }

    @GetMapping("/prop-choices/by-property/{propId}")
    public ResponseEntity<List<EntityModel<PropChoices>>> getChoicesByProperty(@PathVariable("propId") Long propId) {
        List<PropChoices> choicesList = propChoicesRepository.findByPropertyId(propId);
        if (choicesList.isEmpty()) {
            return ResponseEntity.notFound().headers(addCommonHeaders(new HttpHeaders())).build();
        } else {
            List<EntityModel<PropChoices>> resources = choicesList.stream()
                    .map(propChoices -> EntityModel.of(propChoices,
                            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PropChoiceController.class)
                                    .getChoicesByProperty(propId)).withSelfRel()))
                    .collect(Collectors.toList());

            return ResponseEntity.ok().headers(addCommonHeaders(new HttpHeaders())).body(resources);
        }
    }



}
