package com.mvc.lab.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
@Data
public class BookingRequest {
	private Integer courtId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Taipei")
	private Date useDate;
}
