package db1.crud_java.repository;

import db1.crud_java.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findAllByDeletedFalse();

    boolean existsByEmail(String email);
}
