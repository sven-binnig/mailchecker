/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.biware.mailchecker;

import de.biware.mailchecker.impl.SimpleMailCheckService;
import de.biware.mailchecker.ui.DefaultMailCheckPresenter;
import de.biware.mailchecker.ui.DefaultMailCheckView;
import de.biware.mailchecker.ui.MailCheckView;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author svenina
 */
public class JfxMailChecker extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane content = new DefaultMailCheckView(new DefaultMailCheckPresenter(new SimpleMailCheckService()));

        HBox top = new HBox(3);
        VBox center = new VBox(3, top, content);

        Button exit = new Button();
        GlyphsDude.setIcon(exit, FontAwesomeIcon.POWER_OFF);
        top.getChildren().add(exit);
        exit.setOnAction(ae -> System.exit(0));

        Scene scene = new Scene(center);
        scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");

        //grab your root here
        content.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        //move around here
        content.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
                //log("stage repositioning at " + xOffset + " x " + yOffset);
            }
        });

        primaryStage.setTitle("Mail Checker");
        primaryStage.setScene(scene);
        //primaryStage.sizeToScene();
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        this.positioningStageOnScreen(primaryStage);
        primaryStage.show();
        
        Platform.runLater(() -> ((MailCheckView)content).startupPeriodicMailChecking() );
    }

    private void log(String message) {
        System.out.println("[" + this.getClass().getSimpleName() + "] " + message);
    }

    private void positioningStageOnScreen(Stage primaryStage) {
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        //log("screen size " + primScreenBounds.getWidth() + " x " + primScreenBounds.getHeight());
        //log("staget size " + primaryStage.getWidth() + " x " + primaryStage.getHeight());
        primaryStage.setX(0);
        primaryStage.setY(primScreenBounds.getHeight() - 210);
        primaryStage.setWidth(600);
        primaryStage.setHeight(200);
    }

    public static void main(String... args) {
        launch(args);
    }

}
