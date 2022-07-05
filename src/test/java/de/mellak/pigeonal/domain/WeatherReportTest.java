package de.mellak.pigeonal.domain;

import static org.assertj.core.api.Assertions.assertThat;

import de.mellak.pigeonal.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WeatherReportTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WeatherReport.class);
        WeatherReport weatherReport1 = new WeatherReport();
        weatherReport1.setId(1L);
        WeatherReport weatherReport2 = new WeatherReport();
        weatherReport2.setId(weatherReport1.getId());
        assertThat(weatherReport1).isEqualTo(weatherReport2);
        weatherReport2.setId(2L);
        assertThat(weatherReport1).isNotEqualTo(weatherReport2);
        weatherReport1.setId(null);
        assertThat(weatherReport1).isNotEqualTo(weatherReport2);
    }
}
