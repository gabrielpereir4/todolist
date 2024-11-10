/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author gabri
 */
public class DatabaseManager {
    private static final String DATABASE_URL = "jdbc:sqlite:todolist.db";
    static {
        initializeDatabase();  // Garante a criação da tabela ao carregar a classe
    }
    
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL);
    }
    
    public static void initializeDatabase(){
        String createTableSQL = "CREATE TABLE IF NOT EXISTS tasks (" +
                                "task_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                "task_name TEXT NOT NULL, " +
                                "task_description TEXT, " +
                                "urgent BOOLEAN, " +
                                "creation_date DATE, " +
                                "due_date DATE, " +
                                "complete BOOLEAN" +
                                ");";
        try (Connection conn = connect(); 
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("Tabela 'tasks' verificada/criada com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao verificar/criar tabela: " + e.getMessage());
        }
    }
}

