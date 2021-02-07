package com.thaprobit.resengine.controller.service;

import com.thaprobit.global.SystemOperation;
import com.thaprobit.resengine.dao.Order;
import com.thaprobit.resengine.dao.OrderChoices;
import com.thaprobit.resengine.dao.Reservation;
import com.thaprobit.resengine.repo.ReservationRepository;
import com.thaprobit.service.AbstractService;
import com.thaprobit.util.ResponseWrapper;
import com.thaprobit.util.SystemMessages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Tharindu Aththanayake
 * @since 01/09/2021 01:23 AM
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationService extends AbstractService<Reservation>
{
	private final ReservationRepository reservationRepository;

	/**
	 * Get all reservations
	 *
	 * @param pageable The pageable
	 * @return The reservations
	 */
	public ResponseEntity<ResponseWrapper<Page<Reservation>>> getAllReservations( Pageable pageable )
	{
		ResponseEntity<ResponseWrapper<Page<Reservation>>> response;

		try
		{
			Page<Reservation> reservationsPage = reservationRepository.findAll( pageable );

			response = ResponseEntity.ok()
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.READ.withSuccess(), SystemMessages.SUCCESSFULLY_LOADED, reservationsPage ) );
		}
		catch( Exception e )
		{
			response = ResponseEntity.status( HttpStatus.NOT_FOUND )
					.headers( new HttpHeaders() )
					.body( new ResponseWrapper<>( SystemOperation.READ.withError(), SystemMessages.NOT_FOUND, e.getMessage() ) );
		}

		return response;
	}

	/**
	 * Get a reservation
	 *
	 * @param reservationId Id of the reservation
	 * @return The reservation
	 */
	public ResponseEntity<ResponseWrapper<Reservation>> getReservation( Long reservationId )
	{
		ResponseEntity<ResponseWrapper<Reservation>> response;

		Optional<Reservation> optionalReservation = reservationRepository.findById( reservationId );


		if( optionalReservation.isPresent() )
		{
			response = ResponseEntity.status( HttpStatus.FOUND )
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.READ.withSuccess() , SystemMessages.SUCCESSFULLY_LOADED, optionalReservation.get() ) );
		}
		else
		{
			response = buildNotFoundResponseWrapped();
		}

		return response;
	}

	/**
	 * Create a reservation
	 *
	 * @param reservation The reservation
	 * @return The saved reservation
	 */
	public ResponseEntity<ResponseWrapper<Reservation>> createReservation( Reservation reservation )
	{
		ResponseEntity<ResponseWrapper<Reservation>> response;

		try
		{
			reservation.setReservationId( reservationRepository.getNextVal() );
			preProcess( reservation );

			Reservation savedReservation = reservationRepository.save( reservation );

			response = ResponseEntity.status( HttpStatus.CREATED )
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.CREATE.withSuccess(), SystemMessages.RESERVATION_CREATE_SUCCESS, savedReservation ) );
		}
		catch( Exception e )
		{
			response = buildExceptionErrorResponse( SystemOperation.CREATE.withError(), SystemMessages.RESERVATION_CREATE_FAILED, e );
		}

		return  response;
	}

	private void preProcess ( Reservation reservation )
	{
		if ( reservation.getOrders() != null )
		{
			long reservationId = reservation.getReservationId();

			for( Order order : reservation.getOrders() )
			{
				order.getOrderId().setReservationId( reservationId );

				if ( order.getOrderChoices() != null )
				{
					short orderId = order.getOrderId().getOrderId();

					for( OrderChoices orderChoices : order.getOrderChoices() )
					{
						orderChoices.getOrderChoiceId().setReservationId( reservationId );
						orderChoices.getOrderChoiceId().setOrderId( orderId );
					}
				}
			}
		}
	}

	/**
	 * Update a reservation
	 *
	 * @param reservationId The reservation ID
	 * @param reservation 	The reservation
	 * @return The updated reservation
	 */
	public ResponseEntity<ResponseWrapper<Reservation>> updateReservation( Long reservationId, Reservation reservation )
	{
		ResponseEntity<ResponseWrapper<Reservation>> response;

		try
		{
			reservation.setReservationId( reservationId );
			preProcess( reservation );

			Reservation updatedReservation = reservationRepository.save( reservation );

			response = ResponseEntity.ok()
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.MODIFY.withSuccess(), SystemMessages.RESERVATION_UPDATE_SUCCESS, updatedReservation ) );
		}
		catch( Exception e )
		{
			response = buildExceptionErrorResponse( SystemOperation.MODIFY.withError(), SystemMessages.RESERVATION_UPDATE_FAILED, e );
		}

		return  response;
	}

	/**
	 * Delete a reservation
	 *
	 * @param reservationId The reservation ID
	 * @return The deleted reservation response
	 */
	public ResponseEntity<ResponseWrapper<Reservation>> deleteReservation( Long reservationId )
	{
		ResponseEntity<ResponseWrapper<Reservation>> response;

		try
		{
			reservationRepository.deleteById( reservationId );

			response = ResponseEntity.ok()
					.headers( addCommonHeaders( new HttpHeaders() ) )
					.body( new ResponseWrapper<>( SystemOperation.DELETE.withSuccess(), SystemMessages.RESERVATION_DELETE_SUCCESS, "" ) );
		}
		catch( Exception e )
		{
			response = buildExceptionErrorResponse( SystemOperation.DELETE.withError(), SystemMessages.RESERVATION_DELETE_FAILED, e );
		}

		return  response;
	}
}
