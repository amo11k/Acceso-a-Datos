package programesT5;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.hibernate.Query;
import org.hibernate.Session;

import exemples.SessionFactoryUtil;
import geo.Comarques;
import geo.Poblacions;

public class Pantalla_Veure_Pobles_Comarca_Combo_List extends JFrame implements ActionListener, ListSelectionListener {

    private static final long serialVersionUID = 1L;
    JLabel etiqueta = new JLabel("Comarca:");
    JLabel et_ini = new JLabel("Introdueix la comarca:");
    JComboBox<String> com = new JComboBox<String>();
    DefaultListModel<String> listModel = new DefaultListModel<String>();
    JList<String> area = new JList<String>(listModel);
    JTextField peu = new JTextField();
    Session sessio=null;
    
    public static void main(String[] args) {
        final Pantalla_Veure_Pobles_Comarca_Combo_List finestra = new Pantalla_Veure_Pobles_Comarca_Combo_List();
        finestra.iniciar();
    }
    
    // en iniciar posem un contenidor per als elements anteriors
    public void iniciar() {

        sessio = SessionFactoryUtil.getSessionFactory().openSession();
        this.setBounds(100, 100, 450, 300);
        this.setLayout(new BorderLayout());
        // contenidor per als elements
        JPanel panell1 = new JPanel(new FlowLayout());
        panell1.add(et_ini);
        panell1.add(com);
        getContentPane().add(panell1,BorderLayout.NORTH);
        
        JPanel panell2 = new JPanel(new BorderLayout());
        panell2.add(etiqueta,BorderLayout.NORTH);
        area.setForeground(Color.blue);
        JScrollPane scroll = new JScrollPane(area);
        panell2.add(scroll,BorderLayout.CENTER);
        getContentPane().add(panell2,BorderLayout.CENTER);
        getContentPane().add(peu,BorderLayout.SOUTH);

        agafarComarques();

        setVisible(true);
        
        com.addActionListener(this);
        area.addListSelectionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == com) {
            etiqueta.setText("Llista de pobles de la comarca: " + com.getSelectedItem());
            visualitzaCom(com.getSelectedItem().toString());
        }
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e){
        JList<?> l = (JList<?>) e.getSource();
        if (l.getSelectedIndex()>=0){
            visualitzaInstituts(l.getSelectedValue().toString());
        }
    }

    private void visualitzaCom(String comarca) {

        // Instruccions per a llegir la comarca introduïda (s'ha de deixar en un objecte Comarques).
        // S'ha de cuidar que si no exiteix la comarca, en el JList es pose que no existeix.
        // La manera d'anar introduint informació en el JList és a través del DefaultListModel:
        // listModel.addElement("Linia que es vol introduir ")
    	
        Comarques com = (Comarques) sessio.get(Comarques.class, comarca);
        listModel.clear();
        if(com!=null){
	        for (Poblacions p : com.getPoblacionses())
	        	listModel.addElement(p.getNom()); 
        }else{
        	listModel.addElement("Comarca no valida");
        }

    }
    
    @SuppressWarnings("unchecked")
	private void agafarComarques(){
        Query q = sessio.createQuery("select nomC from Comarques order by nomC");
        for(String s : (List<String>)q.list())
        	com.addItem(s);
    }
    
    private void visualitzaInstituts(String poble){
        // Instruccions per a mostrar el número d'Instituts del poble seleccionat
    	Poblacions p = (Poblacions) sessio.createQuery("from Poblacions where nom ='"+poble+"'").uniqueResult();
        peu.setText(poble+" té "+p.getInstitutses().size()+" instituts");
    }
    
    @Override
    public int getDefaultCloseOperation() {
    	// TODO Auto-generated method stub
    	sessio.close();
    	return super.getDefaultCloseOperation();
    }
    
}