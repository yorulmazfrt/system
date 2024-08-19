package com.ad.system.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reports {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "user_id")
    private String userId;
    @Column(name = "advertisement_id")
    private Long advertisementId;

    @Column
    private LocalDateTime date;

    @Column
    private String state;
    @Column
    private String content;

}
