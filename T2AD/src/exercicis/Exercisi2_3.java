package exercicis;

import java.awt.BorderLayout;
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

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class Exercisi2_3 extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private JScrollPane scroll;
	private JFileChooser fc = new JFileChooser();
	private File arxiu;
	private BufferedReader in;
	private FileReader fr;
	private BufferedWriter out;
	private FileWriter fw;
	private StringBuilder builder;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Exercisi2_3 frame = new Exercisi2_3();
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
	public Exercisi2_3() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 420);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Archive");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Open...");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int r = fc.showOpenDialog(null);
				if (r == JFileChooser.APPROVE_OPTION) {
					arxiu = fc.getSelectedFile();
				}
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
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mnNewMenu_1 = new JMenuItem("Save");
		mnNewMenu_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		mnNewMenu.add(mnNewMenu_1);

		JMenuItem mnNewMenu_2 = new JMenuItem("Save as...");
		mnNewMenu_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int r = fc.showSaveDialog(null);
				if (r == JFileChooser.APPROVE_OPTION) {
					arxiu = fc.getSelectedFile();
				}
				
				try {
					fw = new FileWriter(arxiu);
					out = new BufferedWriter(fw);
					out.write(textArea.getText());
					out.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error desant l'arxiu");
				}
				
				JOptionPane.showMessageDialog(null, "Desat correctament");
				textArea.setText("");

			}
		});
		mnNewMenu.add(mnNewMenu_2);

		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Exit");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBounds(20, 141, 420, 310);
		textArea = new JTextArea(22, 35);
		textArea.setBounds(12, 120, 418, 363);
		scroll = new JScrollPane(textArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scroll);
		contentPane.add(panel);
	}

}
