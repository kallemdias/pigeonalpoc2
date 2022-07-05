package de.mellak.pigeonal.domain;

import static org.assertj.core.api.Assertions.assertThat;

import de.mellak.pigeonal.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class GpsCoordinateCheckTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GpsCoordinateCheck.class);
        GpsCoordinateCheck gpsCoordinateCheck1 = new GpsCoordinateCheck();
        gpsCoordinateCheck1.setId(1L);
        GpsCoordinateCheck gpsCoordinateCheck2 = new GpsCoordinateCheck();
        gpsCoordinateCheck2.setId(gpsCoordinateCheck1.getId());
        assertThat(gpsCoordinateCheck1).isEqualTo(gpsCoordinateCheck2);
        gpsCoordinateCheck2.setId(2L);
        assertThat(gpsCoordinateCheck1).isNotEqualTo(gpsCoordinateCheck2);
        gpsCoordinateCheck1.setId(null);
        assertThat(gpsCoordinateCheck1).isNotEqualTo(gpsCoordinateCheck2);
    }
}
