package com.ad.system.entity.enumaration;

import lombok.Getter;

@Getter
public enum AdvertisementStatus {
    ACTIVE("Aktif Ilan"),
    PASSIVE("Pasif Ilan");

    private final String label;

    AdvertisementStatus(String label) {
        this.label = label;
    }
}
