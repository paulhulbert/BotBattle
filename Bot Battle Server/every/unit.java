/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package every;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Dad
 */
public class unit {
    
    private Random rand = new Random();
    
    protected unitdata data;
    protected unitimage picture;
    
    protected int playernum;
    
    protected jpanel1 panel1;
    protected map mainmap;
    
    protected int speed;
    protected int speedlevelcounter = 0;
    protected int reloadlevelcounter = 0;
    protected int accuracylevelcounter = 0;
    protected int attacklevelcounter = 0;
    
    protected boolean goingleft = false;
    protected boolean goingright = false;
    protected boolean goingup = false;
    protected boolean goingdown = false;
    protected boolean mousepressed = false;
    
    protected boolean computercontrolled = false;
    protected AI ai;
    
    
    
    
    
    //##################################Stats##########################
    protected int attack = 10;
    protected int reloadrate = 10;
    protected int reload = 0;
    protected boolean alive = true;
    protected int accuracy = 60;
    //#################################################################
    
    
    public unit(int x, int y, int speed, jpanel1 panel, int playernum)
    {
        
        panel1 = panel;
        data = new unitdata(x,y, playernum);
        data.setLife(100);
        data.setMaxlife(100);
        picture = new unitimage( playernum );
        data.setImagenumber(0);
        this.speed = speed;
        this.playernum = playernum;
    }
    
    
    public void step( graphicscase g )
    {





        int prex = this.getData().getX();
        int prey = this.getData().getY();

        if ( reload < 100 ) {
            reload += reloadrate;
        }




        if ( alive && data.getLife() <= 0 ) {
            data.setOndeadmousex(data.getMousex());
            data.setOndeadmousey(data.getMousey());
            mainmap.getWeapons().add(new weapon( playernum, -30, 0, constants.findangle(350, 350, data.getMousex(), data.getMousey()), data.getX(), data.getY() ));
        
            alive = false;
        }


        boolean ismain = false;
        if ( playernum == panel1.getMainplayernum() ) {
            ismain = true;
        }
        
        if ( computercontrolled ) {
            if ( constants.distance(data.getX(), data.getY(), constants.getnearestgoodunit(this, mainmap.getUnits(), panel1.getPlayeramount()).getData().getX(), constants.getnearestgoodunit(this, mainmap.getUnits(), panel1.getPlayeramount()).getData().getY()) > 1000 ) {
                return;
            }
            ai.step();
        }

        if ( data.getLife() > 0 ) {

            if ( mousepressed && reload >= 100 ) {
                attack();
                reload = 0;
            }

            movement();
        }


        paintit(g, ismain, prex, prey);
    }
    
    public void paintit( graphicscase g, boolean ismainchar, int prex, int prey )
    {
        if ( !alive ) {
            data.setMousex(data.getOndeadmousex());
            data.setMousey(data.getOndeadmousey());
        }
        
        if ( ismainchar ) {
            g.getG2d().translate(prex, prey);
            picture.paintunit(g, 0, 0, constants.findangle(350, 350, data.getMousex(), data.getMousey()), ismainchar, data.getImagenumber(), data);
            
            g.getG2d().translate(-prex, -prey);
            return;
        }
        
        
        
        picture.paintunit(g, data.getX(), data.getY(), constants.findangle(350, 350, data.getMousex(), data.getMousey()), ismainchar, data.getImagenumber(), data);
        
    }
    
    
    public void movement()
    {
        
        int movexdir = 0;
        int moveydir = 0;
        boolean angled = false;
        double anglednum = 1;
        
//        if ( goingleft ) {
//            data.setX(data.getX() - speed);
//        }
//        if ( goingright ) {
//            data.setX(data.getX() + speed);
//        }
//        if ( goingup ) {
//            data.setY(data.getY() - speed);
//        }
//        if ( goingdown ) {
//            data.setY(data.getY() + speed);
//        }
        
        if ( goingleft || goingright ) {
            if ( goingup || goingdown ) {
                angled = true;
                anglednum = Math.sqrt(2);
            }
        }
        
        if ( goingleft && !goingright ) {
            if ( angled ) {
                movexdir = -1;
            } else {
                movexdir = -1;
            }
        }
        if ( !goingleft && goingright ) {
            if ( angled ) {
                movexdir = 1;
            } else {
                movexdir = 1;
            }
        }
        if ( !goingup && goingdown ) {
            if ( angled ) {
                moveydir = 1;
            } else {
                moveydir = 1;
            }
        }
        if ( goingup && !goingdown ) {
            if ( angled ) {
                moveydir = -1;
            } else {
                moveydir = -1;
            }
        }
        
        if ( movexdir != 0 || moveydir != 0 ) {
            speedlevelcounter++;
            if ( speedlevelcounter > 1000 ) {
                speed++;
                speedlevelcounter = 0;
            }
        }
        
        for ( int c = 0; c < speed / anglednum; c++ ) {
            if ( mainmap.canmoveinto(data.getX() + movexdir, data.getY(), playernum) ) {
                data.setX(data.getX() + movexdir);
            }
            if ( mainmap.canmoveinto(data.getX(), data.getY() + moveydir, playernum) ) {
                data.setY(data.getY() + moveydir);
            }
        }
        
        
        
        
    }
    
    public void attack()
    {
        
        mainmap.getWeapons().add(new weapon( playernum, attack, 10, constants.findangle(350, 350, data.getMousex() - accuracy / 2 + rand.nextInt(accuracy), data.getMousey() - accuracy / 2 + rand.nextInt(accuracy)), data.getX(), data.getY() ));
        
        
        reloadlevelcounter++;
        if ( reloadlevelcounter > 200 ) {
            reloadlevelcounter = 0;
            reloadrate++;
        }
    }
    
    public void hitit( weapon w )
    {
        data.setLife(data.getLife() - w.getAttack());
        
        mainmap.getUnits().get(w.getUser()).hittarget();
        
        if ( data.getLife() <= 0 ) {
            mainmap.getUnits().get(w.getUser()).killtarget();
        }
        
        if ( w.getAttack() < 0 ) {
            data.setMaxlife(data.getMaxlife() + 3);
        }
        
        if ( data.getLife() > data.getMaxlife() ) {
            data.setLife(data.getMaxlife());
        }
    }

    public unitdata getData() {
        return data;
    }

    public void setData(unitdata data) {
        this.data = data;
    }
    
    public void setcommanddata( commanddata datapack )
    {
        
        mousepressed = datapack.mousepressed;
        goingleft = datapack.goleft;
        goingright = datapack.goright;
        goingup = datapack.goup;
        goingdown = datapack.godown;
        
        
        
        data.setMousex(datapack.mousex);
        data.setMousey(datapack.mousey);
    }
    
    
    
    public void goleft()    {
        goingleft = true;
    }
    public void stopleft()    {
        goingleft = false;
    }
    public void goright()    {
        goingright = true;
    }
    public void stopright()    {
        goingright = false;
    }
    public void goup()    {
        goingup = true;
    }
    public void stopup()    {
        goingup = false;
    }
    public void godown()    {
        goingdown = true;
    }
    public void stopdown()    {
        goingdown = false;
    }
    public map getMainmap() {
        return mainmap;
    }
    public void setMainmap(map mainmap) {
        this.mainmap = mainmap;
    }
    public int getMousex() {
        return data.getMousex();
    }
    public void setMousex(int mousex) {
        data.setMousex(mousex);
    }
    public int getMousey() {
        return data.getMousey();
    }
    public void setMousey(int mousey) {
        data.setMousey(mousey);
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public boolean isGoingdown() {
        return goingdown;
    }

    public void setGoingdown(boolean goingdown) {
        this.goingdown = goingdown;
    }

    public boolean isGoingleft() {
        return goingleft;
    }

    public void setGoingleft(boolean goingleft) {
        this.goingleft = goingleft;
    }

    public boolean isGoingright() {
        return goingright;
    }

    public void setGoingright(boolean goingright) {
        this.goingright = goingright;
    }

    public boolean isGoingup() {
        return goingup;
    }

    public void setGoingup(boolean goingup) {
        this.goingup = goingup;
    }

    public int getLife() {
        return data.getLife();
    }

    public void setLife(int life) {
        data.setLife(life);
    }

    public boolean isMousepressed() {
        return mousepressed;
    }

    public void setMousepressed(boolean mousepressed) {
        this.mousepressed = mousepressed;
    }

    public jpanel1 getPanel1() {
        return panel1;
    }

    public void setPanel1(jpanel1 panel1) {
        this.panel1 = panel1;
    }

    public unitimage getPicture() {
        return picture;
    }

    public void setPicture(unitimage picture) {
        this.picture = picture;
    }

    public int getPlayernum() {
        return playernum;
    }

    public void setPlayernum(int playernum) {
        this.playernum = playernum;
    }

    public int getReload() {
        return reload;
    }

    public void setReload(int reload) {
        this.reload = reload;
    }

    public int getReloadrate() {
        return reloadrate;
    }

    public void setReloadrate(int reloadrate) {
        this.reloadrate = reloadrate;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public AI getAi() {
        return ai;
    }

    public void setAi(AI ai) {
        this.ai = ai;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isComputercontrolled() {
        return computercontrolled;
    }

    public void setComputercontrolled(boolean computercontrolled) {
        this.computercontrolled = computercontrolled;
    }
    
    
    
    public void hittarget()
    {
        data.damagedone += attack;
        
        accuracylevelcounter++;
        if ( accuracylevelcounter > 15 ) {
            accuracylevelcounter = 0;
            if ( accuracy > 1 ) {
                accuracy--;
            }
        }
    }
    
    
    public void killtarget()
    {
        data.kills++;
        
        attacklevelcounter++;
        
        if ( attacklevelcounter > 5 ) {
            attacklevelcounter = 0;
            attack += 2;
        }
        
        
    }
    
    
}
