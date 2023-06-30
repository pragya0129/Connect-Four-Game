package com.pragya.connect4;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Controller controller;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("game.fxml"));
        GridPane rootGridPane = fxmlLoader.load();
        controller = fxmlLoader.getController();
        controller.createPlayground();

        MenuBar menuBar = createMenu();
        menuBar.prefWidthProperty().bind(stage.widthProperty());

        Pane menuPane = (Pane) rootGridPane.getChildren().get(0);
        menuPane.getChildren().add(menuBar);

        Scene scene = new Scene(rootGridPane);

        stage.setScene(scene);
        stage.setTitle("Connect Four");
        stage.setResizable(false);
        stage.show();
    }

    private MenuBar createMenu() {
        Menu fileMenu = new Menu("File");
        MenuItem newGame = new MenuItem("New Game");
        newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                resetGame();
            }
        });
        MenuItem resetGame = new MenuItem("Reset Game");
        resetGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                resetGame();
            }
        });
        SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
        MenuItem exitGame = new MenuItem("Exit Game");
        exitGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                exitGame();
            }
        });
        fileMenu.getItems().addAll(newGame, resetGame, separatorMenuItem, exitGame);


        Menu helpMenu = new Menu("Help");
        MenuItem aboutGame = new MenuItem("About Connect4");
        aboutGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                aboutConnect4();
            }
        });
        SeparatorMenuItem separatorHelpMenuItem = new SeparatorMenuItem();
        MenuItem aboutMe = new MenuItem("About Developer");
        aboutMe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                aboutDev();
            }
        });
        helpMenu.getItems().addAll(aboutGame, separatorHelpMenuItem, aboutMe);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, helpMenu);

        return menuBar;
    }

    private void aboutDev() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About The Developer");
        alert.setHeaderText("Pragya Gaur");
        alert.setContentText("I Love to play around code and create games." +
                "Connect4 is one of them!");
        alert.show();
    }

    private void aboutConnect4() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About Connect Four");
        alert.setHeaderText("How To Play?");

// Create a Text object to hold the content text
        Text contentText = new Text("Connect Four is a two-player connection game in which the " +
                "players first choose a color and then take turns dropping colored discs " +
                "from the top into a seven-column, six-row vertically suspended grid. "+
                "The pieces fall straight down, occupying the next available space within the column. "+
                "The objective of the game is to be the first to form a horizontal, vertical, " +
                "or diagonal line of four of one's own discs. Connect Four is a solved game. " +
                "The first player can always win by playing the right moves.");
        contentText.setWrappingWidth(400); // Set the desired width for text wrapping

        alert.getDialogPane().setContent(contentText);

        alert.show();

    }

    private void exitGame() {
        Platform.exit();        //only close the application
        System.exit(0);     //shut down all the game resources (threads)
    }

    private void resetGame() {
        controller.resetGame();
    }

    public static void main(String[] args) {
        launch();
    }
}