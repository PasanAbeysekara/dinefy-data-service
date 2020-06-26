package com.solution.x.dao;

import com.solution.x.dao.key.WidenDataGridKey;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Tharinda Wickramaarachchi
 * @since 6/5/2020 12:21 PM
 */
@Data
@AllArgsConstructor
@Entity
@Table(name = "prop_exploded_avail_data")
public class WidenPropData
{
	public WidenPropData()
	{
		this.contractAvailCount = 0;
		this.close = 0;
		this.open = 0;
		this.hold = 0;
		this.booked = 0;
		this.bookable = 0;
	}

	@EmbeddedId
	private WidenDataGridKey widenDataGridKey;

	@Column(name = "contract_avail_count")
	private Short contractAvailCount;

	@Column(name = "open")
	private Short open;

	@Column(name = "close")
	private Short close;

	@Column(name = "bookable")
	private Short bookable;

	@Column(name = "hold")
	private Short hold;

	@Column(name = "booked")
	private Short booked;

	@Column(name = "reservation_id")
	private Long reservationId;

	@Column(name = "contract_id")
	private Long contractId;

	public void calculateBookable()
	{
		this.bookable = (short) ( this.contractAvailCount + this.open - this.close - this.hold - this.booked );
	}
}