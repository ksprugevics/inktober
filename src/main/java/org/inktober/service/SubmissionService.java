package org.inktober.service;

import lombok.RequiredArgsConstructor;
import org.inktober.model.SubmissionEntity;
import org.inktober.model.ThemeEntity;
import org.inktober.repository.SubmissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final SubmissionRepository submissionRepository;

    public SubmissionEntity getSubmission(long submissionId) {
        return submissionRepository.findById(submissionId).orElseThrow(() -> new IllegalArgumentException("Invalid submission Id:" + submissionId));
    }

    public SubmissionEntity saveSubmission(String description, MultipartFile image) throws IOException {
        SubmissionEntity submission = SubmissionEntity.builder()
                .uploadTimestamp(System.currentTimeMillis())
                .theme(ThemeEntity.builder().themeId(1).build())
                .comment(description)
                .image(image.getBytes())
                .build();
        return submissionRepository.save(submission);
    }
}
