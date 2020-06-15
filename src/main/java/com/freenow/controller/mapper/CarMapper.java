/**
 * 
 */
package com.freenow.controller.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.freenow.datatransferobject.CarDTO;
import com.freenow.domainobject.CarDO;

/**
 * Car Mapper to create CarDTO from CarDO and vice versa.
 *
 */
public class CarMapper {

	public static CarDO makeCarDO(CarDTO carDTO)
	{
		return new CarDO(carDTO.getLicense_plate());
	}


	public static CarDTO makeCarDTO(CarDO carDO)
	{
		CarDTO.CarDTOBuilder carDTOBuilder = CarDTO.newBuilder()
				.setId(carDO.getId())
				.setLicense_plate(carDO.getLicensePlate())
				.setEngine(carDO.getEngine())
				.setManufacturer(carDO.getManufacturer())
				.setModel(carDO.getModel())
				.setOrigin(carDO.getOrigin())
				.setRating(carDO.getRating())
				.setSeat_count(carDO.getSeatCount())
				.setVehicle_type(carDO.getVehicleType())
				.setIs_convertible(carDO.getIsConvertible());
			
		return carDTOBuilder.createCarDTO();
	}


	public static List<CarDTO> makeCarDTOList(Collection<CarDO> cars)
	{
		System.out.println();
		return cars.stream()
				.map(CarMapper::makeCarDTO)
				.collect(Collectors.toList());
	}
}
