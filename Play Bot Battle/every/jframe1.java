/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package every;

import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Dad
 */
public class jframe1 extends JFrame implements KeyListener {
    
    private jpanel1 panel1;
    
    
    public jframe1()
    {
        super( "'Bot Battle" );
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setSize(800, 700);
        this.setLocation(300, 0);
        
        this.setResizable(false);
        
        panel1 = new jpanel1();
        
        add(panel1);
        
        addKeyListener(this);
        addMouseMotionListener( new mousemove() );
        addMouseListener(new mouseclick());
    }
    
    public void runserver()
    {
        panel1.runserver();
    }
    
    
    @Override
    public void keyPressed( KeyEvent e )
    {
        if ( e.getKeyCode() == 37 ) {
            panel1.goleft(panel1.getMainplayernum());
        }
        if ( e.getKeyCode() == 38 ) {
            panel1.goup(panel1.getMainplayernum());
        }
        if ( e.getKeyCode() == 39 ) {
            panel1.goright(panel1.getMainplayernum());
        }
        if ( e.getKeyCode() == 40 ) {
            panel1.godown(panel1.getMainplayernum());
        }
    }
    
    
    
    @Override
    public void keyReleased( KeyEvent e )
    {
        if ( e.getKeyCode() == 37 ) {
            panel1.stopleft(panel1.getMainplayernum());
        }
        if ( e.getKeyCode() == 38 ) {
            panel1.stopup(panel1.getMainplayernum());
        }
        if ( e.getKeyCode() == 39 ) {
            panel1.stopright(panel1.getMainplayernum());
        }
        if ( e.getKeyCode() == 40 ) {
            panel1.stopdown(panel1.getMainplayernum());
        }
        
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
    private class mousemove extends MouseMotionAdapter
    {
        @Override
        public void mouseMoved( MouseEvent e )
        {
            panel1.setMousex(e.getX() - 8,panel1.getMainplayernum());
            panel1.setMousey(e.getY() - 30,panel1.getMainplayernum());
        }
        
        @Override
        public void mouseDragged( MouseEvent e )
        {
            panel1.setMousex(e.getX() - 8,panel1.getMainplayernum());
            panel1.setMousey(e.getY() - 30,panel1.getMainplayernum());
        }
        
    }
    
    private class mouseclick extends MouseAdapter
    {
        @Override
        public void mousePressed( MouseEvent e )
        {
            int mouseX = e.getX() - 8;
            int mouseY = e.getY() - 30;
            
            panel1.clicker(mouseX, mouseY);
        }
        
        @Override
        public void mouseReleased( MouseEvent e )
        {
            int mouseX = e.getX() - 8;
            int mouseY = e.getY() - 30;
            
            panel1.unclicker(mouseX, mouseY);
        }
        
    }
    
}
