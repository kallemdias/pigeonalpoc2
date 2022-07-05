package de.mellak.pigeonal.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A RelevanceHeader.
 */
@Entity
@Table(name = "relevance_header")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RelevanceHeader implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "midnight")
    private Boolean midnight;

    @Column(name = "one")
    private Boolean one;

    @Column(name = "two")
    private Boolean two;

    @Column(name = "three")
    private Boolean three;

    @Column(name = "four")
    private Boolean four;

    @Column(name = "five")
    private Boolean five;

    @Column(name = "six")
    private Boolean six;

    @Column(name = "seven")
    private Boolean seven;

    @Column(name = "eight")
    private Boolean eight;

    @Column(name = "nine")
    private Boolean nine;

    @Column(name = "ten")
    private Boolean ten;

    @Column(name = "eleven")
    private Boolean eleven;

    @Column(name = "twelve")
    private Boolean twelve;

    @Column(name = "thirteen")
    private Boolean thirteen;

    @Column(name = "fourteen")
    private Boolean fourteen;

    @Column(name = "fifteen")
    private Boolean fifteen;

    @Column(name = "sixteen")
    private Boolean sixteen;

    @Column(name = "seventeen")
    private Boolean seventeen;

    @Column(name = "eighteen")
    private Boolean eighteen;

    @Column(name = "nineteen")
    private Boolean nineteen;

    @Column(name = "twenty")
    private Boolean twenty;

    @Column(name = "twenty_one")
    private Boolean twentyOne;

    @Column(name = "twenty_two")
    private Boolean twentyTwo;

    @Column(name = "twenty_three")
    private Boolean twentyThree;

    @JsonIgnoreProperties(value = { "releavanceHeader", "checkPoint" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private WeatherDataPoint weatherDataPoint;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public RelevanceHeader id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getMidnight() {
        return this.midnight;
    }

    public RelevanceHeader midnight(Boolean midnight) {
        this.setMidnight(midnight);
        return this;
    }

    public void setMidnight(Boolean midnight) {
        this.midnight = midnight;
    }

    public Boolean getOne() {
        return this.one;
    }

    public RelevanceHeader one(Boolean one) {
        this.setOne(one);
        return this;
    }

    public void setOne(Boolean one) {
        this.one = one;
    }

    public Boolean getTwo() {
        return this.two;
    }

    public RelevanceHeader two(Boolean two) {
        this.setTwo(two);
        return this;
    }

    public void setTwo(Boolean two) {
        this.two = two;
    }

    public Boolean getThree() {
        return this.three;
    }

    public RelevanceHeader three(Boolean three) {
        this.setThree(three);
        return this;
    }

    public void setThree(Boolean three) {
        this.three = three;
    }

    public Boolean getFour() {
        return this.four;
    }

    public RelevanceHeader four(Boolean four) {
        this.setFour(four);
        return this;
    }

    public void setFour(Boolean four) {
        this.four = four;
    }

    public Boolean getFive() {
        return this.five;
    }

    public RelevanceHeader five(Boolean five) {
        this.setFive(five);
        return this;
    }

    public void setFive(Boolean five) {
        this.five = five;
    }

    public Boolean getSix() {
        return this.six;
    }

    public RelevanceHeader six(Boolean six) {
        this.setSix(six);
        return this;
    }

    public void setSix(Boolean six) {
        this.six = six;
    }

    public Boolean getSeven() {
        return this.seven;
    }

    public RelevanceHeader seven(Boolean seven) {
        this.setSeven(seven);
        return this;
    }

    public void setSeven(Boolean seven) {
        this.seven = seven;
    }

    public Boolean getEight() {
        return this.eight;
    }

    public RelevanceHeader eight(Boolean eight) {
        this.setEight(eight);
        return this;
    }

    public void setEight(Boolean eight) {
        this.eight = eight;
    }

    public Boolean getNine() {
        return this.nine;
    }

    public RelevanceHeader nine(Boolean nine) {
        this.setNine(nine);
        return this;
    }

    public void setNine(Boolean nine) {
        this.nine = nine;
    }

    public Boolean getTen() {
        return this.ten;
    }

    public RelevanceHeader ten(Boolean ten) {
        this.setTen(ten);
        return this;
    }

    public void setTen(Boolean ten) {
        this.ten = ten;
    }

    public Boolean getEleven() {
        return this.eleven;
    }

    public RelevanceHeader eleven(Boolean eleven) {
        this.setEleven(eleven);
        return this;
    }

    public void setEleven(Boolean eleven) {
        this.eleven = eleven;
    }

    public Boolean getTwelve() {
        return this.twelve;
    }

    public RelevanceHeader twelve(Boolean twelve) {
        this.setTwelve(twelve);
        return this;
    }

    public void setTwelve(Boolean twelve) {
        this.twelve = twelve;
    }

    public Boolean getThirteen() {
        return this.thirteen;
    }

    public RelevanceHeader thirteen(Boolean thirteen) {
        this.setThirteen(thirteen);
        return this;
    }

    public void setThirteen(Boolean thirteen) {
        this.thirteen = thirteen;
    }

    public Boolean getFourteen() {
        return this.fourteen;
    }

    public RelevanceHeader fourteen(Boolean fourteen) {
        this.setFourteen(fourteen);
        return this;
    }

    public void setFourteen(Boolean fourteen) {
        this.fourteen = fourteen;
    }

    public Boolean getFifteen() {
        return this.fifteen;
    }

    public RelevanceHeader fifteen(Boolean fifteen) {
        this.setFifteen(fifteen);
        return this;
    }

    public void setFifteen(Boolean fifteen) {
        this.fifteen = fifteen;
    }

    public Boolean getSixteen() {
        return this.sixteen;
    }

    public RelevanceHeader sixteen(Boolean sixteen) {
        this.setSixteen(sixteen);
        return this;
    }

    public void setSixteen(Boolean sixteen) {
        this.sixteen = sixteen;
    }

    public Boolean getSeventeen() {
        return this.seventeen;
    }

    public RelevanceHeader seventeen(Boolean seventeen) {
        this.setSeventeen(seventeen);
        return this;
    }

    public void setSeventeen(Boolean seventeen) {
        this.seventeen = seventeen;
    }

    public Boolean getEighteen() {
        return this.eighteen;
    }

    public RelevanceHeader eighteen(Boolean eighteen) {
        this.setEighteen(eighteen);
        return this;
    }

    public void setEighteen(Boolean eighteen) {
        this.eighteen = eighteen;
    }

    public Boolean getNineteen() {
        return this.nineteen;
    }

    public RelevanceHeader nineteen(Boolean nineteen) {
        this.setNineteen(nineteen);
        return this;
    }

    public void setNineteen(Boolean nineteen) {
        this.nineteen = nineteen;
    }

    public Boolean getTwenty() {
        return this.twenty;
    }

    public RelevanceHeader twenty(Boolean twenty) {
        this.setTwenty(twenty);
        return this;
    }

    public void setTwenty(Boolean twenty) {
        this.twenty = twenty;
    }

    public Boolean getTwentyOne() {
        return this.twentyOne;
    }

    public RelevanceHeader twentyOne(Boolean twentyOne) {
        this.setTwentyOne(twentyOne);
        return this;
    }

    public void setTwentyOne(Boolean twentyOne) {
        this.twentyOne = twentyOne;
    }

    public Boolean getTwentyTwo() {
        return this.twentyTwo;
    }

    public RelevanceHeader twentyTwo(Boolean twentyTwo) {
        this.setTwentyTwo(twentyTwo);
        return this;
    }

    public void setTwentyTwo(Boolean twentyTwo) {
        this.twentyTwo = twentyTwo;
    }

    public Boolean getTwentyThree() {
        return this.twentyThree;
    }

    public RelevanceHeader twentyThree(Boolean twentyThree) {
        this.setTwentyThree(twentyThree);
        return this;
    }

    public void setTwentyThree(Boolean twentyThree) {
        this.twentyThree = twentyThree;
    }

    public WeatherDataPoint getWeatherDataPoint() {
        return this.weatherDataPoint;
    }

    public void setWeatherDataPoint(WeatherDataPoint weatherDataPoint) {
        this.weatherDataPoint = weatherDataPoint;
    }

    public RelevanceHeader weatherDataPoint(WeatherDataPoint weatherDataPoint) {
        this.setWeatherDataPoint(weatherDataPoint);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RelevanceHeader)) {
            return false;
        }
        return id != null && id.equals(((RelevanceHeader) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RelevanceHeader{" +
            "id=" + getId() +
            ", midnight='" + getMidnight() + "'" +
            ", one='" + getOne() + "'" +
            ", two='" + getTwo() + "'" +
            ", three='" + getThree() + "'" +
            ", four='" + getFour() + "'" +
            ", five='" + getFive() + "'" +
            ", six='" + getSix() + "'" +
            ", seven='" + getSeven() + "'" +
            ", eight='" + getEight() + "'" +
            ", nine='" + getNine() + "'" +
            ", ten='" + getTen() + "'" +
            ", eleven='" + getEleven() + "'" +
            ", twelve='" + getTwelve() + "'" +
            ", thirteen='" + getThirteen() + "'" +
            ", fourteen='" + getFourteen() + "'" +
            ", fifteen='" + getFifteen() + "'" +
            ", sixteen='" + getSixteen() + "'" +
            ", seventeen='" + getSeventeen() + "'" +
            ", eighteen='" + getEighteen() + "'" +
            ", nineteen='" + getNineteen() + "'" +
            ", twenty='" + getTwenty() + "'" +
            ", twentyOne='" + getTwentyOne() + "'" +
            ", twentyTwo='" + getTwentyTwo() + "'" +
            ", twentyThree='" + getTwentyThree() + "'" +
            "}";
    }
}
