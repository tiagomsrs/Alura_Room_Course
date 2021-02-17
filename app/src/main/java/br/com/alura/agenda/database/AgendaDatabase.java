package br.com.alura.agenda.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


import br.com.alura.agenda.database.converter.ConversorCalendar;
import br.com.alura.agenda.database.converter.ConversorTipoTelefone;
import br.com.alura.agenda.database.dao.AlunoDAO;
import br.com.alura.agenda.database.dao.TelefoneDao;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.model.Telefone;

@Database(entities = {Aluno.class, Telefone.class}, version = 6, exportSchema = false)
@TypeConverters({ConversorCalendar.class, ConversorTipoTelefone.class})
public abstract  class AgendaDatabase  extends RoomDatabase {

    private static final String NOME_BANCO_DE_DADOS = "agenda.db";

    public abstract AlunoDAO getAlunoDAO();
    public abstract TelefoneDao getTelefoneDAO();

    public static AgendaDatabase getInstance(Context context){
            return Room.databaseBuilder(context, AgendaDatabase.class, NOME_BANCO_DE_DADOS + NOME_BANCO_DE_DADOS)
                     //.allowMainThreadQueries()
                    .addMigrations(AgendaMigrations.TODAS_MIGRATION)
                     //.fallbackToDestructiveMigration()       // vai perder todos os dados que tinham antes no nosso app na versão anteior do DB
                    .build();                               // enquanto o app não foi publicado pode-se usar o fallback, pois não teria problema perder os dados
    }                                                       // quando tiver chegado no usuário, é essencial trocar o fallback para o migration
}
