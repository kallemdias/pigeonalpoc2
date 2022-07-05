package de.mellak.pigeonal.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.mellak.pigeonal.domain.enumeration.LogType;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A YcLogEntry.
 */
@Entity
@Table(name = "yc_log_entry")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class YcLogEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "created")
    private Instant created;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "method")
    private String method;

    @Column(name = "step")
    private String step;

    @Column(name = "discription")
    private String discription;

    @Enumerated(EnumType.STRING)
    @Column(name = "log_type")
    private LogType logType;

    @ManyToOne
    @JsonIgnoreProperties(value = { "weatherReports", "legs", "logs", "user", "association" }, allowSetters = true)
    private RacingPlan racingPlan;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public YcLogEntry id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreated() {
        return this.created;
    }

    public YcLogEntry created(Instant created) {
        this.setCreated(created);
        return this;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public YcLogEntry serviceName(String serviceName) {
        this.setServiceName(serviceName);
        return this;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMethod() {
        return this.method;
    }

    public YcLogEntry method(String method) {
        this.setMethod(method);
        return this;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStep() {
        return this.step;
    }

    public YcLogEntry step(String step) {
        this.setStep(step);
        return this;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getDiscription() {
        return this.discription;
    }

    public YcLogEntry discription(String discription) {
        this.setDiscription(discription);
        return this;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public LogType getLogType() {
        return this.logType;
    }

    public YcLogEntry logType(LogType logType) {
        this.setLogType(logType);
        return this;
    }

    public void setLogType(LogType logType) {
        this.logType = logType;
    }

    public RacingPlan getRacingPlan() {
        return this.racingPlan;
    }

    public void setRacingPlan(RacingPlan racingPlan) {
        this.racingPlan = racingPlan;
    }

    public YcLogEntry racingPlan(RacingPlan racingPlan) {
        this.setRacingPlan(racingPlan);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof YcLogEntry)) {
            return false;
        }
        return id != null && id.equals(((YcLogEntry) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "YcLogEntry{" +
            "id=" + getId() +
            ", created='" + getCreated() + "'" +
            ", serviceName='" + getServiceName() + "'" +
            ", method='" + getMethod() + "'" +
            ", step='" + getStep() + "'" +
            ", discription='" + getDiscription() + "'" +
            ", logType='" + getLogType() + "'" +
            "}";
    }
}
