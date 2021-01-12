package com.odx.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Restcontroller {
	
	@Autowired
	private Repository repo;
	@RequestMapping(method = RequestMethod.POST,
                    value = "/",
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public void receiveString(@RequestBody String str) {
	    int end = 1, beginning = 0;
	    // Two outer loops pick all sub strings starting from corner to corner
		for (int i = 0; i < str.length(); i++) {
	        for (int j = i; j < str.length(); j++) {
	            boolean isPalindrome = true;
	            // Inner loop check palindrome
	            for (int k = 0; k < (j - i + 1) / 2; k++)
	                if (str.charAt(i + k) != str.charAt(j - k))
	                    isPalindrome = false;
	 
	            // If palindrome, then record beginning and end indices
	            if (isPalindrome == true && (j - i + 1) > end) {
	            	beginning = i;
	                end = j - i + 1;
	            }
	        }
		}
		StringBuilder sb = new StringBuilder();
	    for (int i = beginning; i <= beginning + end -1; ++i) {		        
	    	sb.append(str.charAt(i));
	    }
	    List<DemoEntity> thisRepo = (List<DemoEntity>) repo.findAll();
	    if (thisRepo.size() == 0) {
	    	DemoEntity tmpEntity = new DemoEntity(sb.toString());
	    	tmpEntity.setName(sb.toString());
	    	thisRepo.add(tmpEntity);
	    }
	    else {
		    for (DemoEntity tmp: thisRepo) {		    	
		    	tmp.setName(sb.toString());
		    }
	    }
	    System.out.print(sb.toString());
	    repo.saveAll(thisRepo);
    }
		  
	@RequestMapping(method = RequestMethod.GET,
            value = "/",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String returnLongestPalindrome() {
		 List<DemoEntity> tmpEntity = (List<DemoEntity>) repo.findAll();
		 for (DemoEntity tmp: tmpEntity) {
			 return tmp.getName();
		 }
		 return null;
	 }
}