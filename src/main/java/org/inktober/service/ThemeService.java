package org.inktober.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.inktober.config.EventConfig;
import org.inktober.model.ThemeEntity;
import org.inktober.repository.ThemeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ThemeService {

    private final ThemeRepository themeRepository;
    private final EventConfig eventConfig;

    private List<ThemeEntity> themes;

    @PostConstruct
    public void initializeThemes() {
        this.themes = themeRepository.findByEventEventId(eventConfig.activeEvent.id);
    }

    public ThemeEntity getTodayTheme() {
        LocalDate todayDate = LocalDate.now();
        for (ThemeEntity theme : themes) {
            if (theme.getDateFor().equals(todayDate)) {
                return theme;
            }
        }

        return null;
    }
}
