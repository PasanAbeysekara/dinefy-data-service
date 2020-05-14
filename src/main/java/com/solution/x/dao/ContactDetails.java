package com.solution.x.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Tharinda Wickramaarachchi
 * @since 4/22/2020 11:54 PM
 */
@Data
@Entity
@Table(name = "sys_contacts")
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class ContactDetails
{
	@Id
	@SequenceGenerator(
			name = "contact_gen",
			sequenceName = "contact_id_seq",
			allocationSize = 1
	)
	@GeneratedValue(generator = "contact_gen", strategy = GenerationType.SEQUENCE) //TODO sequence [hngout.contact_id_seq] defined inconsistent increment-size; found [1] but expecting [50
	@EqualsAndHashCode.Include
	@Column(name = "contact_id")
	private Long contactId;

	@NotBlank
	@Size(max = 5)
	@EqualsAndHashCode.Include
	private String type;

	@Size(max = 100)
	private String name;

	@Size(max = 254)
	private String email;

	@Size(max = 100)
	private String web;

	@Size(max = 20)
	@Column(name = "phone_primary")
	private String phonePrimary;

	@Size(max = 20)
	@Column(name = "phone_secondary")
	private String phoneSecondary;

	@Size(max = 100)
	@Column(name = "address_line1")
	private String address1;

	@Size(max = 100)
	@Column(name = "address_line2")
	private String address2;

	@Size(max = 100)
	@Column(name = "address_line3")
	private String address3;

	@Size(max = 10)
	@Column(name = "zip_code")
	private String zip;

	@JsonBackReference
	@ToString.Exclude
	@OneToOne(mappedBy = "contactDetails", fetch = FetchType.LAZY)
	private Property property;
}
