package com.example.ut4_practica;

import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JDialog implements Runnable{
    private static final long serialVersionUID = 1L;
    private JProgressBar barraProgreso;

    public SplashScreen() {
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        //Creo una etiqueta con la imagen en el centro
        JLabel lblImagen = new JLabel();
        //Indico la imagen que quiero mostrar en la label
        ImageIcon iconSplash = new ImageIcon(SplashScreen.class.getResource("/GUI/pajaro.gif"));

        setSize(iconSplash.getIconWidth(),iconSplash.getIconHeight()+20);

        lblImagen.setIcon(iconSplash);

        contentPane.add(lblImagen, BorderLayout.CENTER);

        //Creo un panel al sur con una barra de carga y una label para el autor
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new GridLayout(4, 1, 0, 0));
        barraProgreso = new JProgressBar();
        //Muestra el % de carga
        barraProgreso.setStringPainted(true);
        panelInferior.add(barraProgreso);

        JLabel lblTexto = new JLabel("Splash Screen 2024");
        lblTexto.setForeground(Color.BLUE);
        lblTexto.setHorizontalAlignment(SwingConstants.CENTER);
        panelInferior.add(lblTexto);

        //Añado el panel inferior al principal
        contentPane.add(panelInferior, BorderLayout.SOUTH);

        setResizable(false); //Impedir redimensionar la ventana
        setUndecorated(true); //Eliminar la barra de título y sus botones
        setLocationRelativeTo(null); //Mostrar en el centro
        setVisible(true);
    }

    //Metodo implementado por Runnable
    @Override
    public void run() {
        try {
            //Cada 20ms avanzamos la barra de progreso 0-100
            for(int i = 0; i < 100; i++) {
                Thread.sleep(20);
                barraProgreso.setValue(i);
            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        //Al terminar la espera cierro el JDialog
        dispose();
    }
}