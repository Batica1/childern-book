import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.*;
import java.io.FileInputStream;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

/**
 * Dashboard Page
 *
 * @Authors Zivkovic, F., Kurfurst, D., Novosel, N., Suric, G.
 * @version 20/04/2022
 */

public class Dashboard extends Application implements EventHandler<ActionEvent> {

    DataLayer db = new DataLayer();

    Font fontTitle = Font.font("Helvetica", FontWeight.NORMAL, 18);
    Font fontButton = Font.font("Helvetica", FontWeight.NORMAL, 12);
    Font fontAccount = Font.font("Helvetica", FontWeight.NORMAL, 35);
    Font fontSubtitle = Font.font("Helvetica", FontWeight.NORMAL, 20);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("edu.hr");
        stage.getIcons().add(new Image(
                "https://upload.wikimedia.org/wikipedia/commons/thumb/3/35/Location_dot_blue.svg/1024px-Location_dot_blue.svg.png"));

        HBox root = new HBox(50);
        VBox leftpane = new VBox(10);
        root.setSpacing(100);

        /** --- Left Pane --- */
        leftpane.prefWidthProperty().bind(root.widthProperty().multiply(0.2));
        leftpane.setStyle(
                "-fx-background-color: rgb(248,248,248); -fx-background-repeat: no-repeat; -fx-background-size: cover; -fx-background-position: center center;");
        root.setStyle("-fx-background-color: white;");

        // Logo
        Image image = new Image(new FileInputStream("C:\\iste-330-group-project\\logo_dash.png"));
        ImageView imageView = new ImageView(image);
        imageView.setX(150);
        imageView.setY(125);
        imageView.setFitHeight(300);
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);

        Image imgCircle = new Image(new FileInputStream("C:\\iste-330-group-project\\circle.png"));
        ImageView imageCircle = new ImageView(imgCircle);
        imageCircle.setX(150);
        imageCircle.setY(125);
        imageCircle.setFitHeight(300);
        imageCircle.setFitWidth(300);
        imageCircle.setPreserveRatio(true);

        Text account = new Text("\tAccount");
        account.setFont(fontTitle);

        Button btnBookshelf = new Button("\tMy Bookshelf");
        Button btnProfile = new Button("\tProfile");
        Button btnCourses = new Button("\tMy Courses");
        btnBookshelf.setStyle(
                "-fx-text-fill: rgb(6, 70, 166); -fx-background-color: rgb(235, 238, 242); -fx-background-radius: 10; -fx-border-radius: 15; -fx-cursor: hand;");
        btnBookshelf.setMinWidth(320);
        btnBookshelf.setPadding(new Insets(15));
        btnBookshelf.setAlignment(Pos.BASELINE_LEFT);
        btnBookshelf.setFont(fontButton);

        btnProfile.setStyle(
                "-fx-text-fill: rgb(6, 70, 166); -fx-background-color: transparent; -fx-background-radius: 10; -fx-border-radius: 15; -fx-cursor: hand;");
        btnProfile.setMinWidth(320);
        btnProfile.setPadding(new Insets(15));
        btnProfile.setAlignment(Pos.BASELINE_LEFT);

        btnCourses.setStyle(
                "-fx-text-fill: rgb(6, 70, 166); -fx-background-color: transparent; -fx-background-radius: 10; -fx-border-radius: 15; -fx-cursor: hand;");
        btnCourses.setMinWidth(320);
        btnCourses.setPadding(new Insets(15));
        btnCourses.setAlignment(Pos.BASELINE_LEFT);

        leftpane.getChildren().addAll(imageCircle, imageView, account, btnBookshelf, btnCourses, btnProfile);

        /** --- Right Pane --- */
        VBox rightpane = new VBox(10);
        Text hello = new Text("\nHello,");
        Text subtitle = new Text("It looks like a slow day.");
        hello.setFont(fontAccount);
        subtitle.setFont(fontSubtitle);
        subtitle.setStyle("-fx-text-fill: grey");

        // Search Bar
        HBox searchbar = new HBox(8);
        TextField tfSearchBooks = new TextField();
        tfSearchBooks.setPromptText("Search for a book");
        tfSearchBooks.setPrefWidth(600);
        tfSearchBooks.setMaxWidth(600);
        tfSearchBooks.setPadding(new Insets(10));
        Button btnSearch = new Button("Search");
        btnSearch.setStyle(
                "-fx-text-fill: rgb(6, 70, 166); -fx-background-color: rgb(235, 238, 242); -fx-background-radius: 10; -fx-border-radius: 15; -fx-cursor: hand;");
        btnSearch.setMinWidth(100);
        btnSearch.setAlignment(Pos.CENTER);
        btnSearch.setPadding(new Insets(10));
        btnSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                ArrayList<Book> booksFound = db.searchBook(tfSearchBooks.getText());
                String out = "";
                for (Book b : booksFound) {
                    out += b.toString();
                }
                TextArea list = new TextArea();
                list.setText("Books found: \n" + out);
                rightpane.getChildren().add(list);

            }
        });

        searchbar.getChildren().addAll(tfSearchBooks, btnSearch);
        searchbar.setAlignment(Pos.CENTER);
        rightpane.getChildren().addAll(hello, subtitle, searchbar);
        rightpane.setSpacing(20);

        root.getChildren().addAll(leftpane, rightpane);
        Scene scene = new Scene(root, 1600, 860);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void handle(ActionEvent event) {

    }
}
