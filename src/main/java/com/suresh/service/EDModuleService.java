package com.suresh.service;

import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.suresh.entities.COTriggers;
import com.suresh.entities.EligibilityDetails;
import com.suresh.repository.COTriggersRepository;
import com.suresh.repository.CitizenRepository;
import com.suresh.requestbindings.CitizenData;
import com.suresh.responsebindings.PlanInfo;

@Service
public class EDModuleService {

	@Autowired
	private CitizenRepository repo;
	@Autowired
	private COTriggersRepository repo1;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public PlanInfo getPlanInfo(CitizenData citizendata) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<CitizenData> requestEntity = new HttpEntity<>(citizendata, headers);
		
		 PlanInfo postForObject = restTemplate.
		postForObject("https://ashokit-ed-rules-api.herokuapp.com/rules", requestEntity, PlanInfo.class );
		 
		
		EligibilityDetails entity = new EligibilityDetails();
		entity.setCaseNo(new Random().nextInt(1000));
		
		BeanUtils.copyProperties(postForObject, entity);
		
		COTriggers coTriggers = new COTriggers();
		coTriggers.setCaseNo(entity.getCaseNo());
		coTriggers.setTriggerStatus("Pending");
		
		
		repo.save(entity);
		repo1.save(coTriggers);
		
		return postForObject;
	}

}
