/**
 * 
 */
package com.freenow.service.car;

import java.util.List;

import com.freenow.domainobject.CarDO;
import com.freenow.domainvalue.VehicleType;
import com.freenow.exception.ConstraintsViolationException;
import com.freenow.exception.EntityNotFoundException;

/**
 * @author created on 09 Aug,19
 *	
 */
public interface CarService {

	CarDO find(Long carId) throws EntityNotFoundException;
	CarDO create(CarDO carDO) throws ConstraintsViolationException;
	void delete(Long carId) throws EntityNotFoundException;
	CarDO findByLicensePlate(String licensePlate) throws EntityNotFoundException;
	void update(CarDO carDO) throws EntityNotFoundException;
	List<CarDO> find(VehicleType vehicleType);
}
