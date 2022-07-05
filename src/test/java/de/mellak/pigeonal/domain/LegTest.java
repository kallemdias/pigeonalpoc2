package de.mellak.pigeonal.domain;

import static org.assertj.core.api.Assertions.assertThat;

import de.mellak.pigeonal.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LegTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Leg.class);
        Leg leg1 = new Leg();
        leg1.setId(1L);
        Leg leg2 = new Leg();
        leg2.setId(leg1.getId());
        assertThat(leg1).isEqualTo(leg2);
        leg2.setId(2L);
        assertThat(leg1).isNotEqualTo(leg2);
        leg1.setId(null);
        assertThat(leg1).isNotEqualTo(leg2);
    }
}
