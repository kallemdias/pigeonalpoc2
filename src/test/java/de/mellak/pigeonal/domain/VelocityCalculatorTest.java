package de.mellak.pigeonal.domain;

import static org.assertj.core.api.Assertions.assertThat;

import de.mellak.pigeonal.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class VelocityCalculatorTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VelocityCalculator.class);
        VelocityCalculator velocityCalculator1 = new VelocityCalculator();
        velocityCalculator1.setId(1L);
        VelocityCalculator velocityCalculator2 = new VelocityCalculator();
        velocityCalculator2.setId(velocityCalculator1.getId());
        assertThat(velocityCalculator1).isEqualTo(velocityCalculator2);
        velocityCalculator2.setId(2L);
        assertThat(velocityCalculator1).isNotEqualTo(velocityCalculator2);
        velocityCalculator1.setId(null);
        assertThat(velocityCalculator1).isNotEqualTo(velocityCalculator2);
    }
}
