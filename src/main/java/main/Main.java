/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import view.ViewManager;

/**
 *
 * @author gabri
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to the To-Do List Manager!");
        
        // Inicializa o ViewManager e exibe o menu principal
        ViewManager viewManager = new ViewManager();
        viewManager.displayMenu();
        
        System.out.println("Application closed.");
    }
}
