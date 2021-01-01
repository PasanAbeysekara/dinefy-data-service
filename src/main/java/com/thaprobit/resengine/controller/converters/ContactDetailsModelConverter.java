package com.thaprobit.resengine.controller.converters;

import com.thaprobit.resengine.dao.ContactDetails;
import com.thaprobit.resengine.facade.dto.ContactDetailsModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Tharindu Aththanayake
 * @since 01/01/2021 11:54 AM
 */
@Component
public class ContactDetailsModelConverter implements Converter<ContactDetails, ContactDetailsModel>
{
	@Override
	public ContactDetailsModel convert( ContactDetails contactDetails )
	{
		ContactDetailsModel contactDetailsModel = new ContactDetailsModel();
		contactDetailsModel.setContactId( contactDetails.getContactId() );
		contactDetailsModel.setType( contactDetails.getType() );
		contactDetailsModel.setName( contactDetails.getName() );
		contactDetailsModel.setEmail( contactDetails.getEmail() );
		contactDetailsModel.setWeb( contactDetails.getWeb() );
		contactDetailsModel.setPhonePrimary( contactDetails.getPhonePrimary() );
		contactDetailsModel.setPhoneSecondary( contactDetails.getPhoneSecondary() );
		contactDetailsModel.setAddress1( contactDetails.getAddress1() );
		contactDetailsModel.setAddress2( contactDetails.getAddress2() );
		contactDetailsModel.setAddress3( contactDetails.getAddress3() );
		contactDetailsModel.setZip( contactDetails.getZip() );

		return contactDetailsModel;
	}
}
