package med.voll.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.DadosListagemMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class MedicoController {

    private final MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
        repository.save(Medico.parseToMedico(dados));
    }

    @GetMapping
    public List<DadosListagemMedico> listar() {
        return repository.findAll().stream().map(DadosListagemMedico::new).toList();
    }
}
