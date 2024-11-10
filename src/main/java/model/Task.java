package model;
import java.time.LocalDate;

/**
 *
 * @author gabri
 */
public class Task {
    private final int task_id; // Novo campo para o ID da tarefa
    private String task_name;
    private String task_description;
    private boolean urgent;
    private LocalDate creation_date;
    private LocalDate due_date;
    private boolean complete;

    // Constructor for Tasks with no ID
    public Task(String task_name, String task_description, boolean urgent, LocalDate creation_date, LocalDate due_date, boolean complete) {
        this.task_id = -1; 
        this.task_name = task_name;
        this.task_description = task_description;
        this.urgent = urgent;
        this.creation_date = creation_date;
        this.due_date = due_date;
        this.complete = complete;
    }
    
    // Constructor for Tasks with ID
    public Task(int task_id, String task_name, String task_description, boolean urgent, LocalDate creation_date, LocalDate due_date, boolean complete) {
        this.task_id = task_id;
        this.task_name = task_name;
        this.task_description = task_description;
        this.urgent = urgent;
        this.creation_date = creation_date;
        this.due_date = due_date;
        this.complete = complete;
    }
    

    // Getters
    
    public String getTask_name() {
        return task_name;
    }

    public String getTask_description() {
        return task_description;
    }

    public boolean getUrgent() {
        return urgent;
    }

    public LocalDate getCreation_date() {
        return creation_date;
    }

    public LocalDate getDue_date() {
        return due_date;
    }
    
    public boolean getComplete(){
        return complete;
    }

    public int getTask_id() {
        return task_id;
    }
    

    // Setters
      
    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public void setTask_description(String task_description) {
        this.task_description = task_description;
    }

    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }

    public void setCreation_date(LocalDate creation_date) {
        this.creation_date = creation_date;
    }

    public void setDue_date(LocalDate due_date) {
        this.due_date = due_date;
    }
    
    public void setComplete(boolean complete){
        this.complete = complete;
    }
}
