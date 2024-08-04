
/**
 * Educator Login Page
 *
 * @Authors Zivkovic, F., Kurfurst, D., Novosel, N., Suric, G.
 * @version 03/04/2022
 */

import java.io.*;
import java.sql.*;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.*;

public class EducatorLogin extends Application implements EventHandler<ActionEvent> {

        // Textfields
        TextField tfUsername;

        // Passwordfield
        PasswordField password;

        // Buttons
        Button btnSubmit;
        Button btnSignin;

        // Font Styles
        Font fontLeft = Font.font("Helvetica", FontWeight.NORMAL, 13);
        Font fontLast = Font.font("Helvetica", FontWeight.NORMAL, 11);
        Font fontRight = Font.font("Helvetica", FontWeight.BOLD, 35);

        public static void main(String[] args) {
                launch(args);
        }

        @Override
        public void start(Stage _stage) throws Exception {

                _stage.setTitle("edu.hr");
                _stage.getIcons().add(new Image(
                                "https://upload.wikimedia.org/wikipedia/commons/thumb/3/35/Location_dot_blue.svg/1024px-Location_dot_blue.svg.png"));

                HBox root = new HBox(200);
                VBox leftPane = new VBox(10);
                VBox rightPane = new VBox(10);
                Scene scene = new Scene(root, 1600, 860);
                root.getChildren().addAll(leftPane, rightPane);
                root.setSpacing(300);
                leftPane.prefWidthProperty().bind(root.widthProperty().multiply(0.45));
                leftPane.setStyle(
                                "-fx-background-image: url('https://images.unsplash.com/photo-1620121692029-d088224ddc74?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2064&q=80'); -fx-background-repeat: no-repeat; -fx-background-size: cover; -fx-background-position: center center;");
                root.setStyle("-fx-background-color: white;");

                Image image = new Image(new FileInputStream("C:\\iste-330-group-project\\logo.png"));
                ImageView imageView = new ImageView(image);
                imageView.setX(150);
                imageView.setY(125);
                imageView.setFitHeight(100);
                imageView.setFitWidth(250);
                imageView.setPreserveRatio(true);

                // Left Pane
                Text educatorText = new Text("To enter the portal as a student please click the button below");
                educatorText.setFont(fontLeft);
                educatorText.setFill(Color.WHITE);
                btnSignin = new Button("SIGN IN");
                btnSignin.setStyle(
                                "-fx-text-fill: white; -fx-background-color: transparent; -fx-border-color: white; -fx-background-radius: 10; -fx-border-radius: 15;");
                btnSignin.setMinWidth(200);
                btnSignin.setPadding(new Insets(8));
                leftPane.getChildren().addAll(imageView, educatorText, btnSignin);
                leftPane.setAlignment(Pos.CENTER);
                leftPane.setSpacing(20);

                // Right Pane
                Text loginText = new Text("Login");
                loginText.setFont(fontRight);
                loginText.setFill(Color.rgb(27, 49, 197));
                tfUsername = new TextField();
                tfUsername.setPromptText("Username");
                tfUsername.setPrefWidth(300);
                tfUsername.setMaxWidth(300);
                password = new PasswordField();
                password.setPromptText("Password");
                btnSubmit = new Button("SUBMIT");
                btnSubmit.setStyle(
                                "-fx-text-fill: white; -fx-background-color: rgb(27, 49, 197); -fx-border-color: rgb(27, 49, 197); -fx-background-radius: 10; -fx-border-radius: 15;");
                btnSubmit.setMinWidth(200);
                btnSubmit.setPadding(new Insets(8));
                Text finalText = new Text(
                                "Contact your administrator to create an account \nor acquire your login credentials");
                finalText.setFont(fontLast);
                finalText.setFill(Color.GREY);
                finalText.setTextAlignment(TextAlignment.JUSTIFY);
                rightPane.getChildren().addAll(loginText, tfUsername, password, btnSubmit, finalText);
                rightPane.setAlignment(Pos.CENTER);
                rightPane.setSpacing(20);

                btnSubmit.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent e) {
                                btnSubmit.setStyle(
                                                "-fx-text-fill: rgb(27, 49, 197); -fx-background-color: white; -fx-border-color: rgb(27, 49, 197); -fx-background-radius: 10; -fx-border-radius: 15; -fx-cursor: hand;");
                        }
                });
                btnSubmit.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent e) {
                                btnSubmit.setStyle(
                                                "-fx-text-fill: white; -fx-background-color: rgb(27, 49, 197); -fx-border-color: rgb(27, 49, 197); -fx-background-radius: 10; -fx-border-radius: 15; -fx-cursor: hand;");
                        }
                });
                btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                                String username = tfUsername.getText();
                                String _password = password.getText();

                                if (username.equals("")) {
                                        Alert alert = new Alert(AlertType.INFORMATION);
                                        alert.setTitle("Error");
                                        alert.setHeaderText("Login Error");
                                        alert.setContentText("Please enter username");
                                        alert.showAndWait();
                                } else if (_password.equals("")) {
                                        Alert newAlert = new Alert(AlertType.INFORMATION);
                                        newAlert.setTitle("Error");
                                        newAlert.setHeaderText("Login Error");
                                        newAlert.setContentText("Please enter password");
                                        newAlert.showAndWait();
                                } else {
                                        SQLDatabase mysql = new SQLDatabase();
                                        mysql.connection();
                                        try {
                                                Statement stmt = mysql.connection().createStatement();
                                                stmt.executeUpdate("USE library");
                                                String st = ("SELECT * FROM USERS WHERE Username = '" + username
                                                                + "' AND UserPassword = '" + _password + "'");
                                                ResultSet rs = stmt.executeQuery(st);

                                                if (rs.next() == false) {
                                                        Alert errorAlert = new Alert(AlertType.ERROR);
                                                        errorAlert.setTitle("Error");
                                                        errorAlert.setHeaderText("Login Error");
                                                        errorAlert.setContentText("Incorrect username and/or password");
                                                        errorAlert.showAndWait();
                                                } else {
                                                        _stage.close();
                                                        rs.beforeFirst();
                                                }
                                        } catch (Exception ex) {
                                                ex.printStackTrace();
                                        }
                                }
                        }
                });
                btnSignin.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent e) {
                                btnSignin.setStyle(
                                                "-fx-text-fill: rgb(27, 49, 197); -fx-background-color: white; -fx-border-color: white; -fx-background-radius: 10; -fx-border-radius: 15; -fx-cursor: hand;");
                        }
                });
                btnSignin.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent e) {
                                btnSignin.setStyle(
                                                "-fx-text-fill: white; -fx-background-color: transparent; -fx-border-color: white; -fx-background-radius: 10; -fx-border-radius: 15; -fx-cursor: hand;");
                        }
                });
                btnSignin.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                                EducatorLogin eduLogin = new EducatorLogin();
                                try {
                                        eduLogin.start(_stage);
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                        }
                });

                _stage.setScene(scene);
                _stage.show();

        }

        public void handle(ActionEvent ie) {
        }

}
