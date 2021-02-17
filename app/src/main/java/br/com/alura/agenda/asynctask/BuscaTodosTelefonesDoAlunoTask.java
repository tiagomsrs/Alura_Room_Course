package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.alura.agenda.database.dao.TelefoneDao;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.model.Telefone;

public class BuscaTodosTelefonesDoAlunoTask extends AsyncTask <Void, Void, List<Telefone>> {

    private final TelefoneDao telefoneDAO;
    private final Aluno aluno;
    private final TelefonesDoAlunoEncontradoListener listner;

    public BuscaTodosTelefonesDoAlunoTask (TelefoneDao telefoneDAO, Aluno aluno, TelefonesDoAlunoEncontradoListener listner) {
        this.telefoneDAO = telefoneDAO;
        this.aluno = aluno;
        this.listner = listner;
    }

    @Override
    protected List<Telefone> doInBackground(Void... voids) {
        return telefoneDAO.buscaTodosTelefonesDoAluno(aluno.getId());
    }

    @Override
    protected void onPostExecute(List<Telefone> telefones) { // o retorno do doInBackground é a entrada do onPostExecute
        super.onPostExecute(telefones);
        listner.quandoEncontrado(telefones);
    }

    public interface TelefonesDoAlunoEncontradoListener {
        void quandoEncontrado(List<Telefone> telefones);
    }
}
