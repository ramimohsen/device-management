package org.devicemanagement.controller;

import org.devicemanagement.dto.DeviceDTO;
import org.devicemanagement.model.Device;
import org.devicemanagement.model.enums.Brand;
import org.devicemanagement.service.IDevice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DeviceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IDevice deviceService;

    @InjectMocks
    private DeviceController deviceController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(deviceController).build();
    }

    @Test
    void testGetDeviceById() throws Exception {
        Device device = new Device();
        device.setId(1L);
        device.setName("Device1");

        when(deviceService.getDeviceById(1L)).thenReturn(device);

        mockMvc.perform(get("/api/v1/device/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Device1")));
    }

    @Test
    void testGetAllDevices() throws Exception {
        Device device1 = new Device();
        device1.setId(1L);
        device1.setName("Device1");

        Device device2 = new Device();
        device2.setId(2L);
        device2.setName("Device2");

        List<Device> devices = Arrays.asList(device1, device2);

        when(deviceService.getAllDevices()).thenReturn(devices);

        mockMvc.perform(get("/api/v1/device"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Device1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Device2")));
    }

    @Test
    void testSaveDevice() throws Exception {
        DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setName("Device1");

        Device savedDevice = new Device();
        savedDevice.setId(1L);
        savedDevice.setName("Device1");
        savedDevice.setBrand(Brand.APPLE);

        when(deviceService.saveDevice(any(DeviceDTO.class))).thenReturn(savedDevice);

        mockMvc.perform(post("/api/v1/device")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Device1\", \"brand\":\"APPLE\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Device1")))
                .andExpect(jsonPath("$.brand", is("APPLE")));
    }

    @Test
    void testGetDevicesByBrand() throws Exception {
        Device device1 = new Device();
        device1.setId(1L);
        device1.setName("Device1");

        List<Device> devices = List.of(device1);

        when(deviceService.getDevicesByBrand("Brand1")).thenReturn(devices);

        mockMvc.perform(get("/api/v1/device/brand/Brand1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Device1")));
    }

    @Test
    void testUpdateDevice() throws Exception {
        DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setId(1L);
        deviceDTO.setName("UpdatedDevice");

        Device updatedDevice = new Device();
        updatedDevice.setId(1L);
        updatedDevice.setName("UpdatedDevice");

        when(deviceService.updateDevice(any(DeviceDTO.class))).thenReturn(updatedDevice);

        mockMvc.perform(put("/api/v1/device")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"UpdatedDevice\",\"brand\":\"APPLE\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("UpdatedDevice")));
    }

    @Test
    void testDeleteDevice() throws Exception {
        doNothing().when(deviceService).deleteDevice(1L);

        mockMvc.perform(delete("/api/v1/device/1"))
                .andExpect(status().isOk());
    }
}
