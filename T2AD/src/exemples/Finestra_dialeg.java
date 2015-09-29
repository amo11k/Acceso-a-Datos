package exemples;
     

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Finestra_dialeg extends JDialog{
    public Finestra_dialeg(Finestra f){
        super(f,"Diàleg",true);
        this.setSize(200,100);
        JPanel panell=new JPanel();
        panell.add(new JLabel("Hola, què tal?"));
        this.getContentPane().add(panell);
        this.setVisible(true);
    }
}