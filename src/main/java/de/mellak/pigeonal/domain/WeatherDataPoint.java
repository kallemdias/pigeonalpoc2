package de.mellak.pigeonal.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.mellak.pigeonal.domain.enumeration.WeatherProperty;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A WeatherDataPoint.
 */
@Entity
@Table(name = "weather_data_point")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WeatherDataPoint implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "property")
    private WeatherProperty property;

    @Column(name = "mid_night")
    private String midNight;

    @Column(name = "mig_night_relevance")
    private Boolean migNightRelevance;

    @Column(name = "one")
    private String one;

    @Column(name = "one_relevance")
    private Boolean oneRelevance;

    @Column(name = "two")
    private String two;

    @Column(name = "two_relevance")
    private Boolean twoRelevance;

    @Column(name = "three")
    private String three;

    @Column(name = "three_relevance")
    private Boolean threeRelevance;

    @Column(name = "four")
    private String four;

    @Column(name = "four_relevance")
    private Boolean fourRelevance;

    @Column(name = "five")
    private String five;

    @Column(name = "five_relevance")
    private Boolean fiveRelevance;

    @Column(name = "six")
    private String six;

    @Column(name = "six_relevance")
    private Boolean sixRelevance;

    @Column(name = "seven")
    private String seven;

    @Column(name = "seven_relevance")
    private Boolean sevenRelevance;

    @Column(name = "eight")
    private String eight;

    @Column(name = "eight_relevance")
    private Boolean eightRelevance;

    @Column(name = "nine")
    private String nine;

    @Column(name = "nine_relevance")
    private Boolean nineRelevance;

    @Column(name = "ten")
    private String ten;

    @Column(name = "ten_relevance")
    private Boolean tenRelevance;

    @Column(name = "eleven")
    private String eleven;

    @Column(name = "eleven_relevance")
    private Boolean elevenRelevance;

    @Column(name = "twelve")
    private String twelve;

    @Column(name = "twelve_relevance")
    private Boolean twelveRelevance;

    @Column(name = "thirteen")
    private String thirteen;

    @Column(name = "thirteen_relevance")
    private Boolean thirteenRelevance;

    @Column(name = "fourteen")
    private String fourteen;

    @Column(name = "fourteen_relevance")
    private Boolean fourteenRelevance;

    @Column(name = "fifteen")
    private String fifteen;

    @Column(name = "fifteen_relevance")
    private Boolean fifteenRelevance;

    @Column(name = "sixteen")
    private String sixteen;

    @Column(name = "sixteen_relevance")
    private Boolean sixteenRelevance;

    @Column(name = "seventeen")
    private String seventeen;

    @Column(name = "seventeen_relevance")
    private Boolean seventeenRelevance;

    @Column(name = "eighteen")
    private String eighteen;

    @Column(name = "eighteen_relevance")
    private Boolean eighteenRelevance;

    @Column(name = "nineteen")
    private String nineteen;

    @Column(name = "nineteen_relevance")
    private Boolean nineteenRelevance;

    @Column(name = "twenty")
    private String twenty;

    @Column(name = "twenty_relevance")
    private Boolean twentyRelevance;

    @Column(name = "twenty_one")
    private String twentyOne;

    @Column(name = "twenty_one_relevance")
    private Boolean twentyOneRelevance;

    @Column(name = "twenty_two")
    private String twentyTwo;

    @Column(name = "twenty_two_relevance")
    private Boolean twentyTwoRelevance;

    @Column(name = "twenty_three")
    private String twentyThree;

    @JsonIgnoreProperties(value = { "weatherDataPoint" }, allowSetters = true)
    @OneToOne(mappedBy = "weatherDataPoint")
    private RelevanceHeader releavanceHeader;

    @ManyToOne
    @JsonIgnoreProperties(value = { "dataPoints", "checkLine" }, allowSetters = true)
    private CheckPoint checkPoint;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public WeatherDataPoint id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WeatherProperty getProperty() {
        return this.property;
    }

    public WeatherDataPoint property(WeatherProperty property) {
        this.setProperty(property);
        return this;
    }

    public void setProperty(WeatherProperty property) {
        this.property = property;
    }

    public String getMidNight() {
        return this.midNight;
    }

    public WeatherDataPoint midNight(String midNight) {
        this.setMidNight(midNight);
        return this;
    }

    public void setMidNight(String midNight) {
        this.midNight = midNight;
    }

    public Boolean getMigNightRelevance() {
        return this.migNightRelevance;
    }

    public WeatherDataPoint migNightRelevance(Boolean migNightRelevance) {
        this.setMigNightRelevance(migNightRelevance);
        return this;
    }

    public void setMigNightRelevance(Boolean migNightRelevance) {
        this.migNightRelevance = migNightRelevance;
    }

    public String getOne() {
        return this.one;
    }

    public WeatherDataPoint one(String one) {
        this.setOne(one);
        return this;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public Boolean getOneRelevance() {
        return this.oneRelevance;
    }

    public WeatherDataPoint oneRelevance(Boolean oneRelevance) {
        this.setOneRelevance(oneRelevance);
        return this;
    }

    public void setOneRelevance(Boolean oneRelevance) {
        this.oneRelevance = oneRelevance;
    }

    public String getTwo() {
        return this.two;
    }

    public WeatherDataPoint two(String two) {
        this.setTwo(two);
        return this;
    }

    public void setTwo(String two) {
        this.two = two;
    }

    public Boolean getTwoRelevance() {
        return this.twoRelevance;
    }

    public WeatherDataPoint twoRelevance(Boolean twoRelevance) {
        this.setTwoRelevance(twoRelevance);
        return this;
    }

    public void setTwoRelevance(Boolean twoRelevance) {
        this.twoRelevance = twoRelevance;
    }

    public String getThree() {
        return this.three;
    }

    public WeatherDataPoint three(String three) {
        this.setThree(three);
        return this;
    }

    public void setThree(String three) {
        this.three = three;
    }

    public Boolean getThreeRelevance() {
        return this.threeRelevance;
    }

    public WeatherDataPoint threeRelevance(Boolean threeRelevance) {
        this.setThreeRelevance(threeRelevance);
        return this;
    }

    public void setThreeRelevance(Boolean threeRelevance) {
        this.threeRelevance = threeRelevance;
    }

    public String getFour() {
        return this.four;
    }

    public WeatherDataPoint four(String four) {
        this.setFour(four);
        return this;
    }

    public void setFour(String four) {
        this.four = four;
    }

    public Boolean getFourRelevance() {
        return this.fourRelevance;
    }

    public WeatherDataPoint fourRelevance(Boolean fourRelevance) {
        this.setFourRelevance(fourRelevance);
        return this;
    }

    public void setFourRelevance(Boolean fourRelevance) {
        this.fourRelevance = fourRelevance;
    }

    public String getFive() {
        return this.five;
    }

    public WeatherDataPoint five(String five) {
        this.setFive(five);
        return this;
    }

    public void setFive(String five) {
        this.five = five;
    }

    public Boolean getFiveRelevance() {
        return this.fiveRelevance;
    }

    public WeatherDataPoint fiveRelevance(Boolean fiveRelevance) {
        this.setFiveRelevance(fiveRelevance);
        return this;
    }

    public void setFiveRelevance(Boolean fiveRelevance) {
        this.fiveRelevance = fiveRelevance;
    }

    public String getSix() {
        return this.six;
    }

    public WeatherDataPoint six(String six) {
        this.setSix(six);
        return this;
    }

    public void setSix(String six) {
        this.six = six;
    }

    public Boolean getSixRelevance() {
        return this.sixRelevance;
    }

    public WeatherDataPoint sixRelevance(Boolean sixRelevance) {
        this.setSixRelevance(sixRelevance);
        return this;
    }

    public void setSixRelevance(Boolean sixRelevance) {
        this.sixRelevance = sixRelevance;
    }

    public String getSeven() {
        return this.seven;
    }

    public WeatherDataPoint seven(String seven) {
        this.setSeven(seven);
        return this;
    }

    public void setSeven(String seven) {
        this.seven = seven;
    }

    public Boolean getSevenRelevance() {
        return this.sevenRelevance;
    }

    public WeatherDataPoint sevenRelevance(Boolean sevenRelevance) {
        this.setSevenRelevance(sevenRelevance);
        return this;
    }

    public void setSevenRelevance(Boolean sevenRelevance) {
        this.sevenRelevance = sevenRelevance;
    }

    public String getEight() {
        return this.eight;
    }

    public WeatherDataPoint eight(String eight) {
        this.setEight(eight);
        return this;
    }

    public void setEight(String eight) {
        this.eight = eight;
    }

    public Boolean getEightRelevance() {
        return this.eightRelevance;
    }

    public WeatherDataPoint eightRelevance(Boolean eightRelevance) {
        this.setEightRelevance(eightRelevance);
        return this;
    }

    public void setEightRelevance(Boolean eightRelevance) {
        this.eightRelevance = eightRelevance;
    }

    public String getNine() {
        return this.nine;
    }

    public WeatherDataPoint nine(String nine) {
        this.setNine(nine);
        return this;
    }

    public void setNine(String nine) {
        this.nine = nine;
    }

    public Boolean getNineRelevance() {
        return this.nineRelevance;
    }

    public WeatherDataPoint nineRelevance(Boolean nineRelevance) {
        this.setNineRelevance(nineRelevance);
        return this;
    }

    public void setNineRelevance(Boolean nineRelevance) {
        this.nineRelevance = nineRelevance;
    }

    public String getTen() {
        return this.ten;
    }

    public WeatherDataPoint ten(String ten) {
        this.setTen(ten);
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Boolean getTenRelevance() {
        return this.tenRelevance;
    }

    public WeatherDataPoint tenRelevance(Boolean tenRelevance) {
        this.setTenRelevance(tenRelevance);
        return this;
    }

    public void setTenRelevance(Boolean tenRelevance) {
        this.tenRelevance = tenRelevance;
    }

    public String getEleven() {
        return this.eleven;
    }

    public WeatherDataPoint eleven(String eleven) {
        this.setEleven(eleven);
        return this;
    }

    public void setEleven(String eleven) {
        this.eleven = eleven;
    }

    public Boolean getElevenRelevance() {
        return this.elevenRelevance;
    }

    public WeatherDataPoint elevenRelevance(Boolean elevenRelevance) {
        this.setElevenRelevance(elevenRelevance);
        return this;
    }

    public void setElevenRelevance(Boolean elevenRelevance) {
        this.elevenRelevance = elevenRelevance;
    }

    public String getTwelve() {
        return this.twelve;
    }

    public WeatherDataPoint twelve(String twelve) {
        this.setTwelve(twelve);
        return this;
    }

    public void setTwelve(String twelve) {
        this.twelve = twelve;
    }

    public Boolean getTwelveRelevance() {
        return this.twelveRelevance;
    }

    public WeatherDataPoint twelveRelevance(Boolean twelveRelevance) {
        this.setTwelveRelevance(twelveRelevance);
        return this;
    }

    public void setTwelveRelevance(Boolean twelveRelevance) {
        this.twelveRelevance = twelveRelevance;
    }

    public String getThirteen() {
        return this.thirteen;
    }

    public WeatherDataPoint thirteen(String thirteen) {
        this.setThirteen(thirteen);
        return this;
    }

    public void setThirteen(String thirteen) {
        this.thirteen = thirteen;
    }

    public Boolean getThirteenRelevance() {
        return this.thirteenRelevance;
    }

    public WeatherDataPoint thirteenRelevance(Boolean thirteenRelevance) {
        this.setThirteenRelevance(thirteenRelevance);
        return this;
    }

    public void setThirteenRelevance(Boolean thirteenRelevance) {
        this.thirteenRelevance = thirteenRelevance;
    }

    public String getFourteen() {
        return this.fourteen;
    }

    public WeatherDataPoint fourteen(String fourteen) {
        this.setFourteen(fourteen);
        return this;
    }

    public void setFourteen(String fourteen) {
        this.fourteen = fourteen;
    }

    public Boolean getFourteenRelevance() {
        return this.fourteenRelevance;
    }

    public WeatherDataPoint fourteenRelevance(Boolean fourteenRelevance) {
        this.setFourteenRelevance(fourteenRelevance);
        return this;
    }

    public void setFourteenRelevance(Boolean fourteenRelevance) {
        this.fourteenRelevance = fourteenRelevance;
    }

    public String getFifteen() {
        return this.fifteen;
    }

    public WeatherDataPoint fifteen(String fifteen) {
        this.setFifteen(fifteen);
        return this;
    }

    public void setFifteen(String fifteen) {
        this.fifteen = fifteen;
    }

    public Boolean getFifteenRelevance() {
        return this.fifteenRelevance;
    }

    public WeatherDataPoint fifteenRelevance(Boolean fifteenRelevance) {
        this.setFifteenRelevance(fifteenRelevance);
        return this;
    }

    public void setFifteenRelevance(Boolean fifteenRelevance) {
        this.fifteenRelevance = fifteenRelevance;
    }

    public String getSixteen() {
        return this.sixteen;
    }

    public WeatherDataPoint sixteen(String sixteen) {
        this.setSixteen(sixteen);
        return this;
    }

    public void setSixteen(String sixteen) {
        this.sixteen = sixteen;
    }

    public Boolean getSixteenRelevance() {
        return this.sixteenRelevance;
    }

    public WeatherDataPoint sixteenRelevance(Boolean sixteenRelevance) {
        this.setSixteenRelevance(sixteenRelevance);
        return this;
    }

    public void setSixteenRelevance(Boolean sixteenRelevance) {
        this.sixteenRelevance = sixteenRelevance;
    }

    public String getSeventeen() {
        return this.seventeen;
    }

    public WeatherDataPoint seventeen(String seventeen) {
        this.setSeventeen(seventeen);
        return this;
    }

    public void setSeventeen(String seventeen) {
        this.seventeen = seventeen;
    }

    public Boolean getSeventeenRelevance() {
        return this.seventeenRelevance;
    }

    public WeatherDataPoint seventeenRelevance(Boolean seventeenRelevance) {
        this.setSeventeenRelevance(seventeenRelevance);
        return this;
    }

    public void setSeventeenRelevance(Boolean seventeenRelevance) {
        this.seventeenRelevance = seventeenRelevance;
    }

    public String getEighteen() {
        return this.eighteen;
    }

    public WeatherDataPoint eighteen(String eighteen) {
        this.setEighteen(eighteen);
        return this;
    }

    public void setEighteen(String eighteen) {
        this.eighteen = eighteen;
    }

    public Boolean getEighteenRelevance() {
        return this.eighteenRelevance;
    }

    public WeatherDataPoint eighteenRelevance(Boolean eighteenRelevance) {
        this.setEighteenRelevance(eighteenRelevance);
        return this;
    }

    public void setEighteenRelevance(Boolean eighteenRelevance) {
        this.eighteenRelevance = eighteenRelevance;
    }

    public String getNineteen() {
        return this.nineteen;
    }

    public WeatherDataPoint nineteen(String nineteen) {
        this.setNineteen(nineteen);
        return this;
    }

    public void setNineteen(String nineteen) {
        this.nineteen = nineteen;
    }

    public Boolean getNineteenRelevance() {
        return this.nineteenRelevance;
    }

    public WeatherDataPoint nineteenRelevance(Boolean nineteenRelevance) {
        this.setNineteenRelevance(nineteenRelevance);
        return this;
    }

    public void setNineteenRelevance(Boolean nineteenRelevance) {
        this.nineteenRelevance = nineteenRelevance;
    }

    public String getTwenty() {
        return this.twenty;
    }

    public WeatherDataPoint twenty(String twenty) {
        this.setTwenty(twenty);
        return this;
    }

    public void setTwenty(String twenty) {
        this.twenty = twenty;
    }

    public Boolean getTwentyRelevance() {
        return this.twentyRelevance;
    }

    public WeatherDataPoint twentyRelevance(Boolean twentyRelevance) {
        this.setTwentyRelevance(twentyRelevance);
        return this;
    }

    public void setTwentyRelevance(Boolean twentyRelevance) {
        this.twentyRelevance = twentyRelevance;
    }

    public String getTwentyOne() {
        return this.twentyOne;
    }

    public WeatherDataPoint twentyOne(String twentyOne) {
        this.setTwentyOne(twentyOne);
        return this;
    }

    public void setTwentyOne(String twentyOne) {
        this.twentyOne = twentyOne;
    }

    public Boolean getTwentyOneRelevance() {
        return this.twentyOneRelevance;
    }

    public WeatherDataPoint twentyOneRelevance(Boolean twentyOneRelevance) {
        this.setTwentyOneRelevance(twentyOneRelevance);
        return this;
    }

    public void setTwentyOneRelevance(Boolean twentyOneRelevance) {
        this.twentyOneRelevance = twentyOneRelevance;
    }

    public String getTwentyTwo() {
        return this.twentyTwo;
    }

    public WeatherDataPoint twentyTwo(String twentyTwo) {
        this.setTwentyTwo(twentyTwo);
        return this;
    }

    public void setTwentyTwo(String twentyTwo) {
        this.twentyTwo = twentyTwo;
    }

    public Boolean getTwentyTwoRelevance() {
        return this.twentyTwoRelevance;
    }

    public WeatherDataPoint twentyTwoRelevance(Boolean twentyTwoRelevance) {
        this.setTwentyTwoRelevance(twentyTwoRelevance);
        return this;
    }

    public void setTwentyTwoRelevance(Boolean twentyTwoRelevance) {
        this.twentyTwoRelevance = twentyTwoRelevance;
    }

    public String getTwentyThree() {
        return this.twentyThree;
    }

    public WeatherDataPoint twentyThree(String twentyThree) {
        this.setTwentyThree(twentyThree);
        return this;
    }

    public void setTwentyThree(String twentyThree) {
        this.twentyThree = twentyThree;
    }

    public RelevanceHeader getReleavanceHeader() {
        return this.releavanceHeader;
    }

    public void setReleavanceHeader(RelevanceHeader relevanceHeader) {
        if (this.releavanceHeader != null) {
            this.releavanceHeader.setWeatherDataPoint(null);
        }
        if (relevanceHeader != null) {
            relevanceHeader.setWeatherDataPoint(this);
        }
        this.releavanceHeader = relevanceHeader;
    }

    public WeatherDataPoint releavanceHeader(RelevanceHeader relevanceHeader) {
        this.setReleavanceHeader(relevanceHeader);
        return this;
    }

    public CheckPoint getCheckPoint() {
        return this.checkPoint;
    }

    public void setCheckPoint(CheckPoint checkPoint) {
        this.checkPoint = checkPoint;
    }

    public WeatherDataPoint checkPoint(CheckPoint checkPoint) {
        this.setCheckPoint(checkPoint);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WeatherDataPoint)) {
            return false;
        }
        return id != null && id.equals(((WeatherDataPoint) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WeatherDataPoint{" +
            "id=" + getId() +
            ", property='" + getProperty() + "'" +
            ", midNight='" + getMidNight() + "'" +
            ", migNightRelevance='" + getMigNightRelevance() + "'" +
            ", one='" + getOne() + "'" +
            ", oneRelevance='" + getOneRelevance() + "'" +
            ", two='" + getTwo() + "'" +
            ", twoRelevance='" + getTwoRelevance() + "'" +
            ", three='" + getThree() + "'" +
            ", threeRelevance='" + getThreeRelevance() + "'" +
            ", four='" + getFour() + "'" +
            ", fourRelevance='" + getFourRelevance() + "'" +
            ", five='" + getFive() + "'" +
            ", fiveRelevance='" + getFiveRelevance() + "'" +
            ", six='" + getSix() + "'" +
            ", sixRelevance='" + getSixRelevance() + "'" +
            ", seven='" + getSeven() + "'" +
            ", sevenRelevance='" + getSevenRelevance() + "'" +
            ", eight='" + getEight() + "'" +
            ", eightRelevance='" + getEightRelevance() + "'" +
            ", nine='" + getNine() + "'" +
            ", nineRelevance='" + getNineRelevance() + "'" +
            ", ten='" + getTen() + "'" +
            ", tenRelevance='" + getTenRelevance() + "'" +
            ", eleven='" + getEleven() + "'" +
            ", elevenRelevance='" + getElevenRelevance() + "'" +
            ", twelve='" + getTwelve() + "'" +
            ", twelveRelevance='" + getTwelveRelevance() + "'" +
            ", thirteen='" + getThirteen() + "'" +
            ", thirteenRelevance='" + getThirteenRelevance() + "'" +
            ", fourteen='" + getFourteen() + "'" +
            ", fourteenRelevance='" + getFourteenRelevance() + "'" +
            ", fifteen='" + getFifteen() + "'" +
            ", fifteenRelevance='" + getFifteenRelevance() + "'" +
            ", sixteen='" + getSixteen() + "'" +
            ", sixteenRelevance='" + getSixteenRelevance() + "'" +
            ", seventeen='" + getSeventeen() + "'" +
            ", seventeenRelevance='" + getSeventeenRelevance() + "'" +
            ", eighteen='" + getEighteen() + "'" +
            ", eighteenRelevance='" + getEighteenRelevance() + "'" +
            ", nineteen='" + getNineteen() + "'" +
            ", nineteenRelevance='" + getNineteenRelevance() + "'" +
            ", twenty='" + getTwenty() + "'" +
            ", twentyRelevance='" + getTwentyRelevance() + "'" +
            ", twentyOne='" + getTwentyOne() + "'" +
            ", twentyOneRelevance='" + getTwentyOneRelevance() + "'" +
            ", twentyTwo='" + getTwentyTwo() + "'" +
            ", twentyTwoRelevance='" + getTwentyTwoRelevance() + "'" +
            ", twentyThree='" + getTwentyThree() + "'" +
            "}";
    }
}
