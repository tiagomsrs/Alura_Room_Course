package br.com.alura.agenda.database.dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.alura.agenda.model.Aluno;

@Dao
public interface AlunoDAO {

    @Insert         // com isso o DAO já vai entender que esse metodo salva, é para ser um metodo de inserção de coisas no banco
    Long salva(Aluno aluno);    // colocando um retorno, o Room ja devolve o Id do aluno que ele salvou

    @Query("SELECT * FROM aluno")
    List<Aluno> todos();

    @Delete
    void remove(Aluno aluno);

    @Update
    void edita(Aluno aluno);
}
