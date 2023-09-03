package com.thaprobit.resengine.controller.converters;

import com.thaprobit.resengine.dao.Reservation;
import com.thaprobit.resengine.facade.dto.ReservationModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author Tharindu Aththanayake
 * @since 31/01/2021 07:26 PM
 */
@Component
public class ReservationModelConverter implements Converter<Reservation, ReservationModel> {
    @Override
    public ReservationModel convert(Reservation reservation) {
        ReservationModel reservationModel = new ReservationModel();
        reservationModel.setReservationId(reservation.getReservationId());
        reservationModel.setDate(reservation.getDate());
        reservationModel.setTime(reservation.getTime());
        reservationModel.setHeadCount(reservation.getHeadCount());
        reservationModel.setTotalAmount(reservation.getOrders().stream().map(order -> order.getTotalAmount()).reduce(BigDecimal.ZERO, (a, b) -> a.add(b)));
        reservationModel.setAmountCurrency(reservation.getOrders().stream().map(order -> order.getAmountCurrency()).filter(i -> i != null).findFirst().orElse("LKR"));

        return reservationModel;
    }
}
