package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import exceptions.InvalidTaskDayException;
import exceptions.InvalidTaskDurationException;
import model.NormalTask;
import model.PermTask;
import model.Planner;
import model.Task;
import persistence.JsonReader;
import persistence.JsonWriter;

public class PlannerGUI extends JFrame {

    private Planner planner;

    private DefaultListModel<String> taskModel;
    private JList<String> taskList;

    private JTextField nameField;
    private JTextField dateField;
    private JTextField timeField;
    private JTextField descriptionField;
    private JTextField locationField;

    private JCheckBox permanentBox;

    private static final String JSON_STORE = "./data/planner.json";

    public PlannerGUI() {

        planner = new Planner();

        setTitle("Weekly Planner");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initializeTaskList();      // LEFT SIDE
        initializeEditorPanel();   // MIDDLE
        initializePersistenceBar(); // BOTTOM
        taskList.addListSelectionListener(e -> populateFieldsFromSelection());

        setVisible(true);
    }

    private void initializeTaskList() {
        taskModel = new DefaultListModel<>();
        taskList = new JList<>(taskModel);
    
        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setPreferredSize(new java.awt.Dimension(250, 0));
    
        JPanel leftPanel = new JPanel(new BorderLayout());
    
        leftPanel.add(new JLabel("Tasks"), BorderLayout.NORTH);
        leftPanel.add(scrollPane, BorderLayout.CENTER);
    
        add(leftPanel, BorderLayout.WEST);
    }

    private void initializeEditorPanel() {

        JPanel editor = new JPanel(new GridLayout(8,2,10,10));
    
        nameField = new JTextField();
        dateField = new JTextField();
        timeField = new JTextField();
        descriptionField = new JTextField();
        locationField = new JTextField();
        permanentBox = new JCheckBox();
    
        editor.add(new JLabel("Name"));
        editor.add(nameField);
    
        editor.add(new JLabel("Day"));
        editor.add(dateField);
    
        editor.add(new JLabel("Time"));
        editor.add(timeField);
    
        editor.add(new JLabel("Description"));
        editor.add(descriptionField);
    
        editor.add(new JLabel("Location"));
        editor.add(locationField);
    
        editor.add(new JLabel("Permanent"));
        editor.add(permanentBox);
    
        JButton addButton = new JButton("Add Task");
        JButton updateButton = new JButton("Update Task");
        JButton deleteButton = new JButton("Delete Task");
        JButton sortButton = new JButton("Sort");
    
        addButton.addActionListener(e -> addTask());
        updateButton.addActionListener(e -> updateSelectedTask());
        deleteButton.addActionListener(e -> deleteSelectedTask());
        sortButton.addActionListener(e -> sortTasks());
    
        editor.add(addButton);
        editor.add(updateButton);
        editor.add(deleteButton);
        editor.add(sortButton);
    
        add(editor, BorderLayout.CENTER);
    }

    private void initializePersistenceBar() {

        JPanel bottomPanel = new JPanel();
    
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        JButton clearButton = new JButton("Clear Non-Permanent");
    
        saveButton.addActionListener(e -> savePlanner());
        loadButton.addActionListener(e -> loadPlanner());
        clearButton.addActionListener(e -> clearTasks());
    
        bottomPanel.add(saveButton);
        bottomPanel.add(loadButton);
        bottomPanel.add(clearButton);
    
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: reads input fields and adds a new task to the planner;
    //          shows an error dialog if the day or duration is invalid
    private void addTask() {
        String name        = nameField.getText();
        String date        = dateField.getText();
        String description = descriptionField.getText();
        String location    = locationField.getText();

        int time;
        try {
            time = Integer.parseInt(timeField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Time must be a whole number.");
            return;
        }

        try {
            Task task = permanentBox.isSelected()
                    ? new PermTask(name, date, time, description, location)
                    : new NormalTask(name, date, time, description, location);
            planner.addTask(task);
            refreshList();
            clearInputFields();
        } catch (InvalidTaskDayException e) {
            JOptionPane.showMessageDialog(this, "Invalid day: " + e.getMessage());
        } catch (InvalidTaskDurationException e) {
            JOptionPane.showMessageDialog(this, "Invalid duration: " + e.getMessage());
        }
    
        planner.addTask(task);
        planner.sortTasksByDay();

        refreshList();
    
        clearInputFields();
    }

    private void clearInputFields() {
        nameField.setText("");
        dateField.setText("");
        timeField.setText("");
        descriptionField.setText("");
        locationField.setText("");
        permanentBox.setSelected(false);
        nameField.requestFocusInWindow();
    }

    private void clearTasks() {
        planner.clearTasks();
        refreshList();
    }

    private void savePlanner() {
        try {
            JsonWriter writer = new JsonWriter(JSON_STORE);
            writer.open();
            writer.write(planner);
            writer.close();
            JOptionPane.showMessageDialog(this, "Planner saved!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Save failed.");
        }
    }

    private void loadPlanner() {
        try {
            JsonReader reader = new JsonReader(JSON_STORE);
            planner = reader.read();
            refreshList();
            JOptionPane.showMessageDialog(this, "Planner loaded!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Load failed.");
        }
    }

    private void refreshList() {
        taskModel.clear();
        for (Task t : planner.getTasks()) {
            taskModel.addElement(t.display());
        }
    }

    private void populateFieldsFromSelection() {
        int index = taskList.getSelectedIndex();
        if (index == -1) {
            return;
        }
        Task task = planner.getTasks().get(index);
        nameField.setText(task.getName());
        dateField.setText(task.getDate());
        timeField.setText(String.valueOf(task.getTime()));
        descriptionField.setText(task.getDescription());
        locationField.setText(task.getLocation());
        permanentBox.setSelected(task instanceof PermTask);
    }

    // MODIFIES: this
    // EFFECTS: updates the selected task with the current field values;
    //          shows an error dialog if the day or duration is invalid
    private void updateSelectedTask() {
        int index = taskList.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Please select a task first.");
            return;
        }
    
        planner.getTasks().remove(index);
    
        refreshList();
        clearInputFields();
    }

    private void updateSelectedTask() {

        int time;
        try {
            time = Integer.parseInt(timeField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Time must be a whole number.");
            return;
        }

        Task task = planner.getTasks().get(index);

        try {
            task.setName(nameField.getText());
            task.setDate(dateField.getText());
            task.setTime(time);
            task.setDescription(descriptionField.getText());
            task.setLocation(locationField.getText());
        } catch (InvalidTaskDayException e) {
            JOptionPane.showMessageDialog(this, "Invalid day: " + e.getMessage());
            return;
        } catch (InvalidTaskDurationException e) {
            JOptionPane.showMessageDialog(this, "Invalid duration: " + e.getMessage());
            return;
        }

        // Swap type if permanence changed
        if (permanentBox.isSelected() && !(task instanceof PermTask)) {
            try {
                planner.getTasks().set(index, new PermTask(
                        task.getName(), task.getDate(), task.getTime(),
                        task.getDescription(), task.getLocation()));
            } catch (InvalidTaskDayException | InvalidTaskDurationException e) {
                JOptionPane.showMessageDialog(this, "Could not convert task: " + e.getMessage());
                return;
            }
        } else if (!permanentBox.isSelected() && (task instanceof PermTask)) {
            try {
                planner.getTasks().set(index, new NormalTask(
                        task.getName(), task.getDate(), task.getTime(),
                        task.getDescription(), task.getLocation()));
            } catch (InvalidTaskDayException | InvalidTaskDurationException e) {
                JOptionPane.showMessageDialog(this, "Could not convert task: " + e.getMessage());
                return;
            }
        }

        refreshList();
        clearInputFields();
    }

    private void sortTasks() {

        planner.sortTasksByDay();
        refreshList();
    }
}