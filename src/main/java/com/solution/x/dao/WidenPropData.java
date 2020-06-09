package com.solution.x.dao;

import com.solution.x.dao.key.WidenDataGridKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Tharinda Wickramaarachchi
 * @since 6/5/2020 12:21 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prop_widen_data_grid")
public class WidenPropData
{
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

	@Column(name = "booked")
	private Short booked;

	@Column(name = "reservation_id")
	private Long reservationId;
}