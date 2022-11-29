package com.keencho.boot3;

import com.keencho.boot3.controller.ControllerPrefix;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
class ApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("후행 슬래쉬 테스트")
    void trailingSlashTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ControllerPrefix.API_CONTROLLER_PREFIX + "/index"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // 기존 spring boot 2.x 대까지는 후행 슬래쉬를 붙여도 알아서 경로를 찾았지만 3.x 부터는 지원하지 않아 404가 떨어지게 된다.
        mockMvc.perform(MockMvcRequestBuilders.get(ControllerPrefix.API_CONTROLLER_PREFIX + "/index/"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
