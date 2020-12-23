package com.odx.test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="demo")
public class DemoEntity {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="id")
  private Long id = (long) 1;
  @Column(name="name")
  private String name;

  protected DemoEntity() {}

  public DemoEntity(String name) {
    this.name = name;
  }

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

  
}