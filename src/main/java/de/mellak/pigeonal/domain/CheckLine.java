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
 * A CheckLine.
 */
@Entity
@Table(name = "check_line")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CheckLine implements Serializable {

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

    @OneToMany(mappedBy = "checkLine")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "dataPoints", "checkLine" }, allowSetters = true)
    private Set<CheckPoint> checkPoints = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "checkLines", "racingPlan" }, allowSetters = true)
    private WeatherReport weatherReport;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CheckLine id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrder() {
        return this.order;
    }

    public CheckLine order(Integer order) {
        this.setOrder(order);
        return this;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Instant getDateTime() {
        return this.dateTime;
    }

    public CheckLine dateTime(Instant dateTime) {
        this.setDateTime(dateTime);
        return this;
    }

    public void setDateTime(Instant dateTime) {
        this.dateTime = dateTime;
    }

    public String getLocationName() {
        return this.locationName;
    }

    public CheckLine locationName(String locationName) {
        this.setLocationName(locationName);
        return this;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Double getLatDecimal() {
        return this.latDecimal;
    }

    public CheckLine latDecimal(Double latDecimal) {
        this.setLatDecimal(latDecimal);
        return this;
    }

    public void setLatDecimal(Double latDecimal) {
        this.latDecimal = latDecimal;
    }

    public Double getLngDecimal() {
        return this.lngDecimal;
    }

    public CheckLine lngDecimal(Double lngDecimal) {
        this.setLngDecimal(lngDecimal);
        return this;
    }

    public void setLngDecimal(Double lngDecimal) {
        this.lngDecimal = lngDecimal;
    }

    public Double getDistance() {
        return this.distance;
    }

    public CheckLine distance(Double distance) {
        this.setDistance(distance);
        return this;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getDistanceDisplayedValue() {
        return this.distanceDisplayedValue;
    }

    public CheckLine distanceDisplayedValue(String distanceDisplayedValue) {
        this.setDistanceDisplayedValue(distanceDisplayedValue);
        return this;
    }

    public void setDistanceDisplayedValue(String distanceDisplayedValue) {
        this.distanceDisplayedValue = distanceDisplayedValue;
    }

    public String getLink() {
        return this.link;
    }

    public CheckLine link(String link) {
        this.setLink(link);
        return this;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Set<CheckPoint> getCheckPoints() {
        return this.checkPoints;
    }

    public void setCheckPoints(Set<CheckPoint> checkPoints) {
        if (this.checkPoints != null) {
            this.checkPoints.forEach(i -> i.setCheckLine(null));
        }
        if (checkPoints != null) {
            checkPoints.forEach(i -> i.setCheckLine(this));
        }
        this.checkPoints = checkPoints;
    }

    public CheckLine checkPoints(Set<CheckPoint> checkPoints) {
        this.setCheckPoints(checkPoints);
        return this;
    }

    public CheckLine addCheckPoint(CheckPoint checkPoint) {
        this.checkPoints.add(checkPoint);
        checkPoint.setCheckLine(this);
        return this;
    }

    public CheckLine removeCheckPoint(CheckPoint checkPoint) {
        this.checkPoints.remove(checkPoint);
        checkPoint.setCheckLine(null);
        return this;
    }

    public WeatherReport getWeatherReport() {
        return this.weatherReport;
    }

    public void setWeatherReport(WeatherReport weatherReport) {
        this.weatherReport = weatherReport;
    }

    public CheckLine weatherReport(WeatherReport weatherReport) {
        this.setWeatherReport(weatherReport);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CheckLine)) {
            return false;
        }
        return id != null && id.equals(((CheckLine) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CheckLine{" +
            "id=" + getId() +
            ", order=" + getOrder() +
            ", dateTime='" + getDateTime() + "'" +
            ", locationName='" + getLocationName() + "'" +
            ", latDecimal=" + getLatDecimal() +
            ", lngDecimal=" + getLngDecimal() +
            ", distance=" + getDistance() +
            ", distanceDisplayedValue='" + getDistanceDisplayedValue() + "'" +
            ", link='" + getLink() + "'" +
            "}";
    }
}
