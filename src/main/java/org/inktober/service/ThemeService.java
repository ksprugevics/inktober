package org.inktober.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.inktober.config.EventConfig;
import org.inktober.model.ThemeEntity;
import org.inktober.repository.ThemeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThemeService {

    private final ThemeRepository themeRepository;
    private final EventConfig eventConfig;

    @PostConstruct
    public void yap() {
        System.out.println(eventConfig.activeEvent);
        List<ThemeEntity> all = themeRepository.findAll();
        for (ThemeEntity themeEntity : all) {
            System.out.println(themeEntity);
        }
    }
}
