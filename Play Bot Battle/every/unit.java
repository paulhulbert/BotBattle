/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package every;

/**
 *
 * @author Dad
 */
public class unit {
    
    private unitdata data;
    private unitimage picture;
    
    private int playernum;
    
    private jpanel1 panel1;
    private map mainmap;
    
    private int speed;
    
    private boolean goingleft = false;
    private boolean goingright = false;
    private boolean goingup = false;
    private boolean goingdown = false;
    private boolean mousepressed = false;
    
    private int ondeadanimation = 0;
    
    
    public unit(int x, int y, int speed, jpanel1 panel, int playernum)
    {
        super();
        panel1 = panel;
        data = new unitdata(x,y, playernum);
        picture = new unitimage(playernum);
        data.setImagenumber(0);
        this.speed = speed;
        this.playernum = playernum;
    }
    
    
    public void step( graphicscase g )
    {
        
        
        int prex = this.getData().getX();
        int prey = this.getData().getY();
        
        if ( ondeadanimation > 0 && ondeadanimation < 10 && data.getLife() <= 0 ) {
            ondeadanimation++;
        }
        
        if ( data.getLife() <= 0 && ondeadanimation == 0 ) {
            ondeadanimation = 1;
        }
        
        if ( data.getLife() > 0 ) {
            ondeadanimation = 0;
        }
        
        
        boolean ismain = false;
        if ( playernum == panel1.getMainplayernum() ) {
            ismain = true;
        }
        
        paintit(g, ismain, prex, prey);
    }
    
    public void paintit( graphicscase g, boolean ismainchar, int prex, int prey )
    {
        if ( data.getLife() <= 0 ) {
            data.setMousex(data.getOndeadmousex());
            data.setMousey(data.getOndeadmousey());
        }
        
        
        if ( ismainchar ) {
            g.getG2d().translate(prex, prey);
            picture.paintunit(g, 0, 0, constants.findangle(350, 350, data.getMousex(), data.getMousey()), ismainchar, data.getImagenumber(), data, ondeadanimation);
            
            g.getG2d().translate(-prex, -prey);
            return;
        }
        
        
        
        picture.paintunit(g, data.getX(), data.getY(), constants.findangle(350, 350, data.getMousex(), data.getMousey()), ismainchar, data.getImagenumber(), data, ondeadanimation);
        
    }
    
    

    public unitdata getData() {
        return data;
    }

    public void setData(unitdata data) {
        this.data = data;
    }
    
    
    
    public commanddata getcommanddata()
    {
        return new commanddata( goingleft, goingright, goingup, goingdown, panel1.getMousex(playernum), panel1.getMousey(playernum), mousepressed );
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

    public unitimage getPicture() {
        return picture;
    }

    public void setPicture(unitimage picture) {
        this.picture = picture;
    }

    public jpanel1 getPanel1() {
        return panel1;
    }

    public void setPanel1(jpanel1 panel1) {
        this.panel1 = panel1;
    }
    
    
}
