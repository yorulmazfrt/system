package com.ad.system.service.impl;

import com.ad.system.dto.AdvertisementDto;
import com.ad.system.entity.Advertisement;
import com.ad.system.entity.Reports;
import com.ad.system.repository.ReportsRepository;
import com.ad.system.service.AdvertisementService;
import com.ad.system.service.ReportsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportsServiceImpl implements ReportsService {
    private final ReportsRepository reportsRepository;
    private final AdvertisementService advertisementService;


    @RabbitListener(queues = "${queue.name}")
    public void consume(@Payload AdvertisementDto advertisementDto) {
        Optional<Advertisement> advertisement = advertisementService.getAdvertisementById(advertisementDto.getId());
        int viewCount = advertisementService.incrementViewCount(advertisementDto.getId());

        Reports reports = new Reports();
        reports.setDate(LocalDateTime.now());
        reports.setAdvertisementId(advertisementDto.getId());
        reports.setUserId(advertisement.get().getUserName());
        reports.setState("SENT");
        reports.setContent(String.format("%s ilanı %s kullanıcısı tarafından %d gün önce oluşturulmuştur. İlan %d kere görüntülenmiştir.",
                advertisement.get().getTitle(), advertisement.get().getUserName(), LocalDateTime.now().until(LocalDateTime.now(), ChronoUnit.DAYS), viewCount));
        log.info("Reports {} gönderildi.", reports);
        reportsRepository.save(reports);
    }
}
