package com.mvc.lab.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mvc.lab.dto.BookingDto;
import com.mvc.lab.dto.BookingRequest;
import com.mvc.lab.entity.Booking;
import com.mvc.lab.entity.ClubMember;
import com.mvc.lab.repository.BookingRepository;
import com.mvc.lab.repository.ClubMemberRepository;


@Controller
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private ClubMemberRepository clubMemberRepository;
	
	@GetMapping("/")
	@ResponseBody
	public List<BookingDto> findAllBooking() {
		List<Booking> bookings = bookingRepository.findAll();
		List<BookingDto> bookingDtos = new ArrayList<>();
	    for (Booking booking : bookings) {
	        BookingDto bookingDto = new BookingDto();
	        bookingDto.setBookingId(booking.getBookingId());
	        bookingDto.setCourtName(booking.getCourt().getCourtName());
	        bookingDto.setClubMemberName(booking.getClubMember().getClubMemberName());
	        bookingDto.setUseDate(booking.getUseDate());
	        bookingDto.setCreateDate(booking.getCreateDate());
	        bookingDtos.add(bookingDto);
	    }

	    return bookingDtos;
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public BookingDto findBookingById(@PathVariable("id") Integer id) {
		Booking booking = bookingRepository.findById(id).get();
		
		BookingDto bookingDto = new BookingDto();
        bookingDto.setBookingId(booking.getBookingId());
        bookingDto.setCourtName(booking.getCourt().getCourtName());
        bookingDto.setClubMemberName(booking.getClubMember().getClubMemberName());
        bookingDto.setUseDate(booking.getUseDate());
        bookingDto.setCreateDate(booking.getCreateDate());
        
        return bookingDto;
	}
	
	@PostMapping("/{id}")
	@ResponseBody
	public String bookingCourt(@PathVariable("id") Integer id, @RequestBody BookingRequest bookingRequest) {
		// 使用 clubMemberId 查找相應的 clubMember
	    ClubMember clubMember = clubMemberRepository.findById(id).get();
	    if(clubMember.getClubMemberBookingRecordId() == null) {
			Booking booking = new Booking(bookingRequest.getCourtId(), id, bookingRequest.getUseDate());
			
			bookingRepository.save(booking);
			
			// 獲取新建 Booking 的 bookingId
		    Integer newBookingId = booking.getBookingId();
	
		    // 將 clubMemberBookingRecordId 設置為新建的 bookingId
		    clubMember.setClubMemberBookingRecordId(newBookingId);
	
		    // 儲存 clubMember 以更新資料庫
		    clubMemberRepository.save(clubMember);
			return "預約成功 !";
	    }else {
			return "已存在預約紀錄, 請取消後再申請預約";
		}
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public String deleteBookingCourt(@PathVariable("id") Integer id) {
		ClubMember clubMember = clubMemberRepository.findById(id).get();
		if(clubMember.getClubMemberBookingRecordId() == null || clubMember.getClubMemberBookingRecordId() == 0) {
			return "沒有預約紀錄, 請開始球場預約";
		}else {
			Booking booking = bookingRepository.findById(clubMember.getClubMemberBookingRecordId()).get();
			bookingRepository.delete(booking);
			clubMember.setClubMemberBookingRecordId(null);
			clubMemberRepository.save(clubMember);
			return "取消預約成功 !";
		}
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	public String updateBookingCourtUseDate(@PathVariable("id") Integer id,@RequestBody Booking useDate) {
		ClubMember clubMember = clubMemberRepository.findById(id).get();
		try {
			Booking booking = bookingRepository.findById(clubMember.getClubMemberBookingRecordId()).get();
			booking.setUseDate(useDate.getUseDate());
			bookingRepository.save(booking);
			return "預約時間修改成功 !";
		} catch (Exception e) {
			return "沒有預約紀錄, 請開始球場預約";
		}
	}

}
