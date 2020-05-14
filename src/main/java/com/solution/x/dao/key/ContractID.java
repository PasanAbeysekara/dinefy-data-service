package com.solution.x.dao.key;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.io.Serializable;

/**
 * @author Tharinda Wickramaarachchi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ContractID implements Serializable
{
	@Id
	@SequenceGenerator(
			name = "contracts_gen",
			sequenceName = "contracts_contract_id_seq",
			initialValue = 0,
			allocationSize = 1
	)
	@GeneratedValue(generator = "contracts_gen", strategy = GenerationType.SEQUENCE)
	@Column(name = "contract_id")
	private long contractId;

	@Column(name = "version")
	private Short version;

}
