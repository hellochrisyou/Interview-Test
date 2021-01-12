package com.odx.test;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

// Extends CrudRepository for JPA implementation.
public interface Repository extends CrudRepository<DemoEntity, Long> {

	  String findByName(String name);

	  void save(List<DemoEntity> thisRepo);
}
