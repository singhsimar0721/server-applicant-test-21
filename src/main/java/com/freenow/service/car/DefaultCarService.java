/**
 * 
 */
package com.freenow.service.car;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freenow.dataaccessobject.CarRepository;
import com.freenow.domainobject.CarDO;
import com.freenow.domainvalue.VehicleType;
import com.freenow.exception.ConstraintsViolationException;
import com.freenow.exception.EntityNotFoundException;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for Car specific things.
 */
@Service
public class DefaultCarService implements CarService
{

	private static final Logger LOG = LoggerFactory.getLogger(DefaultCarService.class);
	
	private final CarRepository carRepository;
	
	public DefaultCarService(final CarRepository carRepository) {
		this.carRepository = carRepository;
	}
	
	/**
	 * Selects a car by carId 
	 * @param carId
	 * @return CarDO Object.
	 * @throws EntityNotFoundException
	 */
	@Override
	public CarDO find(Long carId) throws EntityNotFoundException{
		return findCarChecked(carId);
	}
	
	/**
     * Creates a new car.
     * @param carDO
     * @return CarDO Object
     * @throws ConstraintsViolationException if a car already exists with the given plate_number, ... .
     */
	@Override
    public CarDO create(CarDO carDO) throws ConstraintsViolationException
    {
    	CarDO car;
        try
        {
        	car = carRepository.save(carDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("ConstraintsViolationException while creating a car: {}", carDO, e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return car;
    }
	
	/**
	 * Update the car details like availability.
	 *
	 * @param driverId
	 * @param longitude
	 * @param latitude
	 * @throws EntityNotFoundException
	 */
	@Override
	@Transactional
	public void update(CarDO carDO) throws EntityNotFoundException
	{
		carRepository.save(carDO);
	}
    
    /**
     * Deletes an existing car by id.
     *
     * @param carId
     * @throws EntityNotFoundException if no car with the given id was found.
     */
	@Override
    @Transactional
    public void delete(Long carId) throws EntityNotFoundException
    {
    	CarDO carDO = findCarChecked(carId);
    	carDO.setDeleted(true);
    }
	
	@Override
	public CarDO findByLicensePlate(String licensePlate) throws EntityNotFoundException
	{
		return findCarByLicensePlateChecked(licensePlate);
	}
	
	
	
	/**
	 * Utility method to verify, if car exist with provided carId.
	 * @param carId
	 * @return CarDO object
	 * @throws EntityNotFoundException if no car exist for carId. 
	 */
	private CarDO findCarChecked(Long carId) throws EntityNotFoundException
    {
        return carRepository.findById(carId)
            .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + carId));
    }
	
	/**
	 * Utility method to verify, if car exist with provided LICENSE PLATE.
	 * @param carId
	 * @return CarDO object
	 * @throws EntityNotFoundException if no car exist for carId. 
	 */
	private CarDO findCarByLicensePlateChecked(String licensePlate) throws EntityNotFoundException
    {
		CarDO carDO = carRepository.findByLicensePlate(licensePlate);
		if(carDO==null)
			throw new EntityNotFoundException("Could not find entity with License Plate: " + licensePlate);
        return carDO; 
    }
	
	/**
	 * To get list of all the cars of particular type.
	 */
	@Override
    public List<CarDO> find(VehicleType vehicle_type)
    {
        return carRepository.findByVehicleType(vehicle_type);
    }
}
