package com.hammertime.hammertime2;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.hammertime.hammertime2.domain.client.Client;
import com.hammertime.hammertime2.domain.client.ClientRepository;
import com.hammertime.hammertime2.domain.professional.Professional;
import com.hammertime.hammertime2.domain.professional.ProfessionalRepository;
import com.hammertime.hammertime2.exceptions.ClientNotFoundException;
import com.hammertime.hammertime2.exceptions.ProfessionalNotFoundException;
import com.hammertime.hammertime2.service.IBackendService;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
  classes = Hammertime2Application.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@AutoConfigureTestDatabase
public class BackendServiceImplIntegrationTest {
    
    @Autowired
	IBackendService backendService;

    @MockBean
    private ClientRepository clientRepository;

    @MockBean
    private ProfessionalRepository professionalRepository;

    @Before
    public void setUp() {
        Client Client3 = new Client("Client3", "Client3", "Client3", "Client3", "Client3", "Client3");
        Client3.setId(11L);
        createTestClient(Client3);

        Client Client1 = new Client("Client1", "Client1", "Client1", "Client1", "Client1", "Client1");
        createTestClient(Client1);
        Client Client2 = new Client("Client2", "Client2", "Client2", "Client2", "Client2", "Client2");
        createTestClient(Client2);

        List<Client> allClients = Arrays.asList(Client3, Client1, Client2);

        Mockito.when(clientRepository.findByEmail(Client3.getEmail())).thenReturn(Client3);
        Mockito.when(clientRepository.findByEmail(Client2.getEmail())).thenReturn(Client2);
        Mockito.when(clientRepository.findByEmail("wrong_email")).thenReturn(null);
        Mockito.when(clientRepository.findById(Client3.getId())).thenReturn(Optional.of(Client3));
        Mockito.when(clientRepository.findAll()).thenReturn(allClients);
        Mockito.when(clientRepository.findById(-99L)).thenReturn(Optional.empty());

        Professional Professional3 = new Professional("Professional3", "Professional3", "Professional3", "Professional3", "Professional3", "Professional3", "Professional3");
        Professional3.setId(21L);
        createTestProfessional(Professional3);

        Professional Professional1 = new Professional("Professional1", "Professional1", "Professional1", "Professional1", "Professional1", "Professional1", "Professional1");
        createTestProfessional(Professional1);
        Professional Professional2 = new Professional("Professional2", "Professional2", "Professional2", "Professional2", "Professional2", "Professional2", "Professional2");
        createTestProfessional(Professional2);

        List<Professional> allProfessionals = Arrays.asList(Professional3, Professional1, Professional2);

        Mockito.when(professionalRepository.findByEmail(Professional3.getEmail())).thenReturn(Professional3);
        Mockito.when(professionalRepository.findByEmail(Professional2.getEmail())).thenReturn(Professional2);
        Mockito.when(professionalRepository.findByEmail("wrong_email")).thenReturn(null);
        Mockito.when(professionalRepository.findById(Professional3.getId())).thenReturn(Optional.of(Professional3));
        Mockito.when(professionalRepository.findAll()).thenReturn(allProfessionals);
        Mockito.when(professionalRepository.findById(-99L)).thenReturn(Optional.empty());
    }

    // @Test
    // public void whenValidEmail_thenClientShouldBeFound() {
    //     Client found = clientRepository.findByEmail("Client1");

    //     assertThat(found.getEmail()).isEqualTo("Client1");
    // }

    @Test
    public void whenInValidEmail_thenClientShouldNotBeFound() {
        Client fromDb = clientRepository.findByEmail("wrong_email");
        assertThat(fromDb).isNull();

        verifyClientFindByEmailIsCalledOnce("wrong_email");
    }

    @Test
    public void whenValidId_thenClientShouldBeFound() throws ClientNotFoundException {
        Client fromDb = backendService.getClient(11L);
        assertThat(fromDb.getEmail()).isEqualTo("Client3");

        verifyClientFindByIdIsCalledOnce();
    }

    @Test
    public void whenInValidId_thenClientShouldNotBeFound(){
        Client fromDb;
        try {
            fromDb = backendService.getClient(-99L);
        } catch (ClientNotFoundException e) {
            fromDb = null;
            verifyClientFindByIdIsCalledOnce();
            assertThat(fromDb).isNull();
        }
    }

    // @Test
    // public void whenValidEmail_thenProfessionalShouldBeFound() {
    //     Professional found = professionalRepository.findByEmail("Professional1");

    //     assertThat(found.getEmail()).isEqualTo("Professional1");
    // }

    @Test
    public void whenInValidEmail_thenProfessionalShouldNotBeFound() {
        Professional fromDb = professionalRepository.findByEmail("wrong_email");
        assertThat(fromDb).isNull();

        verifyProfessionalFindByEmailIsCalledOnce("wrong_email");
    }

    @Test
    public void whenValidId_thenProfessionalShouldBeFound() throws ProfessionalNotFoundException {
        Professional fromDb = backendService.getProfessional(21L);
        assertThat(fromDb.getEmail()).isEqualTo("Professional3");

        verifyProfessionalFindByIdIsCalledOnce();
    }

    @Test
    public void whenInValidId_thenProfessionalShouldNotBeFound(){
        Professional fromDb;
        try {
            fromDb = backendService.getProfessional(-99L);
        } catch (ProfessionalNotFoundException e) {
            fromDb = null;
            verifyProfessionalFindByIdIsCalledOnce();
            assertThat(fromDb).isNull();
        }
    }

    private void verifyClientFindByEmailIsCalledOnce(String email) {
        Mockito.verify(clientRepository, VerificationModeFactory.times(1)).findByEmail(email);
        Mockito.reset(clientRepository);
    }

    private void verifyClientFindByIdIsCalledOnce() {
        Mockito.verify(clientRepository, VerificationModeFactory.times(1)).findById(Mockito.anyLong());
        Mockito.reset(clientRepository);
    }

    private void verifyProfessionalFindByEmailIsCalledOnce(String email) {
        Mockito.verify(professionalRepository, VerificationModeFactory.times(1)).findByEmail(email);
        Mockito.reset(professionalRepository);
    }

    private void verifyProfessionalFindByIdIsCalledOnce() {
        Mockito.verify(professionalRepository, VerificationModeFactory.times(1)).findById(Mockito.anyLong());
        Mockito.reset(professionalRepository);
    }

    private void createTestClient(Client cli) {
        clientRepository.saveAndFlush(cli);
    }

    private void createTestProfessional(Professional pro) {
        professionalRepository.saveAndFlush(pro);
    }
}