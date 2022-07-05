package de.mellak.pigeonal.domain;

import static org.assertj.core.api.Assertions.assertThat;

import de.mellak.pigeonal.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CheckLineTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CheckLine.class);
        CheckLine checkLine1 = new CheckLine();
        checkLine1.setId(1L);
        CheckLine checkLine2 = new CheckLine();
        checkLine2.setId(checkLine1.getId());
        assertThat(checkLine1).isEqualTo(checkLine2);
        checkLine2.setId(2L);
        assertThat(checkLine1).isNotEqualTo(checkLine2);
        checkLine1.setId(null);
        assertThat(checkLine1).isNotEqualTo(checkLine2);
    }
}
