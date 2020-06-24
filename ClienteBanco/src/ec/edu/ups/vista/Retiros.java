package ec.edu.ups.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Retiros {

	private JFrame frame;
	private JTextField txtCedula;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Retiros window = new Retiros();
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
	public Retiros() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 476, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 450, 261);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Cajero", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Cedula");
		lblNewLabel_1.setBounds(10, 51, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("RETIROS");
		lblNewLabel.setBounds(187, 11, 44, 16);
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 14));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Correo");
		lblNewLabel_2.setBounds(10, 98, 46, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Cantidad");
		lblNewLabel_3.setBounds(10, 141, 46, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Nombre");
		lblNewLabel_4.setBounds(242, 98, 46, 14);
		panel.add(lblNewLabel_4);
		
		txtCedula = new JTextField();
		txtCedula.setBounds(66, 48, 123, 20);
		panel.add(txtCedula);
		txtCedula.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(242, 47, 89, 23);
		panel.add(btnBuscar);
		
		JLabel lblNewLabel_5 = new JLabel("Cuenta");
		lblNewLabel_5.setBounds(242, 141, 46, 14);
		panel.add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.setBounds(66, 95, 123, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(66, 138, 123, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(298, 95, 123, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(298, 138, 123, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(170, 184, 89, 23);
		panel.add(btnAceptar);
	}

}
