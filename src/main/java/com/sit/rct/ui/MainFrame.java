package com.sit.rct.ui;

import com.sit.rct.service.AuthService;
import com.sit.rct.storage.DataStore;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainFrame extends JFrame {
    public static final String CARD_DASHBOARD = "DASHBOARD";
    public static final String CARD_PROJECTS  = "PROJECTS";
    public static final String CARD_TASKS     = "TASKS";
    public static final String CARD_LAB_LOGS  = "LAB_LOGS";
    public static final String CARD_BUDGET    = "BUDGET";
    public static final String CARD_HISTORY   = "HISTORY";

    private final DataStore dataStore;
    private final AuthService authService;

    private final CardLayout cardLayout;
    private final JPanel centerPanel;

    public MainFrame(DataStore dataStore, AuthService authService) {
        this.dataStore = dataStore;
        this.authService = authService;

        setTitle("Research Collaboration & Project Management System");
        setSize(1200, 760);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        centerPanel = new JPanel(cardLayout);

        centerPanel.add(createPlaceholderPanel("Dashboard Panel - Coming Week 2"), CARD_DASHBOARD);
        centerPanel.add(createPlaceholderPanel("Projects Panel - Coming Week 2"), CARD_PROJECTS);
        centerPanel.add(createPlaceholderPanel("Tasks Panel - Coming Week 2"), CARD_TASKS);
        centerPanel.add(createPlaceholderPanel("Lab Logs & Docs Panel - Coming Week 2"), CARD_LAB_LOGS);
        centerPanel.add(createPlaceholderPanel("Budget Panel - Coming Week 2"), CARD_BUDGET);
        centerPanel.add(createPlaceholderPanel("History Panel - Coming Week 2"), CARD_HISTORY);

        JPanel sidebar = createSidebar();

        add(sidebar, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);

        cardLayout.show(centerPanel, CARD_DASHBOARD);
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(230, 760));
        sidebar.setBorder(new EmptyBorder(20, 15, 20, 15));
        sidebar.setBackground(new Color(242, 244, 248));

        JLabel brandLabel = new JLabel("SIT Research Hub");
        brandLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        brandLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        sidebar.add(brandLabel);

        String userText = (authService != null && authService.getCurrentUser() != null)
                ? authService.getCurrentUser().getRoleName()
                : "Guest Session";
        JLabel userLabel = new JLabel(userText);
        userLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        userLabel.setForeground(Color.DARK_GRAY);
        userLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        sidebar.add(Box.createVerticalStrut(6));
        sidebar.add(userLabel);

        sidebar.add(Box.createVerticalStrut(24));

        addNavButton(sidebar, "Dashboard", CARD_DASHBOARD);
        addNavButton(sidebar, "Projects", CARD_PROJECTS);
        addNavButton(sidebar, "Tasks", CARD_TASKS);
        addNavButton(sidebar, "Lab Logs & Docs", CARD_LAB_LOGS);
        addNavButton(sidebar, "Budget", CARD_BUDGET);
        addNavButton(sidebar, "History", CARD_HISTORY);

        sidebar.add(Box.createVerticalGlue());
        return sidebar;
    }

    private void addNavButton(JPanel sidebar, String label, String cardName) {
        JButton btn = new JButton(label);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setFocusPainted(false);
        btn.setFont(new Font("SansSerif", Font.PLAIN, 14));

        btn.addActionListener(e -> cardLayout.show(centerPanel, cardName));

        sidebar.add(btn);
        sidebar.add(Box.createVerticalStrut(8));
    }

    private JPanel createPlaceholderPanel(String message) {
        JPanel panel = new JPanel(new GridBagLayout());
        JLabel label = new JLabel(message);
        label.setFont(new Font("SansSerif", Font.BOLD, 22));
        label.setForeground(new Color(90, 95, 105));
        panel.add(label);
        return panel;
    }
}