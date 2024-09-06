package db1.crud_java.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;

public record DadosAtualizacaoUsuario(

        String name,

        @Email
        @Column(unique = true)
        String email
) {}
