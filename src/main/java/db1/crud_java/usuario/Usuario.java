package db1.crud_java.usuario;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
//@SQLDelete(sql = "UPDATE Users SET status = 'true' WHERE id = ?")
@Table(name = "users")
@Entity(name = "Usuario")
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private Boolean deleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Usuario(@Valid DadosCadastroUsuario dados) {
        this.name = dados.name();
        this.email = dados.email();
        this.deleted = false;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
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

    @Override
    public String toString() {
        return "Usuario{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
}
