package com.torra.tudo.client.service.impl;

import com.torra.tudo.client.dto.ClientDto;
import com.torra.tudo.client.dto.ClientInputDto;
import com.torra.tudo.client.entity.Client;
import com.torra.tudo.client.mapper.ClientMapper;
import com.torra.tudo.client.repository.ClientRepository;
import com.torra.tudo.client.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper mapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = mapper;
    }

    public ClientDto createClient(ClientInputDto clientInputDto) {
        String value = clientInputDto.getDocumentNumber();
        log.info("create client by documentNumber[{}]", value);
        if(log.isDebugEnabled()){
            log.debug("create user by ClientInputDto[{}]", clientInputDto);
        }
        Client clientToBeSaved = clientMapper.toClient(clientInputDto);
        Client clientSaved = clientRepository.save(clientToBeSaved);
        return clientMapper.toClientDto(clientSaved);
    }

    public Page<ClientDto> listClientPageable(Pageable pageable) {
        log.info("list pageable clients by pageableInput [{}]", pageable);
        Page<Client> pageClient = clientRepository.findAll(pageable);
        List<ClientDto> pageClientsDto = clientMapper.toClientsDto(pageClient.getContent());
        return new PageImpl<>(pageClientsDto, pageable, pageClient.getTotalElements());
    }

    public List<ClientDto> listClientAllNoPageable() {
        log.info("list all clients no pageable");
        return clientMapper.toClientsDto(clientRepository.findAll());
    }


    public ClientDto getClientById(UUID clientId) throws ClassNotFoundException {
        Client clientFound = getByIdOrThrow(clientId);
        return clientMapper.toClientDto(clientFound);
    }

    public void deleteClientById(UUID clientId) throws ClassNotFoundException {
        log.info("delete client by clientId[{}]", clientId);
        Client clientToBeDelete = getByIdOrThrow(clientId);
        clientRepository.delete(clientToBeDelete);
    }

    @Override
    public ClientDto updateClient(ClientInputDto clientInputDto, UUID clientId) throws ClassNotFoundException {
        Client clientFound = getByIdOrThrow(clientId);
        Client clientToBeSave = clientMapper.toClient(clientInputDto);
        clientToBeSave.setCreateAt(clientFound.getCreateAt());
        clientToBeSave.setUpdateAt(new Date());
        clientToBeSave.setId(clientId);
        clientRepository.save(clientToBeSave);
        return clientMapper.toClientDto(clientToBeSave);
    }

    private Client getByIdOrThrow(UUID clientId) throws ClassNotFoundException {
        log.info("find client by clientId[{}]", clientId);
       return clientRepository.findById(clientId).orElseThrow(() -> {
           log.warn("error on get client by id[{}]", clientId);
           return new ClassNotFoundException("client not found by id:" + clientId);
       });
    }

}
