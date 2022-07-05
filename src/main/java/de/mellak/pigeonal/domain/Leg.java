package de.mellak.pigeonal.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.mellak.pigeonal.domain.enumeration.CheckLinePointDistance;
import de.mellak.pigeonal.domain.enumeration.CheckPointDistance;
import de.mellak.pigeonal.domain.enumeration.LatDirection;
import de.mellak.pigeonal.domain.enumeration.LngDirection;
import de.mellak.pigeonal.domain.enumeration.TimeWindow;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Leg.
 */
@Entity
@Table(name = "leg")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Leg implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "jhi_order")
    private Integer order;

    @Min(value = 0)
    @Max(value = 90)
    @Column(name = "dep_lat_deg")
    private Integer depLatDeg;

    @Min(value = 0)
    @Max(value = 59)
    @Column(name = "dep_lat_min")
    private Integer depLatMin;

    @Column(name = "dep_lat_sec")
    private Float depLatSec;

    @Enumerated(EnumType.STRING)
    @Column(name = "dep_lat_direction")
    private LatDirection depLatDirection;

    @Min(value = 0)
    @Max(value = 180)
    @Column(name = "dep_lng_deg")
    private Integer depLngDeg;

    @Min(value = 0)
    @Max(value = 59)
    @Column(name = "dep_lng_min")
    private Integer depLngMin;

    @Column(name = "dep_lng_sec")
    private Float depLngSec;

    @Enumerated(EnumType.STRING)
    @Column(name = "dep_lng_direction")
    private LngDirection depLngDirection;

    @Column(name = "dep_lat_displayed_value")
    private String depLatDisplayedValue;

    @Column(name = "dep_lng_displayed_value")
    private String depLngDisplayedValue;

    @Column(name = "dep_lat_decimal")
    private Double depLatDecimal;

    @Column(name = "dep_lng_decimal")
    private Double depLngDecimal;

    @Min(value = 0)
    @Max(value = 90)
    @Column(name = "arr_lat_deg")
    private Integer arrLatDeg;

    @Min(value = 0)
    @Max(value = 59)
    @Column(name = "arr_lat_min")
    private Integer arrLatMin;

    @Column(name = "arr_lat_sec")
    private Float arrLatSec;

    @Enumerated(EnumType.STRING)
    @Column(name = "arr_lat_direction")
    private LatDirection arrLatDirection;

    @Min(value = 0)
    @Max(value = 180)
    @Column(name = "arr_lng_deg")
    private Integer arrLngDeg;

    @Min(value = 0)
    @Max(value = 59)
    @Column(name = "arr_lng_min")
    private Integer arrLngMin;

    @Column(name = "arr_lng_sec")
    private Float arrLngSec;

    @Enumerated(EnumType.STRING)
    @Column(name = "arr_lng_direction")
    private LngDirection arrLngDirection;

    @Column(name = "arr_lat_displayed_value")
    private String arrLatDisplayedValue;

    @Column(name = "arr_lng_displayed_value")
    private String arrLngDisplayedValue;

    @Column(name = "arr_lat_decimal")
    private Double arrLatDecimal;

    @Column(name = "arr_lng_decimal")
    private Double arrLngDecimal;

    @Enumerated(EnumType.STRING)
    @Column(name = "check_point_distance")
    private CheckPointDistance checkPointDistance;

    @Enumerated(EnumType.STRING)
    @Column(name = "check_line_point_distance")
    private CheckLinePointDistance checkLinePointDistance;

    @Enumerated(EnumType.STRING)
    @Column(name = "time_window")
    private TimeWindow timeWindow;

    @ManyToOne
    @JsonIgnoreProperties(value = { "weatherReports", "legs", "logs", "user", "association" }, allowSetters = true)
    private RacingPlan racingPlan;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Leg id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrder() {
        return this.order;
    }

    public Leg order(Integer order) {
        this.setOrder(order);
        return this;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getDepLatDeg() {
        return this.depLatDeg;
    }

    public Leg depLatDeg(Integer depLatDeg) {
        this.setDepLatDeg(depLatDeg);
        return this;
    }

    public void setDepLatDeg(Integer depLatDeg) {
        this.depLatDeg = depLatDeg;
    }

    public Integer getDepLatMin() {
        return this.depLatMin;
    }

    public Leg depLatMin(Integer depLatMin) {
        this.setDepLatMin(depLatMin);
        return this;
    }

    public void setDepLatMin(Integer depLatMin) {
        this.depLatMin = depLatMin;
    }

    public Float getDepLatSec() {
        return this.depLatSec;
    }

    public Leg depLatSec(Float depLatSec) {
        this.setDepLatSec(depLatSec);
        return this;
    }

    public void setDepLatSec(Float depLatSec) {
        this.depLatSec = depLatSec;
    }

    public LatDirection getDepLatDirection() {
        return this.depLatDirection;
    }

    public Leg depLatDirection(LatDirection depLatDirection) {
        this.setDepLatDirection(depLatDirection);
        return this;
    }

    public void setDepLatDirection(LatDirection depLatDirection) {
        this.depLatDirection = depLatDirection;
    }

    public Integer getDepLngDeg() {
        return this.depLngDeg;
    }

    public Leg depLngDeg(Integer depLngDeg) {
        this.setDepLngDeg(depLngDeg);
        return this;
    }

    public void setDepLngDeg(Integer depLngDeg) {
        this.depLngDeg = depLngDeg;
    }

    public Integer getDepLngMin() {
        return this.depLngMin;
    }

    public Leg depLngMin(Integer depLngMin) {
        this.setDepLngMin(depLngMin);
        return this;
    }

    public void setDepLngMin(Integer depLngMin) {
        this.depLngMin = depLngMin;
    }

    public Float getDepLngSec() {
        return this.depLngSec;
    }

    public Leg depLngSec(Float depLngSec) {
        this.setDepLngSec(depLngSec);
        return this;
    }

    public void setDepLngSec(Float depLngSec) {
        this.depLngSec = depLngSec;
    }

    public LngDirection getDepLngDirection() {
        return this.depLngDirection;
    }

    public Leg depLngDirection(LngDirection depLngDirection) {
        this.setDepLngDirection(depLngDirection);
        return this;
    }

    public void setDepLngDirection(LngDirection depLngDirection) {
        this.depLngDirection = depLngDirection;
    }

    public String getDepLatDisplayedValue() {
        return this.depLatDisplayedValue;
    }

    public Leg depLatDisplayedValue(String depLatDisplayedValue) {
        this.setDepLatDisplayedValue(depLatDisplayedValue);
        return this;
    }

    public void setDepLatDisplayedValue(String depLatDisplayedValue) {
        this.depLatDisplayedValue = depLatDisplayedValue;
    }

    public String getDepLngDisplayedValue() {
        return this.depLngDisplayedValue;
    }

    public Leg depLngDisplayedValue(String depLngDisplayedValue) {
        this.setDepLngDisplayedValue(depLngDisplayedValue);
        return this;
    }

    public void setDepLngDisplayedValue(String depLngDisplayedValue) {
        this.depLngDisplayedValue = depLngDisplayedValue;
    }

    public Double getDepLatDecimal() {
        return this.depLatDecimal;
    }

    public Leg depLatDecimal(Double depLatDecimal) {
        this.setDepLatDecimal(depLatDecimal);
        return this;
    }

    public void setDepLatDecimal(Double depLatDecimal) {
        this.depLatDecimal = depLatDecimal;
    }

    public Double getDepLngDecimal() {
        return this.depLngDecimal;
    }

    public Leg depLngDecimal(Double depLngDecimal) {
        this.setDepLngDecimal(depLngDecimal);
        return this;
    }

    public void setDepLngDecimal(Double depLngDecimal) {
        this.depLngDecimal = depLngDecimal;
    }

    public Integer getArrLatDeg() {
        return this.arrLatDeg;
    }

    public Leg arrLatDeg(Integer arrLatDeg) {
        this.setArrLatDeg(arrLatDeg);
        return this;
    }

    public void setArrLatDeg(Integer arrLatDeg) {
        this.arrLatDeg = arrLatDeg;
    }

    public Integer getArrLatMin() {
        return this.arrLatMin;
    }

    public Leg arrLatMin(Integer arrLatMin) {
        this.setArrLatMin(arrLatMin);
        return this;
    }

    public void setArrLatMin(Integer arrLatMin) {
        this.arrLatMin = arrLatMin;
    }

    public Float getArrLatSec() {
        return this.arrLatSec;
    }

    public Leg arrLatSec(Float arrLatSec) {
        this.setArrLatSec(arrLatSec);
        return this;
    }

    public void setArrLatSec(Float arrLatSec) {
        this.arrLatSec = arrLatSec;
    }

    public LatDirection getArrLatDirection() {
        return this.arrLatDirection;
    }

    public Leg arrLatDirection(LatDirection arrLatDirection) {
        this.setArrLatDirection(arrLatDirection);
        return this;
    }

    public void setArrLatDirection(LatDirection arrLatDirection) {
        this.arrLatDirection = arrLatDirection;
    }

    public Integer getArrLngDeg() {
        return this.arrLngDeg;
    }

    public Leg arrLngDeg(Integer arrLngDeg) {
        this.setArrLngDeg(arrLngDeg);
        return this;
    }

    public void setArrLngDeg(Integer arrLngDeg) {
        this.arrLngDeg = arrLngDeg;
    }

    public Integer getArrLngMin() {
        return this.arrLngMin;
    }

    public Leg arrLngMin(Integer arrLngMin) {
        this.setArrLngMin(arrLngMin);
        return this;
    }

    public void setArrLngMin(Integer arrLngMin) {
        this.arrLngMin = arrLngMin;
    }

    public Float getArrLngSec() {
        return this.arrLngSec;
    }

    public Leg arrLngSec(Float arrLngSec) {
        this.setArrLngSec(arrLngSec);
        return this;
    }

    public void setArrLngSec(Float arrLngSec) {
        this.arrLngSec = arrLngSec;
    }

    public LngDirection getArrLngDirection() {
        return this.arrLngDirection;
    }

    public Leg arrLngDirection(LngDirection arrLngDirection) {
        this.setArrLngDirection(arrLngDirection);
        return this;
    }

    public void setArrLngDirection(LngDirection arrLngDirection) {
        this.arrLngDirection = arrLngDirection;
    }

    public String getArrLatDisplayedValue() {
        return this.arrLatDisplayedValue;
    }

    public Leg arrLatDisplayedValue(String arrLatDisplayedValue) {
        this.setArrLatDisplayedValue(arrLatDisplayedValue);
        return this;
    }

    public void setArrLatDisplayedValue(String arrLatDisplayedValue) {
        this.arrLatDisplayedValue = arrLatDisplayedValue;
    }

    public String getArrLngDisplayedValue() {
        return this.arrLngDisplayedValue;
    }

    public Leg arrLngDisplayedValue(String arrLngDisplayedValue) {
        this.setArrLngDisplayedValue(arrLngDisplayedValue);
        return this;
    }

    public void setArrLngDisplayedValue(String arrLngDisplayedValue) {
        this.arrLngDisplayedValue = arrLngDisplayedValue;
    }

    public Double getArrLatDecimal() {
        return this.arrLatDecimal;
    }

    public Leg arrLatDecimal(Double arrLatDecimal) {
        this.setArrLatDecimal(arrLatDecimal);
        return this;
    }

    public void setArrLatDecimal(Double arrLatDecimal) {
        this.arrLatDecimal = arrLatDecimal;
    }

    public Double getArrLngDecimal() {
        return this.arrLngDecimal;
    }

    public Leg arrLngDecimal(Double arrLngDecimal) {
        this.setArrLngDecimal(arrLngDecimal);
        return this;
    }

    public void setArrLngDecimal(Double arrLngDecimal) {
        this.arrLngDecimal = arrLngDecimal;
    }

    public CheckPointDistance getCheckPointDistance() {
        return this.checkPointDistance;
    }

    public Leg checkPointDistance(CheckPointDistance checkPointDistance) {
        this.setCheckPointDistance(checkPointDistance);
        return this;
    }

    public void setCheckPointDistance(CheckPointDistance checkPointDistance) {
        this.checkPointDistance = checkPointDistance;
    }

    public CheckLinePointDistance getCheckLinePointDistance() {
        return this.checkLinePointDistance;
    }

    public Leg checkLinePointDistance(CheckLinePointDistance checkLinePointDistance) {
        this.setCheckLinePointDistance(checkLinePointDistance);
        return this;
    }

    public void setCheckLinePointDistance(CheckLinePointDistance checkLinePointDistance) {
        this.checkLinePointDistance = checkLinePointDistance;
    }

    public TimeWindow getTimeWindow() {
        return this.timeWindow;
    }

    public Leg timeWindow(TimeWindow timeWindow) {
        this.setTimeWindow(timeWindow);
        return this;
    }

    public void setTimeWindow(TimeWindow timeWindow) {
        this.timeWindow = timeWindow;
    }

    public RacingPlan getRacingPlan() {
        return this.racingPlan;
    }

    public void setRacingPlan(RacingPlan racingPlan) {
        this.racingPlan = racingPlan;
    }

    public Leg racingPlan(RacingPlan racingPlan) {
        this.setRacingPlan(racingPlan);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Leg)) {
            return false;
        }
        return id != null && id.equals(((Leg) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Leg{" +
            "id=" + getId() +
            ", order=" + getOrder() +
            ", depLatDeg=" + getDepLatDeg() +
            ", depLatMin=" + getDepLatMin() +
            ", depLatSec=" + getDepLatSec() +
            ", depLatDirection='" + getDepLatDirection() + "'" +
            ", depLngDeg=" + getDepLngDeg() +
            ", depLngMin=" + getDepLngMin() +
            ", depLngSec=" + getDepLngSec() +
            ", depLngDirection='" + getDepLngDirection() + "'" +
            ", depLatDisplayedValue='" + getDepLatDisplayedValue() + "'" +
            ", depLngDisplayedValue='" + getDepLngDisplayedValue() + "'" +
            ", depLatDecimal=" + getDepLatDecimal() +
            ", depLngDecimal=" + getDepLngDecimal() +
            ", arrLatDeg=" + getArrLatDeg() +
            ", arrLatMin=" + getArrLatMin() +
            ", arrLatSec=" + getArrLatSec() +
            ", arrLatDirection='" + getArrLatDirection() + "'" +
            ", arrLngDeg=" + getArrLngDeg() +
            ", arrLngMin=" + getArrLngMin() +
            ", arrLngSec=" + getArrLngSec() +
            ", arrLngDirection='" + getArrLngDirection() + "'" +
            ", arrLatDisplayedValue='" + getArrLatDisplayedValue() + "'" +
            ", arrLngDisplayedValue='" + getArrLngDisplayedValue() + "'" +
            ", arrLatDecimal=" + getArrLatDecimal() +
            ", arrLngDecimal=" + getArrLngDecimal() +
            ", checkPointDistance='" + getCheckPointDistance() + "'" +
            ", checkLinePointDistance='" + getCheckLinePointDistance() + "'" +
            ", timeWindow='" + getTimeWindow() + "'" +
            "}";
    }
}
