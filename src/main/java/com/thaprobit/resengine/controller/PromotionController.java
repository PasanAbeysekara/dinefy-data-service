package com.thaprobit.resengine.controller;

import com.thaprobit.resengine.controller.service.PromotionService;
import com.thaprobit.resengine.dao.Promotion;
import com.thaprobit.util.ResponseWrapper;
import com.thaprobit.util.URLProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Tharinda Wickramaarachchi
 * @since 6/3/2020 12:09 AM
 */
@RestController
@RequestMapping(URLProvider.SERVICE_DATA)
public class PromotionController {
    @Autowired
    private PromotionService promotionService;

    /**
     * Get multiple Promotions
     *
     * @param propId PropertyModel ID
     * @param live   Promotion live or not
     * @return Matching Promotion
     */
    @GetMapping("/properties/{propId}/promotions")
    public ResponseEntity<ResponseWrapper<List<Promotion>>> getPromotions(@PathVariable("propId") Long propId, @RequestParam(name = "live", required = false) Boolean live) {
        return promotionService.getPromotions(propId, live);
    }

    /**
     * Get Single Promotion
     *
     * @param propId  PropertyModel ID
     * @param promoId Promotion ID
     * @return The Promotion
     */
    @GetMapping("/properties/{propId}/promotions/{promoId}")
    public ResponseEntity<ResponseWrapper<Promotion>> getPromotion(@PathVariable("propId") Long propId, @PathVariable("promoId") Short promoId) {
        return promotionService.getPromotion(propId, promoId);
    }

    /**
     * Create Single Promotion
     *
     * @param Promotion Promotion
     * @return The Promotion
     */
    @PostMapping("/properties/{propId}/promotions")
    public ResponseEntity<ResponseWrapper<Promotion>> createPromotion(@PathVariable("propId") Long propId, @RequestBody Promotion Promotion) {
        return promotionService.createPromotion(propId, Promotion);
    }


    /**
     * Update a Promotion
     *
     * @param propId    PropertyModel ID
     * @param promoId   Promotion ID
     * @param Promotion Promotion
     * @return The Updated Promotion
     */
    @PutMapping("/properties/{propId}/promotions/{promoId}")
    public ResponseEntity<ResponseWrapper<Promotion>> updatePromotion(@PathVariable("propId") Long propId, @PathVariable("promoId") Short promoId, @RequestBody Promotion Promotion) {
        return promotionService.updatePromotion(propId, promoId, Promotion);
    }

    /**
     * Delete a Promotion
     *
     * @param propId  PropertyModel ID
     * @param promoId Promotion ID
     * @return Delete status
     */
    @DeleteMapping("/properties/{propId}/promotions/{promoId}")
    public ResponseEntity<ResponseWrapper<Promotion>> updatePromotion(@PathVariable("propId") Long propId, @PathVariable("promoId") Short promoId) {
        return promotionService.deletePromotion(propId, promoId);
    }
}
