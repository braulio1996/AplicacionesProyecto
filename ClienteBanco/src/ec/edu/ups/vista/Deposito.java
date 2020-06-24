package ec.edu.ups.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import javax.swing.JDesktopPane;
import java.awt.Font;

public class Deposito {

	private JFrame frame;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtCorreo;
	private JTextField txtCuenta;
	private JTextField txtCantidad;
	private JTextField txtDepositante;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Deposito window = new Deposito();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Deposito() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 517, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 501, 261);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Cajero", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cedula:");
		lblNewLabel.setBounds(20, 49, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Correo:");
		lblNewLabel_1.setBounds(20, 100, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setBounds(279, 49, 46, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Cuenta:");
		lblNewLabel_3.setBounds(279, 100, 46, 14);
		panel.add(lblNewLabel_3);
		
		txtCedula = new JTextField();
		txtCedula.setBounds(78, 46, 154, 20);
		panel.add(txtCedula);
		txtCedula.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(335, 46, 151, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(78, 97, 154, 20);
		panel.add(txtCorreo);
		txtCorreo.setColumns(10);
		
		txtCuenta = new JTextField();
		txtCuenta.setBounds(335, 97, 151, 20);
		panel.add(txtCuenta);
		txtCuenta.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(216, 186, 89, 23);
		panel.add(btnAceptar);
		
		JLabel lblNewLabel_4 = new JLabel("Cantidad:");
		lblNewLabel_4.setBounds(20, 151, 52, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Depositante");
		lblNewLabel_5.setBounds(267, 151, 58, 14);
		panel.add(lblNewLabel_5);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(78, 148, 154, 20);
		panel.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		txtDepositante = new JTextField();
		txtDepositante.setBounds(335, 148, 151, 20);
		panel.add(txtDepositante);
		txtDepositante.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("DEPOSITOS");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_6.setBounds(169, 11, 136, 14);
		panel.add(lblNewLabel_6);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Listar", null, panel_1, null);
	}
}
