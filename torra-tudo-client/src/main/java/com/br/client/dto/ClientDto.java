package com.br.client.dto;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private String name;
    private String documentNumber;
}
