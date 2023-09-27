package com.vrs.projetovrs.exception;

import javax.swing.*;

public class ValidationException extends RuntimeException {
    public ValidationException(String campo) {
        super("Campos inválidos");
        JOptionPane.showMessageDialog(null, "Campos inválidos");
    }
}
