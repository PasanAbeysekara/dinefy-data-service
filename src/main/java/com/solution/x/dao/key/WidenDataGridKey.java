package com.solution.x.dao.key;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalTime;

/**
 * @author Tharinda Wickramaarachchi
 * @since 6/5/2020 9:36 PM
 */
@Embeddable
public class WidenDataGridKey implements Serializable
{
	@NotNull
	@Column(name = "prop_id")
	private Long propId;

	@NotNull
	@Column(name = "avail_unit_id")
	private Integer availUnitId;

	@NotNull
	@Column(name = "date")
	private Date date;

	@NotNull
	@Column(name = "time_slot")
	private LocalTime timeSlot;
}
