package com.mvc.lab.dto;

import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.Gson;

import lombok.Data;
@Data
public class BookingDto {
	private Integer bookingId;
	private String courtName;
    private String clubMemberName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Taipei")
    private Date useDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Taipei")
    private Timestamp createDate;
    
    @Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
