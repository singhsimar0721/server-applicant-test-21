/**
 * 
 */
package com.freenow.datatransferobject;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.freenow.domainobject.DriverDO;
import com.freenow.domainvalue.CarEngine;
import com.freenow.domainvalue.VehicleType;

/**
 * Data Transfer Object for Car.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO {

	@JsonIgnore
	private Long id;

	
	private String manufacturer;

	@NotNull(message = "License Plate can not be null!")
	private String license_plate;

	@NotBlank(message = "Seat Count can not be blank!!")
	private Integer seat_count;

	
	private Boolean is_convertible; 

	@NotNull(message = "Rating can not be null!")
	private Double rating;

	
	private CarEngine engine;

	@NotNull(message = "Model can not be null!")
	private String model;

	
	private String origin;

	@NotNull(message = "Vehicle Type can not be null!")
	private VehicleType vehicle_type;
	
	private DriverDTO driverDTO;
	

	private CarDTO() {
	}

	private CarDTO(Long id,String license_plate,String model,VehicleType vehicle_type, Integer seat_count,Double rating,String manufacturer, String origin, Boolean is_convertible,DriverDTO driverDTO) {
		this.id=id;
		this.license_plate=license_plate;
		this.model=model;
		this.vehicle_type=vehicle_type;
		this.seat_count=seat_count;
		this.rating=rating;
		this.manufacturer=manufacturer;
		this.origin=origin;
		this.is_convertible=is_convertible;
		this.driverDTO = driverDTO;
	}

	@JsonProperty
	public Long getId() {
		return id;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public String getLicense_plate() {
		return license_plate;
	}

	public Integer getSeat_count() {
		return seat_count;
	}

	public Boolean getIs_convertible() {
		return is_convertible;
	}

	public Double getRating() {
		return rating;
	}

	public CarEngine getEngine() {
		return engine;
	}

	public String getModel() {
		return model;
	}

	public String getOrigin() {
		return origin;
	}

	public VehicleType getVehicle_type() {
		return vehicle_type;
	}
	
	public DriverDTO getDriverDTO() {
		return driverDTO;
	}

	public static CarDTOBuilder newBuilder() {
		return new CarDTOBuilder();
	}

	public static class CarDTOBuilder
	{
		private Long id;
		private String manufacturer;
		private String license_plate;
		private Integer seat_count;
		private Boolean is_convertible; 
		private Double rating;
		private CarEngine engine;
		private String model;
		private String origin;
		private VehicleType vehicle_type;
		private DriverDTO driverDTO;


		public CarDTOBuilder setId(Long id) {
			this.id = id;
			return this;
		}
		public CarDTOBuilder setManufacturer(String manufacturer) {
			this.manufacturer = manufacturer;
			return this;
		}
		public CarDTOBuilder setLicense_plate(String license_plate) {
			this.license_plate = license_plate;
			return this;
		}
		public CarDTOBuilder setSeat_count(Integer seat_count) {
			this.seat_count = seat_count;
			return this;
		}
		public CarDTOBuilder setIs_convertible(Boolean is_convertible) {
			this.is_convertible = is_convertible;
			return this;
		}
		public CarDTOBuilder setRating(Double rating) {
			this.rating = rating;
			return this;
		}
		public CarDTOBuilder setEngine(CarEngine engine) {
			this.engine = engine;
			return this;
		}
		public CarDTOBuilder setModel(String model) {
			this.model = model;
			return this;
		}
		public CarDTOBuilder setOrigin(String origin) {
			this.origin = origin;
			return this;
		}
		public CarDTOBuilder setVehicle_type(VehicleType vehicle_type) {
			this.vehicle_type = vehicle_type;
			return this;
		}
		
		public void setDriverDTO(DriverDTO driverDTO) {
			this.driverDTO = driverDTO;
		}
		public CarDTO createCarDTO()
		{
			return new CarDTO(id, license_plate, model, vehicle_type,seat_count,rating,manufacturer,origin,is_convertible,driverDTO);
		}
	}
}
