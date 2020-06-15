/**
 * 
 */
package com.freenow.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.freenow.controller.mapper.CarMapper;
import com.freenow.datatransferobject.CarDTO;
import com.freenow.domainobject.CarDO;
import com.freenow.domainvalue.VehicleType;
import com.freenow.exception.ConstraintsViolationException;
import com.freenow.exception.EntityNotFoundException;
import com.freenow.service.car.CarService;

/**
 *	All operations with the Cars will be routed by this controller.
 */
@RestController
@RequestMapping("v1/cars")
public class CarController {

	private CarService carService;

	@Autowired
	public CarController(final CarService carService)
	{
		this.carService = carService;
	}
	
	@GetMapping
    public List<CarDTO> findCars(@RequestParam VehicleType type)
    {
        return CarMapper.makeCarDTOList(carService.find(type));
    }

	@GetMapping("/{carId}")
	public CarDTO getCar(@PathVariable long carId) throws EntityNotFoundException
	{
		return CarMapper.makeCarDTO(carService.find(carId));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CarDTO createCar(@Valid @RequestBody CarDTO carDTO) throws ConstraintsViolationException
	{
		CarDO carDO = CarMapper.makeCarDO(carDTO);
		return CarMapper.makeCarDTO(carService.create(carDO));
	}

	@DeleteMapping("/{carId}")
	public void deleteCar(@PathVariable long carId) throws EntityNotFoundException
	{
		carService.delete(carId);
	}
	
}
