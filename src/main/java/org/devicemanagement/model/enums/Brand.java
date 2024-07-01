package org.devicemanagement.model.enums;


import lombok.Getter;

@Getter
public enum Brand {
    APPLE,
    SAMSUNG,
    HUAWEI,
    XIAOMI,
    OPPO,
    GOOGLE,
    NOKIA,
    SONY;

    public static Brand fromString(String brand) {
        for (Brand b : Brand.values()) {
            if (b.name().equalsIgnoreCase(brand)) {
                return b;
            }
        }
        return null;
    }
}
