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

// Decorator that annotates this class as a controller component.  The controller performs business logic which can be relayed in a service layer as well.
// Includes @Controller and @ResponseBody annotation.
// This annotation is applied to a class to mark it as a request handler. 
// Spring RestController annotation is used to create RESTful web services using Spring MVC.
@RestController
public class Restcontroller {
	
	// The Spring framework enables automatic dependency injection via @Autowiring.
	@Autowired
	private Repository repo;
	
	// Http Post Methods will be navigated to this request handler from the Dispatcher Servlet (Front controller).
	@RequestMapping(method = RequestMethod.POST,
                    value = "/",
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public void receiveString(@RequestBody String str) {
		// Beginning and end indices of longest Palindromic substring.
	    int maxLen = 1, begin = 0;
	    // Two outer loops pick all sub strings starting from corner to corner.
	    // 2nd for loop increases the substring from the root index (i) until the end of the string. Then the root index increases by 1 until it reaches the end.
		for (int i = 0; i < str.length(); i++) {
	        for (int j = i; j < str.length(); j++) {
	            boolean isPalindrome = true;
	            // Inner loop check Palindrome.
	            // "k<(j-i+1)/2" represents the inner loop iterating half the size of the array.
	            // "(i+k)" and "(j-k)" represent the inner loop growing and shrinking the substring while validating if it is a Palindrome.
	            for (int k = 0; k < (j - i + 1) / 2; k++)
	                if (str.charAt(i + k) != str.charAt(j - k))
	                    isPalindrome = false;
	 
	            // If the substring is a Palindrome, then record beginning and end indices
	            // "j-i+1" represents the length of substring
	            if (isPalindrome == true && (j - i + 1) > maxLen) {
	            	begin = i; 
	            	maxLen = j - i + 1;
	            }
	        }
		}
		// Use String Builder to extract substring from string.
		StringBuilder sb = new StringBuilder();
	    for (int i = begin; i <= begin + maxLen -1; ++i) {		        
	    	sb.append(str.charAt(i));
	    }
	    // Retrieve existing repository List Object (if it exists).
	    List<DemoEntity> thisRepo = (List<DemoEntity>) repo.findAll();
	    // If the repository is empty then create a new object and add it to the Repository List.
	    if (thisRepo.size() == 0) {
	    	DemoEntity tmpEntity = new DemoEntity(sb.toString());
	    	tmpEntity.setName(sb.toString());
	    	thisRepo.add(tmpEntity);
	    }
	    // Otherwise, iterate through the list (there should be no more than one element)) and set the name field/column to the acquired palindromic substring.
	    else {
		    for (DemoEntity tmp: thisRepo) {		
		    	tmp.setName(sb.toString());
		    }
	    }
	    // Print out the string for testing
	    System.out.print(sb.toString());
	    // Persist data into the database via Repository save function.
	    repo.saveAll(thisRepo);
    }
	
	// Http Get Methods will be navigated to this request handler from the Dispatcher Servlet (Front controller).
	@RequestMapping(method = RequestMethod.GET,
            value = "/",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String returnLongestPalindrome() {
		 List<DemoEntity> tmpEntity = (List<DemoEntity>) repo.findAll();
		 // List either has one or no elements.  If the list is empty, it will return null.
		 for (DemoEntity tmp: tmpEntity) {
			 return tmp.getName();
		 }
		 return null;
	 }
}