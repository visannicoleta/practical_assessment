package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.classes.ClockingDetails;

public interface ClockingDetailsRepository extends CrudRepository<ClockingDetails, Integer>{

//	@Query("from ClockingDetails c where to_char(currentDate, 'YYYY/MM') = '2017/11' ")
//	public List<ClockingDetails> findByMonthSpecial(Date date);
}
