package com.ad.system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementDto implements Serializable {

    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private String assignee;
    private LocalDateTime advertisementDate;
    private String advertisementStatus;
    private String userId;
    private int incrementViewCount;

    public AdvertisementDto(Long id) {
        this.id = id;
    }
}
