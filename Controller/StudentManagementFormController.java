package Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.Student;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentManagementFormController {

    public TextField txtname;
    public TextField txtid;
    public TextField txtemail;
    public TextField txtcontact;
    public TextField txtaddress;
    public TextField txtnic;


    public void submitOnAction(ActionEvent actionEvent) {
        Student s = new Student(
                txtid.getText(), txtname.getText(), txtemail.getText(), txtcontact.getText(), txtaddress.getText(), txtnic.getText()
        );

        try {
            if (CrudUtil.execute("INSERT INTO student VALUES(?,?,?,?,?,?)", s.getId(), s.getName(), s.getEmail(), s.getContact(), s.getAddress(), s.getNic())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Successfully saved").show();
            }


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }
    }

    public void UpdateOnAction(ActionEvent actionEvent) {
        Student s = new Student(
                txtid.getText(), txtname.getText(), txtemail.getText(), txtcontact.getText(), txtaddress.getText(), txtnic.getText()
        );


        try {
            boolean isUpdated = CrudUtil.execute("UPDATE student SET student_name=?,email=?,contact=?,address=?,nic=? WHERE student_id=?",s.getName(),s.getEmail(),s.getContact(),s.getAddress(),s.getNic(),s.getId());

            if(isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"Successfully Updated").show();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Try again").show();
            }


        } catch (SQLException |ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void DeleteOnAction(ActionEvent actionEvent) {
        Student s = new Student(
                txtid.getText(), txtname.getText(), txtemail.getText(), txtcontact.getText(), txtaddress.getText(), txtnic.getText()
        );


        try {
            boolean isDeleted = CrudUtil.execute("DELETE FROM student WHERE student_id=?", txtid.getText());

            if ( isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION,"Successfully Deleted").show();
            }

            else {
                new Alert(Alert.AlertType.ERROR,"try again").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void SearchOnAction(ActionEvent actionEvent) {
        try {
            search();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }
    }


    public void ClearOnAction(ActionEvent actionEvent) {
        txtid.clear();
        txtname.clear();
        txtemail.clear();
        txtaddress.clear();
        txtnic.clear();
        txtcontact.clear();
    }

    public void search() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM student WHERE student_id=?", txtid.getText());
        if (result.next()) {
            txtname.setText(result.getString(2));
            txtemail.setText(result.getString(3));
            txtcontact.setText(result.getString(4));
            txtaddress.setText(result.getString(4));
            txtnic.setText(result.getString(5));

        }
    }

    public void txtidOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM student WHERE student_id=?", txtid.getText());
        if (result.next()) {
            txtname.setText(result.getString(2));
            txtemail.setText(result.getString(3));
            txtcontact.setText(result.getString(4));
            txtaddress.setText(result.getString(4));
            txtnic.setText(result.getString(5));

        }
    }
}
