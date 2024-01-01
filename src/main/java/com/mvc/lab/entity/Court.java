package com.mvc.lab.entity;

import com.google.gson.Gson;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Court {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer courtId;
	private String courtName;
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
