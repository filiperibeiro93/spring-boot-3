package med.voll.api.paciente;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.endereco.Endereco;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Paciente")
@Table(name = "pacientes")
@Builder
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;

    @Embedded
    private Endereco endereco;

    public static Paciente parseToPaciente(DadosCadastroPaciente dados) {
        return Paciente.builder()
                .nome(dados.nome())
                .email(dados.email())
                .telefone(dados.telefone())
                .cpf(dados.cpf())
                .endereco(Endereco.parseToEndereco(dados.endereco()))
                .build();
    }

    public void atualizarDados(DadosAtualizacaoPaciente dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarDados(dados.endereco());
        }
    }
}
