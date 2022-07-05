package de.mellak.pigeonal.domain;

import de.mellak.pigeonal.domain.enumeration.LatDirection;
import de.mellak.pigeonal.domain.enumeration.LngDirection;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A GpsCoordinateCheck.
 */
@Entity
@Table(name = "gps_coordinate_check")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GpsCoordinateCheck implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Min(value = 0)
    @Max(value = 90)
    @Column(name = "lat_deg")
    private Integer latDeg;

    @Min(value = 0)
    @Max(value = 59)
    @Column(name = "lat_min")
    private Integer latMin;

    @Column(name = "lat_sec")
    private Float latSec;

    @Enumerated(EnumType.STRING)
    @Column(name = "lat_direction")
    private LatDirection latDirection;

    @Min(value = 0)
    @Max(value = 180)
    @Column(name = "lng_deg")
    private Integer lngDeg;

    @Min(value = 0)
    @Max(value = 59)
    @Column(name = "lng_min")
    private Integer lngMin;

    @Column(name = "lng_sec")
    private Float lngSec;

    @Enumerated(EnumType.STRING)
    @Column(name = "lng_direction")
    private LngDirection lngDirection;

    @Column(name = "lat_displayed_value")
    private String latDisplayedValue;

    @Column(name = "lng_displayed_value")
    private String lngDisplayedValue;

    @Column(name = "lat_decimal")
    private Double latDecimal;

    @Column(name = "lng_decimal")
    private Double lngDecimal;

    @Column(name = "link")
    private String link;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public GpsCoordinateCheck id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLatDeg() {
        return this.latDeg;
    }

    public GpsCoordinateCheck latDeg(Integer latDeg) {
        this.setLatDeg(latDeg);
        return this;
    }

    public void setLatDeg(Integer latDeg) {
        this.latDeg = latDeg;
    }

    public Integer getLatMin() {
        return this.latMin;
    }

    public GpsCoordinateCheck latMin(Integer latMin) {
        this.setLatMin(latMin);
        return this;
    }

    public void setLatMin(Integer latMin) {
        this.latMin = latMin;
    }

    public Float getLatSec() {
        return this.latSec;
    }

    public GpsCoordinateCheck latSec(Float latSec) {
        this.setLatSec(latSec);
        return this;
    }

    public void setLatSec(Float latSec) {
        this.latSec = latSec;
    }

    public LatDirection getLatDirection() {
        return this.latDirection;
    }

    public GpsCoordinateCheck latDirection(LatDirection latDirection) {
        this.setLatDirection(latDirection);
        return this;
    }

    public void setLatDirection(LatDirection latDirection) {
        this.latDirection = latDirection;
    }

    public Integer getLngDeg() {
        return this.lngDeg;
    }

    public GpsCoordinateCheck lngDeg(Integer lngDeg) {
        this.setLngDeg(lngDeg);
        return this;
    }

    public void setLngDeg(Integer lngDeg) {
        this.lngDeg = lngDeg;
    }

    public Integer getLngMin() {
        return this.lngMin;
    }

    public GpsCoordinateCheck lngMin(Integer lngMin) {
        this.setLngMin(lngMin);
        return this;
    }

    public void setLngMin(Integer lngMin) {
        this.lngMin = lngMin;
    }

    public Float getLngSec() {
        return this.lngSec;
    }

    public GpsCoordinateCheck lngSec(Float lngSec) {
        this.setLngSec(lngSec);
        return this;
    }

    public void setLngSec(Float lngSec) {
        this.lngSec = lngSec;
    }

    public LngDirection getLngDirection() {
        return this.lngDirection;
    }

    public GpsCoordinateCheck lngDirection(LngDirection lngDirection) {
        this.setLngDirection(lngDirection);
        return this;
    }

    public void setLngDirection(LngDirection lngDirection) {
        this.lngDirection = lngDirection;
    }

    public String getLatDisplayedValue() {
        return this.latDisplayedValue;
    }

    public GpsCoordinateCheck latDisplayedValue(String latDisplayedValue) {
        this.setLatDisplayedValue(latDisplayedValue);
        return this;
    }

    public void setLatDisplayedValue(String latDisplayedValue) {
        this.latDisplayedValue = latDisplayedValue;
    }

    public String getLngDisplayedValue() {
        return this.lngDisplayedValue;
    }

    public GpsCoordinateCheck lngDisplayedValue(String lngDisplayedValue) {
        this.setLngDisplayedValue(lngDisplayedValue);
        return this;
    }

    public void setLngDisplayedValue(String lngDisplayedValue) {
        this.lngDisplayedValue = lngDisplayedValue;
    }

    public Double getLatDecimal() {
        return this.latDecimal;
    }

    public GpsCoordinateCheck latDecimal(Double latDecimal) {
        this.setLatDecimal(latDecimal);
        return this;
    }

    public void setLatDecimal(Double latDecimal) {
        this.latDecimal = latDecimal;
    }

    public Double getLngDecimal() {
        return this.lngDecimal;
    }

    public GpsCoordinateCheck lngDecimal(Double lngDecimal) {
        this.setLngDecimal(lngDecimal);
        return this;
    }

    public void setLngDecimal(Double lngDecimal) {
        this.lngDecimal = lngDecimal;
    }

    public String getLink() {
        return this.link;
    }

    public GpsCoordinateCheck link(String link) {
        this.setLink(link);
        return this;
    }

    public void setLink(String link) {
        this.link = link;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GpsCoordinateCheck)) {
            return false;
        }
        return id != null && id.equals(((GpsCoordinateCheck) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GpsCoordinateCheck{" +
            "id=" + getId() +
            ", latDeg=" + getLatDeg() +
            ", latMin=" + getLatMin() +
            ", latSec=" + getLatSec() +
            ", latDirection='" + getLatDirection() + "'" +
            ", lngDeg=" + getLngDeg() +
            ", lngMin=" + getLngMin() +
            ", lngSec=" + getLngSec() +
            ", lngDirection='" + getLngDirection() + "'" +
            ", latDisplayedValue='" + getLatDisplayedValue() + "'" +
            ", lngDisplayedValue='" + getLngDisplayedValue() + "'" +
            ", latDecimal=" + getLatDecimal() +
            ", lngDecimal=" + getLngDecimal() +
            ", link='" + getLink() + "'" +
            "}";
    }
}
