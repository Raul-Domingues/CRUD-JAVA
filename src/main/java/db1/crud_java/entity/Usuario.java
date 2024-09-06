package db1.crud_java.entity;

import db1.crud_java.dto.DadosAtualizacaoUsuario;
import db1.crud_java.dto.DadosCadastroUsuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Entity(name = "Usuario")
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private Boolean deleted;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    public Usuario(@Valid DadosCadastroUsuario dados) {
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

    public void desativarUsuario() {
        this.deleted = true;
    }

    public void ativarUsuario() {
        this.deleted = false;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
