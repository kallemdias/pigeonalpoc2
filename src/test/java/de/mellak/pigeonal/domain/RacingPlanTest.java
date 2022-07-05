package de.mellak.pigeonal.domain;

import static org.assertj.core.api.Assertions.assertThat;

import de.mellak.pigeonal.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RacingPlanTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RacingPlan.class);
        RacingPlan racingPlan1 = new RacingPlan();
        racingPlan1.setId(1L);
        RacingPlan racingPlan2 = new RacingPlan();
        racingPlan2.setId(racingPlan1.getId());
        assertThat(racingPlan1).isEqualTo(racingPlan2);
        racingPlan2.setId(2L);
        assertThat(racingPlan1).isNotEqualTo(racingPlan2);
        racingPlan1.setId(null);
        assertThat(racingPlan1).isNotEqualTo(racingPlan2);
    }
}
