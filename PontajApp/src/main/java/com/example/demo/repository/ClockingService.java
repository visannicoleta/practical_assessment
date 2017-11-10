package com.example.demo.repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.example.demo.classes.ClockingDetails;

@Service
@Transactional
public class ClockingService {

	@Autowired
	ClockingDetailsRepository clockingRepository;

	public ClockingDetails saveClockingDetails(Date currentDate, String start, String stop) {

		ClockingDetails clockingDetails = new ClockingDetails();
		clockingDetails.setCurrentDate(currentDate);
		clockingDetails.setStart(start);
		clockingDetails.setStop(stop);

		return clockingRepository.save(clockingDetails);
	}

	public List<ClockingDetails> findAllClockingDetails() {
		return (ArrayList<ClockingDetails>) clockingRepository.findAll();
	}
	
//	public List<ClockingDetails> findByMonth(Date currentDate){
//		return clockingRepository.findByMonthSpecial(currentDate);
//	}
	

}
