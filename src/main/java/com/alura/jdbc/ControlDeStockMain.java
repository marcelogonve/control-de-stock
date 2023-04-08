package com.alura.jdbc;

import javax.swing.JFrame;

import com.alura.jdbc.view.ControlDeStockFrame;

public class ControlDeStockMain {
    public static void main(String[] args) {
        ControlDeStockFrame productoCategoriaFrame = new ControlDeStockFrame();
        productoCategoriaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
