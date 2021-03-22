/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.biware.mailchecker;

import de.biware.mailchecker.api.MailAccountConfiguration;
import de.biware.mailchecker.impl.DefaultMailAccountConfiguration;
import de.biware.mailchecker.impl.SimpleMailCheckService;
import de.biware.mailchecker.ui.DefaultMailCheckPresenter;
import de.biware.mailchecker.ui.DefaultMailCheckView;
import de.biware.mailchecker.ui.MailCheckView;
import java.io.File;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author svenina
 */
public class JfxMailChecker extends Application {

    private double xOffset = 0;
    private double yOffset = 0;
    private MailAccountConfiguration config;

    @Override
    public void init() throws Exception {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        String filename = System.getProperty("user.dir") + "/mailaccounts.json";
        log("using " + filename + " as mail account configuration file");
        this.config = new DefaultMailAccountConfiguration(new File(filename));
    }
    
    

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane content = new DefaultMailCheckView(new DefaultMailCheckPresenter(new SimpleMailCheckService()), config);

        HBox top = new HBox(3);
        VBox center = new VBox(3, top, content);

        //Button exit = new Button();
        //GlyphsDude.setIcon(exit, FontAwesomeIcon.POWER_OFF);
        //top.getChildren().add(exit);
        //exit.setOnAction(ae -> System.exit(0));

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
        primaryStage.setY(primScreenBounds.getHeight() - 150);
        primaryStage.setWidth(300);
        primaryStage.setHeight(150);
    }

    public static void main(String... args) {
        launch(args);
    }

}
