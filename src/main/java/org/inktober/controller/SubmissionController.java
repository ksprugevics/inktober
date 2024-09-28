package org.inktober.controller;

import lombok.RequiredArgsConstructor;
import org.inktober.model.SubmissionEntity;
import org.inktober.service.SubmissionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/submission")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionService submissionService;

    @GetMapping("/{id}")
    public String getSubmission(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("submission", submissionService.getSubmission(id));
            return "submission";
        } catch (IllegalArgumentException e) {
            return "Error";
        }
    }

    @GetMapping("/{id}/image")
    @ResponseBody
    public ResponseEntity<byte[]> getSubmissionImage(@PathVariable Long id) {
        try {
            SubmissionEntity submission = submissionService.getSubmission(id);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(submission.getImage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/upload")
    public String upload() {
        return "uploadForm";
    }

    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<String> handleFileUpload(@RequestParam(value = "comment", required = false) String comment,
                                                   @RequestParam(value = "wasFun") Boolean wasFun,
                                                   @RequestParam(value= "file", required = false) MultipartFile file) {
        if (file == null && (comment == null || comment.isEmpty())) {
            return ResponseEntity.badRequest().body("{\"message\": \"Either 'file' or 'comment' must be present.\"}");
        }

        try {
            submissionService.saveSubmission(comment, wasFun, file);
            return ResponseEntity.ok("{\"message\": \"Upload successful\"}");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("{\"message\": \"Upload failed\"}");
        }
    }
}
