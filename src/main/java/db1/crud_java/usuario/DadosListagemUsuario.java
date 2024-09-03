package db1.crud_java.usuario;

public record DadosListagemUsuario(Long id, String name, String email) {

    public DadosListagemUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getName(), usuario.getEmail());
    }
}
