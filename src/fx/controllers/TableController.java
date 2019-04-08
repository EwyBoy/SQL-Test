package fx.controllers;

import database.DatabaseManager;
import fx.models.ModelTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Main;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class TableController implements Initializable {

    @FXML
    private TableView<ModelTable> table;

    @FXML
    private TableColumn<ModelTable, String> col_kurskode;

    @FXML
    private TableColumn<ModelTable, String> col_karakter;

    @FXML
    private TableColumn<ModelTable, String> col_year;

    @FXML
    private TableColumn<ModelTable, String> col_student;

    private ObservableList<ModelTable> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Connection connection = DatabaseManager.connect(Main.database);
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM main.Karakter");
            while (rs.next()) {
                oblist.add(
                        new ModelTable(
                                rs.getString("kurskode"),
                                rs.getString("karakter"),
                                rs.getString("year"),
                                rs.getString("student")
                        )
                );
            }
        } catch (SQLException e) {
            System.out.println(TableController.class.getName());
        }

        col_kurskode.setCellValueFactory(new PropertyValueFactory<>("kurskode"));
        col_karakter.setCellValueFactory(new PropertyValueFactory<>("karakter"));
        col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        col_student.setCellValueFactory(new PropertyValueFactory<>("student"));

        table.setItems(oblist);
    }
}
