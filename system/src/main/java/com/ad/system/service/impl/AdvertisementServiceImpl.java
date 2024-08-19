package com.ad.system.service.impl;

import com.ad.system.dto.AdvertisementDto;
import com.ad.system.entity.Advertisement;
import com.ad.system.entity.enumaration.AdvertisementStatus;
import com.ad.system.repository.AdvertisementRepository;
import com.ad.system.service.AdvertisementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository advertisementRepository;
    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    public AdvertisementServiceImpl(AdvertisementRepository advertisementRepository, RabbitTemplate rabbitTemplate, Queue queue) {
        this.advertisementRepository = advertisementRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
    }

    @Override
    @Transactional
    public AdvertisementDto save(AdvertisementDto advertisementDto) {
        if (advertisementDto.getDescription() == null)
            throw new IllegalArgumentException("Description can not be null");
        Advertisement advertisement = new Advertisement();
        advertisement.setDescription(advertisementDto.getDescription());
        advertisement.setAdvertisementDate(LocalDate.now());
        advertisement.setTitle(advertisementDto.getTitle());
        advertisement.setPrice(advertisementDto.getPrice());
        advertisement.setAdvertisementStatus(AdvertisementStatus.PASSIVE);
        log.info("New advertisement added {}", advertisement.getAdvertisementDate());
        advertisement = advertisementRepository.save(advertisement);
        advertisementDto.setId(advertisement.getId());
        return advertisementDto;
    }

    @Override
    @Transactional
    public Optional<Advertisement> update(Long id, AdvertisementDto advertisementDto) {
        return advertisementRepository.findById(id).map(item -> {
            item.setPrice(advertisementDto.getPrice());
            item.setTitle(advertisementDto.getTitle());
            item.setDescription(advertisementDto.getDescription());
            item.setAdvertisementStatus(AdvertisementStatus.PASSIVE);
            return advertisementRepository.save(item);
        });
    }

    @Override
    @Transactional
    public Optional<Advertisement> changeStatus(Long id, String advertisementStatus) {
        return advertisementRepository.findById(id).map(item -> {
            if (advertisementStatus.equals("approve")) {
                item.setAdvertisementStatus(AdvertisementStatus.ACTIVE);
                rabbitTemplate.convertAndSend(queue.getName(), new AdvertisementDto(id));
            }
            return advertisementRepository.save(item);
        });
    }

    @Override
    public Optional<Advertisement> getAdvertisementById(Long id) {
        return advertisementRepository.findById(id);
    }

    @Override
    public int incrementViewCount(Long id) {
        return 0;
    }

    @Override
    public Optional<Advertisement> getById(Long advertisementId) {
        return advertisementRepository.findById(advertisementId);
    }

    public List<Advertisement> findByAdvertisementStatusPassive() {
        return advertisementRepository.findByAdvertisementStatus(AdvertisementStatus.PASSIVE);
    }

    @Override
    public List<Advertisement> findFirst10ByOrderByCreatedAtDesc() {
        return advertisementRepository.findFirst10ByOrderByCreatedAtDesc();
    }

}


