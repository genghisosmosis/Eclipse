

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FunWithButtons extends JFrame
{
    final List<JButton> buttons;
     
    public FunWithButtons( int numberOfButtons )
    {
        super( "Fun with buttons..." );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
         
        Container pane = getContentPane();
        pane.setLayout( new GridLayout( 4, 2 ) );
         
        buttons = new ArrayList<JButton>( numberOfButtons );        
         
        for ( int i = 0; i < numberOfButtons; i++ )
        {
            JButton button = new JButton( new SomeAction( "Button " + i )  );
            pane.add( button );
            // Remember buttons in collection, we might need to access them sometime
            buttons.add( button );
        }
         
        pack();
    }
     
    public static void main( String[] args ) throws InterruptedException, InvocationTargetException
    {
        EventQueue.invokeAndWait( new Runnable()
        {   
            public void run()
            {
                int numberOfButtons = Integer.parseInt(
                    JOptionPane.showInputDialog( "Enter the number of buttons: " ) );
                new FunWithButtons( numberOfButtons ).setVisible( true );
            }
        });
    }
}
 
class SomeAction extends AbstractAction
{
    public SomeAction( String text )
    {
        super( text );
    }
     
    public void actionPerformed( ActionEvent e )
    {
        JOptionPane.showMessageDialog( null, "Action occured: " + getValue( NAME ) );
    }
}
