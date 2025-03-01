package com.pikachu.purple.application.review.service.complaint;

import com.pikachu.purple.application.review.port.in.complaint.GetComplaintsUseCase;
import com.pikachu.purple.application.review.port.out.ComplaintRepository;
import com.pikachu.purple.domain.review.Complaint;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetComplaintsService implements GetComplaintsUseCase {

    private final ComplaintRepository complaintRepository;

    @Override
    public Result findAll(Long userId, Long perfumeId) {
        List<Complaint> complaints = complaintRepository.findAll(
            userId,
            perfumeId
        );

        return new Result(complaints);
    }

}
