package org.inktober.controller;

import lombok.RequiredArgsConstructor;
import org.inktober.service.ThemeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/theme")
public class ThemeController {

    private final ThemeService themeService;

    @GetMapping
    public String getTodayTheme() {
        return themeService.getTodayTheme().getDescription();
    }
}
