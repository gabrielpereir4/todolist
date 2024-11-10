/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.Scanner;
import java.util.List;
import model.Task;
import controller.TaskController;
import java.time.LocalDate;

/**
 *
 * @author gabri
 */
public class ViewManager {
    private static final TaskController controller = TaskController.getInstance();
    
    public ViewManager(){}
    
    public void displayMenu(){
        Scanner scanner = new Scanner (System.in);
        
        while(true){
            System.out.println("Menu:");
            System.out.println("1. New Task");
            System.out.println("2. Edit Task");
            System.out.println("3. Complete Task");            
            System.out.println("4. Delete Task");
            System.out.println("5. List All Tasks");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addTaskInput();
                    break;
                case 2:
                    editTaskInput();
                    break;
                case 3:
                    completeTaskInput();
                    break;
                case 4:
                    deleteTaskInput();
                    break;
                case 5:
                    listTasks();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid Option.");
            }
        }
    }
    
    public void addTaskInput(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("(NEW TASK) Task Name: ");
        String taskName = scanner.nextLine();
        
        System.out.print("(NEW TASK) Task Description: ");
        String taskDescription = scanner.nextLine();
        
        System.out.print("(NEW TASK) Urgent? (true/false): ");
        boolean urgent = scanner.nextBoolean();
        
        System.out.print("(NEW TASK) Due date (YYYY-MM-DD): ");
        String dueDateInput = scanner.next();
        LocalDate dueDate = LocalDate.parse(dueDateInput);

        // Current date
        LocalDate creationDate = LocalDate.now();

        Task task = new Task(taskName, taskDescription, urgent, creationDate, dueDate, false);
        controller.addTask(task);
    }
    
    public void viewTask(Task task){
        System.out.println("ID: " + task.getTask_id());
        System.out.println("Name: " + task.getTask_name());
        System.out.println("Description: " + task.getTask_description());
        System.out.println("Urgent: " + task.getUrgent());
        System.out.println("Created: " + task.getCreation_date());
        System.out.println("Due: " + task.getDue_date());
        System.out.println("Complete: " + task.getComplete());
        System.out.print('\n');
    }
    
    public void listTasks(){
        List<Task> tasks = controller.getAllTasks();
        System.out.println("Listing All Tasks...\n");
        if (tasks.isEmpty()){
            System.out.println("No tasks found.\n");
        }
        else{
            for(int aux=0; aux < tasks.size(); aux++){
                viewTask(tasks.get(aux));
            }
        System.out.println("All tasks listed!");
        }
    }
    
    public void deleteTaskInput(){
        listTasks();
        
        Scanner scanner = new Scanner(System.in);

        System.out.print("(DELETE TASK) Enter Task ID: ");
        int task_id = scanner.nextInt();
        
        if (controller.getTask(task_id) == null){
            System.out.println("Task ID " + task_id + " not found.\n");            
        }
        else{  
            controller.deleteTask(task_id);
        }
    }
    
    public void editTaskInput() {
        listTasks(); // Exibe todas as tarefas antes de editar

        Scanner scanner = new Scanner(System.in);

        System.out.print("(EDIT TASK) Enter Task ID: ");
        int task_id = scanner.nextInt();

        // Obtém a tarefa pelo ID
        Task task = controller.getTask(task_id);

        if (task == null) {
            System.out.println("Task not found!");
            return;
        }

        // Exibe os detalhes da tarefa antes de editar
        viewTask(task);

        while (true) {
            System.out.println("(EDIT TASK) This is the current task ID " + task_id + ". Do you want to edit it?");
            System.out.println("1. Yes");
            System.out.println("2. No, exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    break; // Sai do menu de confirmação para entrar no menu de edição
                case 2:
                    return; // Sai do método sem editar
                default:
                    System.out.println("Invalid option.");
                    continue; // Retorna ao loop para tentar novamente
            }

            // Menu para editar os atributos da tarefa
            while (true) {
                System.out.println("(EDIT TASK) Choose Attribute to edit:");
                System.out.println("1. Task Name");
                System.out.println("2. Task Description");
                System.out.println("3. Urgent");
                System.out.println("4. Due Date");
                System.out.println("5. Complete");
                System.out.println("0. Exit");

                int editChoice = scanner.nextInt(); // Escolha de atributo para editar
                scanner.nextLine(); // Consumir a nova linha pendente após nextInt()

                switch (editChoice) {
                    case 1: // Editar o nome da tarefa
                        System.out.print("Enter new task name: ");
                        String newName = scanner.nextLine();
                        task.setTask_name(newName);
                        System.out.println("Task name updated!");
                        break;
                    case 2: // Editar a descrição da tarefa
                        System.out.print("Enter new task description: ");
                        String newDescription = scanner.nextLine();
                        task.setTask_description(newDescription);
                        System.out.println("Task description updated!");
                        break;
                    case 3: // Editar urgência
                        System.out.print("Is it urgent? (true/false): ");
                        boolean isUrgent = scanner.nextBoolean();
                        task.setUrgent(isUrgent);
                        System.out.println("Task urgency updated!");
                        break;
                    case 4: // Editar data de vencimento
                        System.out.print("Enter new due date (YYYY-MM-DD): ");
                        String newDueDate = scanner.nextLine();
                        LocalDate dueDate = LocalDate.parse(newDueDate);
                        task.setDue_date(dueDate);
                        System.out.println("Task due date updated!");
                        break;
                    case 5: // Marcar como completo
                        System.out.print("Is it complete? (true/false): ");
                        boolean complete = scanner.nextBoolean();
                        task.setComplete(complete);
                        System.out.println("Task completion updated!");
                        break;
                    case 0: // Sair da edição
                        return;
                    default:
                        System.out.println("Invalid option.");
                }

                // Perguntar se o usuário quer editar outro atributo
                System.out.print("Do you want to edit another attribute? (yes/no): ");
                String continueEditing = scanner.nextLine().trim().toLowerCase();
                if (!continueEditing.equals("yes")) {
                    break; // Sai do loop de edição
                }
            }

            // Atualizar a tarefa no banco de dados após editar
            controller.editTask(task); 
            System.out.println("Task updated successfully!");
            break;
        }
    }
    
    public void completeTaskInput(){
        listTasks();
        
        Scanner scanner = new Scanner(System.in);

        System.out.print("(COMPLETE TASK) Enter Task ID: ");
        int task_id = scanner.nextInt();
        
        controller.completeTask(task_id);
    }
}
