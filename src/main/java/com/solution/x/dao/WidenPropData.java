package com.solution.x.dao;

import com.solution.x.dao.key.WidenDataGridKey;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;

/**
 * @author Tharinda Wickramaarachchi
 * @since 6/5/2020 12:21 PM
 */
public class WidenPropData
{
	@EmbeddedId
	protected WidenDataGridKey widenDataGridKey;

	@Column(name = "contract_avail_count")
	private Short contractAvailCount;

	@Column(name = "open")
	private Short open;

	@Column(name = "close")
	private Short close;

	@Column(name = "bookable")
	private Short bookable;

	@Column(name = "booked")
	private Short booked;

	@Column(name = "reservation_id")
	private Long reservationId;
}