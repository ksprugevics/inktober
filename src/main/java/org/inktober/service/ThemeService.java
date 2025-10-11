package org.inktober.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.inktober.config.EventConfig;
import org.inktober.model.ThemeEntity;
import org.inktober.repository.ThemeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
        var themeOpt = getTargetDateTheme(LocalDate.now());
        return themeOpt.orElse(null);
    }

    public Optional<ThemeEntity> getTargetDateTheme(LocalDate date) {
        return themes.stream()
                .filter(th -> th.getDateFor().equals(date))
                .findFirst();
    }
}
