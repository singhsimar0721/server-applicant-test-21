/**
 * 
 */
package com.freenow.domainobject;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;

import com.freenow.domainvalue.CarEngine;
import com.freenow.domainvalue.VehicleType;

/**
 * @author Simarpreet Singh, created on 09 Aug, 19
 * 
 * Domain Object responsible to store Cars related information.
 */
@Entity
@Table(
		name = "car",
		uniqueConstraints = @UniqueConstraint(name = "uc_licensePlate", columnNames = {"licensePlate"})
		)
public class CarDO {

	@Id
	@GenericGenerator(name = "generator", strategy = "foreign", 
	parameters = @Parameter(name = "property", value = "driver"))
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(nullable = false)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime dateCreated = ZonedDateTime.now();

	@Column(nullable = false)
	private String manufacturer;

	@Column(nullable=false)
	private String licensePlate;

	@Column(nullable = false)
	private Integer seatCount;

	@Column(nullable = true)
	private Boolean isConvertible=false; 

	@Column(nullable = false)
	private Double rating;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private CarEngine engine;

	@Column(nullable = false)
	private String model;

	private String origin;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private VehicleType vehicleType;

	@Column(nullable = false)
	private Boolean deleted = false;
	
	@Column(nullable = true)
	private Boolean isInUse=false;
	
	
	public CarDO() {
		
	}
	
	public CarDO(String license_plate) {
		this.licensePlate=license_plate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ZonedDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(ZonedDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public Boolean getIsInUse() {
		return isInUse;
	}

	public void setIsInUse(Boolean isInUse) {
		this.isInUse = isInUse;
	}
	public Integer getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(Integer seatCount) {
		this.seatCount = seatCount;
	}

	public Boolean getIsConvertible() {
		return isConvertible;
	}

	public void setIsConvertible(Boolean isConvertible) {
		this.isConvertible = isConvertible;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public CarEngine getEngine() {
		return engine;
	}

	public void setEngine(CarEngine engine) {
		this.engine = engine;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}	

}
