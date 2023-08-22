package com.example.eurukaCilent2.Entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "restro")
public class RestroEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column
  private String name;

  @Column
  private int licenseNo;

  public RestroEntity() {}

  public RestroEntity(int id, String name, int licenseNo) {
    this.id = id;
    this.name = name;
    this.licenseNo = licenseNo;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getLicenseNo() {
    return this.licenseNo;
  }

  public void setLicenseNo(int licenseNo) {
    this.licenseNo = licenseNo;
  }
}
