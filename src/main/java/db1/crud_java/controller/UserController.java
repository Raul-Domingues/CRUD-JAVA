package db1.crud_java.controller;

import db1.crud_java.usuario.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<Map<String, Object>> cadastrar(@RequestBody @Valid DadosCadastroUsuario dados) {
        Usuario usuario = new Usuario(dados);
        repository.save(usuario);

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("ID do usuário criado", usuario.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @GetMapping
    public List<DadosListagemUsuario> listar() {
        return repository.findAllByDeletedFalse().stream().map(DadosListagemUsuario::new).toList();
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosAtualizacaoUsuario> atualizar(@PathVariable Long id, @RequestBody DadosAtualizacaoUsuario dados) {
        var usuario = repository.findById(id).orElseThrow();
        usuario.atualizarInformacoes(dados);
        repository.save(usuario);
        var resposta = new DadosAtualizacaoUsuario(usuario.getName(), usuario.getEmail());

        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping("/desativar/{id}")
    @Transactional
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
       var usuario = repository.findById(id).orElseThrow();
       usuario.desativarUsuario();
       return ResponseEntity.noContent().build();
    }

    @PatchMapping("/ativar/{id}")
    @Transactional
    public ResponseEntity<String> ativarUsuario(@PathVariable Long id) {
        var usuario = repository.findById(id).orElseThrow();
        usuario.ativarUsuario();
        //  STATUS code pode ser feito ResponseEntity.status(HttpStatus.OK).body( ...
        return ResponseEntity.ok().body("Usuário ativado com sucesso!");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluirUsuario(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @DeleteMapping
    @Transactional
    public void excluirUsuarios() {
        repository.deleteAll();
    }
}
