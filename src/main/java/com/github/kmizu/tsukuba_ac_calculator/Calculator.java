package com.github.kmizu.tsukuba_ac_calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class Calculator extends JFrame {
    private JTextField inputField;
    private JTextField outputField;
    private Splitter splitter;
    private JButton calculateButton;
    private String[] array;
    private Expression node;
    public Calculator() {
        super("数式計算機");
        this.setLayout(new FlowLayout());
        this.setBackground(Color.PINK);
        this.inputField = new JTextField(20);
        this.outputField = new JTextField(20);
        this.splitter = new Splitter();
        this.array = null;
        this.calculateButton = new JButton("calculate");
        this.calculateButton.addActionListener((ActionEvent evt) -> {
            array = splitter.split(inputField.getText());
            node = Expression.makeNode(array, 0, array.length - 1);
            outputField.setText(Integer.toString(node.evaluate()));
        });
        this.getContentPane().add(inputField);
        this.getContentPane().add(outputField);
        this.getContentPane().add(calculateButton);
    }
    public static void main( String[] args ) throws Exception {
        if(false) {
            // コマンドライン上で計算するとき
            DataInputStream ds = new DataInputStream(new BufferedInputStream(System.in));
            String line;
            Splitter spl = new Splitter();
            String[] arr = null;
            Expression node = null;
            while((line = ds.readLine()).length() > 0) {
                try {
                    arr = spl.split(line);
                    System.out.println(Arrays.asList(arr));
                    node = Expression.makeNode(arr, 0, arr.length - 1);
                    System.out.println(node.evaluate());
                } catch(Exception e) {
                    e.printStackTrace();
                    System.out.println("Error!");
                }
            }
        } else {
            // GUI
            var calculator = new Calculator();
            calculator.pack();
            calculator.setVisible(true);
        }
    }
}
