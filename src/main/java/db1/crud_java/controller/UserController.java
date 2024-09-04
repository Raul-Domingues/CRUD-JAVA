package db1.crud_java.controller;

import db1.crud_java.usuario.*;
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
        return repository.findAll().stream().map(DadosListagemUsuario::new).toList();
    }

    @PatchMapping("/{id}")
    @Transactional
    public void atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoUsuario dados) {
        var usuario = repository.getReferenceById(id);
        System.out.println(usuario);
        usuario.atualizarInformacoes(dados);
//        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

//    @DeleteMapping("/{id}")
//    @Transactional
//    public void excluir(@PathVariable Long id) {
//        repository.deleteById(id);
//    }

    @DeleteMapping("/{id}")
    @Transactional
    public void desativar(@PathVariable Long id) {
        var usuario = repository.getReferenceById(id);
        usuario.desativar();
    }
}
