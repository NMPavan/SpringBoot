package com.example.HealthCareMini.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="spec_tab")
public class Specialization {
	@javax.persistence.Id
	@Column(name="spec_Id_col")
	//for auto increment we need to use this
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column(name="spec_name_col" ,length = 10,unique = true)
	private String name;
	
	@Column(name="spec_code_col", length = 25,unique = true)
	private String code;
	@Column(name="spec_note_col" ,length = 250,unique = true)
	private String note;
}
