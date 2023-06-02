// package com.hammertime.hammertime2;

// import static org.assertj.core.api.Assertions.assertThat;

// import java.util.Arrays;
// import java.util.List;
// import java.util.Optional;

// import org.junit.Before;
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.mockito.Mockito;
// import org.mockito.internal.verification.VerificationModeFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.test.context.junit4.SpringRunner;

// import com.hammertime.hammertime2.domain.client.Client;
// import com.hammertime.hammertime2.domain.client.ClientRepository;
// import com.hammertime.hammertime2.exceptions.ClientNotFoundException;
// import com.hammertime.hammertime2.service.IBackendService;

// @RunWith(SpringRunner.class)
// public class BackendServiceImplIntegrationTest {
//     @Autowired
// 	IBackendService backendService;

//     @MockBean
//     private ClientRepository clientRepository;

//     @Before
//     public void setUp() {
//         Client john = new Client("Test3", "Test3", "Test3", "Test3", "Test3", "Test3");
//         john.setId(11L);

//         Client bob = new Client("Test1", "Test1", "Test1", "Test1", "Test1", "Test1");
//         Client alex = new Client("Test2", "Test2", "Test2", "Test2", "Test2", "Test2");

//         List<Client> allClients = Arrays.asList(john, bob, alex);

//         Mockito.when(clientRepository.findByEmail(john.getFName())).thenReturn(john);
//         Mockito.when(clientRepository.findByEmail(alex.getFName())).thenReturn(alex);
//         Mockito.when(clientRepository.findByEmail("wrong_name")).thenReturn(null);
//         Mockito.when(clientRepository.findById(john.getId())).thenReturn(Optional.of(john));
//         Mockito.when(clientRepository.findAll()).thenReturn(allClients);
//         Mockito.when(clientRepository.findById(-99L)).thenReturn(Optional.empty());
//     }

//     @Test
//     public void whenValidEmail_thenClientShouldBeFound() {
//         String email = "Test1";
//         Client found = clientRepository.findByEmail(email);

//         assertThat(found.getEmail()).isEqualTo(email);
//     }

//     @Test
//     public void whenInValidName_thenClientShouldNotBeFound() {
//         Client fromDb = clientRepository.findByEmail("wrong_name");
//         assertThat(fromDb).isNull();

//         verifyFindByNameIsCalledOnce("wrong_name");
//     }

//     @Test
//     public void whenValidId_thenClientShouldBeFound() throws ClientNotFoundException {
//         Client fromDb = backendService.getClient(11L);
//         assertThat(fromDb.getFName()).isEqualTo("Test3");

//         verifyFindByIdIsCalledOnce();
//     }

//     @Test
//     public void whenInValidId_thenClientShouldNotBeFound() throws ClientNotFoundException {
//         Client fromDb = backendService.getClient(-99L);
//         verifyFindByIdIsCalledOnce();
//         assertThat(fromDb).isNull();
//     }

//     private void verifyFindByNameIsCalledOnce(String email) {
//         Mockito.verify(clientRepository, VerificationModeFactory.times(1)).findByEmail(email);
//         Mockito.reset(clientRepository);
//     }

//     private void verifyFindByIdIsCalledOnce() {
//         Mockito.verify(clientRepository, VerificationModeFactory.times(1)).findById(Mockito.anyLong());
//         Mockito.reset(clientRepository);
//     }
// }