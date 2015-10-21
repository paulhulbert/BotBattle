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
    
    private boolean gameover = false;
    private String closer;
    private int closernumber = 99;
    
    private ArrayList<unit> units;
    private Timer timer1;
    private Timer timer2;
    
    private int mousex;
    private int mousey;
    
    private int playeramount = 2;
    
    private map mainmap;
    
    private int mainplayernum;
    
    
    private ArrayList<Socket> sockets;
    private ServerSocket serversocket;
    private ArrayList<ObjectOutputStream> outputs;
    private ArrayList<ObjectInputStream> inputs;
    
    public jpanel1()
    {
        units = new ArrayList<>();
        units.add(new unit(100,100,6, this,0));
//        units.add(new unit(200,100,5, this,1));
//        units.add(new bag1(300, 0, this, 2));
//        units.add(new bag1(600, 0, this, 3));
        
        mainmap = new map(units);
        units.get(0).setMainmap(mainmap);
//        units.get(1).setMainmap(mainmap);
//        units.get(2).setMainmap(mainmap);
//        units.get(3).setMainmap(mainmap);
        
        
        
        sockets = new ArrayList<>();
        outputs = new ArrayList<>();
        inputs = new ArrayList<>();
        
        playeramount = Integer.parseInt(JOptionPane.showInputDialog("How many players?"));
//        
//        playeramount = 1;
        
        if ( playeramount > 5 ) {
            playeramount = 5;
        }
        if ( playeramount < 1 ) {
            playeramount = 1;
        }
        
        if ( playeramount > 1 ) {
            for ( int c = 1; c < playeramount; c++ ) {
                units.add(new unit(100 + c * 55,100 + c * 55,6, this,c));
                units.get(c).setMainmap(mainmap);
            }
        }
        
        
        timer2 = new Timer(25,new timerhandler());
//        timer2.start();
        
        timer1 = new Timer(30,new timerhandlerforoutput());
        
        
        mousex = 0;
        mousey = 0;
        
        mainplayernum = 0; // make this change
        
        
        
        
        
    }
    
    
    public void runserver()
    {
        try
        {
            
            serversocket = new ServerSocket( 45612, 100 );
            
            datapack temp;
                
                temp = new datapack("map");
                    
                    temp.setMapd(mainmap.getData());
            try 
            {
                
                for ( int c = 0; c < playeramount; c++ ) {
                    sockets.add(serversocket.accept());


                    outputs.add(new ObjectOutputStream( sockets.get(c).getOutputStream() ));
                    outputs.get(c).flush();
                    inputs.add(new ObjectInputStream( sockets.get(c).getInputStream() ));






                    
                }
                
                
                for ( int c = 0; c < playeramount; c++ ) {
                    outputs.get(c).writeObject(new detailpack(c));
                    outputs.get(c).flush();
                    
                    outputs.get(c).writeObject(temp);
                    outputs.get(c).flush();
                }
                    
                
                
                    
//                    temp.setUnitd(new unitdata(units.get(0).getData().getX(), units.get(0).getData().getY(), 0));
                    
                    
                    
                    
                    
                    
                timer2.start();
                timer1.start();
                
                process();
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
    
    public void process()
    {
        
        commanddata temp;
        
//        datapart tempout;
        
        
       
        
        while ( true ) {
            
            
            
            
//                everyother = false;
//            outputit();
            
            
            
            for ( int c = 0; c < playeramount; c++ ) {
                try 
                {
                    temp = ( commanddata ) inputs.get(c).readObject();

                    if ( temp != null ) {

                        setunitcommanddata(temp, c);
                    } else {
                        continue;
                    }


                    if ( temp.mousex == -9999 && temp.mousey == -9999 && temp.mousepressed ) {
                        JOptionPane.showMessageDialog(null, "Game ended by Client.");
                        gameover = true;
                        closer = "client";
                        closernumber = c;
                    }

                }
                catch ( ClassNotFoundException e )
                {
                    e.printStackTrace();
                }
                catch ( IOException e )
                {
                }
            }
            
            
            
        }
        
    }
    
    
    public void closeconnections() throws IOException
    {
        for ( int c = 0; c < sockets.size(); c++ ) {
            outputs.get(c).close();
            inputs.get(c).close();
            sockets.get(c).close();
        }
    }
    
    
    public void sendmapdata()
    {
        
        datapack temp;
                
                temp = new datapack("map");
                
                
                mapdata tdata = new mapdata();
                
                ArrayList<wall> twalls = ( ArrayList<wall> )mainmap.getData().getWalls().clone();
                
                tdata.setWalls(twalls);
                
                ArrayList<room> trooms = ( ArrayList<room> )mainmap.getData().getRooms().clone();
                
                tdata.setRooms(trooms);
                    
//                    temp.setMapd(mainmap.getData());
                temp.setMapd(tdata);
                    
                    
        
        if ( playeramount == 0 || !timer1.isRunning() || !timer2.isRunning() ) {
            return;
        }
        for ( int c = 0; c < playeramount; c++ ) {
            try {
                outputs.get(c).writeObject(temp);
                outputs.get(c).flush();
            } catch (IOException ex) {
                Logger.getLogger(jpanel1.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
    }
    
    
    
    @Override
    public void paintComponent( Graphics g )
    {
        super.paintComponent(g);
        
        g.setColor(Color.blue);
        g.fillRect(300, 300, 100, 100);
        
        mainmap.step(g);
        
        
        
    }
    
    
    public void clicker( int x, int y ) {
        
        
        
    }
    
    public void unclicker( int x, int y ) {
        
        if ( x > 600 && x < 1000 && y > 0 && y < 50 ) {
//            exit();
            gameover = true;
            closer = "server";
            
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
        return units.get(playernum).getMousex();
    }
    public void setMousex(int mousex, int playernum) {
        units.get(playernum).setMousex(mousex);
    }
    public int getMousey( int playernum ) {
        return units.get(playernum).getMousey();
    }
    public void setMousey(int mousey, int playernum) {
        units.get(playernum).setMousey(mousey);
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

    public int getPlayeramount() {
        return playeramount;
    }

    public void setPlayeramount(int playeramount) {
        this.playeramount = playeramount;
    }
    
    
    public void setunitcommanddata( commanddata d, int unitnumber )
    {
        units.get(unitnumber).setcommanddata(d);
    }
    
    
    public void senddata()
    {
        
            datapack temp;
            
            
                try {
                    
                    for ( int c = 0; c < units.size(); c++ ) {
                        
                        temp = new datapack("unit");
                        if ( gameover ) {
                            temp.setName("close" + closer);
                        }
                        temp.setUnitd(new unitdata(units.get(c).getData().getX(), units.get(c).getData().getY(), c));
                        
                        temp.getUnitd().setMousex(units.get(c).getMousex());
                        temp.getUnitd().setMousey(units.get(c).getMousey());
                        temp.getUnitd().setImagenumber(units.get(c).getData().getImagenumber());
                        temp.getUnitd().setOndeadmousex(units.get(c).getData().getOndeadmousex());
                        temp.getUnitd().setOndeadmousey(units.get(c).getData().getOndeadmousey());
                        temp.getUnitd().setLife(units.get(c).getData().getLife());
                        temp.getUnitd().setMaxlife(units.get(c).getData().getMaxlife());
                        temp.getUnitd().damagedone = units.get(c).getData().damagedone;
                        temp.getUnitd().kills = units.get(c).getData().kills;
                        
                        
                        for ( int d = 0; d < playeramount; d++ ) {
                            if ( d == closernumber ) {
                                continue;
                            }
                            outputs.get(d).writeObject(temp);
                            outputs.get(d).flush();
                        }
                    }
                    
                    if ( gameover ) {
                        exit();
                    }
                    
                    temp = new datapack("weapons");
                    
                    ArrayList<weapon> tempweap = new ArrayList<>();
                    
                    weapon tempw;
                    
                    if ( mainmap.getWeapons().size() > 0 ) {
                        for ( int c = 0; c < mainmap.getWeapons().size(); c++ ) {
                            tempw = new weapon(mainmap.getWeapons().get(c).getUser(), mainmap.getWeapons().get(c).getAttack(), mainmap.getWeapons().get(c).getSpeed(), mainmap.getWeapons().get(c).getAngle(), mainmap.getWeapons().get(c).getX(), mainmap.getWeapons().get(c).getY());
                        
                            tempweap.add(tempw);
                        }
                    }
                    
                    temp.setWeaps(tempweap);
                    
                    
                    for ( int q = 0; q < playeramount; q++ ) {
                        outputs.get(q).writeObject(temp);
                        outputs.get(q).flush();
                    }
                    
                    
                } catch (IOException event)
                {
                    System.out.println(" " + event.toString());
                }
    }
    
    private class timerhandler implements ActionListener
    {
        @Override
        public void actionPerformed( ActionEvent e ) 
        {
            repaint();
        }
    }
    
    private class timerhandlerforoutput implements ActionListener
    {
        @Override
        public void actionPerformed( ActionEvent e ) 
        {
            senddata();
            
            
        }
    }
}
