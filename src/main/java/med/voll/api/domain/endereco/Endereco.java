package med.voll.api.domain.endereco;

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

    public void atualizarDados(DadosEndereco dados) {
        if (dados.logradouro() != null) {
            this.logradouro = dados.logradouro();
        }
        if (dados.bairro() != null) {
            this.bairro = dados.bairro();
        }
        if (dados.cep() != null) {
            this.cep = dados.cep();
        }
        if (dados.numero() != null) {
            this.numero = dados.numero();
        }
        if (dados.complemento() != null) {
            this.complemento = dados.complemento();
        }
        if (dados.cidade() != null) {
            this.cidade = dados.cidade();
        }
        if (dados.uf() != null) {
            this.uf = dados.uf();
        }
    }
}
