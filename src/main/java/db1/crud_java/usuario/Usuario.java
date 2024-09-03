package db1.crud_java.usuario;

import jakarta.persistence.*;
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

    public Usuario(DadosCadastroUsuario dados) {
        this.name = dados.name();
        this.email = dados.email();
    }
}
