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
 * A CheckPoint.
 */
@Entity
@Table(name = "check_point")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CheckPoint implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Min(value = 0)
    @Column(name = "jhi_order")
    private Integer order;

    @Column(name = "date_time")
    private Instant dateTime;

    @Column(name = "location_name")
    private String locationName;

    @Column(name = "lat_decimal")
    private Double latDecimal;

    @Column(name = "lng_decimal")
    private Double lngDecimal;

    @DecimalMin(value = "0")
    @Column(name = "distance")
    private Double distance;

    @Column(name = "distance_displayed_value")
    private String distanceDisplayedValue;

    @Column(name = "link")
    private String link;

    @Column(name = "alerts")
    private String alerts;

    @OneToMany(mappedBy = "checkPoint")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "releavanceHeader", "checkPoint" }, allowSetters = true)
    private Set<WeatherDataPoint> dataPoints = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "checkPoints", "weatherReport" }, allowSetters = true)
    private CheckLine checkLine;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CheckPoint id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrder() {
        return this.order;
    }

    public CheckPoint order(Integer order) {
        this.setOrder(order);
        return this;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Instant getDateTime() {
        return this.dateTime;
    }

    public CheckPoint dateTime(Instant dateTime) {
        this.setDateTime(dateTime);
        return this;
    }

    public void setDateTime(Instant dateTime) {
        this.dateTime = dateTime;
    }

    public String getLocationName() {
        return this.locationName;
    }

    public CheckPoint locationName(String locationName) {
        this.setLocationName(locationName);
        return this;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Double getLatDecimal() {
        return this.latDecimal;
    }

    public CheckPoint latDecimal(Double latDecimal) {
        this.setLatDecimal(latDecimal);
        return this;
    }

    public void setLatDecimal(Double latDecimal) {
        this.latDecimal = latDecimal;
    }

    public Double getLngDecimal() {
        return this.lngDecimal;
    }

    public CheckPoint lngDecimal(Double lngDecimal) {
        this.setLngDecimal(lngDecimal);
        return this;
    }

    public void setLngDecimal(Double lngDecimal) {
        this.lngDecimal = lngDecimal;
    }

    public Double getDistance() {
        return this.distance;
    }

    public CheckPoint distance(Double distance) {
        this.setDistance(distance);
        return this;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getDistanceDisplayedValue() {
        return this.distanceDisplayedValue;
    }

    public CheckPoint distanceDisplayedValue(String distanceDisplayedValue) {
        this.setDistanceDisplayedValue(distanceDisplayedValue);
        return this;
    }

    public void setDistanceDisplayedValue(String distanceDisplayedValue) {
        this.distanceDisplayedValue = distanceDisplayedValue;
    }

    public String getLink() {
        return this.link;
    }

    public CheckPoint link(String link) {
        this.setLink(link);
        return this;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAlerts() {
        return this.alerts;
    }

    public CheckPoint alerts(String alerts) {
        this.setAlerts(alerts);
        return this;
    }

    public void setAlerts(String alerts) {
        this.alerts = alerts;
    }

    public Set<WeatherDataPoint> getDataPoints() {
        return this.dataPoints;
    }

    public void setDataPoints(Set<WeatherDataPoint> weatherDataPoints) {
        if (this.dataPoints != null) {
            this.dataPoints.forEach(i -> i.setCheckPoint(null));
        }
        if (weatherDataPoints != null) {
            weatherDataPoints.forEach(i -> i.setCheckPoint(this));
        }
        this.dataPoints = weatherDataPoints;
    }

    public CheckPoint dataPoints(Set<WeatherDataPoint> weatherDataPoints) {
        this.setDataPoints(weatherDataPoints);
        return this;
    }

    public CheckPoint addDataPoint(WeatherDataPoint weatherDataPoint) {
        this.dataPoints.add(weatherDataPoint);
        weatherDataPoint.setCheckPoint(this);
        return this;
    }

    public CheckPoint removeDataPoint(WeatherDataPoint weatherDataPoint) {
        this.dataPoints.remove(weatherDataPoint);
        weatherDataPoint.setCheckPoint(null);
        return this;
    }

    public CheckLine getCheckLine() {
        return this.checkLine;
    }

    public void setCheckLine(CheckLine checkLine) {
        this.checkLine = checkLine;
    }

    public CheckPoint checkLine(CheckLine checkLine) {
        this.setCheckLine(checkLine);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CheckPoint)) {
            return false;
        }
        return id != null && id.equals(((CheckPoint) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CheckPoint{" +
            "id=" + getId() +
            ", order=" + getOrder() +
            ", dateTime='" + getDateTime() + "'" +
            ", locationName='" + getLocationName() + "'" +
            ", latDecimal=" + getLatDecimal() +
            ", lngDecimal=" + getLngDecimal() +
            ", distance=" + getDistance() +
            ", distanceDisplayedValue='" + getDistanceDisplayedValue() + "'" +
            ", link='" + getLink() + "'" +
            ", alerts='" + getAlerts() + "'" +
            "}";
    }
}
