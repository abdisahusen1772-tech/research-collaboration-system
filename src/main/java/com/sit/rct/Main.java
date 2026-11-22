package com.sit.rct;

import com.formdev.flatlaf.FlatLightLaf;
import com.sit.rct.model.ProjectLeader;
import com.sit.rct.model.Researcher;
import com.sit.rct.model.User;
import com.sit.rct.service.AuthService;
import com.sit.rct.storage.DataStore;
import com.sit.rct.storage.StorageManager;
import com.sit.rct.ui.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        FlatLightLaf.setup();

        DataStore dataStore = StorageManager.load();
        AuthService authService = new AuthService(dataStore);

        if (dataStore.getUsers().isEmpty()) {
            authService.registerUser(new ProjectLeader(
                    "U-001", "SIT-001", "Abdisa Husein Fitesa", "abdisa@sit.edu.et", "pass123"));
            authService.registerUser(new Researcher(
                    "U-002", "SIT-002", "Demo Researcher", "researcher@sit.edu.et", "pass123"));
        }

        User loggedIn = authService.login("SIT-001", "pass123");
        if (loggedIn != null) {
            System.out.println("Logged in as: " + loggedIn.getName()
                    + " | Role: " + loggedIn.getRoleName()
                    + " | Can Create Project: " + loggedIn.canCreateProject());
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            StorageManager.save(dataStore);
        }));

        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame(dataStore, authService);
            frame.setVisible(true);
        });
    }
}