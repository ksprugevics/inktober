package org.inktober.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SubmissionController {

    @GetMapping("/")
    public String home() {
        return "home";
    }
}
