package com.ikiningyou.drserver.util;

import com.ikiningyou.drserver.controller.CryptoController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CryptoController.class)
public class RSACryptoTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private RSACrypto rsaCrypto;

  @DisplayName("crypto 암호화 복호화")
  @Test
  @WithMockUser
  public void testCrypto() throws Exception {
    RequestBuilder requestBuilder = MockMvcRequestBuilders
      .get("/crypto/test")
      .param("plain", "test");

    ResultMatcher resultMatcher = MockMvcResultMatchers.status().isOk();
    ResultHandler resultHandler = MockMvcResultHandlers.print();
    mockMvc
      .perform(requestBuilder)
      .andExpect(resultMatcher)
      .andDo(resultHandler);
  }
}
