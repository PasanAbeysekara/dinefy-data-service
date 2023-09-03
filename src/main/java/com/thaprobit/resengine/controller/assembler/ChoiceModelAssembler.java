package com.thaprobit.resengine.controller.assembler;

import com.thaprobit.resengine.controller.service.HATEOASProvider;
import com.thaprobit.resengine.controller.sys.SysChoiceController;
import com.thaprobit.resengine.dao.sys.Choices;
import com.thaprobit.resengine.facade.dto.ChoiceModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * @author Tharindu Aththanayaka
 */
@Component
public class ChoiceModelAssembler extends RepresentationModelAssemblerSupport<Choices, ChoiceModel> {
    public ChoiceModelAssembler() {
        super(SysChoiceController.class, ChoiceModel.class);
    }

    @Override
    public ChoiceModel toModel(Choices entity) {
        ChoiceModel choiceModel = new ChoiceModel();
        choiceModel.setChoiceId(entity.getChoiceId());
        choiceModel.setName(entity.getName());
        choiceModel.setDescription(entity.getDescription());

        choiceModel.add(HATEOASProvider.sysChoicesSelfLinkProvider(entity.getChoiceId()));

        return choiceModel;
    }

    @Override
    public CollectionModel<ChoiceModel> toCollectionModel(Iterable<? extends Choices> entities) {
        CollectionModel<ChoiceModel> choicesModels = super.toCollectionModel(entities);

        choicesModels.add(HATEOASProvider.sysChoicesSelfLinkProvider(1));

        return choicesModels;
    }
}
