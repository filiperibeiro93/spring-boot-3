package med.voll.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
@Slf4j
public class MedicoController {

    @PostMapping
    public void cadastrar(@RequestBody String json) {
        log.info(json);
    }
}
