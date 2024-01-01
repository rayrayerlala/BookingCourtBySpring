package com.mvc.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mvc.lab.entity.Court;

public interface CourtRepository extends JpaRepository<Court, Integer>{

}
