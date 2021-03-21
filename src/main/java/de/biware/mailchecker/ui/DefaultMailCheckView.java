/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.biware.mailchecker.ui;

import de.biware.mailchecker.api.MailAccount;
import de.biware.mailchecker.api.MailCheckerException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/**
 *
 * @author svenina
 */
public class DefaultMailCheckView extends BorderPane implements MailCheckView {

    private static final MailAccount[] M_ACCOUNTS = {
        new MailAccount("Corina Dörnenburg (posteo)", "corina.doernenburg@posteo.de", "Kacs6670", "posteo.de", 993),
        new MailAccount("Sven Binnig (posteo)", "sven.binnig@posteo.de", "Kacs6670", "posteo.de", 993),
        new MailAccount("Corina Dörnenburg (ekiba)", "corina.doernenburg@kbz.ekiba.de", "C10d_08oe!", "outlook.office.de", 993)
    };
    private GridPane gpUnreadMails;
    private final MailCheckPresenter presenter;
    private Label message;

    public DefaultMailCheckView(MailCheckPresenter presenter) {
        super();
        this.presenter = presenter;
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
        initiateMailChecking();
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.seconds(60),
                ae -> initiateMailChecking()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void initiateMailChecking() {
        Arrays.asList(M_ACCOUNTS).forEach(account -> {
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
        AtomicInteger row = new AtomicInteger(0);
        Arrays.asList(M_ACCOUNTS).forEach(a -> {
            if (a.getName().equals(account.getName())) {
                this.gpUnreadMails.add(new Label(account.getName()), 1, row.get());
                String style = unreadMessages > 0 ? "lbl-danger" : "lbl-success";
                this.gpUnreadMails.add(new StyledLabel("" + unreadMessages, "lbl", style), 0, row.get());
            }
            row.incrementAndGet();
        });
    }

    @Override
    public void onMailCheckerException(MailCheckerException ex) {
        ex.printStackTrace();
        this.message.setText(ex.getMessage());
    }
}
