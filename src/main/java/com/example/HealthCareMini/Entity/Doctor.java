package com.example.HealthCareMini.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="doc_tab")
public class Doctor {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="doc_col_id",unique = true)
	private Long id;
	
	@Column(name="doc_col_firstname",unique = true)
	private String firstName;
	
	@Column(name="doc_col_lastname",unique = true)
	private String lastName;
	
	@Column(name="doc_col_email",unique = true)
	private String email;
	
	@Column(name="doc_col_address",unique = true)
	private String address;
	
	@Column(name="doc_col_mobile",unique = true)
	private String mobile;
	
	@Column(name="doc_col_gender",unique = true)
	private String gender;
	
	@Column(name="doc_col_note",unique = true)
	private String note;
	
	
	
}
