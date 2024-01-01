package com.mvc.lab.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.lab.dto.BookingDto;
import com.mvc.lab.dto.ClubMemberDto;
import com.mvc.lab.dto.ClubMemberRequest;
import com.mvc.lab.entity.Booking;
import com.mvc.lab.entity.ClubMember;
import com.mvc.lab.repository.BookingRepository;
import com.mvc.lab.repository.ClubMemberRepository;

@Controller
@RequestMapping("/club_member")
public class ClubMemberController {
	@Autowired
	private ClubMemberRepository clubMemberRepository;
	@Autowired
	private BookingRepository bookingRepository;
	
	@GetMapping("/")
	@ResponseBody
	public List<ClubMemberDto> findAllClubMembers(){
		List<ClubMember> clubMembers = clubMemberRepository.findAll();
		List<ClubMemberDto> clubMemberDtos = new ArrayList<>();
		for(ClubMember clubMember:clubMembers) {
			ClubMemberDto clubMemberDto = new ClubMemberDto();
			Booking booking = bookingRepository.findById(clubMember.getClubMemberBookingRecordId()).get();
			BookingDto bookingDto = new BookingDto();
	        bookingDto.setBookingId(booking.getBookingId());
	        bookingDto.setCourtName(booking.getCourt().getCourtName());
	        bookingDto.setClubMemberName(booking.getClubMember().getClubMemberName());
	        bookingDto.setUseDate(booking.getUseDate());
	        bookingDto.setCreateDate(booking.getCreateDate());
			clubMemberDto.setClubMember(clubMember);
			clubMemberDto.setBookingDto(bookingDto);
			clubMemberDtos.add(clubMemberDto);
		}
		return clubMemberDtos;
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public ClubMemberDto findClubMemberById(@PathVariable("id") Integer id){
		ClubMember clubMember = clubMemberRepository.findById(id).get();
		ClubMemberDto clubMemberDto = new ClubMemberDto();
		Booking booking = bookingRepository.findById(clubMember.getClubMemberBookingRecordId()).get();
		BookingDto bookingDto = new BookingDto();
        bookingDto.setBookingId(booking.getBookingId());
        bookingDto.setCourtName(booking.getCourt().getCourtName());
        bookingDto.setClubMemberName(booking.getClubMember().getClubMemberName());
        bookingDto.setUseDate(booking.getUseDate());
        bookingDto.setCreateDate(booking.getCreateDate());
		clubMemberDto.setClubMember(clubMember);
		clubMemberDto.setBookingDto(bookingDto);
		
		return clubMemberDto;
	}
	
	@PostMapping("/register")
    @ResponseBody
    public ResponseEntity<String> registerClubMember(@RequestBody ClubMemberRequest clubMemberRequest) {
        // 將從請求中接收到的 JSON 資料轉換為 ClubMember 物件
        ClubMember newClubMember = new ClubMember();
        newClubMember.setClubMemberUsername(clubMemberRequest.getClubMemberUsername());
        newClubMember.setClubMemberPassword(clubMemberRequest.getClubMemberPassword());
        newClubMember.setClubMemberName(clubMemberRequest.getClubMemberName());
        newClubMember.setClubMemberBirth(clubMemberRequest.getClubMemberBirth());

        // 進行註冊（保存到資料庫等操作）
        clubMemberRepository.save(newClubMember);

        // 回傳成功訊息
        return ResponseEntity.ok("Club member registered successfully");
    }
}
