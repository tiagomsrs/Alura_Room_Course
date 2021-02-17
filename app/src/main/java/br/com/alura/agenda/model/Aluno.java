package br.com.alura.agenda.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Entity
public class Aluno implements Serializable {

    @PrimaryKey (autoGenerate = true)
    private int id = 0;
    private String nome;
    // private String sobrenome;
    private String email;

    private Calendar momentoCadastro = Calendar.getInstance();



    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    // public void setSobrenome(String sobrenome) { this.sobrenome = sobrenome; }

    // public String getSobrenome() { return sobrenome; }

    @NonNull
    @Override
    public String toString() {
        return this.nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean temIdValido() {
        return this.id > 0;
    }

    public Calendar getMomentoCadastro() { return this.momentoCadastro; }

    public void setMomentoCadastro(Calendar momentoCadastro) { this.momentoCadastro = momentoCadastro; }

    public String dataFormatada () {
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        return formatador.format(momentoCadastro.getTime());
    }
}
