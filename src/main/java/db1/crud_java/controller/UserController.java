package db1.crud_java.controller;

import db1.crud_java.usuario.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Cria um novo usuário")
    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso")
    @Transactional
    @PostMapping
    public ResponseEntity<Map<String, Object>> cadastrar(@RequestBody @Valid DadosCadastroUsuario dados) {
        Usuario usuario = new Usuario(dados);
        if (repository.existsByEmail(dados.getEmail())) {
            Map<String, Object> erro = new HashMap<>();
            erro.put("mensagem", "Email não disponível");
            return ResponseEntity.badRequest().body(erro);
        }
        repository.save(usuario);

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("ID do usuário criado", usuario.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @Operation(summary = "Listar usuários")
    @GetMapping
    public List<DadosListagemUsuario> listar() {
        return repository.findAllByDeletedFalse().stream().map(DadosListagemUsuario::new).toList();
    }

    @Operation(summary = "Editar usuário")
    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<DadosAtualizacaoUsuario> atualizar(@PathVariable Long id, @RequestBody DadosAtualizacaoUsuario dados) {
        var usuario = repository.findById(id).orElseThrow();
        usuario.atualizarInformacoes(dados);
        repository.save(usuario);
        var resposta = new DadosAtualizacaoUsuario(usuario.getName(), usuario.getEmail());

        return ResponseEntity.ok(resposta);
    }

    @Operation(summary = "Desativar usuário")
    @Transactional
    @DeleteMapping("/desativar/{id}")
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
       var usuario = repository.findById(id).orElseThrow();
       usuario.desativarUsuario();
       return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Ativar usuário")
    @Transactional
    @PatchMapping("/ativar/{id}")
    public ResponseEntity<String> ativarUsuario(@PathVariable Long id) {
        var usuario = repository.findById(id).orElseThrow();
        usuario.ativarUsuario();
        //  STATUS code pode ser feito ResponseEntity.status(HttpStatus.OK).body( ...
        return ResponseEntity.ok().body("Usuário ativado com sucesso!");
    }

    @Operation(summary = "Deletar usuário")
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirUsuario(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().body("Usuário deletado com sucesso!");
    }

    @Operation(summary = "Deletar todos os usuários")
    @Transactional
    @DeleteMapping
    public void excluirUsuarios() {
        repository.deleteAll();
    }
}
