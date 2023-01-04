package med.voll.api.medico;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.endereco.Endereco;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public static Medico parseToMedico(DadosCadastroMedico dados) {
        return Medico.builder()
                .nome(dados.nome())
                .email(dados.email())
                .telefone(dados.telefone())
                .crm(dados.crm())
                .especialidade(dados.especialidade())
                .endereco(Endereco.parseToEndereco(dados.endereco()))
                .build();
    }

    public void atualizarDados(DadosAtualizacaoMedico dados) {
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
