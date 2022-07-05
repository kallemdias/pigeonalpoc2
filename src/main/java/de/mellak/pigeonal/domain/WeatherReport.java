package de.mellak.pigeonal.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A WeatherReport.
 */
@Entity
@Table(name = "weather_report")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WeatherReport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "initiated")
    private Instant initiated;

    @Column(name = "release_date_time")
    private Instant releaseDateTime;

    @Min(value = 5)
    @Column(name = "checkpoints_distance")
    private Integer checkpointsDistance;

    @Column(name = "alerts")
    private String alerts;

    @OneToMany(mappedBy = "weatherReport")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "checkPoints", "weatherReport" }, allowSetters = true)
    private Set<CheckLine> checkLines = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "weatherReports", "legs", "logs", "user", "association" }, allowSetters = true)
    private RacingPlan racingPlan;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public WeatherReport id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getInitiated() {
        return this.initiated;
    }

    public WeatherReport initiated(Instant initiated) {
        this.setInitiated(initiated);
        return this;
    }

    public void setInitiated(Instant initiated) {
        this.initiated = initiated;
    }

    public Instant getReleaseDateTime() {
        return this.releaseDateTime;
    }

    public WeatherReport releaseDateTime(Instant releaseDateTime) {
        this.setReleaseDateTime(releaseDateTime);
        return this;
    }

    public void setReleaseDateTime(Instant releaseDateTime) {
        this.releaseDateTime = releaseDateTime;
    }

    public Integer getCheckpointsDistance() {
        return this.checkpointsDistance;
    }

    public WeatherReport checkpointsDistance(Integer checkpointsDistance) {
        this.setCheckpointsDistance(checkpointsDistance);
        return this;
    }

    public void setCheckpointsDistance(Integer checkpointsDistance) {
        this.checkpointsDistance = checkpointsDistance;
    }

    public String getAlerts() {
        return this.alerts;
    }

    public WeatherReport alerts(String alerts) {
        this.setAlerts(alerts);
        return this;
    }

    public void setAlerts(String alerts) {
        this.alerts = alerts;
    }

    public Set<CheckLine> getCheckLines() {
        return this.checkLines;
    }

    public void setCheckLines(Set<CheckLine> checkLines) {
        if (this.checkLines != null) {
            this.checkLines.forEach(i -> i.setWeatherReport(null));
        }
        if (checkLines != null) {
            checkLines.forEach(i -> i.setWeatherReport(this));
        }
        this.checkLines = checkLines;
    }

    public WeatherReport checkLines(Set<CheckLine> checkLines) {
        this.setCheckLines(checkLines);
        return this;
    }

    public WeatherReport addCheckLine(CheckLine checkLine) {
        this.checkLines.add(checkLine);
        checkLine.setWeatherReport(this);
        return this;
    }

    public WeatherReport removeCheckLine(CheckLine checkLine) {
        this.checkLines.remove(checkLine);
        checkLine.setWeatherReport(null);
        return this;
    }

    public RacingPlan getRacingPlan() {
        return this.racingPlan;
    }

    public void setRacingPlan(RacingPlan racingPlan) {
        this.racingPlan = racingPlan;
    }

    public WeatherReport racingPlan(RacingPlan racingPlan) {
        this.setRacingPlan(racingPlan);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WeatherReport)) {
            return false;
        }
        return id != null && id.equals(((WeatherReport) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WeatherReport{" +
            "id=" + getId() +
            ", initiated='" + getInitiated() + "'" +
            ", releaseDateTime='" + getReleaseDateTime() + "'" +
            ", checkpointsDistance=" + getCheckpointsDistance() +
            ", alerts='" + getAlerts() + "'" +
            "}";
    }
}
