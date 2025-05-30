package org.inktober.controller;

import lombok.RequiredArgsConstructor;
import org.inktober.config.EventConfig;
import org.inktober.service.SubmissionService;
import org.inktober.service.ThemeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final EventConfig eventConfig;
    private final ThemeService themeService;
    private final SubmissionService submissionService;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("event", eventConfig.activeEvent.eventName);
        model.addAttribute("theme", themeService.getTodayTheme());
        model.addAttribute("todaySubmission", submissionService.getTodaySubmission());
        return "home";
    }
}
