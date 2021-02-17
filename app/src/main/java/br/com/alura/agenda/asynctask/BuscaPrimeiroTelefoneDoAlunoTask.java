package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import br.com.alura.agenda.database.dao.TelefoneDao;
import br.com.alura.agenda.model.Telefone;

public class BuscaPrimeiroTelefoneDoAlunoTask extends AsyncTask <Void, Void, Telefone>{

    public static final String LOG_TAG = "BuscaPrimeiroTelefoneDoAlunoBackground";
    private final TelefoneDao dao;
    // private final TextView campoTelefone;
    private final int alunoId;
    private final PrimeiroTelefoneEncontradoListener listener;


    public BuscaPrimeiroTelefoneDoAlunoTask(TelefoneDao dao,  int alunoId, PrimeiroTelefoneEncontradoListener listener) {
        this.dao = dao;
        // this.campoTelefone = campoTelefone;
        this.alunoId = alunoId;
        this.listener = listener;
    }

    @Override
    protected Telefone doInBackground(Void... voids) {
        return dao.buscaPrimeiroTelefoneDoAluno(alunoId);
    }

    @Override
    protected void onPostExecute(Telefone primeiroTelefone) {
        super.onPostExecute(primeiroTelefone);
        listener.quandoEncontrado(primeiroTelefone);
        Log.d(LOG_TAG, "vincula: alunoId = " + alunoId + " primeiroTelefone: " + primeiroTelefone);
    }

    public interface PrimeiroTelefoneEncontradoListener {
        void quandoEncontrado(Telefone telefoneEncontrado);
    }
}
