/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import java.util.ArrayList;
import model.TaskDAO;
import model.Task;

/**
 *
 * @author gabri
 */
public class TaskController {
    private static TaskController instance = null;
    private static TaskDAO taskdao = TaskDAO.getInstance();
    
    private TaskController(){
    };
    
    public static TaskController getInstance(){
        if (instance == null){
            instance = new TaskController();
        } 
        return instance;
    }
    
    public void addTask(Task task){
        taskdao.addTask(task);
    }
    
    public void editTask(Task task){
        taskdao.updateTask(task, task.getTask_id());
    }
    
    public Task getTask(int taskid){
        Task task = taskdao.getTaskByID(taskid);
        return task;
    }
    
    public List<Task> getAllTasks(){
        List<Task> tasks = taskdao.getAllTasks();
        return tasks;
    }
    
    public void completeTask(int taskid){
        taskdao.completeTask(taskid);
    }
    
    public void deleteTask(int taskid){
        taskdao.deleteTaskByID(taskid);
    }
}
