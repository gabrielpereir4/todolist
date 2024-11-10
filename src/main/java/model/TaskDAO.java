/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;


/**
 *
 * @author gabri
 */
public class TaskDAO {
    private static TaskDAO instance = null; // Singleton
    private static final String URL = "jdbc:sqlite:todolist.db";
    
    private TaskDAO(){};
    
    public static synchronized TaskDAO getInstance(){
        if (instance == null){
            instance = new TaskDAO();
        }
        return instance;
    }
    
    public void addTask(Task task){
        String sql = "INSERT INTO tasks (task_name, task_description, urgent, creation_date, due_date, complete)"
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, task.getTask_name());
            pstmt.setString(2, task.getTask_description());
            pstmt.setBoolean(3, task.getUrgent());
            pstmt.setDate(4, Date.valueOf(task.getCreation_date()));
            pstmt.setDate(5, Date.valueOf(task.getDue_date()));
            pstmt.setBoolean(6, task.getComplete());
            
            pstmt.executeUpdate();
            System.out.println("Task created successfully.");
        }
        catch (SQLException e){
            System.out.println("Failed to create task: " + e.getMessage());
        }
    }
    
    public void updateTask(Task task, int taskId) {
        String sql = "UPDATE tasks SET task_name = ?, task_description = ?, urgent = ?, creation_date = ?, due_date = ?, complete = ? " +
                     "WHERE task_id = ?";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, task.getTask_name());
            pstmt.setString(2, task.getTask_description());
            pstmt.setBoolean(3, task.getUrgent());
            pstmt.setDate(4, Date.valueOf(task.getCreation_date()));
            pstmt.setDate(5, Date.valueOf(task.getDue_date()));
            pstmt.setBoolean(6, task.getComplete());
            pstmt.setInt(7, taskId);

            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " tasks(s) updated.");
        } catch (SQLException e) {
            System.err.println("Failed to update task: " + e.getMessage());
        }
    }
    
    public void completeTask(int task_id){
        String sql = "UPDATE tasks SET complete = true WHERE task_id = ?";
        
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, task_id);
            
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " tasks(s) updated.");
        }
        catch (SQLException e) {
            System.err.println("Failed to update task: " + e.getMessage());
        }
        
    }
        
    public void deleteTaskByID(int task_id){
        String sql = "DELETE FROM tasks WHERE task_id = ?";
        
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setInt(1, task_id);
            
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " task(s) deleted(s).");
        }
        catch (SQLException e){
            System.err.println("Failed to delete task: " + e.getMessage());
        }
    }
    
    public Task getTaskByID(int task_id){
        Task task = null; 
        String sql = "SELECT * FROM tasks WHERE task_id = ?";
        
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setInt(1, task_id);
            try (ResultSet rs = pstmt.executeQuery()) {
            // Verifica se um resultado foi retornado
                if (rs.next()) {
                    // Cria um novo objeto Task com os dados retornados
                    task = new Task(
                        rs.getInt("task_id"),
                        rs.getString("task_name"),
                        rs.getString("task_description"),
                        rs.getBoolean("urgent"),
                        rs.getDate("creation_date").toLocalDate(),  // Converte a data para LocalDate
                        rs.getDate("due_date").toLocalDate(),
                        rs.getBoolean("complete")
                    );
                }
            }
        }
        catch (SQLException e){
            System.err.println("Failed to retrieve task: " + e.getMessage());
        }
        return task;
    }
    
    public List<Task> getAllTasks(){
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks";
        
        try(Connection conn = DatabaseManager.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            try(ResultSet rs = pstmt.executeQuery()){
                while(rs.next()){
                    Task task = new Task(
                        rs.getInt("task_id"),
                        rs.getString("task_name"),
                        rs.getString("task_description"),
                        rs.getBoolean("urgent"),
                        rs.getDate("creation_date").toLocalDate(),
                        rs.getDate("due_date").toLocalDate(),
                        rs.getBoolean("complete")
                    );
                    tasks.add(task);   
                }
            }
        }
        catch (SQLException e){
            System.err.println("Failed to retrieve all tasks: " + e.getMessage());
        }
        return tasks;
    }
}

