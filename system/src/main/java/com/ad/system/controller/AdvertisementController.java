package com.ad.system.controller;

import com.ad.system.dto.AdvertisementDto;
import com.ad.system.entity.Advertisement;
import com.ad.system.service.AdvertisementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/advertisement")
@RequiredArgsConstructor
public class AdvertisementController {

    private final AdvertisementService advertisementService;

    @PostMapping("/create")
    public ResponseEntity<AdvertisementDto> createAdvertisement(@RequestBody AdvertisementDto advertisementDto) {
        return ResponseEntity.ok(advertisementService.save(advertisementDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Optional<Advertisement>> updateAdvertisement(@PathVariable Long id, @RequestBody AdvertisementDto advertisementDto) {
        return ResponseEntity.ok(advertisementService.update(id, advertisementDto));
    }

    @PostMapping("/changeStatus/{id}/{advertisementStatus}")
    public ResponseEntity<Optional<Advertisement>> changeStatus(@PathVariable Long id, @PathVariable String advertisementStatus) {
        return ResponseEntity.ok(advertisementService.changeStatus(id, advertisementStatus));
    }

    @GetMapping("/getAd/{id}")
    public ResponseEntity<Optional<Advertisement>> getAd(@PathVariable Long id) {
        return ResponseEntity.ok(advertisementService.getById(id));
    }

    @GetMapping("/getlist")
    public ResponseEntity<List<Advertisement>> getAdvertisementList() {
        return ResponseEntity.ok(advertisementService.findFirst10ByOrderByCreatedAtDesc());
    }

    @GetMapping("/getPassiveList")
    public ResponseEntity<List<Advertisement>> findByAdvertisementStatusPassive() {
        return ResponseEntity.ok(advertisementService.findByAdvertisementStatusPassive());
    }

}
