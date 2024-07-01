package org.devicemanagement.service;

import org.devicemanagement.dto.DeviceDTO;
import org.devicemanagement.exception.custom.DeviceNotFoundException;
import org.devicemanagement.model.Device;

import java.util.List;

public interface IDevice {

    /**
     * Get device by id
     *
     * @param id device id
     * @return Device
     */
    Device getDeviceById(Long id) throws DeviceNotFoundException;

    /**
     * Save device
     *
     * @param device device to save
     * @return Device
     */
    Device saveDevice(DeviceDTO device);

    /**
     * Delete device by id
     *
     * @param id device id
     */
    void deleteDevice(Long id) throws DeviceNotFoundException;

    Device updateDevice(DeviceDTO device) throws DeviceNotFoundException;

    /**
     * Get all devices
     *
     * @return {@link List}<Device>
     */
    List<Device> getAllDevices();

    /**
     * Get devices by brand
     *
     * @param brand device brand
     * @return {@link List}<Device>
     */
    List<Device> getDevicesByBrand(String brand);
}
