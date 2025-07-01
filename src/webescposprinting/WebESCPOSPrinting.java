package webescposprinting;

import com.sun.net.httpserver.HttpServer;
import java.awt.Component;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import java.util.regex.Pattern;
import javax.print.attribute.standard.PrinterState;
import javax.print.attribute.standard.PrinterStateReason;
import javax.print.attribute.standard.PrinterStateReasons;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

/**
 *
 * @author jesusespinoza
 */
public class WebESCPOSPrinting extends javax.swing.JFrame {

    HttpServer server;
    Preferences preferences = Preferences.userRoot().node(this.getClass().getName());
    Boolean isServerRunning = false;
    int impresorasDetectadas = 0;

    /**
     * Creates new form Principal
     */
    public WebESCPOSPrinting() {
        initComponents();

        
        labelGifProgress.setVisible(false);
        detenerButton.setEnabled(false);
        llenarInformacion();

        autoRunServer();
    }
    
    final void llenarInformacion() {

        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        System.out.println("Numero de impresoras detectadas: " + printServices.length);
        impresorasDetectadas = printServices.length;
        
        String defaultValueImpresora = "";
        String defaultValuePuerto = "8779";
        String impresoraDefault = preferences.get("impresoraDefault", defaultValueImpresora);

        String puertoDefault =  preferences.get("puertoDefault", defaultValuePuerto);
        int impresoraDefaultIndex = 0;
        int index = 1;
        impresorasComboBox.addItem("");
        for (PrintService printer : printServices) {
            
            impresorasComboBox.addItem(printer.getName());
            printer.getAttributes();
            
            if(printer.getName().compareTo(impresoraDefault) == 0){
                impresoraDefaultIndex = index;
            }else{
            //No ha seleccionado una impresora default y se seteará la primera de la lista del combobox
                impresoraDefaultIndex = 1;
            }
            index ++;
        }
       
        setImpresoraDefault(impresoraDefaultIndex, impresoraDefault);
        setPuertoDefault(puertoDefault);
    }
    
    public void setImpresoraDefault(int index, String impresora){
            impresorasComboBox.setSelectedIndex(index);
            impresoraSeleccionadaLabel.setText(impresora);
    }
    
    public void setPuertoDefault(String puerto){
            puertoTF.setText(puerto);
            puertoSeleccionadoLabel.setText(puerto);
    }
    
    public void autoRunServer(){
        if (impresorasDetectadas == 0) {
            Component frame = null;

            JOptionPane.showMessageDialog(frame,
                    "No se pudo arrancar el server de impresión, debido a que no se detectaron impresoras.",
                    "Oops! algo salio mal",
                    JOptionPane.ERROR_MESSAGE);
        }else if(puertoSeleccionadoLabel.getText().compareTo("") == 0){
                        Component frame = null;

                JOptionPane.showMessageDialog(frame,
                    "No se pudo arrancar el server de impresión, debido a que no ha seleccionado un puerto.",
                    "Oops! algo salio mal",
                    JOptionPane.ERROR_MESSAGE);
        
        }else {
            labelGifProgress.setVisible(true);
            try {
                server = HttpServer.create(new InetSocketAddress(Caster.strToInteger(puertoSeleccionadoLabel.getText())), 0);
                server.createContext("/print", new MyHandler(impresoraSeleccionadaLabel.getText()));
                server.setExecutor(null); // creates a default executor
                server.start();
                isServerRunning = true;

                iniciarButton.setEnabled(false);
                detenerButton.setEnabled(true);
                
            } catch (IOException ex) {
                Logger.getLogger(WebESCPOSPrinting.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        establecerButton = new javax.swing.JButton();
        impresorasComboBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        impresoraSeleccionadaLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        puertoTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        puertoSeleccionadoLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        iniciarButton = new javax.swing.JButton();
        detenerButton = new javax.swing.JButton();
        labelGifProgress = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccione Impresora"));
        jPanel1.setName(""); // NOI18N

        establecerButton.setText("Establecer");
        establecerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                establecerButtonActionPerformed(evt);
            }
        });

        impresorasComboBox.setToolTipText("");
        impresorasComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                impresorasComboBoxActionPerformed(evt);
            }
        });

        jLabel2.setText("Instaladas:");

        jLabel3.setText("Impresora seleccionada:");

        jLabel4.setText("Puerto:");

        puertoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                puertoTFActionPerformed(evt);
            }
        });

        jLabel5.setText("Puerto seleccionado:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(impresorasComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(puertoTF, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(establecerButton))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(impresoraSeleccionadaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addComponent(puertoSeleccionadoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(impresorasComboBox)
                    .addComponent(puertoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(establecerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(impresoraSeleccionadaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                    .addComponent(puertoSeleccionadoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Estado del Servidor"));

        iniciarButton.setText("Iniciar");
        iniciarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarButtonActionPerformed(evt);
            }
        });

        detenerButton.setText("Detener");
        detenerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detenerButtonActionPerformed(evt);
            }
        });

        labelGifProgress.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/progressbar.gif"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iniciarButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(detenerButton)
                .addGap(46, 46, 46)
                .addComponent(labelGifProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelGifProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(iniciarButton)
                        .addComponent(detenerButton)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jPanel1.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void establecerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_establecerButtonActionPerformed

         if (isServerRunning) {
            JOptionPane.showMessageDialog(null,
                    "Para cambiar la configuración, primero detén el server de impresión.",
                    "Oops! algo salio mal",
                    JOptionPane.WARNING_MESSAGE);
            
                    //Setear de nuevo los valores que ya tenia, por si los habia borrado.
                    String impresoraDefault = preferences.get("impresoraDefault", "");
                    String puertoDefault =  preferences.get("puertoDefault", "");
            
                    impresorasComboBox.setSelectedItem(impresoraDefault);
                    puertoTF.setText(puertoDefault);
        }else{
             if (puertoTF.getText().equals("") || !(Pattern.matches("^[0-9]+$", puertoTF.getText()))){
                JOptionPane.showMessageDialog(null, "El puerto es invalido",
                        "Oops! algo salio mal",
                        JOptionPane.WARNING_MESSAGE);
              }else{
                    impresoraSeleccionadaLabel.setText(impresorasComboBox.getSelectedItem().toString());
                    puertoSeleccionadoLabel.setText(puertoTF.getText());
                    preferences.put("impresoraDefault", impresorasComboBox.getSelectedItem().toString());
                    preferences.put("puertoDefault", puertoSeleccionadoLabel.getText());
             }
         }
    
    }//GEN-LAST:event_establecerButtonActionPerformed

    private void impresorasComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_impresorasComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_impresorasComboBoxActionPerformed

    private void detenerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detenerButtonActionPerformed
        // TODO add your handling code here:
        //progressBar.setIndeterminate(false);
        labelGifProgress.setVisible(false);

        server.removeContext("/print");
        server.stop(1);
        isServerRunning = false;

        iniciarButton.setEnabled(true);
        detenerButton.setEnabled(false);
    }//GEN-LAST:event_detenerButtonActionPerformed

    private void iniciarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniciarButtonActionPerformed
        // TODO add your handling code here:
        if (impresoraSeleccionadaLabel.getText().compareTo("") == 0) {
            Component frame = null;

            JOptionPane.showMessageDialog(frame,
                    "Seleccione una impresora.",
                    "Oops! algo salio mal",
                    JOptionPane.ERROR_MESSAGE);
        }else if(puertoSeleccionadoLabel.getText().compareTo("") == 0){
                        Component frame = null;

                JOptionPane.showMessageDialog(frame,
                    "Seleccione un puerto.",
                    "Oops! algo salio mal",
                    JOptionPane.ERROR_MESSAGE);
        
        }else {
            //Progress p = new Progress(progressBar);
            //p.execute();
            labelGifProgress.setVisible(true);
            try {
                server = HttpServer.create(new InetSocketAddress(Caster.strToInteger(puertoSeleccionadoLabel.getText())), 0);
                server.createContext("/print", new MyHandler(impresoraSeleccionadaLabel.getText()));
                server.setExecutor(null); // creates a default executor
                server.start();
                isServerRunning = true;

                iniciarButton.setEnabled(false);
                detenerButton.setEnabled(true);

            } catch (IOException ex) {
                Logger.getLogger(WebESCPOSPrinting.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_iniciarButtonActionPerformed

    private void puertoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_puertoTFActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_puertoTFActionPerformed

    public static void main(String args[]) throws Exception {

        /*HttpServer server = HttpServer.create(new InetSocketAddress(8779), 0);
        server.createContext("/print", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
         */
        WebESCPOSPrinting programa = new WebESCPOSPrinting();
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                final TrayIcon trayIcon;
                if (SystemTray.isSupported()) {
                    SystemTray tray = SystemTray.getSystemTray();

                    //Se asigna la imagen del tray icon
                    ImageIcon im = new ImageIcon(WebESCPOSPrinting.class.getResource("/recursos/printericon.png"));
                    Image image = Toolkit.getDefaultToolkit().getImage("printericon.png");
                    //Este listener permite salir de la aplicacion  
                    ActionListener exitListener = new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            System.out.println("Deteniendo...");
                            System.exit(0);
                        }
                    };

                    //Aquí se crea un popup menu  
                    PopupMenu popup = new PopupMenu();

                    //Se agrega la opción de salir  
                    MenuItem defaultItem = new MenuItem("Detener");

                    //Se le asigna al item del popup el listener para salir de la app  
                    defaultItem.addActionListener(exitListener);
                    popup.add(defaultItem);

                    /*Aqui creamos una instancia del tray icon y asignamos 
                    la imagen, el nombre del tray icon y el popup*/
                    trayIcon = new TrayIcon(im.getImage(), "Printer Icon", popup);

                    /*Creamos un acction listener que se ejecuta cuando le damos 
                    doble click al try icon*/
                    ActionListener actionListener = new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            //new WebESCPOSPrinting().setVisible(true);
                            programa.setVisible(true);
                        }
                    };
                    

                    trayIcon.setImageAutoSize(true);
                    trayIcon.addActionListener(actionListener);

                    try {

                        tray.add(trayIcon);

                    } catch (AWTException ex) {
                        ex.printStackTrace();
                    }

                } else {
                    System.err.println("System tray is currently not supported.");
                }

            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton detenerButton;
    private javax.swing.JButton establecerButton;
    private javax.swing.JLabel impresoraSeleccionadaLabel;
    private javax.swing.JComboBox<String> impresorasComboBox;
    private javax.swing.JButton iniciarButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelGifProgress;
    private javax.swing.JLabel puertoSeleccionadoLabel;
    private javax.swing.JTextField puertoTF;
    // End of variables declaration//GEN-END:variables
}
