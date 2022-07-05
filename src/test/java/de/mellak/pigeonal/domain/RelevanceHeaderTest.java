package de.mellak.pigeonal.domain;

import static org.assertj.core.api.Assertions.assertThat;

import de.mellak.pigeonal.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RelevanceHeaderTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RelevanceHeader.class);
        RelevanceHeader relevanceHeader1 = new RelevanceHeader();
        relevanceHeader1.setId(1L);
        RelevanceHeader relevanceHeader2 = new RelevanceHeader();
        relevanceHeader2.setId(relevanceHeader1.getId());
        assertThat(relevanceHeader1).isEqualTo(relevanceHeader2);
        relevanceHeader2.setId(2L);
        assertThat(relevanceHeader1).isNotEqualTo(relevanceHeader2);
        relevanceHeader1.setId(null);
        assertThat(relevanceHeader1).isNotEqualTo(relevanceHeader2);
    }
}
