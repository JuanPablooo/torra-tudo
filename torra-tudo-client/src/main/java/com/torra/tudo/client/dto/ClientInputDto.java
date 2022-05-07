package com.torra.tudo.client.dto;

import lombok.*;

import java.sql.Date;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientInputDto {
    private String name;
    private String documentNumber;
    private String email;
    private Date birthday;
}
