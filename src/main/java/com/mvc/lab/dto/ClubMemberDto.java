package com.mvc.lab.dto;

import com.mvc.lab.entity.ClubMember;

import lombok.Data;
@Data
public class ClubMemberDto {
	
	private ClubMember clubMember;
	private BookingDto bookingDto;
}
