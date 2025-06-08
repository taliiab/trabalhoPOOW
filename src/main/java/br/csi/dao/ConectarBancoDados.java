package br.csi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectarBancoDados {

    public static Connection conectarBancoPostgres()
            throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        System.out.println("Conectado com sucesso!");

        String url = "jdbc:postgresql://localhost:5432/poowTrab";
        String usuario = "postgres";
        String senha = "1234";
        Connection connection =
                DriverManager.getConnection(url, usuario, senha);
        return connection;
    }

    public Connection conectarBancoPostgresMySql(){
        return null;
    }
}

