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
    private JdbcTemplate jdbcTemplate;
	@Autowired
	private Repository repo;
	@RequestMapping(method = RequestMethod.POST,
                    value = "/",
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public void receiveString(@RequestBody String str) {
	    int n = str.length(), maxLength = 1, start = 0;
	    
		for (int i = 0; i < str.length(); i++) {
	        for (int j = i; j < str.length(); j++) {
	            int flag = 1;
	 
	            // Check palindrome
	            for (int k = 0; k < (j - i + 1) / 2; k++)
	                if (str.charAt(i + k) != str.charAt(j - k))
	                    flag = 0;
	 
	            // Palindrome
	            if (flag!=0 && (j - i + 1) > maxLength) {
	                start = i;
	                maxLength = j - i + 1;
	            }
	        }
		}
		StringBuilder sb = new StringBuilder();
	    for (int i = start; i <= start + maxLength -1; ++i) {		        
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