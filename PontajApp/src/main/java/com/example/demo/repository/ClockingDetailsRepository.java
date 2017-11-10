package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.classes.ClockingDetails;

public interface ClockingDetailsRepository extends CrudRepository<ClockingDetails, Integer>{

//	@Query("SELECT c from ClockingDetails e where MONTH(c.currentDate) = MONTH(?1)")
//	public List<ClockingDetails> findByMonthSpecial(Date currentDate);
}
