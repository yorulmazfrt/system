package com.ad.system.entity;

import com.ad.system.entity.enumaration.AdvertisementStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Advertisement extends BaseEntityModel {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column
    private Long id;

    @Column(length = 50)
    private String title;

    @Column(length = 5000)
    private String description;

    @Column
    private BigDecimal price;

    @Column
    private LocalDate advertisementDate;

    @Column
    private String userName;

    @Enumerated(EnumType.ORDINAL)
    @Column
    private AdvertisementStatus advertisementStatus;

    @Column
    private int viewCount = 0;

}
