package db1.crud_java.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
