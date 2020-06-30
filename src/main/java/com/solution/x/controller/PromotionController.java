package com.solution.x.controller;

import com.solution.x.controller.service.PromotionService;
import com.solution.x.dao.Promotion;
import com.solution.x.util.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tharinda Wickramaarachchi
 * @since 6/3/2020 12:09 AM
 */
@RestController
public class PromotionController
{
	@Autowired
	private PromotionService promotionService;

	/**
	 * Get Single Promotion
	 *
	 * @param propId  Property ID
	 * @param promoId Promotion ID
	 * @return The Promotion
	 */
	@GetMapping("/properties/{propId}/promotions/{promoId}")
	public ResponseEntity<ResponseWrapper<Promotion>> getPromotion( @PathVariable("propId") Long propId, @PathVariable("promoId") Short promoId )
	{
		return promotionService.getPromotion( propId, promoId );
	}

	/**
	 * Create Single Promotion
	 *
	 * @param Promotion Promotion
	 * @return The Promotion
	 */
	@PostMapping("/properties/{propId}/promotions")
	public ResponseEntity<ResponseWrapper<Promotion>> createPromotion( @PathVariable("propId") Long propId, @RequestBody Promotion Promotion )
	{
		return promotionService.createPromotion( propId, Promotion );
	}


	/**
	 * Update a Promotion
	 *
	 * @param propId    Property ID
	 * @param promoId   Promotion ID
	 * @param Promotion Promotion
	 * @return The Updated Promotion
	 */
	@PutMapping("/properties/{propId}/promotions/{promoId}")
	public ResponseEntity<ResponseWrapper<Promotion>> updatePromotion( @PathVariable("propId") Long propId, @PathVariable("promoId") Short promoId, @RequestBody Promotion Promotion )
	{
		return promotionService.updatePromotion( propId, promoId, Promotion );
	}

	/**
	 * Delete a Promotion
	 *
	 * @param propId  Property ID
	 * @param promoId Promotion ID
	 * @return Delete status
	 */
	@DeleteMapping("/properties/{propId}/promotions/{promoId}")
	public ResponseEntity<ResponseWrapper<Promotion>> updatePromotion( @PathVariable("propId") Long propId, @PathVariable("promoId") Short promoId )
	{
		return promotionService.deletePromotion( propId, promoId );
	}
}
