package com.mustache.gui;

import com.mustache.main.Controller;
import com.mustache.translate.Translate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainMenu {

    private Translate translate;
    private Controller controller;
    private Container contentPane;
    private JPanel panelButtons = new JPanel(new GridLayout(4,1));

    private JButton buttonSingleplayer;
    private JButton buttonMultiplayer;
    private JButton buttonOptions;
    private JButton buttonExit;

    public MainMenu(Controller con) {
        contentPane = con.getWindow().getContentPane();
        translate = new Translate("english", con.getLanguage());
        controller = con;
        setupMainMenu();
        contentPane.revalidate();
        con.setWindow(contentPane);
    }

    private void setupMainMenu() {
        buttonSingleplayer = new JButton(translate.translated("Singleplayer"));
        buttonMultiplayer = new JButton(translate.translated("Multiplayer"));
        setupGameStartListener();
        buttonOptions = new JButton(translate.translated("Options"));
        buttonExit = new JButton(translate.translated("Exit"));
        setupExitButton();
        panelButtons.add(buttonSingleplayer);
        panelButtons.add(buttonMultiplayer);
        panelButtons.add(buttonOptions);
        panelButtons.add(buttonExit);
        panelButtons.setBounds(300, 300, 400, 100);
        contentPane.add(panelButtons);
    }

    private void setupGameStartListener() {
        for(ActionListener act : buttonSingleplayer.getActionListeners()) {
            buttonSingleplayer.removeActionListener(act);
        }
        for(ActionListener act : buttonMultiplayer.getActionListeners()) {
            buttonMultiplayer.removeActionListener(act);
        }
        buttonSingleplayer.addActionListener(e -> {
                controller.startGame(false);
        });
        buttonMultiplayer.addActionListener(e -> {
                controller.startGame(true);
        });
    }

    private void setupExitButton() {
        for (ActionListener act : buttonExit.getActionListeners()) {
            buttonExit.removeActionListener(act);
        }
        buttonExit.addActionListener(e -> controller.getWindow().dispose());
    }

}
