package org.inktober.model;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.inktober.util.LocalDateConverter;

import java.time.LocalDate;

@Entity
@Table(name = "theme")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ThemeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long themeId;
    private String description;
    @Convert(converter = LocalDateConverter.class)
    private LocalDate dateFor;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private EventEntity event;
}
