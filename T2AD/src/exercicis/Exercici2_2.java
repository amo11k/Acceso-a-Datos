package exercicis;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class Exercici2_2 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private File arxiu;
	private BufferedReader in;
	private FileReader fr;
	private BufferedWriter out;
	private FileWriter fw;
	private StringBuilder builder;
	private JScrollPane scroll;
	// ruta de prova /home/alumnes/2CFSL/alu53381650f/git/Acceso-a-Datos/T2AD/musica

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Exercici2_2 frame = new Exercici2_2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Exercici2_2() {
		setTitle("Editor de Text");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblFitxer = new JLabel("Fitxer:");
		lblFitxer.setBounds(43, 12, 70, 15);
		contentPane.add(lblFitxer);

		textField = new JTextField();
		textField.setBounds(105, 10, 267, 19);
		contentPane.add(textField);
		textField.setColumns(10);

		textArea = new JTextArea();
		// textArea.setBounds(12, 120, 418, 363);
		// 1display = new JTextArea(16, 58);
		scroll = new JScrollPane(textArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scroll);

		JButton btnObrir = new JButton("Obrir");
		btnObrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ruta = textField.getText();
				arxiu = new File(ruta);
				String line;
				builder = new StringBuilder();
				try {
					fr = new FileReader(arxiu);
					in = new BufferedReader(fr);
					while ((line = in.readLine()) != null) {
						// textArea.setText(textArea.getText()+"\n"+line);
						builder.append(line + "\n");
					}
					textArea.setText(builder.toString());
					in.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "El arxiu no s'ha trobat");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error greu");
				}
				
			}
			
		});
		btnObrir.setBounds(76, 79, 117, 25);
		contentPane.add(btnObrir);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*String ruta = textField.getText();
				arxiu = new File(ruta);*/
				try {
					fw = new FileWriter(arxiu);
					out = new BufferedWriter(fw);
					out.write(textArea.getText());
					out.close();
					JOptionPane.showMessageDialog(null, "Desat correctament");
					textArea.setText("");
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error desant l'arxiu");
				}
			}
		});
		btnGuardar.setBounds(255, 79, 117, 25);
		contentPane.add(btnGuardar);
		
		//Poner Scroll a un textArea
		JPanel panel = new JPanel();
		panel.setBounds(20, 141, 420, 310);
		textArea = new JTextArea(20, 30);
		textArea.setBounds(12, 120, 418, 363);
		scroll = new JScrollPane(textArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scroll);
		contentPane.add(panel);
		
		// ruta de prova /home/alumnes/2CFSL/alu53381650f/git/Acceso-a-Datos/T2AD/musica
	}
}
