package com.example.geoquiz_v4_sqlite;

public class RespostasDbSchema {
    public static final class RespostasTbl {
        public static final String NOME = "respostas"; // Nome da tabela

        public static final class Cols {
            public static final String UUID_QUESTAO = "uuid_questao"; // UUID da questão
            public static final String RESPOSTA_CORRETA = "resposta_correta"; // Se a resposta foi correta (0 ou 1)
            public static final String RESPOSTA_OFERECIDA = "resposta_oferecida"; // Resposta oferecida (verdadeiro ou falso)
            public static final String COLOU = "colou"; // Se o usuário colou (0 ou 1)
        }
    }
}
