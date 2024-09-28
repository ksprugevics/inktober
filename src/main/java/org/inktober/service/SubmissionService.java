package org.inktober.service;

import lombok.RequiredArgsConstructor;
import org.inktober.model.SubmissionEntity;
import org.inktober.model.ThemeEntity;
import org.inktober.repository.SubmissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final ThemeService themeService;

    public SubmissionEntity getSubmission(long submissionId) {
        return submissionRepository.findById(submissionId).orElseThrow(() -> new IllegalArgumentException("Invalid submission Id:" + submissionId));
    }

    public SubmissionEntity getTodaySubmission() {
        ThemeEntity todayTheme = themeService.getTodayTheme();
        List<SubmissionEntity> todaySubmission = submissionRepository.findByThemeThemeId(todayTheme.getThemeId());
        if (todaySubmission.isEmpty()) {
            return null;
        }
        return todaySubmission.getFirst();
    }

    public SubmissionEntity saveSubmission(String description, Boolean wasFun, MultipartFile file) throws IOException {
        if (!canSubmitToday()) {
            throw new RuntimeException("Submission already exists");
        }

        SubmissionEntity submission = SubmissionEntity.builder()
                .uploadTimestamp(System.currentTimeMillis())
                .theme(themeService.getTodayTheme())
                .comment(description)
                .wasFun(wasFun)
                .build();

        if (file != null) {
            submission.setImage(file.getBytes());
        }
        return submissionRepository.save(submission);
    }

    public boolean canSubmitToday() {
        SubmissionEntity todaySubmission = getTodaySubmission();
        return todaySubmission == null;
    }
}
