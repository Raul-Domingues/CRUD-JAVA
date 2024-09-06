package db1.crud_java.dto;

import db1.crud_java.entity.Usuario;

public record DadosListagemUsuario(Long id, String name, String email) {

    public DadosListagemUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getName(), usuario.getEmail());
    }
}
