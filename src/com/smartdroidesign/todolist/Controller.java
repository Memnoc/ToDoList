package com.smartdroidesign.todolist;

import com.smartdroidesign.todolist.datamodel.TodoItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;


import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private List<TodoItem> todoItems;

    @FXML
    private TextArea itemDetailsTextArea;

    @FXML
    private ListView<TodoItem> todoListView;
    @FXML
    private Label deadLineLabel;

    public void initialize() {
        TodoItem item1 = new TodoItem("Mail Birthday Card", "Buy a 30th birthday card for John",
                LocalDate.of(2018, Month.AUGUST, 25));
        TodoItem item2 = new TodoItem("Doctor's appointment", "See Dr. Nowazardan at Houston, TX",
                LocalDate.of(2018, Month.OCTOBER, 23));
        TodoItem item3 = new TodoItem("Finish design proposal for client", "Bring the project templates wit you",
                LocalDate.of(2018, Month.NOVEMBER, 1));
        TodoItem item4 = new TodoItem("Go buy some furniture at IKEA", "You need a desk",
                LocalDate.of(2018, Month.MARCH, 7));
        TodoItem item5 = new TodoItem("Last lesson of the coding course", "Build a cool app",
                LocalDate.of(2018, Month.SEPTEMBER, 7));

        todoItems = new ArrayList<>();
        todoItems.add(item1);
        todoItems.add(item2);
        todoItems.add(item3);
        todoItems.add(item4);
        todoItems.add(item5);

        todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
            @Override
            public void changed(ObservableValue<? extends TodoItem> observable, TodoItem oldValue, TodoItem newValue) {
                if(newValue != null) {
                    TodoItem item = todoListView.getSelectionModel().getSelectedItem();
                    itemDetailsTextArea.setText(item.getDetails());
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d, yyyy"); // "d M yy");
                    deadLineLabel.setText(df.format(item.getDeadLine()));
                }
            }
        });

        todoListView.getItems().setAll(todoItems); // adding items to the view
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();
    }

    @FXML
    public void handleClickListView() {
        TodoItem item = todoListView.getSelectionModel().getSelectedItem();
        itemDetailsTextArea.setText(item.getDetails());
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        deadLineLabel.setText(df.format(item.getDeadLine()));
//        System.out.println("A selected item is " + item);
//        StringBuilder sb = new StringBuilder(item.getDetails());
//        sb.append("\n\n\n\n");
//        sb.append("Due: ");
//        sb.append(item.getDeadLine().toString());
//        itemDetailsTextArea.setText(sb.toString());


    }
}
