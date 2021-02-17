package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;
import java.util.List;
import br.com.alura.agenda.database.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.ui.adapter.ListaAlunosAdapter;

public class BuscaAlunosTask extends AsyncTask<Void, Void, List<Aluno>>{    //tipo de parâmetro que recebe no doInBackground, que utiliza no decorrer, e que recebe no onPostExecute
                                                                            // esse é o uso de Generics
    private final AlunoDAO dao;
    private final ListaAlunosAdapter adapter;

    public BuscaAlunosTask(AlunoDAO dao, ListaAlunosAdapter adapter) {
        this.dao = dao;
        this.adapter = adapter;
    }

    @Override
    protected List<Aluno> doInBackground(Void[] objects) {
        return dao.todos();
    }

    @Override
    protected void onPostExecute(List<Aluno> todosAlunos) {    // serve para trabalhar com a UI thread
        super.onPostExecute(todosAlunos);
        adapter.atualiza(todosAlunos);
    }
}
