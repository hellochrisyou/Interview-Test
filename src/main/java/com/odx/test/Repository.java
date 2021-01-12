package com.odx.test;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

// Extends CrudRepository for JPA implementation.  Can Add Repository annotation if desired.
// The Java Persistence API (JPA) is a Java specification for accessing, persisting, and managing data between Java objects / classes and a relational database. 
public interface Repository extends CrudRepository<DemoEntity, Long> {

	  String findByName(String name);

	  void save(List<DemoEntity> thisRepo);
}
