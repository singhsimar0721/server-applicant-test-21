/**
 * 
 */
package com.freenow.dataaccessobject;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.freenow.domainobject.CarDO;
import com.freenow.domainvalue.VehicleType;

/**
 * @author Simarpreet Singh, 09 Aug, 19
 * 
 *  Database Access Object for Car table, to interact with database.
 */

public interface CarRepository extends CrudRepository<CarDO, Long> {

	List<CarDO> findByVehicleType(VehicleType vehicleType);
	CarDO findByLicensePlate(String licensePlate);
	
}
