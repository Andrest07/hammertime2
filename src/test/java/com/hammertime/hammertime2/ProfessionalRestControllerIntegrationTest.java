package com.hammertime.hammertime2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.hammertime.hammertime2.domain.professional.Professional;
import com.hammertime.hammertime2.domain.professional.ProfessionalRepository;
import com.hammertime.hammertime2.service.IBackendService;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
  classes = Hammertime2Application.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@AutoConfigureTestDatabase
public class ProfessionalRestControllerIntegrationTest {

    @Autowired
	  IBackendService backendService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProfessionalRepository repository;

    @After
    public void resetDb() {
        repository.deleteAll();
    }

    // Integration test. Check if status is ok, that content is in JSON and if the first object has fname of Test1 and second object has fname of Test2
    @Test
    public void givenProfessionals_whenGetProfessionals_thenStatus200() throws Exception {
        createTestProfessional("Test1", "Test1","Test1", "Test1", "Test1", "Test1", "Test1");
        createTestProfessional("Test2", "Test2","Test2", "Test2", "Test2", "Test2", "Test2");

        // @formatter:off
        mvc.perform(get("/dataRequest/professionals").contentType(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
          .andExpect(jsonPath("$[0].fname", is("Test1")))
          .andExpect(jsonPath("$[1].fname", is("Test2")));
        // @formatter:on
    }

    //

    private void createTestProfessional(String firstName, String lastName, String businessName, String address, String phone, String email, String password) {
        Professional pro = new Professional(firstName, lastName, businessName, address, phone, email, password);
        repository.saveAndFlush(pro);
    }
}