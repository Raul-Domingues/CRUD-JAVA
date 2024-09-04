package db1.crud_java.usuario;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoUsuario(

        @NotNull
        Long id,
        String name,
        String email
) {}
