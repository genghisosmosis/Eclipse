import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class TillDisplay extends JFrame {

	JFrame TillDisplay =new JFrame();
	TillButton tillbutton ;
	static Productpanel PP;
private static  boolean displaymode;


	public TillDisplay(){
		super();
		CreateUI();
		CreateLayout();


	}



	private void CreateLayout() {
		JPanel master_top = new JPanel(new GridLayout(1,2));  // Grid layout - rows,cols
		JPanel master_bottom = new JPanel(new GridLayout(1,1));
		JPanel till_window = new JPanel();
		till_window.setBackground(Color.BLACK);
		JPanel mgmt_window  = new JPanel();
		MgmtPanel MP = new MgmtPanel();
		mgmt_window.add(MP);
		master_top.add(till_window);
		master_top.add(mgmt_window);
		PP = new Productpanel();
		master_bottom.add(PP,BorderLayout.CENTER);
		Container windowinterface = this.getContentPane();
		windowinterface.setLayout(new GridLayout(2,1));
		windowinterface.add(master_top);
		windowinterface.add(master_bottom);
		PP.setVisible(displaymode);

	}

	private void CreateUI() {
		this.setSize(800,600);
		this.setResizable(true);
		this.setTitle("BarMan V1.0");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);


	}

	public static void ResetUI(){
	
		PP.redo();
		
		

	}
	public static void setinterface(boolean newdisplaymode){
		displaymode = newdisplaymode;
			PP.setVisible(displaymode);
		
	}


	


	
}
