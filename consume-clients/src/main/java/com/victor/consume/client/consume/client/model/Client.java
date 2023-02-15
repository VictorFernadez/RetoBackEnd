package com.victor.consume.client.consume.client.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Client {
    private Long id;
    private String codigoUnico;
    private String nombres;
    private String apellidos;
    private String tipoDocumento;
    private String numeroDocumento;
}
