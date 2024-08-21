package br.edu.ifsuldeminas.mch.championsgym.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.ifsuldeminas.mch.championsgym.model.Treino;

public class DBHandler extends SQLiteOpenHelper {

    // Versão do banco de dados
    private static final int DATABASE_VERSION = 1;

    // Nome do banco de dados
    private static final String DATABASE_NAME = "treinoDB";

    // Nome da tabela
    private static final String TABLE_TREINO = "treino";

    // Nomes das colunas da tabela treino
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOME_EXERCICIO = "nomeExercicio";
    private static final String COLUMN_DURACAO = "duracao";
    private static final String COLUMN_DATA = "data";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Criando a tabela
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TREINO_TABLE = "CREATE TABLE " + TABLE_TREINO + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NOME_EXERCICIO + " TEXT,"
                + COLUMN_DURACAO + " TEXT,"  // Armazenando duração como string (hh:mm)
                + COLUMN_DATA + " TEXT" + ")"; // Armazenando data como string (yyyy-MM-dd)
        db.execSQL(CREATE_TREINO_TABLE);
    }

    // Atualizando o banco de dados se a versão mudar
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TREINO);
        onCreate(db);
    }

    // Métodos CRUD (Create, Read, Update, Delete)

    // Adiciona um novo treino
    public void addTreino(Treino treino) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NOME_EXERCICIO, treino.getNomeExercicio());
        values.put(COLUMN_DURACAO, treino.getDuracao().toString()); // Armazena o Time como string (hh:mm:ss)
        values.put(COLUMN_DATA, treino.getData().toString()); // Armazena a Date como string (yyyy-MM-dd)

        db.insert(TABLE_TREINO, null, values);
        db.close(); // Fecha a conexão com o banco de dados
    }

    // Retorna um treino pelo ID
    public Treino getTreino(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TREINO, new String[]{COLUMN_ID, COLUMN_NOME_EXERCICIO, COLUMN_DURACAO, COLUMN_DATA},
                COLUMN_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Treino treino = new Treino(
                cursor.getInt(0),
                cursor.getString(1),
                Time.valueOf(cursor.getString(2)), // Converte a string de volta para Time
                java.sql.Date.valueOf(cursor.getString(3)) // Converte a string de volta para Date
        );

        cursor.close();
        return treino;
    }

    // Retorna todos os treinos
    public List<Treino> getAllTreinos() {
        List<Treino> treinoList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_TREINO;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

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

    // Atualiza um treino
    public int updateTreino(Treino treino) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NOME_EXERCICIO, treino.getNomeExercicio());
        values.put(COLUMN_DURACAO, treino.getDuracao().toString());
        values.put(COLUMN_DATA, treino.getData().toString());

        return db.update(TABLE_TREINO, values, COLUMN_ID + " = ?", new String[]{String.valueOf(treino.getId())});
    }

    // Deleta um treino
    public void deleteTreino(Treino treino) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TREINO, COLUMN_ID + " = ?", new String[]{String.valueOf(treino.getId())});
        db.close();
    }

    // Retorna a contagem total de treinos
    public int getTreinosCount() {
        String countQuery = "SELECT * FROM " + TABLE_TREINO;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        return count;
    }
}

