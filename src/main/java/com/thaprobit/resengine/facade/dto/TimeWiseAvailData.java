package com.thaprobit.resengine.facade.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalTime;

/**
 * @author Tharinda Wickramaarachchi
 * @since 7/28/2020 1:05 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TimeWiseAvailData {
    private LocalTime timeSlot;
    private Integer availUnitId;
    private Short contractAvailCount;
    private Short open;
    private Short close;
    private Short bookable;
    private Short hold;
    private Short booked;
}
