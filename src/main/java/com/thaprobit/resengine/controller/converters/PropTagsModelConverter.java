package com.thaprobit.resengine.controller.converters;

import com.thaprobit.resengine.controller.assembler.TagsModelAssembler;
import com.thaprobit.resengine.dao.PropTags;
import com.thaprobit.resengine.facade.dto.PropTagsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Tharindu Aththanayake
 * @since 01/01/2021 11:48 AM
 */
@Component
public class PropTagsModelConverter implements Converter<PropTags, PropTagsModel> {
    @Autowired
    private TagsModelAssembler tagsModelAssembler;

    @Override
    public PropTagsModel convert(PropTags propTags) {
        PropTagsModel propTagsModel = new PropTagsModel();
        propTagsModel.setPropTagID(propTags.getPropTagID());
        propTagsModel.setName(propTags.getName());
        propTagsModel.setOrder(propTags.getOrder());
        propTagsModel.setDescription(propTags.getDescription());

        if (propTags.getSysTags() != null) {
            propTagsModel.setSysTags(tagsModelAssembler.toModel(propTags.getSysTags()));
        }

        return propTagsModel;
    }
}
