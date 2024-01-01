package com.mvc.lab.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class ClubMember {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer clubMemberId;
	private String clubMemberUsername;
	private String clubMemberPassword;
	private String clubMemberName;
	private String clubMemberBirth;
	private Integer clubMemberBookingRecordId;
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
