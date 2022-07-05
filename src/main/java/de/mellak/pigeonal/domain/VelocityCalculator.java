package de.mellak.pigeonal.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A VelocityCalculator.
 */
@Entity
@Table(name = "velocity_calculator")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VelocityCalculator implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "release_date_time")
    private Instant releaseDateTime;

    @Column(name = "arrival_date_time")
    private Instant arrivalDateTime;

    @Column(name = "distance_km")
    private Integer distanceKM;

    @Column(name = "distance_m")
    private Integer distanceM;

    @Column(name = "velocity")
    private Double velocity;

    @Column(name = "velocity_disp_val")
    private String velocityDispVal;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public VelocityCalculator id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getReleaseDateTime() {
        return this.releaseDateTime;
    }

    public VelocityCalculator releaseDateTime(Instant releaseDateTime) {
        this.setReleaseDateTime(releaseDateTime);
        return this;
    }

    public void setReleaseDateTime(Instant releaseDateTime) {
        this.releaseDateTime = releaseDateTime;
    }

    public Instant getArrivalDateTime() {
        return this.arrivalDateTime;
    }

    public VelocityCalculator arrivalDateTime(Instant arrivalDateTime) {
        this.setArrivalDateTime(arrivalDateTime);
        return this;
    }

    public void setArrivalDateTime(Instant arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public Integer getDistanceKM() {
        return this.distanceKM;
    }

    public VelocityCalculator distanceKM(Integer distanceKM) {
        this.setDistanceKM(distanceKM);
        return this;
    }

    public void setDistanceKM(Integer distanceKM) {
        this.distanceKM = distanceKM;
    }

    public Integer getDistanceM() {
        return this.distanceM;
    }

    public VelocityCalculator distanceM(Integer distanceM) {
        this.setDistanceM(distanceM);
        return this;
    }

    public void setDistanceM(Integer distanceM) {
        this.distanceM = distanceM;
    }

    public Double getVelocity() {
        return this.velocity;
    }

    public VelocityCalculator velocity(Double velocity) {
        this.setVelocity(velocity);
        return this;
    }

    public void setVelocity(Double velocity) {
        this.velocity = velocity;
    }

    public String getVelocityDispVal() {
        return this.velocityDispVal;
    }

    public VelocityCalculator velocityDispVal(String velocityDispVal) {
        this.setVelocityDispVal(velocityDispVal);
        return this;
    }

    public void setVelocityDispVal(String velocityDispVal) {
        this.velocityDispVal = velocityDispVal;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VelocityCalculator)) {
            return false;
        }
        return id != null && id.equals(((VelocityCalculator) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VelocityCalculator{" +
            "id=" + getId() +
            ", releaseDateTime='" + getReleaseDateTime() + "'" +
            ", arrivalDateTime='" + getArrivalDateTime() + "'" +
            ", distanceKM=" + getDistanceKM() +
            ", distanceM=" + getDistanceM() +
            ", velocity=" + getVelocity() +
            ", velocityDispVal='" + getVelocityDispVal() + "'" +
            "}";
    }
}
