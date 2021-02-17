package br.com.alura.agenda.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import br.com.alura.agenda.model.Telefone;
import br.com.alura.agenda.model.TipoTelefone;

import static br.com.alura.agenda.model.TipoTelefone.FIXO;

class AgendaMigrations {

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {    // versão antiga para a nova, adicionando o sobrenome
        @Override
        // a cada mudança é bom entrar no código qual a mudança que houve no DB
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Aluno ADD COLUMN sobrenome TEXT");
        }
    };

    private static final Migration MIGRATION_2_3 = new Migration(2, 3) {    // irei remover o campo sobrenome, nestes casos nunca execute o aplicativo, pois senão teria que fazer mais uma migration de 3 para 4
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // criar uma nova tabela com as informações (colunas) desejadas
            database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo`" +
                    "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nome` TEXT, `telefone` TEXT, `email` TEXT)");

            // copiar dados da tabela antiga para a nova (query)
            database.execSQL("INSERT INTO Aluno_novo (id, nome, telefone, email)" +
                    "SELECT id, nome, telefone, email FROM aluno");

            // remoção da tabela antiga (deletar)
            database.execSQL("DROP TABLE Aluno");

            // precisa renomear a tabela nova com o nome da tabela antiga
            database.execSQL("ALTER TABLE Aluno_novo RENAME TO Aluno");
        }
    };

    private static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Aluno ADD COLUMN momentoCadastro INTEGER");
        }
    };

    private static final Migration MIGRATION_4_5 = new Migration(4, 5) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // criar uma nova tabela com as informações (colunas) desejadas
            database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    " `nome` TEXT, `telefoneFixo` TEXT, `telefoneCelular` TEXT, `email` TEXT, `momentoCadastro` INTEGER)");

            // copiar dados da tabela antiga para a nova (query)
            database.execSQL("INSERT INTO Aluno_novo (id, nome, telefoneFixo, email, momentoCadastro)" +
                    "SELECT id, nome, telefone, email, momentoCadastro FROM Aluno");

            // remoção da tabela antiga (deletar)
            database.execSQL("DROP TABLE Aluno");

            // precisa renomear a tabela nova com o nome da tabela antiga
            database.execSQL("ALTER TABLE Aluno_novo RENAME TO Aluno");
        }
    };

    private static final Migration MIGRATION_5_6 = new Migration(5, 6) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`nome` TEXT, " +
                    "`email` TEXT, " +
                    "`momentoDeCadastro` INTEGER)");

            database.execSQL("INSERT INTO Aluno_novo (id, nome, email, momentoDeCadastro) " +
                    "SELECT id, nome, email, momentoDeCadastro FROM Aluno");

            database.execSQL("CREATE TABLE IF NOT EXISTS `Telefone` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`numero` TEXT, " +
                    "`tipo` TEXT, " +
                    "`alunoId` INTEGER NOT NULL)");

            database.execSQL("INSERT INTO Telefone (numero, alunoId) " +
                    "SELECT telefoneFixo, id FROM Aluno");

            database.execSQL("UPDATE Telefone SET tipo = ?", new TipoTelefone[] {FIXO});

            database.execSQL("DROP TABLE Aluno");

            database.execSQL("ALTER TABLE Aluno_novo RENAME TO Aluno");
        }
    };


    static final Migration[] TODAS_MIGRATION = {
            MIGRATION_1_2,
            MIGRATION_2_3,
            MIGRATION_3_4,
            MIGRATION_4_5,
            MIGRATION_5_6};
}
