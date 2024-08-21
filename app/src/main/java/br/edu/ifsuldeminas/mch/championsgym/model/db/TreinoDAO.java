package br.edu.ifsuldeminas.mch.championsgym.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsuldeminas.mch.championsgym.model.Treino;

public class TreinoDAO extends DAO {

    public TreinoDAO(Context context) {
        super(context);
    }

    // Método para adicionar um treino
    public void addTreino(Treino treino) throws SQLException {
        SQLiteDatabase db = openToWrite();
        ContentValues values = new ContentValues();

        values.put("nomeExercicio", treino.getNomeExercicio());
        values.put("duracao", treino.getDuracao().toString()); // Armazena a duração como string (hh:mm:ss)
        values.put("data", treino.getData().toString()); // Armazena a data como string (yyyy-MM-dd)

        db.insert("treino", null, values);
        db.close();
    }

    // Método para recuperar um treino pelo ID
    public Treino getTreino(int id) throws SQLException {
        SQLiteDatabase db = openToRead();
        Cursor cursor = db.query("treino", new String[]{"id", "nomeExercicio", "duracao", "data"},
                "id = ?", new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Treino treino = new Treino(
                cursor.getInt(0),
                cursor.getString(1),
                Time.valueOf(cursor.getString(2)), // Converte a string para Time
                java.sql.Date.valueOf(cursor.getString(3)) // Converte a string para Date
        );

        cursor.close();
        return treino;
    }

    // Método para recuperar todos os treinos
    public List<Treino> getAllTreinos() throws SQLException {
        List<Treino> treinoList = new ArrayList<>();
        SQLiteDatabase db = openToRead();
        Cursor cursor = db.rawQuery("SELECT * FROM treino", null);

        if (cursor.moveToFirst()) {
            do {
                Treino treino = new Treino();
                treino.setId(cursor.getInt(0));
                treino.setNomeExercicio(cursor.getString(1));
                treino.setDuracao(Time.valueOf(cursor.getString(2)));
                treino.setData(java.sql.Date.valueOf(cursor.getString(3)));

                treinoList.add(treino);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return treinoList;
    }

    // Método para atualizar um treino
    public int updateTreino(Treino treino) throws SQLException {
        SQLiteDatabase db = openToWrite();
        ContentValues values = new ContentValues();

        values.put("nomeExercicio", treino.getNomeExercicio());
        values.put("duracao", treino.getDuracao().toString());
        values.put("data", treino.getData().toString());

        return db.update("treino", values, "id = ?", new String[]{String.valueOf(treino.getId())});
    }

    // Método para deletar um treino
    public void deleteTreino(int id) throws SQLException {
        SQLiteDatabase db = openToWrite();
        db.delete("treino", "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}
