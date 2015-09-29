package exemples;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class Finestra extends JFrame implements ActionListener {
	JFileChooser fc = new JFileChooser();
	JMenuBar menu_p = new JMenuBar();

	JMenu menu_1 = new JMenu("Menu 1");
	JMenu menu_2 = new JMenu("Menu 2");
	JMenu menu_3 = new JMenu("Menu 3");

	JMenuItem menu_11 = new JMenuItem("Menu 11");
	JMenu menu_12 = new JMenu("Menu 12");
	JMenuItem menu_13 = new JMenuItem("Menu 13");
	JMenuItem menu_21 = new JMenuItem("Menu 21");
	JMenuItem menu_22 = new JMenuItem("Menu 22");
	JMenuItem menu_e = new JMenuItem("Eixir");
	JMenuItem menu_121 = new JMenuItem("Menu 121");
	JMenuItem menu_122 = new JMenuItem("Menu 122 (eixir)");

	public void iniciar() {
		this.setSize(400, 300);
		this.setJMenuBar(menu_p);
		menu_p.add(menu_1);
		menu_p.add(menu_2);
		menu_p.add(menu_3);

		menu_1.add(menu_11);
		menu_1.add(menu_12);
		menu_1.add(new JSeparator());
		menu_1.add(menu_13);

		menu_12.add(menu_121);
		menu_12.add(menu_122);

		menu_2.add(menu_21);
		menu_2.add(new JSeparator());
		menu_2.add(menu_22);

		menu_3.add(menu_e);

		this.setVisible(true);

		menu_122.addActionListener(this);
		menu_e.addActionListener(this);
		menu_121.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ((e.getSource() == menu_e) || (e.getSource() == menu_122)) {
			
			int r = fc.showOpenDialog(this);
			if (r == JFileChooser.APPROVE_OPTION) {
				System.out.println("Fitxer seleccionat: " + fc.getSelectedFile().getName());
			} else
				System.out.println("No s'ha seleccionat res");
		}

	}

	public static void main(String[] args) {
		Finestra pantalla = new Finestra();
		pantalla.iniciar();
	}
}