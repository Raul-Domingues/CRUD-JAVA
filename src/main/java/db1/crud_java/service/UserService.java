package db1.crud_java.service;

import db1.crud_java.dto.DadosAtualizacaoUsuario;
import db1.crud_java.dto.DadosCadastroUsuario;
import db1.crud_java.dto.DadosListagemUsuario;
import db1.crud_java.repository.UsuarioRepository;
import db1.crud_java.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class UserService {

    private final UsuarioRepository repository;

    public UserService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Map<String, Object> cadastrarUsuario(DadosCadastroUsuario dados) {
        if (repository.existsByEmail(dados.getEmail())) {
            Map<String, Object> erro = new HashMap<>();
            erro.put("mensagem", "Email não disponível");
            return erro;
        }

        Usuario usuario = new Usuario(dados);
        repository.save(usuario);

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("ID do usuário criado", usuario.getId());
        return resposta;
    }

    public List<DadosListagemUsuario> listarUsuarios() {
        return repository.findAllByDeletedFalse().stream().map(DadosListagemUsuario::new).toList();
    }

    public DadosAtualizacaoUsuario atualizarUsuario(Long id, DadosAtualizacaoUsuario dados) {
        var usuario = repository.findById(id).orElseThrow();
        usuario.atualizarInformacoes(dados);
        repository.save(usuario);

        return new DadosAtualizacaoUsuario(usuario.getName(), usuario.getEmail());
    }

    public void desativarUsuario(Long id) {
        var usuario = repository.findById(id).orElseThrow();
        usuario.desativarUsuario();
        repository.save(usuario);
    }

    public String ativarUsuario(Long id) {
        var usuario = repository.findById(id).orElseThrow();
        usuario.ativarUsuario();
        repository.save(usuario);
        return "Usuário ativado com sucesso!";
    }

    public void excluirUsuario(Long id) {
        repository.deleteById(id);
    }

    public void excluirTodosUsuarios() {
        repository.deleteAll();
    }
}
