package info.keidi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.keidi.domain.CPF;
import info.keidi.domain.Client;
import info.keidi.ports.ClientRepository;
import info.keidi.rest.controller.ClientController;
import info.keidi.util.RequestDataCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = TestConfig.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(ClientController.class)
class ClientControllerTests {
  private final ObjectMapper mapper = new ObjectMapper();
  @Autowired private MockMvc mockMvc;
  @MockBean private ClientRepository clientRepository;

  @Test
  @DisplayName("Should register client")
  void shouldRequesClientRegister() throws Exception {
    var client = RequestDataCreator.clientRequest();

    this.mockMvc
        .perform(
            post("/client")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("utf-8")
                .content(mapper.writeValueAsString(client)))
        .andDo(print())
        .andExpect(status().isCreated());
  }

  @Test
  @DisplayName("Should request deposit")
  void shouldRequestDeposit() throws Exception {
    var deposit = RequestDataCreator.deposit();

    var client = new Client(new CPF(deposit.getClientId()), "", BigDecimal.ZERO);

    when(clientRepository.findById(deposit.getClientId())).thenReturn(Optional.of(client));

    this.mockMvc
        .perform(
            post("/client/deposit")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("utf-8")
                .content(mapper.writeValueAsString(deposit)))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("Should request transfer")
  void shouldRequestTransfer() throws Exception {
    var transfer = RequestDataCreator.transfer();

    var sender = new Client(new CPF(transfer.getFrom()), "", BigDecimal.TEN);
    var receiver = new Client(new CPF(transfer.getTo()), "", BigDecimal.ZERO);

    when(clientRepository.findById(sender.getIdentifier().getId())).thenReturn(Optional.of(sender));
    when(clientRepository.findById(receiver.getIdentifier().getId()))
        .thenReturn(Optional.of(receiver));

    this.mockMvc
        .perform(
            post("/client/transfer")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("utf-8")
                .content(mapper.writeValueAsString(transfer)))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("Should request client by the id")
  void shouldRequestClientById() throws Exception {
    var client = RequestDataCreator.client();

    when(clientRepository.findById(client.getIdentifier().getId())).thenReturn(Optional.of(client));

    this.mockMvc
        .perform(get("/client/{id}", client.getIdentifier().getId()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.cpf").value(client.getIdentifier().getId()))
        .andExpect(jsonPath("$.balance").exists())
        .andExpect(jsonPath("$.fullName").value(client.getFullName()))
        .andDo(print());
  }
}
