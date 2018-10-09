package com.bass.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bass.test.service.StudentService;
import com.bass.test.vo.StudentInfo;

@Controller
public class StudentController {
	
	@Autowired
	private StudentService ss;

	@RequestMapping( value="/studentPage", method=RequestMethod.GET)
	@ResponseBody
	public List<StudentInfo> getStudentList(@ModelAttribute StudentInfo si){
		return ss.getStudentList(si);
	}
	@RequestMapping(value="/studentPage/{studentnum}",method=RequestMethod.GET)
	@ResponseBody
	public StudentInfo getStudent(@PathVariable Integer studentnum) {
		return ss.getStudent(studentnum);
	}
	
	@RequestMapping(value="/studentPage",method=RequestMethod.POST)
	@ResponseBody
	public Integer insertStudent(@RequestBody StudentInfo si) {
		return ss.insertStudent(si);
	}
	
	@RequestMapping(value="/studentPage/{studentnum}",method=RequestMethod.PUT)
	@ResponseBody
	public Integer updateStudent(@RequestBody StudentInfo si,@PathVariable Integer studentnum) {
		si.setStudent_num(studentnum);
		return ss.updateStudent(si);
	}
	
	@RequestMapping(value="/studentPage/{studentnum}",method=RequestMethod.DELETE)
	@ResponseBody
	public Integer updateStudent(@PathVariable Integer studentnum) {
		return ss.deleteStudent(studentnum);
	}
	
	
}
