package com.thaprobit.resengine.controller.converters;

import com.thaprobit.resengine.controller.service.HATEOASProvider;
import com.thaprobit.resengine.dao.Organization;
import com.thaprobit.resengine.facade.dto.OrganizationModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Tharindu Aththanayake
 * @since 01/01/2021 12:16 AM
 */
@Component
public class OrganizationModelConverter implements Converter<Organization, OrganizationModel> {
    @Override
    public OrganizationModel convert(Organization organization) {
        OrganizationModel organizationModel = new OrganizationModel();
        organizationModel.setOrgId(organization.getOrgId());
        organizationModel.setCode(organization.getCode());
        organizationModel.setName(organization.getName());

        organizationModel.add(HATEOASProvider.organizationSelfLinkProvider(organization.getOrgId()));

        return organizationModel;
    }
}
