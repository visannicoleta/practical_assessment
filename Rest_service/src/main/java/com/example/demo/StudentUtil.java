package com.example.demo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StudentUtil {

	/**
	 * 
	 * @return current date
	 */
	public static String calculateDate(){
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY HH:mm");
		return dateFormat.format(new Date());
	}
	
	/**
	 * 
	 * @param stud
	 * @return concatenated firstName and lastName with a space and all CAPS
	 */
	public static String concatenate(Student stud){
		StringBuilder builder = new StringBuilder();
		builder.append(stud.getStudentFirstName().toUpperCase());
		builder.append(" "+stud.getStudentLastName().toUpperCase());
		
		return builder.toString();
	}
	
	/**
	 * create StudentResult object based on Student object parameter
	 * static business methods were used
	 * @param stud
	 * @return StudentResult object
	 */
	public static StudentResult createStudentResult(Student stud){
		StudentResult studResult = new StudentResult();
		studResult.setCalculationDate(calculateDate());
		studResult.setResult(concatenate(stud));
		
		return studResult;
	}
}
