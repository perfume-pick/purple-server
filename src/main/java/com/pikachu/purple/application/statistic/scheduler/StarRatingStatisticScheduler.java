//package com.pikachu.purple.application.statistic.scheduler;
//
//import static com.pikachu.purple.util.StringUtil.DELIMITER;
//
//import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumeIdsUseCase;
//import com.pikachu.purple.application.review.common.dto.PerfumeStarRatingStatisticDTO;
//import com.pikachu.purple.application.review.port.in.starrating.GetStarRatingsByUpdatedDateUseCase;
//import com.pikachu.purple.application.statistic.service.domain.StarRatingStatisticDomainService;
//import com.pikachu.purple.domain.review.StarRating;
//import com.pikachu.purple.domain.statistic.StarRatingStatistic;
//import com.pikachu.purple.util.DateUtil;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//import lombok.RequiredArgsConstructor;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//@Component
//@RequiredArgsConstructor
//public class StarRatingStatisticScheduler {
//
//    private final GetPerfumeIdsUseCase getPerfumeIdsUseCase;
//    private final StarRatingStatisticDomainService starRatingStatisticDomainService;
//    private final GetStarRatingsByUpdatedDateUseCase getStarRatingsByUpdateDateUseCase;
//
//    @Transactional
//    @Scheduled(cron = "${scheduler.daily-cron}")
//    public void dailyRecountStarRatingStatistics() {
//        List<Long> perfumeIds = getPerfumeIdsUseCase.invoke().perfumeIds();
//
//        String theDayBeforeYesterday = DateUtil.theDayBeforeYesterday();
//        List<StarRatingStatistic> theDayBeforeYesterdayStarRatingStatistic = starRatingStatisticDomainService
//            .findAll(theDayBeforeYesterday);
//        Map<String, Integer> theDayBeforeYesterdayStarRatingStatisticMap =
//            theDayBeforeYesterdayStarRatingStatistic.stream()
//                .collect(Collectors.toMap(
//                    starRatingStatistic -> buildMapKey(
//                        starRatingStatistic.getPerfume().getId(),
//                        starRatingStatistic.getScore()
//                    ),
//                    StarRatingStatistic::getVotes
//                ));
//
//        String yesterday = DateUtil.yesterday();
//        List<StarRating> yesterdayStarRatings = getStarRatingsByUpdateDateUseCase.invoke(
//            new GetStarRatingsByUpdatedDateUseCase.Command(yesterday)
//        ).starRatings();
//        Map<String, Integer> yesterdayStarRatingStatisticMap =
//            yesterdayStarRatings.stream()
//                .collect(Collectors.groupingBy(
//                    starRating -> buildMapKey(
//                        starRating.getPerfume().getId(),
//                        starRating.getScore()
//                    ),
//                    Collectors.summingInt(starRating -> 1)
//                ));
//
//        List<PerfumeStarRatingStatisticDTO> perfumeStarRatingStatisticDTOs = new ArrayList<>();
//        int[] scores = {1, 2, 3, 4, 5};
//        for (Long perfumeId : perfumeIds) {
//            List<StarRatingStatistic> starRatingStatistics = new ArrayList<>();
//            for (int score : scores) {
//                String key = buildMapKey(
//                    perfumeId,
//                    score
//                );
//                int theDayBeforeYesterdayVotes = theDayBeforeYesterdayStarRatingStatisticMap.getOrDefault(key, 0);
//                int yesterdayVotes = yesterdayStarRatingStatisticMap.getOrDefault(key, 0);
//
//                StarRatingStatistic starRatingStatistic = StarRatingStatistic.builder()
//                    .score(score)
//                    .votes(theDayBeforeYesterdayVotes + yesterdayVotes)
//                    .build();
//                starRatingStatistics.add(starRatingStatistic);
//            }
//
//            perfumeStarRatingStatisticDTOs.add(
//                PerfumeStarRatingStatisticDTO.builder()
//                    .perfumeId(perfumeId)
//                    .starRatingStatistics(starRatingStatistics)
//                    .build()
//            );
//        }
//
//        starRatingStatisticDomainService.updateAll(
//            yesterday,
//            perfumeStarRatingStatisticDTOs
//        );
//
//    }
//
//    private String buildMapKey(Long perfumeId, int score) {
//        return perfumeId + DELIMITER + score;
//    }
//
//}
