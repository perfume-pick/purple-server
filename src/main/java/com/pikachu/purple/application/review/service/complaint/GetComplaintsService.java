package com.pikachu.purple.application.review.service.complaint;

import com.pikachu.purple.application.review.port.in.complaint.GetComplaintsUseCase;
import com.pikachu.purple.application.review.port.out.ComplaintRepository;
import com.pikachu.purple.domain.review.Complaint;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class GetComplaintsService implements GetComplaintsUseCase {

    private final ComplaintRepository complaintRepository;

    @Transactional
    @Override
    public Result findAllByUserIdAndPerfumeId(
        Long userId,
        Long perfumeId
    ) {
        List<Complaint> complaints = complaintRepository.findAllByUserIdAndPerfumeId(
            userId,
            perfumeId
        );

        return new Result(complaints);
    }

}
