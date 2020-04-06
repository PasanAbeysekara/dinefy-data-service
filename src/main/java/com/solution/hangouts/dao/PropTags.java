//package com.solution.hangouts.dao;
//
//import lombok.Data;
//
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.SequenceGenerator;
//import javax.persistence.Table;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Size;
//
//@Data
//@Entity
//@Table(name = "sys_tags")
//public class PropTags
//{
//	@Id
//	@SequenceGenerator(
//			name = "tags_gen",
//			sequenceName = "tags_tag_id_seq",
//			initialValue = 100
//	)
//	private int tag_id;
//
//	@NotBlank
//	@Size(max = 10)
//	private String code;
//
//	@Size(max = 100)
//	private String name;
//
//	private String description;
//}
