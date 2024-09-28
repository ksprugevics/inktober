package org.inktober.controller;

import lombok.RequiredArgsConstructor;
import org.inktober.config.EventConfig;
import org.inktober.service.CollectionService;
import org.inktober.service.ThemeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/collection")
@RequiredArgsConstructor
public class CollectionController {

    private final CollectionService collectionService;
    private final ThemeService themeService;
    private final EventConfig eventConfig;

    @GetMapping(value = {"", "/{id}"})
    public String getCollection(@PathVariable(required = false) Integer id, Model model) {
        if (id == null) {
            id = eventConfig.activeEvent.id;
        }

        try {
            model.addAttribute("event", eventConfig.activeEvent.eventName);
            model.addAttribute("theme", themeService.getTodayTheme());
            model.addAttribute("submissions", collectionService.getSubmissionsByEvent(id));
            return "collection";
        } catch (IllegalArgumentException e) {
            return "Error";
        }
    }
}
