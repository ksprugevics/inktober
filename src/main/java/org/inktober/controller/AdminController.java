package org.inktober.controller;

import lombok.AllArgsConstructor;
import org.inktober.service.SubmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private final SubmissionService submissionService;

    @GetMapping
    public String getPage() {
        return "admin";
    }

    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<String> handleFileUpload(@RequestParam(value = "comment", required = false) String comment,
                                                   @RequestParam(value = "wasFun") Boolean wasFun,
                                                   @RequestParam(value = "date") String date,
                                                   @RequestParam(value = "file", required = false) MultipartFile file) {
        if (file == null && (comment == null || comment.isEmpty())) {
            return ResponseEntity.badRequest().body("{\"message\": \"Either 'file' or 'comment' must be present.\"}");
        }

        try {
            submissionService.saveSubmission(comment, wasFun, LocalDate.parse(date), file);
            return ResponseEntity.ok("{\"message\": \"Upload successful\"}");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("{\"message\": \"Upload failed\"}");
        }
    }
}
