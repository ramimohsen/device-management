package org.devicemanagement.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.devicemanagement.dto.DeviceDTO;
import org.devicemanagement.exception.custom.DeviceNotFoundException;
import org.devicemanagement.model.Device;
import org.devicemanagement.service.IDevice;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/device")
@RequiredArgsConstructor
@Validated
public class DeviceController {

    private final IDevice deviceService;

    @GetMapping("/{id}")
    public Device getDeviceById(@PathVariable("id") Long id) throws DeviceNotFoundException {
        return deviceService.getDeviceById(id);
    }

    @GetMapping
    public List<Device> getAllDevices() {
        return deviceService.getAllDevices();
    }

    @PostMapping
    public Device saveDevice(@RequestBody @Valid DeviceDTO device) {
        return deviceService.saveDevice(device);
    }

    @GetMapping("/brand/{brand}")
    public List<Device> getDevicesByBrand(@PathVariable("brand") String brand) {
        return deviceService.getDevicesByBrand(brand);
    }

    @PutMapping
    public Device updateDevice(@RequestBody @Valid DeviceDTO device) throws DeviceNotFoundException {
        return deviceService.updateDevice(device);
    }

    @DeleteMapping("/{id}")
    public void deleteDevice(@PathVariable("id") Long id) throws DeviceNotFoundException {
        deviceService.deleteDevice(id);
    }
}
