package br.com.alura.agenda.asynctask;



import java.util.List;
import br.com.alura.agenda.database.dao.AlunoDAO;
import br.com.alura.agenda.database.dao.TelefoneDao;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.model.Telefone;
import br.com.alura.agenda.model.TipoTelefone;


public class EditaAlunoTask extends BaseAlunoComTelefoneTask {

    private final AlunoDAO alunoDAO;
    private final Aluno aluno;
    private final Telefone telefoneFixo;
    private final Telefone telefoneCelular;
    private final TelefoneDao telefoneDAO;

    private final List<Telefone> telefonesDoAluno;

    public EditaAlunoTask(AlunoDAO alunoDAO, Aluno aluno,
                          Telefone telefoneFixo, Telefone telefoneCelular,
                          TelefoneDao telefoneDAO, List<Telefone> telefonesDoAluno, FinalizadaListener listener) {

        super(listener);
        this.alunoDAO = alunoDAO;
        this.aluno = aluno;
        this.telefoneFixo = telefoneFixo;
        this.telefoneCelular = telefoneCelular;
        this.telefoneDAO = telefoneDAO;
        this.telefonesDoAluno = telefonesDoAluno;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        alunoDAO.edita(aluno);
        vinculaAlunoComTelefone(aluno.getId(), telefoneFixo, telefoneCelular);
        atualizaIdDosTelefones(telefoneFixo, telefoneCelular);
        telefoneDAO.atualiza(telefoneFixo, telefoneCelular);
        return null;
    }

    private void atualizaIdDosTelefones(Telefone telefoneFixo, Telefone telefoneCelular) {
        for (Telefone telefone : telefonesDoAluno) {
            if (telefone.getTipo() == TipoTelefone.FIXO){
                telefoneFixo.setId(telefone.getId());
            } else{
                telefoneCelular.setId(telefone.getId());
            }
        }
    }
}
