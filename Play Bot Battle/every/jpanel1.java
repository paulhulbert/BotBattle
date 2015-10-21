/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package every;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.JPanel;

/**
 *
 * @author Dad
 */
public class jpanel1 extends JPanel {
    
    private ArrayList<unit> units;
    private Timer timer1;
    
    private int mousex;
    private int mousey;
    
    private map mainmap;
    
    private int mainplayernum;
    
    private boolean mousepressed = false;
    
    private boolean tester = false;
    
    
    private Socket socket;
    private ServerSocket serversocket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    
    public jpanel1()
    {
        units = new ArrayList<>();
        units.add(new unit(100,100,5, this,0));
        units.add(new unit(200,100,5, this,1));
        mainmap = new map(units);
        units.get(0).setMainmap(mainmap);
        units.get(1).setMainmap(mainmap);
        
        
        this.setBackground(Color.black);
        
        timer1 = new Timer(25,new timerhandler());
//        timer1.start();
        
        mousex = 0;
        mousey = 0;
        
        mainplayernum = 1; // make this change
        
        
        
        
        
    }
    
    
    public void runserver()
    {
        
        try
        {
            
            
            
            
            try 
            {
                
                //#####################################################
                String ipadd = JOptionPane.showInputDialog(null, "Enter IP address of server.");
                //#####################################################
                socket = new Socket( InetAddress.getByName(ipadd), 45612 );  //change "127.0.0.1" to ipadd
                
                output = new ObjectOutputStream( socket.getOutputStream() );
                output.flush();
                input = new ObjectInputStream( socket.getInputStream() );
                
                while ( inputdetails() ) {
                    
                }
                
                timer1.start();
                
                commandsend();
                
                
//                process();  // ############################replace when ready###############################
            }
            catch ( EOFException e )
            {
                e.printStackTrace();
            }
            finally 
            {
                
                closeconnections();
                
            }
            
            
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
        
        
    }
    
    
    public boolean inputdetails()
    {
        detailpack temp2 = null;
        try 
        {
            if ( true ) {
                temp2 = ( detailpack ) input.readObject();
//                    JOptionPane.showMessageDialog(null, "timerhandler.actionperformed: available");
            }


            if ( temp2 != null ) {
                this.setMainplayernum(temp2.playernum);
                return false;
            }



        }
        catch ( ClassNotFoundException event )
        {
            event.printStackTrace();
        }
        catch ( IOException event )
        {

        }
        
        return true;
    }
    
    public void commandsend()
    {
        
//        commanddata temp;
        
        while ( true ) {
//            try {
//                temp = units.get(0).getcommanddata();
//                
//                output.writeObject(temp);
//                output.flush();
//                
//            } catch (IOException e)
//            {
//                continue;
//            }
        
        
        
        datapack temp2 = null;
        try 
            {
                if ( true ) {
                    temp2 = ( datapack ) input.readObject();
//                    JOptionPane.showMessageDialog(null, "timerhandler.actionperformed: available");
                }
                
                
                if ( temp2 != null ) {
                
                    if ( temp2.name.equals("unit") ) {
                        if ( temp2.getUnitd().getPlayernum() >= units.size() ) {
                            units.add(new unit( temp2.getUnitd().getX(), temp2.getUnitd().getY(), 5, this, temp2.getUnitd().getPlayernum() ));
                        } else {
                            units.get(temp2.getUnitd().getPlayernum()).setData(temp2.getUnitd());
                        }
                    }
                    
                    if ( temp2.name.equals("weapons") ) {
                        mainmap.setWeapons(temp2.getWeaps());
                    }
                    
                    if ( temp2.name.equals("map") ) {
                        mainmap.setData(temp2.getMapd());
                    }
                    
                    if ( temp2.name.equals("picture") ) {
                        units.get(temp2.getPic().playernum).setPicture(temp2.getPic());
                    }
                    if ( temp2.name.equals("closeclient") ) {
                        JOptionPane.showMessageDialog(null, "Game ended by client.");
                        exit();
                    }
                    if ( temp2.name.equals("closeserver") ) {
                        JOptionPane.showMessageDialog(null, "Game ended by server.");
                        exit();
                    }
                }
                
                
                
            }
            catch ( ClassNotFoundException event )
            {
                event.printStackTrace();
            }
            catch ( IOException event )
            {
                
            }
        }
        
    }
    
    public void process()
    {
        
        map temp;
        commanddata temp2;
//        datapart tempout;
        
        
       
        
        while ( true ) {
            
            
            
            
            try {
                temp2 = units.get(mainplayernum).getcommanddata();
                
                
                
                output.writeObject(temp2);
                output.flush();
                
            } catch (IOException e)
            {
                continue;
            }
//                everyother = false;
//            outputit();
            
            
            
            
            try 
            {
                temp = ( map ) input.readObject();
                
                if ( temp != null ) {
                
                    this.mainmap = temp;
                }
                
                
                
                
            }
            catch ( ClassNotFoundException e )
            {
                e.printStackTrace();
            }
            catch ( IOException e )
            {
                continue;
            }
            
            
        }
        
    }
    
    
    public void closeconnections() throws IOException
    {
        
            output.close();
            input.close();
            socket.close();
        
    }
    
    
    
    @Override
    public void paintComponent( Graphics g )
    {
        super.paintComponent(g);
        
        
        mainmap.step(g);
        
    }
    
    
    public void clicker( int x, int y ) {
        
        mousepressed = true;
        
    }
    
    public void unclicker( int x, int y ) {
        
        mousepressed = false;
        
        if ( x > 700 && x < 1000 && y > 0 && y < 50 ) {
            
            commanddata temp;
            
            try {
                temp = units.get(mainplayernum).getcommanddata();
                
                temp.mousex = -9999;
                temp.mousey = -9999;
                
                temp.mousepressed = true;
                
                
                output.writeObject(temp);
                output.flush();
                
            } catch (IOException event)
            {
            }
            
            exit();
            
        }
        
        
    }
    
    public void exit()
    {
        try {
            closeconnections();
        } catch (IOException ex) {
            Logger.getLogger(jpanel1.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }
    
    public void goleft( int playernum )    {
        units.get(playernum).goleft();
    }
    public void stopleft( int playernum )    {
        units.get(playernum).stopleft();
    }
    public void goright( int playernum )    {
        units.get(playernum).goright();
    }
    public void stopright( int playernum )    {
        units.get(playernum).stopright();
    }
    public void goup( int playernum )    {
        units.get(playernum).goup();
    }
    public void stopup( int playernum )    {
        units.get(playernum).stopup();
    }
    public void godown( int playernum )    {
        units.get(playernum).godown();
    }
    public void stopdown( int playernum )    {
        units.get(playernum).stopdown();
    }

    public int getMousex( int playernum ) {
//        return units.get(playernum).getMousex();
        return mousex;
    }
    public void setMousex(int mousex, int playernum) {
//        units.get(playernum).setMousex(mousex);
        this.mousex = mousex;
    }
    public int getMousey( int playernum ) {
//        return units.get(playernum).getMousey();
        return mousey;
    }
    public void setMousey(int mousey, int playernum) {
//        units.get(playernum).setMousey(mousey);
        this.mousey = mousey;
    }

    public int getMainplayernum() {
        return mainplayernum;
    }

    public void setMainplayernum(int mainplayernum) {
        this.mainplayernum = mainplayernum;
    }

    public ArrayList<unit> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<unit> units) {
        this.units = units;
    }
    
    
    private class timerhandler implements ActionListener
    {
        @Override
        public void actionPerformed( ActionEvent e ) 
        {
            repaint();
            commanddata temp;
            
            try {
                temp = units.get(mainplayernum).getcommanddata();
                
                temp.mousepressed = mousepressed;
                
                
                
                
                output.writeObject(temp);
                output.flush();
                
            } catch (IOException event)
            {
            }
            
//            datapack temp = null;
//        try 
//            {
//                if ( true ) {
//                    temp = ( datapack ) input.readObject();
////                    JOptionPane.showMessageDialog(null, "timerhandler.actionperformed: available");
//                }
//                
//                
//                if ( temp != null ) {
//                
//                    if ( temp.name.equals("unit") ) {
//                        units.get(temp.getUnitd().getPlayernum()).setData(temp.getUnitd());
//                    }
//                    
//                    if ( temp.name.equals("map") ) {
//                        mainmap.setData(temp.getMapd());
//                    }
//                    
//                    if ( temp.name.equals("picture") ) {
//                        units.get(temp.getPic().playernum).setPicture(temp.getPic());
//                    }
//                }
//                
//                
//                
//                
//            }
//            catch ( ClassNotFoundException event )
//            {
//                event.printStackTrace();
//            }
//            catch ( IOException event )
//            {
//                
//            }
        }
    }
}
