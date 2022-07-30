package com.suresh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.suresh.requestbindings.CitizenData;
import com.suresh.responsebindings.PlanInfo;
import com.suresh.service.EDModuleService;

@RestController
public class EDModuleRestController {

	@Autowired
	private EDModuleService service;
	
	@PostMapping("/rules")
	public ResponseEntity<PlanInfo> getPlanInfo(@RequestBody CitizenData citizendata) {
		PlanInfo planInfo = service.getPlanInfo(citizendata);
		return new ResponseEntity<>(planInfo, HttpStatus.OK);
	}
	
}
