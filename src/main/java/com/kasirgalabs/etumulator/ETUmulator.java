package com.kasirgalabs.etumulator;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.kasirgalabs.etumulator.menu.FileMenuController;
import com.kasirgalabs.etumulator.processor.GUISafeProcessor;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ETUmulator extends Application {
    @Inject
    private FileMenuController fileMenuController;
    @Inject
    private GUISafeProcessor processor;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Module module = new ETUmulatorModule();
        Injector injector = Guice.createInjector(module);

        primaryStage.setTitle("ETUmulator");
        ClassLoader classLoader = ETUmulator.class.getClassLoader();
        FXMLLoader fxmlLoader = new FXMLLoader(classLoader.getResource("fxml/ETUmulator.fxml"));
        fxmlLoader.setControllerFactory(injector::getInstance);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        injector.injectMembers(this);
        fileMenuController.setWindow(primaryStage.getOwner());

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                processor.stop();
            }
        });
        primaryStage.setOnCloseRequest((WindowEvent event) -> {
            int firstOne = fileMenuController.getLength();
            int lastOne = fileMenuController.document.getText().length();
            if (firstOne != lastOne && !fileMenuController.document.getTargetFile().getName().equals(
                    "untitled")) {
                Stage stage = new Stage();
                VBox box = new VBox();
                box.setPadding(new Insets(10));
                box.setAlignment(Pos.CENTER);
                Label label = new Label("Are you sure exit before saving?");
                Button btnSave = new Button();
                btnSave.setText("Save");
                Button btnExit = new Button();
                btnExit.setText("Exit");
                btnExit.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.exit(0);
                    }
                });
                btnSave.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            fileMenuController.document.saveDocument();
                            System.exit(0);
                        } catch (IOException ex) {
                            Logger.getLogger(ETUmulator.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                box.getChildren().add(label);
                box.getChildren().add(btnSave);
                box.getChildren().add(btnExit);
                Scene scene1 = new Scene(box, 250, 150);
                stage.setScene(scene1);
                stage.show();
                stage.show();
                event.consume();
            } else if (firstOne != lastOne && fileMenuController.document.getTargetFile().getName().equals(
                    "untitled")) {
                Stage stage = new Stage();
                VBox box = new VBox();
                box.setPadding(new Insets(10));
                box.setAlignment(Pos.CENTER);
                Label label = new Label("Are you sure exit before saving?");
                Button btnSave = new Button();
                btnSave.setText("Save As");
                Button btnExit = new Button();
                btnExit.setText("Exit");
                btnExit.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.exit(0);
                    }
                });
                btnSave.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        File file = fileMenuController.fileChooser.showSaveDialog(fileMenuController.window);
                        if (file == null) {
                            return;
                        }
                        fileMenuController.document.setTargetFile(file);
                        try {
                            fileMenuController.document.saveDocument();
                        } catch (IOException ex) {
                            Logger.getLogger(ETUmulator.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.exit(0);
                    }
                });
                box.getChildren().add(label);
                box.getChildren().add(btnSave);
                box.getChildren().add(btnExit);
                Scene scene1 = new Scene(box, 250, 150);
                stage.setScene(scene1);
                stage.show();
                stage.show();
                event.consume();
            } else {
                System.exit(0);
            }
        });
    }
}
