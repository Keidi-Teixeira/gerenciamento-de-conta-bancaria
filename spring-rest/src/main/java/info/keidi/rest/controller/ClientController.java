package info.keidi.rest.controller;

import info.keidi.domain.CPF;
import info.keidi.rest.dto.ClientRequest;
import info.keidi.rest.dto.ClientResponse;
import info.keidi.rest.dto.DepositRequest;
import info.keidi.rest.dto.TransferRequest;
import info.keidi.usecases.Deposit;
import info.keidi.usecases.GetClient;
import info.keidi.usecases.RegisterClient;
import info.keidi.usecases.Transfer;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "client", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {
  private final RegisterClient registerClient;
  private final Transfer transfer;
  private final Deposit deposit;
  private final GetClient getClient;

  public ClientController(
      RegisterClient registerClient, Transfer transfer, Deposit deposit, GetClient getClient) {
    this.registerClient = registerClient;
    this.transfer = transfer;
    this.deposit = deposit;
    this.getClient = getClient;
  }

  @PostMapping
  public ResponseEntity<?> registerClient(@Valid @RequestBody ClientRequest clientRequest) {
    var client = clientRequest.toClient();
    registerClient.create(client);
    return ResponseEntity.created(getResourceURI(clientRequest.getCpf())).build();
  }

  private URI getResourceURI(String id) {
    return ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(id)
        .toUri();
  }

  @GetMapping("{id}")
  public ResponseEntity<ClientResponse> getClientById(@PathVariable String id) {
    var client = getClient.get(new CPF(id));
    return ResponseEntity.ok(ClientResponse.fromClient(client));
  }

  @PostMapping("deposit")
  public ResponseEntity<?> deposit(@Valid @RequestBody final DepositRequest depositRequest) {

    deposit.deposit(depositRequest.getClientId(), depositRequest.getValue());

    return ResponseEntity.ok().build();
  }

  @PostMapping("transfer")
  public ResponseEntity<?> transfer(@Valid @RequestBody TransferRequest transferRequest) {

    transfer.transfer(transferRequest.toTransferData());

    return ResponseEntity.ok().build();
  }
}
