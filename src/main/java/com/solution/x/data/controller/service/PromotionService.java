package com.solution.x.data.controller.service;

import com.solution.x.dao.Promotion;
import com.solution.x.dao.key.PromoID;
import com.solution.x.global.SystemOperation;
import com.solution.x.repo.PromotionRepository;
import com.solution.x.service.AbstractService;
import com.solution.x.util.ResponseWrapper;
import com.solution.x.util.SystemMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Tharinda Wickramaarachchi
 * @since 6/2/2020 11:58 PM
 */
@Service
@Slf4j
public class PromotionService extends AbstractService<Promotion>
{
	@Autowired
	private PromotionRepository promotionRepository;


	public ResponseEntity<ResponseWrapper<List<Promotion>>> getPromotions( Long propId, Boolean live )
	{
		List<Promotion> promotions;
		if( live != null )
		{
			promotions = promotionRepository.findLivePromotions( propId, live );
		}
		else
		{
			promotions = promotionRepository.findAllPromotionsByPropId( propId );
		}

		ResponseEntity<ResponseWrapper<List<Promotion>>> response;

		if( !promotions.isEmpty() )
		{
			promotions.forEach( promo -> promo.add( HATEOASProvider.promotionSelfLinkProvider( promo.getPromoId() ) ) );

			response = ResponseEntity.ok()
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, promotions ) );
		}
		else
		{
			response = ResponseEntity.status( HttpStatus.NOT_FOUND )
					.headers( new HttpHeaders() )
					.body( new ResponseWrapper<>( SystemOperation.READ.withSuccess(), SystemMessages.NOT_FOUND, "" ) );
		}

		return response;
	}

	/**
	 * Get Single Promotion
	 *
	 * @param propId  PropertyModel ID
	 * @param promoId Promotion ID
	 * @return The Promotion
	 */
	public ResponseEntity<ResponseWrapper<Promotion>> getPromotion( long propId, short promoId )
	{
		Optional<Promotion> promotionOptional = promotionRepository.findById( new PromoID( propId, promoId ) );

		ResponseEntity<ResponseWrapper<Promotion>> response;

		if( promotionOptional.isPresent() )
		{
			Promotion promotion = promotionOptional.get();
			Link selfRel = HATEOASProvider.promotionSelfLinkProvider( promotion.getPromoId() );
			promotion.add( selfRel );

			response = ResponseEntity.ok()
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, promotion ) );
		}
		else
		{
			response = buildNotFoundResponseWrapped();
		}

		return response;
	}


	/**
	 * Create a new Promotion
	 *
	 * @param propId
	 * @param promotion Promotion
	 * @return Saved Promotion Response wrapper
	 */
	public ResponseEntity<ResponseWrapper<Promotion>> createPromotion( long propId, Promotion promotion )
	{
		ResponseEntity<ResponseWrapper<Promotion>> response;

		try
		{
			Short nextPromoId = promotionRepository.nextPromoId( propId );
			promotion.setPromoId( new PromoID( propId, nextPromoId ) );

			preProcess( promotion );

			Promotion savedPromotion = promotionRepository.save( promotion );

			Link selfRel = HATEOASProvider.promotionSelfLinkProvider( savedPromotion.getPromoId() );
			savedPromotion.add( selfRel );

			response = ResponseEntity.status( HttpStatus.CREATED )
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.CREATE.withSuccess(), SystemMessages.PROMOTION_CREATE_SUCCESS, savedPromotion ) );
		}
		catch( Exception e )
		{
			log.error( "Error Occurred during Promotion saving : ", e );
			response = buildExceptionErrorResponse( SystemOperation.CREATE, SystemMessages.PROMOTION_CREATE_FAILED, e );
		}

		return response;
	}

	/**
	 * Create a new Promotion
	 *
	 * @param propId    PropertyModel ID
	 * @param promoId   Promotion ID
	 * @param promotion Promotion
	 * @return Saved Promotion Response wrapper
	 */
	public ResponseEntity<ResponseWrapper<Promotion>> updatePromotion( long propId, short promoId, Promotion promotion )
	{
		ResponseEntity<ResponseWrapper<Promotion>> response;

		try
		{
			PromoID promoID = new PromoID( propId, promoId );
			promotion.setPromoId( promoID );

			Promotion savedPromotion = promotionRepository.save( promotion );

			Link selfRel = HATEOASProvider.promotionSelfLinkProvider( savedPromotion.getPromoId() );
			savedPromotion.add( selfRel );

			response = ResponseEntity.status( HttpStatus.CREATED )
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.MODIFY.withSuccess(), SystemMessages.PROMOTION_UPDATE_SUCCESS, savedPromotion ) );
		}
		catch( Exception e )
		{
			log.error( "Error Occurred during Promotion updating : ", e );
			response = buildExceptionErrorResponse( SystemOperation.MODIFY, SystemMessages.PROMOTION_UPDATE_FAILED, e );
		}

		return response;
	}


	private void preProcess( Promotion Promotion )
	{

	}

	/**
	 * Delete Promotion
	 *
	 * @param propId  PropertyModel ID
	 * @param promoId Promotion ID
	 * @return Delete response
	 */
	public ResponseEntity<ResponseWrapper<Promotion>> deletePromotion( long propId, short promoId )
	{
		ResponseEntity<ResponseWrapper<Promotion>> response;

		try
		{
			promotionRepository.deleteById( new PromoID( propId, promoId ) );

			response = ResponseEntity.ok()
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.DELETE.withSuccess(), SystemMessages.PROMOTION_DELETE_SUCCESS, "" ) );
		}
		catch( Exception e )
		{
			log.error( "Error Occurred during Promotion deleting : ", e );
			response = buildExceptionErrorResponse( SystemOperation.DELETE, SystemMessages.PROMOTION_DELETE_FAILED, e );
		}

		return response;
	}
}