package db1.crud_java.controller;

import db1.crud_java.dto.DadosAtualizacaoUsuario;
import db1.crud_java.dto.DadosCadastroUsuario;
import db1.crud_java.dto.DadosListagemUsuario;
import db1.crud_java.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Cria um novo usuário")
    @ApiResponse(responseCode = "201")
    @PostMapping
    public ResponseEntity<Map<String, Object>> cadastrar(@RequestBody @Valid DadosCadastroUsuario dados) {
        Map<String, Object> resposta = userService.cadastrarUsuario(dados);
        if (resposta.containsKey("mensagem")) {
            return ResponseEntity.badRequest().body(resposta);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @Operation(summary = "Listar usuários")
    @ApiResponse(responseCode = "200")
    @GetMapping
    public List<DadosListagemUsuario> listar() {
        return userService.listarUsuarios();
    }

    @Operation(summary = "Editar usuário")
    @ApiResponse(responseCode = "200")
    @PatchMapping("/{id}")
    public ResponseEntity<DadosAtualizacaoUsuario> atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoUsuario dados) {
        DadosAtualizacaoUsuario resposta = userService.atualizarUsuario(id, dados);
        return ResponseEntity.ok(resposta);
    }

    @ApiResponse(responseCode = "204")
    @Operation(summary = "Desativar usuário")
    @DeleteMapping("/desativar/{id}")
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        userService.desativarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Ativar usuário")
    @ApiResponse(responseCode = "200")
    @PatchMapping("/ativar/{id}")
    public ResponseEntity<String> ativarUsuario(@PathVariable Long id) {
        String resposta = userService.ativarUsuario(id);
        return ResponseEntity.ok(resposta);
    }

    @Operation(summary = "Deletar usuário")
    @ApiResponse(responseCode = "200")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirUsuario(@PathVariable Long id) {
        userService.excluirUsuario(id);
        return ResponseEntity.ok("Usuário deletado com sucesso!");
    }

    @Operation(summary = "Deletar todos os usuários")
    @ApiResponse(responseCode = "204")
    @DeleteMapping
    public ResponseEntity<Void> excluirUsuarios() {
        userService.excluirTodosUsuarios();
        return ResponseEntity.noContent().build();
    }
}
