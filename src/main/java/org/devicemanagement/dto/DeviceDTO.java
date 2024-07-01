package org.devicemanagement.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.devicemanagement.model.enums.Brand;

@Data
public class DeviceDTO {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Brand brand;
}
