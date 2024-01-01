package com.mvc.lab.entity;

import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.Gson;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookingId;
	private Integer courtId;
	private Integer clubMemberId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Taipei")
	private Date useDate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Taipei")
	@Column(name = "create_date", nullable = false, updatable = true, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp createDate;
	
	@ManyToOne
    @JoinColumn(name = "courtId", referencedColumnName = "courtId", insertable = false, updatable = false)
    private Court court;
	
	@ManyToOne
	@JoinColumn(name = "clubMemberId", referencedColumnName = "clubMemberId", insertable = false, updatable = false)
    private ClubMember clubMember;
	
	public Booking(Integer courtId, Integer clubMemberId, Date useDate) {
		this.courtId = courtId;
		this.clubMemberId = clubMemberId;
		this.useDate = useDate;
	}
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
	
}
