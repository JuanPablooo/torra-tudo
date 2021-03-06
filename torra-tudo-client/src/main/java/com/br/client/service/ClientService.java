package com.br.client.service;

import com.br.client.dto.ClientDto;
import com.br.client.dto.ClientInputDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ClientService {
    ClientDto createClient(ClientInputDto clientInputDto);
    Page<ClientDto> listClientPageable(Pageable pageable);
    List<ClientDto> listClientAllNoPageable();
    ClientDto getClientById(UUID clientId) throws ClassNotFoundException;
    void deleteClientById(UUID clientId) throws ClassNotFoundException;
    ClientDto updateClient(ClientInputDto clientInputDto, UUID clientId) throws ClassNotFoundException;

}
