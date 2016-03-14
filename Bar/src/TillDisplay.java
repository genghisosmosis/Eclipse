import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TillDisplay extends JFrame {

	JFrame TillDisplay =new JFrame();
	TillButton tillbutton ;
	static Productpanel PP;
	private static  boolean displaymode;
	private static String displaymessage;
	static JTextArea TillScroll;
	static JTextArea Price;
	public TillDisplay(){
		super();
		CreateUI();
		CreateLayout();


	}



	private void CreateLayout() {
		JPanel master_top = new JPanel(new GridLayout(1,2));  // Grid layout - rows,cols
		JPanel master_bottom = new JPanel(new GridLayout(1,1));
		JPanel till_window = new JPanel();
		Font font = new Font("TimesRoman", Font.BOLD, 96);
		
		Price = new JTextArea();
		Price.setBackground(Color.BLACK);
		Price.setForeground(Color.green);
		Price.setFont(font);
		TillScroll = new JTextArea();
		TillScroll.setBackground(Color.BLACK);
		TillScroll.setForeground(Color.GREEN);
		TillScroll.setSize(till_window.getWidth(), till_window.getHeight());
		till_window.setBackground(Color.BLACK);
		JPanel mgmt_window  = new JPanel(new GridLayout(2,1));
		MgmtPanel MP = new MgmtPanel();
		mgmt_window.add(MP);
		mgmt_window.add(Price);
		till_window.add(TillScroll);
		master_top.add(till_window);
		master_top.setBackground(Color.BLACK);
		master_top.add(mgmt_window);
		PP = new Productpanel();
		master_bottom.add(PP,BorderLayout.CENTER);
		Container windowinterface = this.getContentPane();
		windowinterface.setLayout(new GridLayout(2,1));
		windowinterface.add(master_top);
		windowinterface.add(master_bottom);
		PP.setVisible(displaymode);
		TillScroll.append("YO MAMA\n");
	}

	private void CreateUI() {
		this.setSize(1000,800);
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

public static void pushmessage(String message){
	displaymessage = message;
	TillScroll.append(displaymessage);
}



public static void cleardisplay() {
	TillScroll.setText(null);
	
}

public static void updateprice(String price){
	Price.setText(price);
}
public static void clearprice(String price){
	Price.setText("€0.00");
}

}
