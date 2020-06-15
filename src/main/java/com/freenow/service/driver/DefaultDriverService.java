package com.freenow.service.driver;

import com.freenow.dataaccessobject.DriverRepository;
import com.freenow.domainobject.CarDO;
import com.freenow.domainobject.DriverDO;
import com.freenow.domainvalue.GeoCoordinate;
import com.freenow.domainvalue.OnlineStatus;
import com.freenow.exception.CarAlreadyInUseException;
import com.freenow.exception.ConstraintsViolationException;
import com.freenow.exception.EntityNotFoundException;
import com.freenow.exception.NoOfflineDriverSelectCarException;
import com.freenow.search.SearchDriver;
import com.freenow.service.car.CarService;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific things.
 * <p/>
 */
@Service
public class DefaultDriverService implements DriverService
{

    private static final Logger LOG = LoggerFactory.getLogger(DefaultDriverService.class);

    private final DriverRepository driverRepository;
    private final CarService carService;
    @Autowired
    private SearchDriver searchDriver;


	public DefaultDriverService(final DriverRepository driverRepository, final CarService carService)
	{
		this.driverRepository = driverRepository;
		this.carService=carService;
	}


    /**
     * Selects a driver by id.
     *
     * @param driverId
     * @return found driver
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    public DriverDO find(Long driverId) throws EntityNotFoundException
    {
        return findDriverChecked(driverId);
    }


    /**
     * Creates a new driver.
     *
     * @param driverDO
     * @return
     * @throws ConstraintsViolationException if a driver already exists with the given username,
     */
    @Override
    public DriverDO create(DriverDO driverDO) throws ConstraintsViolationException
    {
        DriverDO driver;
        try
        {
            driver = driverRepository.save(driverDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("ConstraintsViolationException while creating a driver: {}", driverDO, e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return driver;
    }


    /**
     * Deletes an existing driver by id.
     *
     * @param driverId
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long driverId) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setDeleted(true);
    }


    /**
     * Update the location for a driver.
     *
     * @param driverId
     * @param longitude
     * @param latitude
     * @throws EntityNotFoundException
     */
    @Override
    @Transactional
    public void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setCoordinate(new GeoCoordinate(latitude, longitude));
    }


    /**
     * Find all drivers by online state.
     *
     * @param onlineStatus
     */
    @Override
    public List<DriverDO> find(OnlineStatus onlineStatus)
    {
        return driverRepository.findByOnlineStatus(onlineStatus);
    }


    private DriverDO findDriverChecked(Long driverId) throws EntityNotFoundException
    {
        return driverRepository.findById(driverId)
            .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + driverId));
    }
    
    /**
	 * To assign the selected car to particular driver.
     * @throws EntityNotFoundException 
     * @throws CarAlreadyInUseException 
     * @throws NoOfflineDriverSelectCarException 
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void updateCarSelection(long driverId, long carId) throws EntityNotFoundException, CarAlreadyInUseException, NoOfflineDriverSelectCarException  {
		CarDO carDO = null;
		DriverDO driverDO = findDriverChecked(driverId);
		if(driverDO.getOnlineStatus().equals(OnlineStatus.ONLINE)) {
			carDO = carService.find(carId);
			selectCar(driverDO, carDO);
		}else {
			throw new NoOfflineDriverSelectCarException("Invalid operation - only ONLINE driver can make car selection.");
		}
	}
    
    /**
	 * Utility method to update car status.
	 * @param driverDO
	 * @param carDO
	 * @throws CarAlreadyInUseException
	 * @throws EntityNotFoundException
	 */
    
	private void selectCar(DriverDO driverDO,CarDO carDO) throws CarAlreadyInUseException, EntityNotFoundException {
		if(!carDO.getIsInUse()) {
			carDO.setIsInUse(true);
			driverDO.setCarDO(carDO);
			driverRepository.save(driverDO);
		}else {
			throw new CarAlreadyInUseException("Unavailable - Car already in use, carId "+carDO.getId());
		}
	}
	
	/**
	 * To unselect the selected car by Driver.
	 * @param driverId
	 * @throws EntityNotFoundException
	 */
	@Override
	@Transactional
	public void unselectCar(long driverId) throws EntityNotFoundException {
		DriverDO driverDO = findDriverChecked(driverId);
		CarDO carDO = driverDO.getCarDO();
		if(carDO!=null) {
			carDO.setIsInUse(false);
			carService.update(carDO);
			driverDO.setCarDO(null);
			driverRepository.save(driverDO);
		}else {
			throw new EntityNotFoundException("No car assigned to driverId: "+driverId);
		}
	}
	
	@Override
	public List<DriverDO> search(String text) {
		return searchDriver.search(text);
	}


	

}
