package com.mustache.gui;

import com.mustache.main.Controller;
import com.mustache.translate.Translate;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainMenu {

    private final Translate translate = new Translate("english", "german");

    private final JPanel contentPane = new JPanel(null);
    private final JPanel panelButtons = new JPanel(new GridLayout(4,1));

    private final JButton singleplayer = new JButton(translate.translated("Singleplayer"));
    private final JButton multiplayer = new JButton(translate.translated("Multiplayer"));
    private final JButton options = new JButton(translate.translated("Options"));
    private final JButton exit = new JButton(translate.translated("Exit"));

    private final JButton changeLanguage = new JButton("German");

    public MainMenu(Controller con) throws IOException {
        setupMainMenu();
        con.setWindow(contentPane);
    }

    private void setupMainMenu() {
        panelButtons.add(singleplayer);
        panelButtons.add(multiplayer);
        panelButtons.add(options);
        panelButtons.add(exit);
        panelButtons.setBounds(400, 200, 200, 100);
        changeLanguage.setBounds(970, 490, 30, 10);
        contentPane.add(panelButtons);
        contentPane.add(changeLanguage);
    }

}
