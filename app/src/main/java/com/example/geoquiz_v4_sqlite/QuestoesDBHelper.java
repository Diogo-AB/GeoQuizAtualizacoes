package com.example.geoquiz_v4_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class QuestoesDBHelper extends SQLiteOpenHelper {
    private static final int VERSAO = 1;
    private static final String NOME_DATABASE = "questoesDB";

    public QuestoesDBHelper(Context context) {
        super(context, NOME_DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Criação da tabela de questões (já existente)
        db.execSQL("CREATE TABLE "+ QuestoesDbSchema.QuestoesTbl.NOME+ "("+
                "_id integer PRIMARY KEY autoincrement,"+
                QuestoesDbSchema.QuestoesTbl.Cols.UUID + ","+
                QuestoesDbSchema.QuestoesTbl.Cols.QUESTAO_CORRETA + ","+
                QuestoesDbSchema.QuestoesTbl.Cols.TEXTO_QUESTAO + ")");

        // Criação da tabela de respostas dos usuários
        db.execSQL("CREATE TABLE " + RespostasDbSchema.RespostasTbl.NOME + " (" +
                "_id integer PRIMARY KEY autoincrement," +
                RespostasDbSchema.RespostasTbl.Cols.UUID_QUESTAO + " TEXT," + // Referência à questão
                RespostasDbSchema.RespostasTbl.Cols.RESPOSTA_CORRETA + " INTEGER," + // Resposta correta (0 ou 1)
                RespostasDbSchema.RespostasTbl.Cols.RESPOSTA_OFERECIDA + " TEXT," + // Resposta oferecida (verdadeiro ou falso)
                RespostasDbSchema.RespostasTbl.Cols.COLOU + " INTEGER" + // Se colou (0 ou 1)
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao) {
        // Exclui a tabela de questões se já existir
        db.execSQL("DROP TABLE IF EXISTS " + QuestoesDbSchema.QuestoesTbl.NOME);

        // Exclui a tabela de respostas se já existir
        db.execSQL("DROP TABLE IF EXISTS " + RespostasDbSchema.RespostasTbl.NOME);

        // Recria ambas as tabelas
        onCreate(db);
    }

    public void inserirResposta(String uuidQuestao, int respostaCorreta, String respostaOferecida, int colou) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(RespostasDbSchema.RespostasTbl.Cols.UUID_QUESTAO, uuidQuestao);
        valores.put(RespostasDbSchema.RespostasTbl.Cols.RESPOSTA_CORRETA, respostaCorreta);
        valores.put(RespostasDbSchema.RespostasTbl.Cols.RESPOSTA_OFERECIDA, respostaOferecida);
        valores.put(RespostasDbSchema.RespostasTbl.Cols.COLOU, colou);

        db.insert(RespostasDbSchema.RespostasTbl.NOME, null, valores);

    }

}

