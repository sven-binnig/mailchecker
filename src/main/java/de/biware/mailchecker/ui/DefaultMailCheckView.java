/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.biware.mailchecker.ui;

import de.biware.mailchecker.api.MailAccount;
import de.biware.mailchecker.api.MailAccountConfiguration;
import de.biware.mailchecker.api.MailCheckerException;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/**
 *
 * @author svenina
 */
public class DefaultMailCheckView extends BorderPane implements MailCheckView {
    private GridPane gpUnreadMails;
    private final MailCheckPresenter presenter;
    private final MailAccountConfiguration config;
    private Label message;

    public DefaultMailCheckView(MailCheckPresenter presenter, MailAccountConfiguration config) {
        super();
        this.presenter = presenter;
        this.config = config;
        this.presenter.registerMailCheckView(this);
        initUI();
    }

    private void initUI() {
        createComponents();
        layoutComponents();
        installActionListeners();
    }

    private void createComponents() {
        this.gpUnreadMails = new GridPane();
        this.gpUnreadMails.setVgap(5);
        this.gpUnreadMails.setHgap(15);
        this.message = new Label();
        //this.message.getStyleClass().addAll("lbl", "lbl-primary");
    }

    private void layoutComponents() {
        this.setCenter(this.gpUnreadMails);
        this.setBottom(this.message);
    }

    private void installActionListeners() {
        //Platform.runLater(() -> startupPeriodicMailChecking() );

    }

    @Override
    public void startupPeriodicMailChecking() {
        try {
            initiateMailChecking();
            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.seconds(60),
                    ae -> {
                try {
                    initiateMailChecking();
                } catch (IOException ex) {
                    onMailCheckerException(new MailCheckerException(ex));
                }
            }));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
        } catch (IOException ex) {
            onMailCheckerException(new MailCheckerException(ex));
        }
    }

    private void initiateMailChecking() throws IOException {
        //Arrays.asList(M_ACCOUNTS).forEach(account -> {
        this.config.getKnownMailAccounts().forEach(account -> {
            log("check account " + account);
            this.presenter.performMailCheck(account);
            log(" ");
        });
    }

    private void log(String message) {
        System.out.println("[" + this.getClass().getSimpleName() + "] " + message);
        //Platform.runLater(() -> this.message.setText(message));
        this.message.setText(message);
    }

    @Override
    public void onMailCheckPerformed(MailAccount account, int unreadMessages) {
        try {
            AtomicInteger row = new AtomicInteger(0);
            //Arrays.asList(M_ACCOUNTS).forEach(a -> {
            this.config.getKnownMailAccounts().forEach(a -> {
                if (a.getName().equals(account.getName())) {
                    this.gpUnreadMails.add(new Label(account.getName()), 1, row.get());
                    String style = unreadMessages > 0 ? "lbl-danger" : "lbl-success";
                    this.gpUnreadMails.add(new StyledLabel("" + unreadMessages, "lbl", style), 0, row.get());
                }
                row.incrementAndGet();
            });
        } catch (IOException ex) {
            onMailCheckerException(new MailCheckerException(ex));
        }
    }

    @Override
    public void onMailCheckerException(MailCheckerException ex) {
        ex.printStackTrace();
        this.message.setText(ex.getMessage());
    }
}
