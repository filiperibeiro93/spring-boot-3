package med.voll.api.endereco;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public static Endereco parseToEndereco(DadosEndereco dados) {
        return Endereco.builder()
                .logradouro(dados.logradouro())
                .bairro(dados.bairro())
                .cep(dados.cep())
                .cidade(dados.cidade())
                .uf(dados.uf())
                .complemento(dados.complemento())
                .numero(dados.numero())
                .build();
    }
}
