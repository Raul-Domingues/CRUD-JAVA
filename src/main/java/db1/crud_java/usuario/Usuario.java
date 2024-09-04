package db1.crud_java.usuario;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "users")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private Boolean deleted;

    public Usuario(DadosCadastroUsuario dados) {
        this.name = dados.name();
        this.email = dados.email();
        this.deleted = false;
    }

    public void atualizarInformacoes(@Valid DadosAtualizacaoUsuario dados) {
        if(dados.name() != null) {
            this.name = dados.name();
        }

        if(dados.email() != null) {
            this.email = dados.email();
        }
    }

    public void desativar() {
        this.deleted = true;
    }
}
