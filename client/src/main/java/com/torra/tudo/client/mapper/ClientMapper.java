package com.torra.tudo.client.mapper;


import com.torra.tudo.client.dto.ClientDto;
import com.torra.tudo.client.dto.ClientInputDto;
import com.torra.tudo.client.entity.Client;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel="spring")
public abstract class ClientMapper {

    public abstract ClientDto toClientDto(Client client);
    public abstract List<ClientDto> toClientsDto(List<Client> clients);

    public abstract Client toClient(ClientInputDto clientInputDto);
}
