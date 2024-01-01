package com.mvc.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mvc.lab.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer>{

}
