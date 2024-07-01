package org.devicemanagement.service;

import lombok.RequiredArgsConstructor;
import org.devicemanagement.dto.DeviceDTO;
import org.devicemanagement.exception.custom.DeviceNotFoundException;
import org.devicemanagement.model.Device;
import org.devicemanagement.model.enums.Brand;
import org.devicemanagement.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceService implements IDevice {

    private final DeviceRepository deviceRepository;

    @Override
    public Device getDeviceById(Long id) throws DeviceNotFoundException {
        return deviceRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFoundException("Device not found"));
    }

    @Override
    public Device saveDevice(DeviceDTO device) {
        return deviceRepository.save(Device.builder()
                .name(device.getName())
                .brand(device.getBrand())
                .build());
    }

    @Override
    public void deleteDevice(Long id) throws DeviceNotFoundException {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFoundException("Device not found"));
        deviceRepository.delete(device);
    }

    @Override
    public Device updateDevice(DeviceDTO device) throws DeviceNotFoundException {
        Device deviceToUpdate = deviceRepository.findById(device.getId())
                .orElseThrow(() -> new DeviceNotFoundException("Device not found"));

        deviceToUpdate.setName(device.getName());
        deviceToUpdate.setBrand(device.getBrand());

        return deviceRepository.save(deviceToUpdate);}

    @Override
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    @Override
    public List<Device> getDevicesByBrand(String brand) {
        return deviceRepository.findDevicesByBrand(Brand.fromString(brand));
    }
}
