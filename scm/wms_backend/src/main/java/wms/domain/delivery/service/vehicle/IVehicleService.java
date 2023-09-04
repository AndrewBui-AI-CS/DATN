package wms.domain.delivery.service.vehicle;

import com.fasterxml.jackson.core.JsonProcessingException;
import wms.common.ReturnPaginationDTO;
import wms.domain.delivery.entity.DroneEntity;
import wms.domain.delivery.entity.TruckEntity;
import wms.domain.delivery.dto.vehicle.DroneDTO;
import wms.domain.delivery.dto.vehicle.DroneResponseDTO;
import wms.domain.delivery.dto.vehicle.TruckDTO;
import wms.domain.delivery.dto.vehicle.TruckResponseDTO;
import wms.exception.CustomException;

public interface IVehicleService {
    TruckEntity createTruck(TruckDTO truckDTO) throws CustomException;
    ReturnPaginationDTO<TruckResponseDTO> getAllTrucks(int page, int pageSize, String sortField, boolean isSortAsc) throws JsonProcessingException;
    TruckEntity getTruckById(long id);
    TruckEntity getTruckByCode(String code);
    TruckEntity updateTruck(TruckDTO truckDTO, long id) throws CustomException;
    void deleteTruckById(long id);
    void deleteTruckByCode(String code) throws CustomException;
    DroneEntity createDrone(DroneDTO droneDTO) throws CustomException;
    ReturnPaginationDTO<DroneResponseDTO> getAllDrones(int page, int pageSize, String sortField, boolean isSortAsc) throws JsonProcessingException;
    DroneEntity getDroneById(long id);
    DroneEntity getDroneByCode(String code);
    DroneEntity updateDrone(DroneDTO droneDTO, long id) throws CustomException;
    void deleteDroneById(long id);
    void deleteDroneByCode(String code) throws CustomException;
}
