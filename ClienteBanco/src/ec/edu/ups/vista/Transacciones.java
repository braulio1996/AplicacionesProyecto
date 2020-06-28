package ec.edu.ups.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Transacciones extends javax.swing.JInternalFrame {

	
	private JTextField txtCuenta;
	private JTextField txtNombre;
	private JTextField txtCorreo;
	private JTextField txtMonto;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public Transacciones() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 477, 261);

		
		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("TRANSACCIONES");
		lblNewLabel.setBounds(161, 26, 109, 14);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 13));
		
		JLabel lblNewLabel_1 = new JLabel("Num Cuenta");
		lblNewLabel_1.setBounds(10, 59, 64, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre");
		lblNewLabel_2.setBounds(10, 95, 46, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Correo");
		lblNewLabel_3.setBounds(10, 133, 46, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Monto");
		lblNewLabel_4.setBounds(10, 169, 46, 14);
		panel.add(lblNewLabel_4);
		
		txtCuenta = new JTextField();
		txtCuenta.setBounds(92, 56, 238, 20);
		panel.add(txtCuenta);
		txtCuenta.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(92, 92, 238, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(92, 130, 241, 20);
		panel.add(txtCorreo);
		txtCorreo.setColumns(10);
		
		txtMonto = new JTextField();
		txtMonto.setBounds(92, 166, 238, 20);
		panel.add(txtMonto);
		txtMonto.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(327, 199, 89, 23);
		panel.add(btnAceptar);
	}

}
