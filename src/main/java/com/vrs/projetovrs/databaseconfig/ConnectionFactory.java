package com.vrs.projetovrs.databaseconfig;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.stream.Collectors;

public class ConnectionFactory {
    private static final String URL_SERVER = "jdbc:postgresql://localhost:5432/";
    private static final String URL = "jdbc:postgresql://localhost:5432/vrsoftware";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "root";

    /**
     * Obtém uma conexão com o banco de dados PostgreSQL.
     *
     * @return Uma conexão com o banco de dados.
     * @throws RuntimeException Se ocorrer um erro ao estabelecer a conexão.
     */
    private Connection getConnectionServer() {
        try {
            return DriverManager.getConnection(URL_SERVER, USUARIO, SENHA);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void verificarEstruturaBanco() {
        try (Connection connectionServer = getConnectionServer();
             Statement statementServer = connectionServer.createStatement()) {

            // Verifica se o banco de dados existe
            if (!checkIfDatabaseExists(connectionServer, "vrsoftware")) {
                // Banco de dados não existe, então execute o script SQL
                executeSqlScript(statementServer, "scripts/script_banco.sql");
            }

            try (Connection connection = getConnection();
                Statement statement = connection.createStatement()) {
                // Verifica se as tabelas existem e as cria se necessário
                verificarETabelas(connection, statement);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao verificar/criar a estrutura do banco de dados. \nErro: " + e.getMessage());
        }
    }

    private boolean checkIfDatabaseExists(Connection connectionServer, String databaseName) throws Exception {
        String sql = "SELECT datname FROM pg_database WHERE datname = ?";
        try (PreparedStatement statement = connectionServer.prepareStatement(sql)) {
            statement.setString(1, databaseName);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    private void verificarETabelas(Connection connection, Statement statement) throws Exception {
        // Tabelas a serem verificadas e criadas
        String[] tabelas = {"tb_clientes", "tb_produtos", "tb_vendas", "tb_itensvendas"};

        for (String tabela : tabelas) {
            if (!checkIfTableExists(statement, tabela)) {
                executeSqlScript(statement, "scripts/script_" + tabela + ".sql");
            }
        }
    }

    private boolean checkIfTableExists(Statement statement, String tableName) throws Exception {
        ResultSet resultSet = statement.executeQuery("SELECT to_regclass('" + tableName + "')");
        if (resultSet.next()) {
            String result = resultSet.getString(1);
            return result != null;
        }
        return false;
    }

    private void executeSqlScript(Statement statement, String scriptFileName) throws Exception {
        // Carregue o script SQL do arquivo na pasta resources
        InputStream inputStream = getClass().getResourceAsStream("/" + scriptFileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String scriptContent = reader.lines().collect(Collectors.joining("\n"));

        // Execute o script SQL
        statement.executeUpdate(scriptContent);
    }

}
