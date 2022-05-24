package com.example.HealthCareMini.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	@Column(name="doc_col_gender")
	private String gender;
	
	@Column(name="doc_col_note",unique = true)
	private String note;
	
	@Column(name="doc_col_image", unique= true)
	private String photoLoc;
	
	
	
	//MODULE INTEGRATION
	/* ASSOCIATION MAPPING NEED TO DO
	 * PRIMARY KEY OF CHILD DATABASE NEED TO USE AS FOREIGNKEY TO GET THE DATA
	 * EX: HERE WE HAVE DOCTOR (PARENT) --> SPECIALIZATION(CHILD) HERE WE ARE
	 *  INTEGRATING SPECIALIZATION MODULE TO DOCTOR MODULE.
	 *  STEP 1: CREATE A JOIN COLUMN of SPECIALIZATION MODULE
	 *  STEP 2: GO TO CHILD REPOSITORY AND WRITE THE HQL TO GET ID,NAME
	 *  STEP 3 GO TO CHILD SERVICE AND SERVICEIMPL TO RETURN THE ID,NAME IN MAP<INTEGER,STRING>
	 *  STEP 4: GO TO PARENT CONTROLLER TO WRITE THE SOME METHOD TO SEND THE DATA TO REGISTER AND EDIT PAGE
	 *  */
	
	@ManyToOne
	@JoinColumn(name="doc_spec_id")
	private Specialization specId;
}
