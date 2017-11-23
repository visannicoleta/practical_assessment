package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

//	@RequestMapping(value = "/")
//	public ResponseEntity<StudentResult> testStudentResult(){
//		Student stud = new Student();
//		stud.setStudentFirstName("John");
//		stud.setStudentLastName("Doe");
//		StudentResult studResult = StudentUtil.createStudentResult(stud);
//		
//		return new ResponseEntity<StudentResult>(studResult, HttpStatus.OK);
//	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<StudentResult> postStudentResult(@RequestBody Student stud){
//		Student stud = new Student();
//		stud.setStudentFirstName("John");
//		stud.setStudentLastName("Doe");
		
		StudentResult studResult = null;
		
		if(stud != null){
			studResult = StudentUtil.createStudentResult(stud);
			
			return new ResponseEntity<StudentResult>(studResult, HttpStatus.OK);
		}
		else{
			return new ResponseEntity<StudentResult>(studResult, HttpStatus.NOT_FOUND);
		}
		
		
	}
}
