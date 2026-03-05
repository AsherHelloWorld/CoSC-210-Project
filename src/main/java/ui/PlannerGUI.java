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

        initializeTaskList();
        taskList.addListSelectionListener(e -> populateFieldsFromSelection());
        initializeInputPanel();
        initializeButtons();

        setVisible(true);
    }

    private void initializeTaskList() {

        taskModel = new DefaultListModel<>();
        taskList = new JList<>(taskModel);

        JScrollPane scrollPane = new JScrollPane(taskList);

        add(scrollPane, BorderLayout.CENTER);
    }

    private void initializeInputPanel() {

        JPanel panel = new JPanel(new GridLayout(6,2));

        nameField = new JTextField();
        dateField = new JTextField();
        timeField = new JTextField();
        descriptionField = new JTextField();
        locationField = new JTextField();

        permanentBox = new JCheckBox();

        panel.add(new JLabel("Name"));
        panel.add(nameField);

        panel.add(new JLabel("Date"));
        panel.add(dateField);

        panel.add(new JLabel("Time"));
        panel.add(timeField);

        panel.add(new JLabel("Description"));
        panel.add(descriptionField);

        panel.add(new JLabel("Location"));
        panel.add(locationField);

        panel.add(new JLabel("Permanent"));
        panel.add(permanentBox);

        add(panel, BorderLayout.NORTH);
    }

    private void initializeButtons() {

        JPanel panel = new JPanel();

        JButton addButton = new JButton("Add Task");
        JButton clearButton = new JButton("Clear Non-Permanent");
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        JButton updateButton = new JButton("Update Task");
 


        addButton.addActionListener(e -> addTask());
        clearButton.addActionListener(e -> clearTasks());
        saveButton.addActionListener(e -> savePlanner());
        loadButton.addActionListener(e -> loadPlanner());
        updateButton.addActionListener(e -> updateSelectedTask());

        panel.add(addButton);
        panel.add(clearButton);
        panel.add(saveButton);
        panel.add(loadButton);
        panel.add(updateButton);

        add(panel, BorderLayout.SOUTH);
    }

    private void addTask() {

        String name = nameField.getText();
        String date = dateField.getText();
        int time = Integer.parseInt(timeField.getText());
        String description = descriptionField.getText();
        String location = locationField.getText();
    
        Task task;
    
        if (permanentBox.isSelected()) {
            task = new PermTask(name, date, time, description, location);
        } else {
            task = new NormalTask(name, date, time, description, location);
        }
    
        planner.addTask(task);
    
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
    
        // place cursor back in the first field
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

    private Task getSelectedTask() {

        int index = taskList.getSelectedIndex();
    
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Please select a task first.");
            return null;
        }
    
        return planner.getTasks().get(index);
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

    private void deleteSelectedTask() {

        int index = taskList.getSelectedIndex();
    
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Select a task first.");
            return;
        }
    
        planner.getTasks().remove(index);
    
        refreshList();
    }

    private void updateSelectedTask() {

        int index = taskList.getSelectedIndex();
    
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Please select a task first.");
            return;
        }
    
        Task task = planner.getTasks().get(index);
    
        // Update basic fields
        task.setName(nameField.getText());
        task.setDate(dateField.getText());
        task.setTime(Integer.parseInt(timeField.getText()));
        task.setDescription(descriptionField.getText());
        task.setLocation(locationField.getText());
    
        // Update permanence type if it changed
        if (permanentBox.isSelected() && !(task instanceof PermTask)) {
            planner.getTasks().set(index, new PermTask(
                task.getName(), task.getDate(), task.getTime(),
                task.getDescription(), task.getLocation()
            ));
        } else if (!permanentBox.isSelected() && (task instanceof PermTask)) {
            planner.getTasks().set(index, new NormalTask(
                task.getName(), task.getDate(), task.getTime(),
                task.getDescription(), task.getLocation()
            ));
        }
    
        refreshList();
        clearInputFields();
    }
}