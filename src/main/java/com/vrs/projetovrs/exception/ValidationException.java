package com.vrs.projetovrs.exception;

import javax.swing.*;

public class ValidationException extends RuntimeException {
    public ValidationException() {
        super("Campos inválidos");
        JOptionPane.showMessageDialog(null, "Campos inválidos");
    }
}
