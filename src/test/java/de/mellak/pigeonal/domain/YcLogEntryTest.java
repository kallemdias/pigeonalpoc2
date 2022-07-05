package de.mellak.pigeonal.domain;

import static org.assertj.core.api.Assertions.assertThat;

import de.mellak.pigeonal.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class YcLogEntryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(YcLogEntry.class);
        YcLogEntry ycLogEntry1 = new YcLogEntry();
        ycLogEntry1.setId(1L);
        YcLogEntry ycLogEntry2 = new YcLogEntry();
        ycLogEntry2.setId(ycLogEntry1.getId());
        assertThat(ycLogEntry1).isEqualTo(ycLogEntry2);
        ycLogEntry2.setId(2L);
        assertThat(ycLogEntry1).isNotEqualTo(ycLogEntry2);
        ycLogEntry1.setId(null);
        assertThat(ycLogEntry1).isNotEqualTo(ycLogEntry2);
    }
}
