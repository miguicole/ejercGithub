import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.*;

import java.io.*;
/**
 *Telefono.
 * 
 * @author Miguel Cenzano Martinez 
 * @version 1.0
 */
public class TelefonoLoco2 extends JFrame
{
    // Constantes
    private static final int ESPACIO = 10;
    private static final String MENSAJE_LLAMADA = "LLAMANDO!!";
    private static final String NUMFIJO = "638230289";
    // instance variables 
    private JFrame telefono;//es la ventana  que simula el telefono
    private JTextField display;
    private String numeroMarcado;
    private Random generador;

    /**
     * Constructor de la clase Telefono
     */
    public TelefonoLoco2()
    {
        // initialise instance variables
        construirTelefono();//construimos el telefono (metodo por excelencia)
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();//coge las dimensiones de la pantalla
        telefono.setLocation(d.width/2 - telefono.getWidth()/2, d.height/2 - telefono.getHeight()/2);//coloca el telefono en el medio de la pantalla (diviendo entre 2 su altura y su anchura)
        generador = new Random();
    }

    private void construirTelefono()
    {
        telefono = new JFrame("Telefono");//lo que va a ser el marco del telefono
        JPanel contenidoTelefono = (JPanel) telefono.getContentPane();//contenedor donde meteremos todos los componentes(los botones...)
        contenidoTelefono.setBorder(new EmptyBorder(ESPACIO, ESPACIO, ESPACIO, ESPACIO));
        contenidoTelefono.setLayout(new BorderLayout(ESPACIO, ESPACIO));

        display = new JTextField();//inicializamos el display
        contenidoTelefono.add(display, BorderLayout.NORTH);//situa el display al norte
        JPanel panelBotones = new JPanel(new GridLayout(5, 3, ESPACIO,ESPACIO));//creamos el panel de botones con el formato 5x3(5 por alto y 3 por ancho)

        //----Agrego los botones-----
        agregarBotonNum(panelBotones, "1");
        agregarBotonNum(panelBotones, "2");
        agregarBotonNum(panelBotones, "3");

        agregarBotonNum(panelBotones, "4");
        agregarBotonNum(panelBotones, "5");
        agregarBotonNum(panelBotones, "6");

        agregarBotonNum(panelBotones, "7");
        agregarBotonNum(panelBotones, "8");
        agregarBotonNum(panelBotones, "9");

        agregarBotonBorrar(panelBotones, "B");
        agregarBotonNum(panelBotones, "0");
        agregarBotonLimpiar(panelBotones, "L");

        agregarBotonRepetir(panelBotones, "R");
        agregarBotonLlamar(panelBotones, "LL");
        agregarBotonColgar(panelBotones, "C");

        contenidoTelefono.add(panelBotones, BorderLayout.CENTER);//metemos el contenedor de los botones al contenedor principal al centro

        telefono.setVisible(true);//con esto vemos el marco del telefono
        telefono.pack();//empaqueta todos los componentes del contenedor
    }

    /**
     * metodo para agregar los botones
     * @param Jpanel panel donde lo queremos situar los botones
     * @param String textoBoton el valor del boton
     */
    private void agregarBotonNum(Container panel, String textoBoton)
    {
        JButton boton = new JButton(textoBoton);
        panel.add(boton);
        boton.setToolTipText("marca el " + textoBoton);
        boton.addActionListener(new ActionListener() {
                public void actionPerformed (ActionEvent e){
                    String numTelefono = display.getText();
                    if(!numTelefono.equals(MENSAJE_LLAMADA)){                    
                        int numDigitos = numTelefono.length();
                        char digitoFijo = hallarDigitoFijo(numDigitos);
                        display.setText(numTelefono + digitoFijo);
                        //char digitoFijo = String.valueOf(numDigitos).charAt(e.getActionCommand());
                    }
                }
            });

    }

    private void agregarBotonBorrar(Container panel, String textoBoton)
    {
        JButton boton = new JButton(textoBoton);
        panel.add(boton);
        boton.setToolTipText("borra el último digito");
        boton.addActionListener(new ActionListener() {
                public void actionPerformed (ActionEvent e) {
                    String numTelefono = display.getText();
                    if(!numTelefono.equals(MENSAJE_LLAMADA) && numTelefono.length() != 0){
                        display.setText(numTelefono.substring(0,numTelefono.length()-1));
                    }
                }
            });
    }

    private void agregarBotonLimpiar(Container panel, String textoBoton)
    {
        JButton boton = new JButton(textoBoton);
        panel.add(boton);
        boton.setToolTipText("borra todos los numeros");
        boton.addActionListener(new ActionListener() {
                public void actionPerformed (ActionEvent e) {
                    String numTelefono = display.getText();
                    display.setText("");
                }
            });
    }

    private void agregarBotonLlamar(Container panel, String textoBoton)
    {
        JButton boton = new JButton(textoBoton);
        panel.add(boton);
        boton.setToolTipText("llama al numero");
        boton.addActionListener(new ActionListener() {
                public void actionPerformed (ActionEvent e) {
                    numeroMarcado = display.getText();
                    display.setText(MENSAJE_LLAMADA);
                }
            });
    }

    private void agregarBotonRepetir(Container panel, String textoBoton)
    {
        JButton boton = new JButton(textoBoton);
        panel.add(boton);
        boton.setToolTipText("repite el último numero pulsado");
        boton.addActionListener(new ActionListener() {
                public void actionPerformed (ActionEvent e) {
                    display.setText(numeroMarcado);
                }
            });
    }

    public void actionPerformed(ActionEvent event){
        String tecla = event.getActionCommand();
    }

    private void agregarBotonColgar(Container panel, String textoBoton)
    {
        JButton boton = new JButton(textoBoton);
        panel.add(boton);
        boton.setToolTipText("cuelga el telefono");
        boton.addActionListener(new ActionListener() {
                public void actionPerformed (ActionEvent e) {
                    System.exit(0);
                }
            });
    }

    //     private void numeroLoco(String numeroBoton, Container panel)
    //     {
    //          Random generador = new Random();
    //         int numRandom = generador.nextInt(10);
    //         JButton boton = new JButton(String.valueOf(numRandom));
    //         panel.add(boton);
    //         String numeroTexto = String.valueOf(numRandom);
    //         if(String.valueOf(numRandom) != numeroBoton){
    //             boton.addActionListener(new ActionListener() {
    //                 public void actionPerformed (ActionEvent e) {
    //                     display.setText(String.valueOf(numeroTexto));
    //                 }
    //             });
    //         }
    //     }

    private char hallarDigitoFijo(int posicion)
    {
        posicion = posicion % NUMFIJO.length();
        return NUMFIJO.charAt(posicion);
    }
   
}
