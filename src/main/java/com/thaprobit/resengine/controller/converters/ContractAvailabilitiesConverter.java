package com.thaprobit.resengine.controller.converters;

import com.thaprobit.resengine.dao.ContractAvailability;
import com.thaprobit.resengine.facade.dto.ContractAvailabilityModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Tharindu Aththanayake
 * @since 01/01/2021 11:25 AM
 */
@Component
public class ContractAvailabilitiesConverter implements Converter<ContractAvailability, ContractAvailabilityModel> {
    @Override
    public ContractAvailabilityModel convert(ContractAvailability contractAvailability) {
        ContractAvailabilityModel contractAvailabilityModel = new ContractAvailabilityModel();
        contractAvailabilityModel.setAvailabilityID(contractAvailability.getAvailabilityID());
        contractAvailabilityModel.setCount(contractAvailability.getCount());

        return contractAvailabilityModel;
    }
}
