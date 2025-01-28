/*
 * SPDX-FileCopyrightText: 2025 Swiss Confederation
 *
 * SPDX-License-Identifier: MIT
 */

package ch.admin.bit.eid.status_registry.data_service.it;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("test")
@AutoConfigureMockMvc
class StatusListControllerTest {

    private static final String STATUSLIST_BASE_URL = "/api/v1/statuslist/";

    @Autowired
    protected MockMvc mvc;

    @Test
    void testCheck_invalidId_response() throws Exception {
        mvc.perform(get(STATUSLIST_BASE_URL + "unknown.jwt"))
                .andExpect(status().is4xxClientError())
        ;
    }

    @Test
    void testCheck_unknownId_response() throws Exception {
        mvc.perform(get(STATUSLIST_BASE_URL + "00000000-0000-0000-0000-00000000000a.jwt"))
                .andExpect(status().isNotFound())
        ;
    }

    @Test
    void testCheck_statusIsSetup_response() throws Exception {
        mvc.perform(get(STATUSLIST_BASE_URL + "00000000-0000-0000-0000-000000000000.jwt"))
                .andExpect(status().isTooEarly())
        ;
    }

    @Test
    void testCheck_validTokenStatusList_response() throws Exception {
        var id = "00000000-0000-0000-0000-000000000001";
        mvc.perform(get(STATUSLIST_BASE_URL + id + ".jwt"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/statuslist+jwt"))
                .andExpect(content().string("TEST_READ"))
        ;
    }

    @Test
    void testCheck_statusIsDisabled_response() throws Exception {
        mvc.perform(get(STATUSLIST_BASE_URL + "00000000-0000-0000-0000-000000000004.jwt"))
                .andExpect(status().isTooEarly())
        ;
    }

    @Test
    void testCheck_statusIsDeactivated_response() throws Exception {
        mvc.perform(get(STATUSLIST_BASE_URL + "00000000-0000-0000-0000-000000000005.jwt"))
                .andExpect(status().isTooEarly())
        ;
    }
}
