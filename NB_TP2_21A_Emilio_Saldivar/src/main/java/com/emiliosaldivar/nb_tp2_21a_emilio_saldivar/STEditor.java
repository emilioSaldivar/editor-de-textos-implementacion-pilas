package com.emiliosaldivar.nb_tp2_21a_emilio_saldivar;

import com.emiliosaldivar.pilas.*;
/*
 * Java Program to create a text editor using java.
 * The original explanation and source code is available at: https://www.geeksforgeeks.org/java-swing-create-a-simple-text-editor/
 * Original author: Arnab Kunut (@andrew1234 https://auth.geeksforgeeks.org/user/andrew1234)
 *
 * Esta es una adaptacion para agregar la funcionalidad de "Deshacer"
 */
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import javax.swing.plaf.metal.*;

class STEditor extends JFrame implements ActionListener {
    // Text component

    JTextArea t;

    // Frame
    JFrame f;

    //File opened
    File fi;

    // Pila para controlar cambios del texto
    Pila<String> cambios = new Pila<>();

    // Constructor
    STEditor() {
        fi = null;

        // Create a frame
        f = new JFrame("STEditor");

        try {
            // Set metal look and feel
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

            // Set theme to ocean
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
        }

        // Text component
        t = new JTextArea();

        // Create a menubar
        JMenuBar mb = new JMenuBar();

        // Create amenu for menu
        JMenu m1 = new JMenu("Archivo");

        // Create menu items
        JMenuItem mi1 = new JMenuItem("Nuevo");
        JMenuItem mi2 = new JMenuItem("Abrir");
        JMenuItem mi3 = new JMenuItem("Grabar");
        JMenuItem mi9 = new JMenuItem("Imprimir");

        // Add action listener
        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mi9.addActionListener(this);

        m1.add(mi1);
        m1.add(mi2);
        m1.add(mi3);
        m1.add(mi9);

        // Create amenu for menu
        JMenu m2 = new JMenu("Editar");

        // Create menu items
        JMenuItem mi4 = new JMenuItem("Cortar");
        JMenuItem mi5 = new JMenuItem("Copiar");
        JMenuItem mi6 = new JMenuItem("Pegar");

        // Add action listener
        mi4.addActionListener(this);
        mi5.addActionListener(this);
        mi6.addActionListener(this);

        m2.add(mi4);
        m2.add(mi5);
        m2.add(mi6);

        JMenuItem mc = new JMenuItem("Cerrar");
        mc.addActionListener(this);

        JMenuItem md = new JMenuItem("Deshacer");
        md.addActionListener(this);

        mb.add(m1);
        mb.add(m2);
        mb.add(md);
        mb.add(mc);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setJMenuBar(mb);
        f.add(t);
        f.setSize(500, 500);
        f.show();
    }

    // If a button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        switch (s) {
            case "Cortar":
                apilarCambios();
                t.cut();
                break;
            case "Copiar":
                t.copy();
                break;
            case "Pegar":
                apilarCambios();
                t.paste();
                break;
            case "Grabar":
                if (fi == null) {
                    // Create an object of JFileChooser class
                    JFileChooser j = new JFileChooser("f:");

                    // Invoke the showsSaveDialog function to show the save dialog
                    int r = j.showSaveDialog(null);

                    if (r == JFileChooser.APPROVE_OPTION) {

                        // Set the label to the path of the selected directory
                        fi = new File(j.getSelectedFile().getAbsolutePath());
                        grabarArchivo();
                        apilarCambios();
                    } // If the user cancelled the operation
                    else {
                        JOptionPane.showMessageDialog(f, "Operacion cancelada");
                    }

                } else {
                    grabarArchivo();
                    apilarCambios();
                }
                break;
            case "Imprimir":
                    try {
                // print the file
                t.print();
            } catch (PrinterException evt) {
                JOptionPane.showMessageDialog(f, evt.getMessage());
            }
            break;

            case "Abrir":
                // Create an object of JFileChooser class
                JFileChooser j = new JFileChooser("f:");
                // Invoke the showsOpenDialog function to show the save dialog
                int r = j.showOpenDialog(null);
                // If the user selects a file
                if (r == JFileChooser.APPROVE_OPTION) {
                    // Set the label to the path of the selected directory
                    fi = new File(j.getSelectedFile().getAbsolutePath());

                    try {
                        // String
                        String s1 = "", sl = "";

                        // File reader
                        FileReader fr = new FileReader(fi);

                        // Buffered reader
                        BufferedReader br = new BufferedReader(fr);

                        // Initialize sl
                        sl = br.readLine();

                        // Take the input from the file
                        while ((s1 = br.readLine()) != null) {
                            sl = sl + "\n" + s1;
                        }

                        // Set the text
                        t.setText(sl);

                        vaciarPila();
                    } catch (IOException evt) {
                        JOptionPane.showMessageDialog(f, evt.getMessage());
                    }
                } // If the user cancelled the operation
                else {
                    JOptionPane.showMessageDialog(f, "Operacion cancelada");
                }
                break;
            case "Nuevo":
                fi = null;
                t.setText("");
                vaciarPila();
                break;
            case "Cerrar":
                f.setVisible(false);
                f.dispose();
                System.exit(0);
            case "Deshacer":
                desapilarCambios();
                break;
            default:
                break;
        }
    }

    private void grabarArchivo() {
        try {
            // Create a file writer
            FileWriter wr = new FileWriter(fi, false);

            // Write
            try ( // Create buffered writer to write
                    BufferedWriter w = new BufferedWriter(wr)) {
                // Write
                w.write(t.getText());

                w.flush();
            }
        } catch (IOException evt) {
            JOptionPane.showMessageDialog(f, evt.getMessage());
        }
    }

    private void vaciarPila() {
        //Vacia la pila auxiliar en caso de que no este vacia
        
        if( !this.cambios.esVacia() )
            
            this.cambios.anular();
        
    }

    private void apilarCambios() {
        //Almacenar el estado actual del texto
        try {
            this.cambios.apilar( this.t.getText() );//inta acceder a los datos del JTextArea
        } catch (Exception e) {
            
            System.out.println( e.getMessage() );
        }
        

        //JOptionPane.showMessageDialog(f, "Operacion pendiente de ser implementada: ApilarCambios");
    }

    private void desapilarCambios() {
        //Estado previo del texto almacenado en la Pila Auxiliar
        
        try {

            this.t.setText(this.cambios.desapilar());//intentar agregar contenido al texto

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

        //JOptionPane.showMessageDialog(f, "Operacion pendiente de ser implementada: DesapilarCambios");
    }

    // Main class
    public static void main(String args[]) {
        STEditor e = new STEditor();
    }
}
