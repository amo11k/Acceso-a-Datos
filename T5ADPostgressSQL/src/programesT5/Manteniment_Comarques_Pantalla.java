package programesT5;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import exemples.SessionFactoryUtil;
import geo.Comarques;

public class Manteniment_Comarques_Pantalla extends JFrame {

	private JPanel contentPane;
	private JTextField comarcaField;
	private JTextField provinciaField;
	private int firstList, lastList, position, opt;
	private List<Comarques> list;
	private JButton nextBtn, prevBtn, inserirBtn, modifBtn, esborrarBtn, okBtn, cancelBtn, btnFirstItem, btnFinalItem;
	private Session sessio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manteniment_Comarques_Pantalla frame = new Manteniment_Comarques_Pantalla();
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
	public Manteniment_Comarques_Pantalla() {
		position = 0;
		llegir();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridLayout(6, 0, 0, 0));
		setContentPane(contentPane);

		JLabel lblMantenimentDeComarques = new JLabel("Manteniment de Comarques");
		lblMantenimentDeComarques.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblMantenimentDeComarques);

		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(2, 2, 0, 0));

		JLabel lblNewLabel = new JLabel("Nom Comarca");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);

		comarcaField = new JTextField();
		comarcaField.setHorizontalAlignment(SwingConstants.CENTER);
		comarcaField.setEditable(false);
		panel.add(comarcaField);
		comarcaField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nom Provincia");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_1);

		provinciaField = new JTextField();
		provinciaField.setHorizontalAlignment(SwingConstants.CENTER);
		provinciaField.setEditable(false);
		panel.add(provinciaField);
		provinciaField.setColumns(10);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		prevBtn = new JButton("<");
		prevBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (position - 1 >= 0) {
					position--;
					comarcaField.setText(list.get(position).getNomC());
					provinciaField.setText(list.get(position).getProvincia());
					nextBtn.setEnabled(true);
					if (position == 0) {
						prevBtn.setEnabled(false);
					}
				}
			}
		});
		btnFirstItem = new JButton("<<");
		btnFirstItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comarcaField.setText(list.get(0).getNomC());
				provinciaField.setText(list.get(0).getProvincia());
				position = 0;
				prevBtn.setEnabled(false);
				nextBtn.setEnabled(true);
			}
		});
		panel_2.add(btnFirstItem);
		panel_2.add(prevBtn);

		nextBtn = new JButton(">");
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (position + 1 <= lastList) {
					position++;
					comarcaField.setText(list.get(position).getNomC());
					provinciaField.setText(list.get(position).getProvincia());
					prevBtn.setEnabled(true);
					if (position == lastList) {
						nextBtn.setEnabled(false);

					}
				}
			}
		});
		panel_2.add(nextBtn);

		btnFinalItem = new JButton(">>");
		btnFinalItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comarcaField.setText(list.get(lastList).getNomC());
				provinciaField.setText(list.get(lastList).getProvincia());
				position = lastList;
				nextBtn.setEnabled(false);
				prevBtn.setEnabled(true);
			}
		});
		panel_2.add(btnFinalItem);

		comarcaField.setText(list.get(position).getNomC());
		provinciaField.setText(list.get(position).getProvincia());

		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		inserirBtn = new JButton("Inserir");
		inserirBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifBtn.setEnabled(false);
				esborrarBtn.setEnabled(false);
				opt = 0;
				prevBtn.setEnabled(false);
				cancelBtn.setEnabled(true);
				okBtn.setEnabled(true);
				nextBtn.setEnabled(false);
				btnFinalItem.setEnabled(false);
				btnFirstItem.setEnabled(false);
				comarcaField.setText("");
				comarcaField.setEditable(true);
				provinciaField.setText("");
				provinciaField.setEditable(true);
			}
		});
		panel_3.add(inserirBtn);

		modifBtn = new JButton("Modificar");
		modifBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserirBtn.setEnabled(false);
				esborrarBtn.setEnabled(false);
				prevBtn.setEnabled(false);
				cancelBtn.setEnabled(true);
				okBtn.setEnabled(true);
				nextBtn.setEnabled(false);
				btnFinalItem.setEnabled(false);
				btnFirstItem.setEnabled(false);
				opt = 1;
				provinciaField.setEditable(true);
			}
		});
		panel_3.add(modifBtn);
		

		esborrarBtn = new JButton("Esborrar");
		esborrarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserirBtn.setEnabled(false);
				modifBtn.setEnabled(false);
				prevBtn.setEnabled(false);
				cancelBtn.setEnabled(true);
				okBtn.setEnabled(true);
				nextBtn.setEnabled(false);
				btnFinalItem.setEnabled(false);
				btnFirstItem.setEnabled(false);
				opt = 2;
			}
		});
		panel_3.add(esborrarBtn);

		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4);

		okBtn = new JButton("Acceptar");
		okBtn.setEnabled(false);
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Comarques com;
				switch (opt) {
				case 0:
					com = new Comarques();
					com.setNomC(comarcaField.getText());
					com.setProvincia(provinciaField.getText());
					inserir(com);
					llegir();
					break;
				case 1:
					com = list.get(position);
					modificar(com);
					llegir();
					break;
				case 2:
					com = list.get(position);
					esborrar(com);
					llegir();
					break;
				default:
					break;
				}
				inserirBtn.setEnabled(true);
				modifBtn.setEnabled(true);
				esborrarBtn.setEnabled(true);
				opt = 0;
				position = 0;
				comarcaField.setEditable(false);
				comarcaField.setText(list.get(0).getNomC());
				provinciaField.setEditable(false);
				provinciaField.setText(list.get(0).getProvincia());
				btnFinalItem.setEnabled(true);
				btnFirstItem.setEnabled(true);
				nextBtn.setEnabled(true);
				prevBtn.setEnabled(true);
			}
			
		});
		panel_4.add(okBtn);

		cancelBtn = new JButton("Cancel·lar");
		cancelBtn.setEnabled(false);
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserirBtn.setEnabled(true);
				modifBtn.setEnabled(true);
				esborrarBtn.setEnabled(true);
				opt = 0;
				position = 0;
				btnFinalItem.setEnabled(true);
				btnFirstItem.setEnabled(true);
				nextBtn.setEnabled(true);
				prevBtn.setEnabled(true);
				comarcaField.setEditable(false);
				comarcaField.setText(list.get(0).getNomC());
				provinciaField.setEditable(false);
				provinciaField.setText(list.get(0).getProvincia());
			}
		});
		panel_4.add(cancelBtn);
	}

	public int getDefaultCloseOperation() {
		// TODO Auto-generated method stub
		if (sessio != null){
			sessio.close();
		}
		return super.getDefaultCloseOperation();
	}

	public void inserir(Comarques com) {
		sessio = SessionFactoryUtil.getSessionFactory().openSession();
		Transaction t = sessio.beginTransaction();
		sessio.save(com);

		t.commit();
		sessio.close();
	}

	public void esborrar(Comarques com) {
		sessio = SessionFactoryUtil.getSessionFactory().openSession();
		Transaction t = sessio.beginTransaction();
		sessio.delete(com);
		t.commit();
		sessio.close();
		
	}

	public void llegir() {
		SessionFactory sfact = SessionFactoryUtil.getSessionFactory();
		sessio = sfact.openSession();
		//Comarques com = (Comarques) sessio.get(Comarques.class, "Comarca");
		Query q = sessio.createQuery("from Comarques order by nomC");
		list = q.list();
		firstList = 0;
		lastList = list.size() - 1;
		sessio.close();
	}

	public void modificar(Comarques com) {
		sessio = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction t = sessio.beginTransaction();
        com.setProvincia(provinciaField.getText());
        sessio.update(com);

        t.commit();
        sessio.close();
        
	}
}
