package com.ad.system.service;

import com.ad.system.dto.AdvertisementDto;
import com.ad.system.entity.Advertisement;

import java.util.List;
import java.util.Optional;


public interface AdvertisementService {

    AdvertisementDto save(AdvertisementDto advertisementDto);

    Optional<Advertisement> update(Long id, AdvertisementDto AdvertisementDto);

    Optional<Advertisement> getById(Long advertisementId);

    List<Advertisement> findFirst10ByOrderByCreatedAtDesc();

    List<Advertisement> findByAdvertisementStatusPassive();

    Optional<Advertisement> changeStatus(Long id, String advertisementStatus);

    Optional<Advertisement> getAdvertisementById(Long id);

    int incrementViewCount(Long id);
}
