package com.torra.tudo.client.controller;

import com.torra.tudo.client.dto.ClientDto;
import com.torra.tudo.client.dto.ClientInputDto;
import com.torra.tudo.client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "client")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @PostMapping
    public ClientDto createClient(@RequestBody ClientInputDto clientInputDto) {
        return clientService.createClient(clientInputDto);
    }

    @GetMapping("/{clientId}")
    public ClientDto findById(@PathVariable UUID clientId) throws ClassNotFoundException {
        return clientService.getClientById(clientId);
    }

    @GetMapping
    public Page<ClientDto> listClientPageable(Pageable pageable) {
        return clientService.listClientPageable(pageable);
    }

    @GetMapping("/all")
    public List<ClientDto> listClientPageable() {
        return clientService.listClientAllNoPageable();
    }

    @DeleteMapping("/{clientId}")
    public void deleteClientById(@PathVariable UUID clientId) throws ClassNotFoundException {
        clientService.deleteClientById(clientId);
    }

    @PutMapping("/{clientId}")
    public ClientDto updateClientById(@PathVariable UUID clientId, @RequestBody ClientInputDto clientInputDto) throws ClassNotFoundException {
        return clientService.updateClient(clientInputDto, clientId);
    }

}
