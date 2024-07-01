package org.devicemanagement.service;

import org.devicemanagement.dto.DeviceDTO;
import org.devicemanagement.exception.custom.DeviceNotFoundException;
import org.devicemanagement.model.Device;
import org.devicemanagement.model.enums.Brand;
import org.devicemanagement.repository.DeviceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DeviceServiceTest {

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private DeviceService deviceService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetDeviceById() throws DeviceNotFoundException {
        Device device = new Device();
        device.setId(1L);
        device.setName("Device1");

        when(deviceRepository.findById(1L)).thenReturn(Optional.of(device));

        Device foundDevice = deviceService.getDeviceById(1L);
        assertEquals(1L, foundDevice.getId());
        assertEquals("Device1", foundDevice.getName());
    }

    @Test
    void testGetDeviceByIdNotFound() {
        when(deviceRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(DeviceNotFoundException.class, () -> deviceService.getDeviceById(1L));
    }

    @Test
    void testSaveDevice() {
        DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setName("Device1");
        deviceDTO.setBrand(Brand.APPLE);

        Device savedDevice = new Device();
        savedDevice.setId(1L);
        savedDevice.setName("Device1");
        savedDevice.setBrand(Brand.APPLE);

        when(deviceRepository.save(any(Device.class))).thenReturn(savedDevice);

        Device result = deviceService.saveDevice(deviceDTO);
        assertEquals(1L, result.getId());
        assertEquals("Device1", result.getName());
        assertEquals(Brand.APPLE, result.getBrand());
    }

    @Test
    void testDeleteDevice() throws DeviceNotFoundException {
        Device device = new Device();
        device.setId(1L);

        when(deviceRepository.findById(1L)).thenReturn(Optional.of(device));
        doNothing().when(deviceRepository).delete(device);

        deviceService.deleteDevice(1L);

        verify(deviceRepository, times(1)).delete(device);
    }

    @Test
    void testDeleteDeviceNotFound() {
        when(deviceRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(DeviceNotFoundException.class, () -> deviceService.deleteDevice(1L));
    }

    @Test
    void testUpdateDevice() throws DeviceNotFoundException {
        DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setId(1L);
        deviceDTO.setName("UpdatedDevice");
        deviceDTO.setBrand(Brand.SAMSUNG);

        Device existingDevice = new Device();
        existingDevice.setId(1L);
        existingDevice.setName("OldDevice");
        existingDevice.setBrand(Brand.APPLE);

        when(deviceRepository.findById(1L)).thenReturn(Optional.of(existingDevice));
        when(deviceRepository.save(any(Device.class))).thenReturn(existingDevice);

        Device updatedDevice = deviceService.updateDevice(deviceDTO);

        assertEquals(1L, updatedDevice.getId());
        assertEquals("UpdatedDevice", updatedDevice.getName());
        assertEquals(Brand.SAMSUNG, updatedDevice.getBrand());
    }

    @Test
    void testUpdateDeviceNotFound() {
        DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setId(1L);
        deviceDTO.setName("UpdatedDevice");
        deviceDTO.setBrand(Brand.SAMSUNG);

        when(deviceRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(DeviceNotFoundException.class, () -> deviceService.updateDevice(deviceDTO));
    }

    @Test
    void testGetAllDevices() {
        Device device1 = new Device();
        device1.setId(1L);
        device1.setName("Device1");

        Device device2 = new Device();
        device2.setId(2L);
        device2.setName("Device2");

        List<Device> devices = Arrays.asList(device1, device2);

        when(deviceRepository.findAll()).thenReturn(devices);

        List<Device> result = deviceService.getAllDevices();
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("Device1", result.get(0).getName());
        assertEquals(2L, result.get(1).getId());
        assertEquals("Device2", result.get(1).getName());
    }

    @Test
    void testGetDevicesByBrand() {
        Device device1 = new Device();
        device1.setId(1L);
        device1.setName("Device1");
        device1.setBrand(Brand.APPLE);

        List<Device> devices = List.of(device1);

        when(deviceRepository.findDevicesByBrand(Brand.APPLE)).thenReturn(devices);

        List<Device> result = deviceService.getDevicesByBrand("APPLE");
        assertEquals(1, result.size());
        assertEquals(1L, result.getFirst().getId());
        assertEquals("Device1", result.getFirst().getName());
        assertEquals(Brand.APPLE, result.getFirst().getBrand());
    }
}
