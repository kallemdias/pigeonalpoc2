package de.mellak.pigeonal.domain;

import static org.assertj.core.api.Assertions.assertThat;

import de.mellak.pigeonal.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WeatherDataPointTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WeatherDataPoint.class);
        WeatherDataPoint weatherDataPoint1 = new WeatherDataPoint();
        weatherDataPoint1.setId(1L);
        WeatherDataPoint weatherDataPoint2 = new WeatherDataPoint();
        weatherDataPoint2.setId(weatherDataPoint1.getId());
        assertThat(weatherDataPoint1).isEqualTo(weatherDataPoint2);
        weatherDataPoint2.setId(2L);
        assertThat(weatherDataPoint1).isNotEqualTo(weatherDataPoint2);
        weatherDataPoint1.setId(null);
        assertThat(weatherDataPoint1).isNotEqualTo(weatherDataPoint2);
    }
}
