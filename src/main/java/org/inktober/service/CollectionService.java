package org.inktober.service;

import lombok.RequiredArgsConstructor;
import org.inktober.model.SubmissionEntity;
import org.inktober.repository.SubmissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CollectionService {

    private final SubmissionRepository submissionRepository;

    public List<SubmissionEntity> getSubmissionsByEvent(long eventId) {
        return submissionRepository.findByEventId(eventId);
    }
}
