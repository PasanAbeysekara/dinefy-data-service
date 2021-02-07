package com.thaprobit.resengine.facade.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Tharindu Aththanayake
 * @since 31/01/2021 07:26 PM
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ReservationModel
{
	private Long reservationId;
	private LocalDate date;
	private LocalTime time;
	private int headCount;
	private BigDecimal totalAmount;
	private String amountCurrency;
}
