package com.ad.system.repository;

import com.ad.system.entity.Advertisement;
import com.ad.system.entity.enumaration.AdvertisementStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, String> {
    List<Advertisement> findFirst10ByOrderByCreatedAtDesc();

    Optional<Advertisement> findById(Long advertisementId);

    List<Advertisement> findByAdvertisementStatus(AdvertisementStatus advertisementStatus);
}
