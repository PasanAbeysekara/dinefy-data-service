package com.thaprobit.resengine.controller.assembler;

import com.thaprobit.resengine.controller.ContractController;
import com.thaprobit.resengine.controller.converters.SeasonModelConverter;
import com.thaprobit.resengine.controller.service.HATEOASProvider;
import com.thaprobit.resengine.dao.Contract;
import com.thaprobit.resengine.facade.dto.ContractModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

;

/**
 * @author Tharindu Aththanayake
 * @since 01/01/2021 10:44 AM
 */
@Component
public class ContractModelAssembler extends RepresentationModelAssemblerSupport<Contract, ContractModel> {

    @Autowired
    private SeasonModelConverter seasonModelConverter;

    public ContractModelAssembler() {
        super(ContractController.class, ContractModel.class);
    }

    @Override
    public ContractModel toModel(Contract entity) {
        ContractModel contractModel = new ContractModel();
        contractModel.setContractId(entity.getContractId());
        contractModel.setVersion(entity.getVersion());
        contractModel.setPropId(entity.getPropId());
        contractModel.setName(entity.getName());
        contractModel.setValidFrom(entity.getValidFrom());
        contractModel.setValidTo(entity.getValidTo());
        contractModel.setVersionTxt(entity.getVersionTxt());
        contractModel.add(HATEOASProvider.contractSelfLinkProvider(entity.getContractId()));
        contractModel.setSeasons(entity.getSeasons().stream().map(seasonModelConverter::convert).collect(Collectors.toSet()));

        return contractModel;
    }
}
