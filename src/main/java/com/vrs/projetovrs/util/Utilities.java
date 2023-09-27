package com.vrs.projetovrs.util;

import javax.swing.*;
import java.awt.*;

public class Utilities {

    /**
     * método utilitário que recebe um painel e seta todos os seus campos como vazio
     * @param container
     */
    public void limpaCampos(JPanel container) {
        Component components[] = container.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText("");
            }
        }
    }

}
