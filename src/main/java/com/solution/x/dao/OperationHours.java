package com.solution.x.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.solution.x.dao.key.OperationHourKey;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.time.LocalTime;

/**
 * @author Tharinda Wickramaarachchi
 * @since 6/9/2020 7:25 PM
 */
@Data
@Entity
@Table(name = "prop_operation_hours")
@ToString
public class OperationHours
{
	@EmbeddedId
	@EqualsAndHashCode.Include
	private OperationHourKey operationHourKey;

	@Column(name = "time_start")
	private LocalTime timeStart;

	@Column(name = "time_end")
	private LocalTime timeEnd;

	@Size(max = 100)
	@Column(name = "name")
	private String name;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prop_id", insertable = false, updatable = false)
	@ToString.Exclude
	private Property properties;
}
