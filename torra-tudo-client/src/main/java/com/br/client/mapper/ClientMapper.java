package com.br.client.mapper;


import com.br.client.dto.ClientDto;
import com.br.client.dto.ClientInputDto;
import com.br.client.entity.Client;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel="spring")
public abstract class ClientMapper {

    public abstract ClientDto toClientDto(Client client);
    public abstract List<ClientDto> toClientsDto(List<Client> clients);

    public abstract Client toClient(ClientInputDto clientInputDto);
}
