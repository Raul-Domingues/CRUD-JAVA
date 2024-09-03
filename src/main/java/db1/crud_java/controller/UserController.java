package db1.crud_java.controller;

import db1.crud_java.usuario.DadosCadastroUsuario;
import db1.crud_java.usuario.DadosListagemUsuario;
import db1.crud_java.usuario.Usuario;
import db1.crud_java.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroUsuario dados) {
        repository.save(new Usuario(dados));
    }

    @GetMapping
    public List<DadosListagemUsuario> listar() {
        return repository.findAll().stream().map(DadosListagemUsuario::new).toList();
    }

//    @PatchMapping
//    @Transactional
//    public void atualizar(@RequestBody @Valid ) {
//
//    }

}
