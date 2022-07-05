package de.mellak.pigeonal.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.mellak.pigeonal.domain.enumeration.CheckLinePointDistance;
import de.mellak.pigeonal.domain.enumeration.CheckPointDistance;
import de.mellak.pigeonal.domain.enumeration.TimeWindow;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A RacingPlan.
 */
@Entity
@Table(name = "racing_plan")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RacingPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "assocation", nullable = false)
    private String assocation;

    @Column(name = "release_date")
    private Instant releaseDate;

    @Column(name = "release_point")
    private String releasePoint;

    @Column(name = "arrival_point")
    private String arrivalPoint;

    @Column(name = "release_point_dms")
    private String releasePointDMS;

    @Column(name = "arrival_point_dms")
    private String arrivalPointDMS;

    @Column(name = "release_map_link")
    private String releaseMapLink;

    @Column(name = "arrival_map_link")
    private String arrivalMapLink;

    @DecimalMin(value = "0")
    @Column(name = "distance")
    private Double distance;

    @Column(name = "distance_displayed_value")
    private String distanceDisplayedValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "check_point_distance")
    private CheckPointDistance checkPointDistance;

    @Column(name = "check_lined_reporting")
    private Boolean checkLinedReporting;

    @Enumerated(EnumType.STRING)
    @Column(name = "check_line_point_distance")
    private CheckLinePointDistance checkLinePointDistance;

    @Enumerated(EnumType.STRING)
    @Column(name = "time_window")
    private TimeWindow timeWindow;

    @Column(name = "generation_in_progress")
    private Boolean generationInProgress;

    @Column(name = "generated")
    private Boolean generated;

    @Column(name = "reset")
    private Boolean reset;

    @Column(name = "progress")
    private Integer progress;

    @OneToMany(mappedBy = "racingPlan")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "checkLines", "racingPlan" }, allowSetters = true)
    private Set<WeatherReport> weatherReports = new HashSet<>();

    @OneToMany(mappedBy = "racingPlan")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "racingPlan" }, allowSetters = true)
    private Set<Leg> legs = new HashSet<>();

    @OneToMany(mappedBy = "racingPlan")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "racingPlan" }, allowSetters = true)
    private Set<YcLogEntry> logs = new HashSet<>();

    @ManyToOne
    private User user;

    @ManyToOne
    @JsonIgnoreProperties(value = { "racingPlans" }, allowSetters = true)
    private Association association;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public RacingPlan id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public RacingPlan name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAssocation() {
        return this.assocation;
    }

    public RacingPlan assocation(String assocation) {
        this.setAssocation(assocation);
        return this;
    }

    public void setAssocation(String assocation) {
        this.assocation = assocation;
    }

    public Instant getReleaseDate() {
        return this.releaseDate;
    }

    public RacingPlan releaseDate(Instant releaseDate) {
        this.setReleaseDate(releaseDate);
        return this;
    }

    public void setReleaseDate(Instant releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleasePoint() {
        return this.releasePoint;
    }

    public RacingPlan releasePoint(String releasePoint) {
        this.setReleasePoint(releasePoint);
        return this;
    }

    public void setReleasePoint(String releasePoint) {
        this.releasePoint = releasePoint;
    }

    public String getArrivalPoint() {
        return this.arrivalPoint;
    }

    public RacingPlan arrivalPoint(String arrivalPoint) {
        this.setArrivalPoint(arrivalPoint);
        return this;
    }

    public void setArrivalPoint(String arrivalPoint) {
        this.arrivalPoint = arrivalPoint;
    }

    public String getReleasePointDMS() {
        return this.releasePointDMS;
    }

    public RacingPlan releasePointDMS(String releasePointDMS) {
        this.setReleasePointDMS(releasePointDMS);
        return this;
    }

    public void setReleasePointDMS(String releasePointDMS) {
        this.releasePointDMS = releasePointDMS;
    }

    public String getArrivalPointDMS() {
        return this.arrivalPointDMS;
    }

    public RacingPlan arrivalPointDMS(String arrivalPointDMS) {
        this.setArrivalPointDMS(arrivalPointDMS);
        return this;
    }

    public void setArrivalPointDMS(String arrivalPointDMS) {
        this.arrivalPointDMS = arrivalPointDMS;
    }

    public String getReleaseMapLink() {
        return this.releaseMapLink;
    }

    public RacingPlan releaseMapLink(String releaseMapLink) {
        this.setReleaseMapLink(releaseMapLink);
        return this;
    }

    public void setReleaseMapLink(String releaseMapLink) {
        this.releaseMapLink = releaseMapLink;
    }

    public String getArrivalMapLink() {
        return this.arrivalMapLink;
    }

    public RacingPlan arrivalMapLink(String arrivalMapLink) {
        this.setArrivalMapLink(arrivalMapLink);
        return this;
    }

    public void setArrivalMapLink(String arrivalMapLink) {
        this.arrivalMapLink = arrivalMapLink;
    }

    public Double getDistance() {
        return this.distance;
    }

    public RacingPlan distance(Double distance) {
        this.setDistance(distance);
        return this;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getDistanceDisplayedValue() {
        return this.distanceDisplayedValue;
    }

    public RacingPlan distanceDisplayedValue(String distanceDisplayedValue) {
        this.setDistanceDisplayedValue(distanceDisplayedValue);
        return this;
    }

    public void setDistanceDisplayedValue(String distanceDisplayedValue) {
        this.distanceDisplayedValue = distanceDisplayedValue;
    }

    public CheckPointDistance getCheckPointDistance() {
        return this.checkPointDistance;
    }

    public RacingPlan checkPointDistance(CheckPointDistance checkPointDistance) {
        this.setCheckPointDistance(checkPointDistance);
        return this;
    }

    public void setCheckPointDistance(CheckPointDistance checkPointDistance) {
        this.checkPointDistance = checkPointDistance;
    }

    public Boolean getCheckLinedReporting() {
        return this.checkLinedReporting;
    }

    public RacingPlan checkLinedReporting(Boolean checkLinedReporting) {
        this.setCheckLinedReporting(checkLinedReporting);
        return this;
    }

    public void setCheckLinedReporting(Boolean checkLinedReporting) {
        this.checkLinedReporting = checkLinedReporting;
    }

    public CheckLinePointDistance getCheckLinePointDistance() {
        return this.checkLinePointDistance;
    }

    public RacingPlan checkLinePointDistance(CheckLinePointDistance checkLinePointDistance) {
        this.setCheckLinePointDistance(checkLinePointDistance);
        return this;
    }

    public void setCheckLinePointDistance(CheckLinePointDistance checkLinePointDistance) {
        this.checkLinePointDistance = checkLinePointDistance;
    }

    public TimeWindow getTimeWindow() {
        return this.timeWindow;
    }

    public RacingPlan timeWindow(TimeWindow timeWindow) {
        this.setTimeWindow(timeWindow);
        return this;
    }

    public void setTimeWindow(TimeWindow timeWindow) {
        this.timeWindow = timeWindow;
    }

    public Boolean getGenerationInProgress() {
        return this.generationInProgress;
    }

    public RacingPlan generationInProgress(Boolean generationInProgress) {
        this.setGenerationInProgress(generationInProgress);
        return this;
    }

    public void setGenerationInProgress(Boolean generationInProgress) {
        this.generationInProgress = generationInProgress;
    }

    public Boolean getGenerated() {
        return this.generated;
    }

    public RacingPlan generated(Boolean generated) {
        this.setGenerated(generated);
        return this;
    }

    public void setGenerated(Boolean generated) {
        this.generated = generated;
    }

    public Boolean getReset() {
        return this.reset;
    }

    public RacingPlan reset(Boolean reset) {
        this.setReset(reset);
        return this;
    }

    public void setReset(Boolean reset) {
        this.reset = reset;
    }

    public Integer getProgress() {
        return this.progress;
    }

    public RacingPlan progress(Integer progress) {
        this.setProgress(progress);
        return this;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Set<WeatherReport> getWeatherReports() {
        return this.weatherReports;
    }

    public void setWeatherReports(Set<WeatherReport> weatherReports) {
        if (this.weatherReports != null) {
            this.weatherReports.forEach(i -> i.setRacingPlan(null));
        }
        if (weatherReports != null) {
            weatherReports.forEach(i -> i.setRacingPlan(this));
        }
        this.weatherReports = weatherReports;
    }

    public RacingPlan weatherReports(Set<WeatherReport> weatherReports) {
        this.setWeatherReports(weatherReports);
        return this;
    }

    public RacingPlan addWeatherReport(WeatherReport weatherReport) {
        this.weatherReports.add(weatherReport);
        weatherReport.setRacingPlan(this);
        return this;
    }

    public RacingPlan removeWeatherReport(WeatherReport weatherReport) {
        this.weatherReports.remove(weatherReport);
        weatherReport.setRacingPlan(null);
        return this;
    }

    public Set<Leg> getLegs() {
        return this.legs;
    }

    public void setLegs(Set<Leg> legs) {
        if (this.legs != null) {
            this.legs.forEach(i -> i.setRacingPlan(null));
        }
        if (legs != null) {
            legs.forEach(i -> i.setRacingPlan(this));
        }
        this.legs = legs;
    }

    public RacingPlan legs(Set<Leg> legs) {
        this.setLegs(legs);
        return this;
    }

    public RacingPlan addLeg(Leg leg) {
        this.legs.add(leg);
        leg.setRacingPlan(this);
        return this;
    }

    public RacingPlan removeLeg(Leg leg) {
        this.legs.remove(leg);
        leg.setRacingPlan(null);
        return this;
    }

    public Set<YcLogEntry> getLogs() {
        return this.logs;
    }

    public void setLogs(Set<YcLogEntry> ycLogEntries) {
        if (this.logs != null) {
            this.logs.forEach(i -> i.setRacingPlan(null));
        }
        if (ycLogEntries != null) {
            ycLogEntries.forEach(i -> i.setRacingPlan(this));
        }
        this.logs = ycLogEntries;
    }

    public RacingPlan logs(Set<YcLogEntry> ycLogEntries) {
        this.setLogs(ycLogEntries);
        return this;
    }

    public RacingPlan addLog(YcLogEntry ycLogEntry) {
        this.logs.add(ycLogEntry);
        ycLogEntry.setRacingPlan(this);
        return this;
    }

    public RacingPlan removeLog(YcLogEntry ycLogEntry) {
        this.logs.remove(ycLogEntry);
        ycLogEntry.setRacingPlan(null);
        return this;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RacingPlan user(User user) {
        this.setUser(user);
        return this;
    }

    public Association getAssociation() {
        return this.association;
    }

    public void setAssociation(Association association) {
        this.association = association;
    }

    public RacingPlan association(Association association) {
        this.setAssociation(association);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RacingPlan)) {
            return false;
        }
        return id != null && id.equals(((RacingPlan) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RacingPlan{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", assocation='" + getAssocation() + "'" +
            ", releaseDate='" + getReleaseDate() + "'" +
            ", releasePoint='" + getReleasePoint() + "'" +
            ", arrivalPoint='" + getArrivalPoint() + "'" +
            ", releasePointDMS='" + getReleasePointDMS() + "'" +
            ", arrivalPointDMS='" + getArrivalPointDMS() + "'" +
            ", releaseMapLink='" + getReleaseMapLink() + "'" +
            ", arrivalMapLink='" + getArrivalMapLink() + "'" +
            ", distance=" + getDistance() +
            ", distanceDisplayedValue='" + getDistanceDisplayedValue() + "'" +
            ", checkPointDistance='" + getCheckPointDistance() + "'" +
            ", checkLinedReporting='" + getCheckLinedReporting() + "'" +
            ", checkLinePointDistance='" + getCheckLinePointDistance() + "'" +
            ", timeWindow='" + getTimeWindow() + "'" +
            ", generationInProgress='" + getGenerationInProgress() + "'" +
            ", generated='" + getGenerated() + "'" +
            ", reset='" + getReset() + "'" +
            ", progress=" + getProgress() +
            "}";
    }
}
