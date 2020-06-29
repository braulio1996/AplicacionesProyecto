package ec.edu.ups.vista;


import java.awt.Dimension;
import java.awt.Toolkit;

public class Principal extends javax.swing.JFrame {

    Dimension pantallaTamano = Toolkit.getDefaultToolkit().getScreenSize();
    
    public Principal() {
        initComponents();
		
		Login123 login = new Login123();
		login.setVisible(true);

		this.setSize((pantallaTamano.width), (pantallaTamano.height)); 
		Dimension tamanoPanel = login.getSize(); 
		login.setBounds(((pantallaTamano.width - tamanoPanel.width) / 2), (((pantallaTamano.height - tamanoPanel.height) / 2)-100),(int) tamanoPanel.getWidth(), (int) tamanoPanel.getHeight()); 
		pnl_Nombre.setVisible(true);
		mnuPrincipal.setVisible(false);
		dskPane.add(login);

		this.setExtendedState(Principal.MAXIMIZED_BOTH);
		this.setTitle("BANCA");
    }//Fin Constructor

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        dskPane = new javax.swing.JDesktopPane();
        pnl_Nombre = new javax.swing.JPanel();
        lbl_Sucursal = new javax.swing.JLabel();
        lbl_Nombre = new javax.swing.JLabel();
        mnuPrincipal = new javax.swing.JMenuBar();
        mnuArchivo = new javax.swing.JMenu();
        mnuSesion = new javax.swing.JMenuItem();
        mnuSalir = new javax.swing.JMenuItem();
        mnuPersonas = new javax.swing.JMenu();
        mnuRetiro = new javax.swing.JMenuItem();
        mnuDeposito = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);


        dskPane.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout dskPaneLayout = new javax.swing.GroupLayout(dskPane);
        dskPane.setLayout(dskPaneLayout);
        dskPaneLayout.setHorizontalGroup(
            dskPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        dskPaneLayout.setVerticalGroup(
            dskPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 428, Short.MAX_VALUE)
        );

        pnl_Nombre.setLayout(new java.awt.GridBagLayout());

        lbl_Sucursal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.8;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        pnl_Nombre.add(lbl_Sucursal, gridBagConstraints);

        lbl_Nombre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        pnl_Nombre.add(lbl_Nombre, gridBagConstraints);

        mnuPrincipal.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        mnuPrincipal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        mnuArchivo.setText("Archivo");
        mnuArchivo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        mnuSesion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        mnuSesion.setText("Cerrar Sesi�n");
        mnuSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSesionActionPerformed(evt);
            }
        });
        mnuArchivo.add(mnuSesion);

        mnuSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        mnuSalir.setText("Salir");
        mnuSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSalirActionPerformed(evt);
            }
        });
        mnuArchivo.add(mnuSalir);

        mnuPrincipal.add(mnuArchivo);

        mnuPersonas.setText("Acciones");
        mnuPersonas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        mnuRetiro.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, java.awt.event.InputEvent.ALT_MASK));
        mnuRetiro.setText("Retiros");
        mnuRetiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRetiroActionPerformed(evt);
            }
        });
        mnuPersonas.add(mnuRetiro);

        mnuDeposito.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, java.awt.event.InputEvent.ALT_MASK));
        mnuDeposito.setText("Dep�sitos");
        mnuDeposito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuDepositoActionPerformed(evt);
            }
        });
        mnuPersonas.add(mnuDeposito);

        mnuPrincipal.add(mnuPersonas);

        setJMenuBar(mnuPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_Nombre, javax.swing.GroupLayout.DEFAULT_SIZE, 796, Short.MAX_VALUE)
            .addComponent(dskPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnl_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dskPane))
        );

        pack();
    }// </editor-fold>                        


    private void mnuSesionActionPerformed(java.awt.event.ActionEvent evt) {                                          
        Login123 login = new Login123();
        
        this.setSize((pantallaTamano.width), (pantallaTamano.height)); 
        Dimension tamanoPanel = login.getSize(); 
        login.setBounds((pantallaTamano.width - tamanoPanel.width) / 2, (((pantallaTamano.height - tamanoPanel.height) / 2)-100),(int) tamanoPanel.getWidth(), (int) tamanoPanel.getHeight()); 
        
        dskPane.removeAll();
        dskPane.repaint();
        login.setVisible(true);
        dskPane.add(login); 
        
        login.txtNombre.requestFocus();
        this.setExtendedState(Principal.MAXIMIZED_BOTH);
        mnuPrincipal.setVisible(false);
        lbl_Nombre.setText("");
    }                                         

    private void mnuSalirActionPerformed(java.awt.event.ActionEvent evt) {                                         
        System.exit(0);
    }                                        

    private void mnuRetiroActionPerformed(java.awt.event.ActionEvent evt) {                                          
       
            Retiros r = new Retiros();
            
            Dimension dim = dskPane.getSize();
            r.setSize(dim);
            dskPane.removeAll();
            dskPane.repaint();
            r.setVisible(true); 
            dskPane.add(r);
       
    }                                         

    private void mnuDepositoActionPerformed(java.awt.event.ActionEvent evt) {                                            
        
            Deposito dep = new Deposito();
            
            Dimension dim = dskPane.getSize();
            dep.setSize(dim);
            dskPane.removeAll();
            dskPane.repaint();
            dep.setVisible(true); 
            dskPane.add(dep);	
        
    }                                           


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify                     
    public static javax.swing.JDesktopPane dskPane;
    public static javax.swing.JLabel lbl_Nombre;
    public static javax.swing.JLabel lbl_Sucursal;
    public static javax.swing.JMenu mnuArchivo;
    private javax.swing.JMenuItem mnuDeposito;
    public static javax.swing.JMenu mnuPersonas;
    public static javax.swing.JMenuBar mnuPrincipal;
    private javax.swing.JMenuItem mnuRetiro;
    private javax.swing.JMenuItem mnuSalir;
    private javax.swing.JMenuItem mnuSesion;
    public static javax.swing.JPanel pnl_Nombre;
    // End of variables declaration                   
}
