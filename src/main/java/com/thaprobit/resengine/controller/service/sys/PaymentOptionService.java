package com.thaprobit.resengine.controller.service.sys;

import com.thaprobit.global.SystemOperation;
import com.thaprobit.resengine.dao.sys.PaymentOptions;
import com.thaprobit.resengine.repo.sys.PaymentOptionsRepository;
import com.thaprobit.service.AbstractService;
import com.thaprobit.util.ResponseWrapper;
import com.thaprobit.util.SystemMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Tharindu Aththanayake
 */
@Service
@Slf4j
public class PaymentOptionService extends AbstractService<PaymentOptions>
{

	@Autowired
	private PaymentOptionsRepository paymentOptionsRepository;

	/**
	 * Get All Payment Options
	 *
	 * @return all Payment Options
	 */
	public ResponseEntity<ResponseWrapper<List<PaymentOptions>>> getPaymentOptions()
	{
		List<PaymentOptions> paymentOptions = paymentOptionsRepository.findAll();

		return ResponseEntity.ok()
				.headers( addCommonHeaders( new HttpHeaders() ) )
				.body( new ResponseWrapper<>( SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, paymentOptions ) );
	}

	/**
	 * Get Single Payment Option
	 *
	 * @param id Payment Option ID
	 * @return The Payment Option
	 */
	public ResponseEntity<ResponseWrapper<PaymentOptions>> getPaymentOption( short id )
	{
		Optional<PaymentOptions> optionalPaymentOption = paymentOptionsRepository.findById( id );

		ResponseEntity<ResponseWrapper<PaymentOptions>> response;

		if( optionalPaymentOption.isPresent() )
		{
			PaymentOptions paymentOption = optionalPaymentOption.get();

			response = ResponseEntity.ok()
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, paymentOption ) );
		}
		else
		{
			response = buildNotFoundResponseWrapped();
		}

		return response;

	}

	/**
	 * Create a Payment Option
	 *
	 * @param paymentOption The Payment Option
	 * @return Saved paymentOption response
	 */
	public ResponseEntity<ResponseWrapper<PaymentOptions>> createPaymentOptions( PaymentOptions paymentOption )
	{
		ResponseEntity<ResponseWrapper<PaymentOptions>> response;

		try
		{
			short nextOptionId = paymentOptionsRepository.nextOptionId();
			paymentOption.setOptionId( nextOptionId );
			PaymentOptions savedPaymentOption = paymentOptionsRepository.save( paymentOption );

			response = ResponseEntity.status( HttpStatus.CREATED )
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.CREATE.withSuccess(), SystemMessages.PAYMENT_OPTION_CREATE_SUCCESS, savedPaymentOption ) );
		}
		catch( Exception e )
		{
			e.printStackTrace();
			response = buildExceptionErrorResponse( SystemOperation.CREATE, SystemMessages.PAYMENT_OPTION_CREATE_FAILED, e );
		}

		return response;
	}


	/**
	 * Update a Payment Option
	 *
	 * @param id            The Payment Option ID
	 * @param paymentOption The Payment Option
	 * @return Updated Payment Option response
	 */
	public ResponseEntity<ResponseWrapper<PaymentOptions>> updatePaymentOption( short id, PaymentOptions paymentOption )
	{
		ResponseEntity<ResponseWrapper<PaymentOptions>> response;

		try
		{
			paymentOption.setOptionId( id );
			PaymentOptions savedPaymentOption = paymentOptionsRepository.save( paymentOption );

			response = ResponseEntity.ok()
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.MODIFY.withSuccess(), SystemMessages.PAYMENT_OPTION_UPDATE_SUCCESS, savedPaymentOption ) );
		}
		catch( Exception e )
		{
			e.printStackTrace();
			response = buildExceptionErrorResponse( SystemOperation.MODIFY, SystemMessages.PAYMENT_OPTION_UPDATE_FAILED, e );
		}

		return response;
	}

	/**
	 * Delete a Payment Option
	 *
	 * @param id The Payment Option ID
	 * @return Delete Payment Option response
	 */
	public ResponseEntity<ResponseWrapper<PaymentOptions>> deletePaymentOption( short id )
	{
		ResponseEntity<ResponseWrapper<PaymentOptions>> response;

		try
		{
			paymentOptionsRepository.deleteById( id );

			response = ResponseEntity.ok()
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.DELETE.withSuccess(), SystemMessages.PAYMENT_OPTION_DELETE_SUCCESS, "" ) );
		}
		catch( Exception e )
		{
			e.printStackTrace();
			response = buildExceptionErrorResponse( SystemOperation.DELETE, SystemMessages.PAYMENT_OPTION_DELETE_FAILED, e );
		}

		return response;
	}
}
