package med.voll.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


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
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 5, sort = {"nome"}) Pageable pageable) {
        return repository.findAllByAtivoTrue(pageable).map(DadosListagemMedico::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarDados(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void desativar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.desativar();
    }
}
