package de.mellak.pigeonal.domain;

import static org.assertj.core.api.Assertions.assertThat;

import de.mellak.pigeonal.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DistanceCalculatorTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DistanceCalculator.class);
        DistanceCalculator distanceCalculator1 = new DistanceCalculator();
        distanceCalculator1.setId(1L);
        DistanceCalculator distanceCalculator2 = new DistanceCalculator();
        distanceCalculator2.setId(distanceCalculator1.getId());
        assertThat(distanceCalculator1).isEqualTo(distanceCalculator2);
        distanceCalculator2.setId(2L);
        assertThat(distanceCalculator1).isNotEqualTo(distanceCalculator2);
        distanceCalculator1.setId(null);
        assertThat(distanceCalculator1).isNotEqualTo(distanceCalculator2);
    }
}
