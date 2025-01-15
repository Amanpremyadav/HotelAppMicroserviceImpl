package com.service.hotel.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staffs")
public class StaffController {
	@GetMapping
	public ResponseEntity<List<String>> getStaffs() {
		List<String> staffs = Arrays.asList("Ram", "Aman", "Preet", "Dhoni");

		return new ResponseEntity<List<String>>(staffs, HttpStatus.OK);
	}
}
