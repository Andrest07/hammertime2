package com.hammertime.hammertime2;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.hammertime.hammertime2.domain.client.Client;
import com.hammertime.hammertime2.domain.client.ClientRepository;
import com.hammertime.hammertime2.service.IBackendService;

@EnableAutoConfiguration

@RunWith(SpringRunner.class)
@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
  classes = Hammertime2ApplicationTests.class)
@AutoConfigureMockMvc
@TestPropertySource(
  locations = "classpath:application-integrationtest.properties")
public class ClientRestControllerIntegrationTest {

    @Autowired
	  IBackendService backendService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ClientRepository repository;

    @After
    public void resetDb() {
        repository.deleteAll();
    }

    // Integration Test. Check if status is ok, that content is in JSON and if the first object has fname of Test1 and second object has fname of Test2
    @Test
    @WithMockUser(username="admin",roles="ADMIN")
    public void getAllClientsAPI() throws Exception {
      mvc.perform(get("/dataRequest/clients")
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(content()
      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$[0].fname", Matchers.is("Demo")))
      .andExpect(jsonPath("$[1].fname", Matchers.is("Andreas")));
    } 
}