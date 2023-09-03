package com.thaprobit.resengine.controller.sys;

import com.thaprobit.resengine.controller.service.sys.PaymentOptionService;
import com.thaprobit.resengine.dao.sys.PaymentOptions;
import com.thaprobit.util.ResponseWrapper;
import com.thaprobit.util.URLProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tharindu Aththanayake
 */
@RestController
@RequestMapping(URLProvider.SERVICE_DATA)
@Slf4j
public class PaymentOptionController {

    @Autowired
    private PaymentOptionService paymentOptionService;

    /**
     * Get All Payment Options
     *
     * @param pageable Pageable
     * @return all Payment Options
     */
    @GetMapping("/payment-options")
    public ResponseEntity<ResponseWrapper<Page<PaymentOptions>>> getPaymentOptions(Pageable pageable) {
        return paymentOptionService.getPaymentOptions(pageable);
    }

    /**
     * Get Single Payment Option
     *
     * @param id Payment Option ID
     * @return The Payment Option
     */
    @GetMapping("/payment-options/{id}")
    public ResponseEntity<ResponseWrapper<PaymentOptions>> getPaymentOption(@PathVariable("id") short id) {
        return paymentOptionService.getPaymentOption(id);
    }

    /**
     * Create a Payment Option
     *
     * @param paymentOption The Payment Option
     * @return Saved paymentOption response
     */
    @PostMapping("/payment-options")
    public ResponseEntity<ResponseWrapper<PaymentOptions>> createPaymentOption(@RequestBody PaymentOptions paymentOption) {
        return paymentOptionService.createPaymentOptions(paymentOption);
    }

    /**
     * Update a Payment Option
     *
     * @param id            The Payment Option ID
     * @param paymentOption The Payment Option
     * @return Updated Payment Option response
     */
    @PutMapping("/payment-options/{id}")
    public ResponseEntity<ResponseWrapper<PaymentOptions>> updatePaymentOption(@PathVariable("id") short id, @RequestBody PaymentOptions paymentOption) {
        return paymentOptionService.updatePaymentOption(id, paymentOption);
    }

    /**
     * Delete a Payment Option
     *
     * @param id The Payment Option ID
     * @return Delete Payment Option response
     */
    @DeleteMapping("/payment-options/{id}")
    public ResponseEntity<ResponseWrapper<PaymentOptions>> deletePaymentOption(@PathVariable("id") short id) {
        return paymentOptionService.deletePaymentOption(id);
    }
}
