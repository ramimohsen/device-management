package org.devicemanagement.repository;

import org.devicemanagement.model.Device;
import org.devicemanagement.model.enums.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    List<Device> findDevicesByBrand(Brand brand);
}
