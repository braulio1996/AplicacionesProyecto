package ec.edu.ups.vista;

import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.logging.LoggingFeature;

import ec.edu.ups.Modelo.Respuesta;

public class Login extends javax.swing.JInternalFrame {
	private String cajero;
	private String cuenta;

	public String getCajero() {
		return cajero;
	}

	public void setCajero(String cajero) {
		this.cajero = cajero;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public Login() {
		initComponents();
		this.setTitle("FORMULARIO DE REGISTRO");

		txtNombre.requestFocus();
	}// Fin constructor

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		txtNombre = new javax.swing.JTextField();
		txtClave = new javax.swing.JPasswordField();
		jPanel2 = new javax.swing.JPanel();
		btn_Entrar = new javax.swing.JButton();
		btn_Salir = new javax.swing.JButton();
		jLabel4 = new javax.swing.JLabel();

		setBackground(new java.awt.Color(102, 102, 102));
		setBorder(null);
		setOpaque(true);

		jPanel1.setBackground(new java.awt.Color(102, 102, 102));
		java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
		jPanel1Layout.columnWidths = new int[] { 0, 10, 0 };
		jPanel1Layout.rowHeights = new int[] { 0, 10, 0, 10, 0 };
		jPanel1.setLayout(jPanel1Layout);

		jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

		jLabel1.setText("Correo:");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
		jPanel1.add(jLabel1, gridBagConstraints);

		jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

		jLabel2.setText("Contraseña:");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
		jPanel1.add(jLabel2, gridBagConstraints);

		txtNombre.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				txtNombreKeyReleased(evt);
			}

			public void keyTyped(java.awt.event.KeyEvent evt) {
				txtNombreKeyTyped(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 0.4;
		gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 5);
		jPanel1.add(txtNombre, gridBagConstraints);

		txtClave.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		txtClave.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtClaveActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
		jPanel1.add(txtClave, gridBagConstraints);

		jPanel2.setBackground(new java.awt.Color(102, 102, 102));
		java.awt.GridBagLayout jPanel2Layout = new java.awt.GridBagLayout();
		jPanel2Layout.columnWidths = new int[] { 0, 10, 0 };
		jPanel2Layout.rowHeights = new int[] { 0 };
		jPanel2.setLayout(jPanel2Layout);

		btn_Entrar.setText("Entrar");
		btn_Entrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		btn_Entrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		btn_Entrar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn_EntrarActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		jPanel2.add(btn_Entrar, gridBagConstraints);

		btn_Salir.setText(" Salir ");
		btn_Salir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		btn_Salir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		btn_Salir.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn_SalirActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		jPanel2.add(btn_Salir, gridBagConstraints);

		jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel4.setText("LOGIN");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE).addComponent(jLabel4,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 83,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>

	private void btn_EntrarActionPerformed(java.awt.event.ActionEvent evt) {
		Rest r = new Rest();

		try {
		

			Respuesta respuesta = r.login(txtNombre.getText(), txtClave.getText());
			JOptionPane.showMessageDialog(null, "Correcto", "Ingreso "+respuesta.getMensaje(), JOptionPane.INFORMATION_MESSAGE);
			if (respuesta.getCodigo() == 0) {
				cajero = respuesta.getMensaje();
				setCajero(respuesta.getMensaje());
				Deposito d = new Deposito();
				Deposito.cajero = getCajero();
				d.setVisible(true);
				Principal.dskPane.add(d);
				Principal.mnuPrincipal.setVisible(true);
				this.dispose();
				Retiros rr = new Retiros();
				Retiros.cajero = getCajero();
				rr.setVisible(true);
				Principal.dskPane.add(rr);
				Principal.mnuPrincipal.setVisible(true);
				this.dispose();
			} else if (respuesta.getCodigo() == 1) {
				cuenta = respuesta.getMensaje();
				setCuenta(respuesta.getMensaje());
				Tran t = new Tran();
				Tran.cedula = getCuenta();
				t.setVisible(true);
				Principal.dskPane.add(t);
				Principal.mnuPrincipal.setVisible(true);
				this.dispose();

			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Expected exception: Exception has occurred.");
			System.out.println(e.toString());
		}
		{

		}

		/*
		 * try { String nombre = txtNombre.getText(); String clave = txtClave.getText();
		 * 
		 * Principal.pnl_Nombre.setVisible(true);
		 * 
		 * if((nombre, clave)){ try {
		 * Principal.lbl_Nombre.setText(Metodos_Empleados.empCorreo(nombre));
		 * this.setVisible(false);
		 * 
		 * Principal.mnuPrincipal.setVisible(true);
		 * Principal.mnuArchivo.setVisible(true); Principal.mnuFactura.setVisible(true);
		 * Principal.mnuPersonas.setVisible(true);
		 * Principal.lbl_Sucursal.setText(Metodos_Empleados.empSuc(txtNombre.getText()))
		 * ;
		 * 
		 * Menu menu = new Menu();
		 * 
		 * Dimension dim = Principal.dskPane.getSize(); menu.setSize(dim);
		 * menu.setVisible(true); Principal.dskPane.add(menu); } catch (SQLException ex)
		 * { Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex); }//Fin
		 * try-catch//Fin try-catch }else{ JOptionPane.showMessageDialog(this,
		 * "Usuario Incorrecto"); }//Fin if } catch (SQLException ex) {
		 * Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex); }//Fin
		 * try-catch//Fin try-catch
		 */
	}

	private void btn_SalirActionPerformed(java.awt.event.ActionEvent evt) {
		System.exit(0);
	}

	private void txtClaveActionPerformed(java.awt.event.ActionEvent evt) {
		btn_EntrarActionPerformed(evt);
	}

	private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {

	}

	private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {
		char c = evt.getKeyChar();

		if (c == KeyEvent.VK_SPACE) {
			getToolkit().beep();
			JOptionPane.showMessageDialog(this, "ERROR..! Caracter no permitido");
			evt.consume();

		} // Fin if
	}

	// Variables declaration - do not modify
	private javax.swing.JButton btn_Entrar;
	private javax.swing.JButton btn_Salir;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPasswordField txtClave;
	public static javax.swing.JTextField txtNombre;
	// End of variables declaration
}
