package db1.crud_java.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(

        @NotBlank
        String name,

        @NotBlank
        @Email
        String email
) { }
