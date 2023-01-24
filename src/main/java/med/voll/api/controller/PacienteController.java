package med.voll.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("pacientes")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PacienteController {

    private final PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroPaciente dados) {

        repository.save(Paciente.parseToPaciente(dados));
    }

    @GetMapping
    public List<DadosListagemPaciente> listar() {

        return repository.findAll().stream().map(DadosListagemPaciente::new).collect(Collectors.toList());
    }

    @PostMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dados) {

        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizarDados(dados);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {

        repository.deleteById(id);
    }
}
