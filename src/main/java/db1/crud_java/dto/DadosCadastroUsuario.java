package db1.crud_java.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(

        @NotBlank
        @Schema(example = "Raul Domingues")
        String name,

        @NotBlank
        @Email
        @Schema(example = "raul.domingues@example.com")
        String email
) {
        public String getEmail() {
                return email;
        }
}
