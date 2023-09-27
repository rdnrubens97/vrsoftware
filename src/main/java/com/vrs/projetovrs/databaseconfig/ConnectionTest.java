package com.vrs.projetovrs.databaseconfig;

import javax.swing.*;

public class ConnectionTest {
    public static void main(String[] args) {
        try {
            new ConnectionFactory().verificarEstruturaBanco();
            JOptionPane.showMessageDialog(null, "Conexão com o banco realizada com sucesso");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao se conectar com o banco: " + e);
        }
    }
}
