package com.example.weathercheck.domain.forecast.model;

import com.example.weathercheck.domain.forecast.model.sub.ForecastStatementType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "forecast_statement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ForecastStatement {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne
    private Forecast forecast;

    @Enumerated(EnumType.STRING)
    private ForecastStatementType type;

    private String phenomenon;
    private String statement;

    private Integer minTemperature;
    private Integer maxTemperature;

    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant modifiedAt;
}
