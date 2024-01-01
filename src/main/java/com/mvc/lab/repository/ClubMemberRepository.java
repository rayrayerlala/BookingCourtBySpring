package com.mvc.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mvc.lab.entity.ClubMember;

public interface ClubMemberRepository extends JpaRepository<ClubMember, Integer>{

}
