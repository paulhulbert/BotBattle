/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package every;

import java.io.Serializable;

/**
 *
 * @author Dad
 */
public class commanddata implements Serializable {

    public boolean goleft;
    public boolean goright;
    public boolean goup;
    public boolean godown;
    public int mousex;
    public int mousey;
    public boolean mousepressed;

    public commanddata(boolean goleft, boolean goright, boolean goup, boolean godown, int mousex, int mousey, boolean mousepressed) {
        this.goleft = goleft;
        this.goright = goright;
        this.goup = goup;
        this.godown = godown;
        this.mousex = mousex;
        this.mousey = mousey;
        this.mousepressed = mousepressed;
    }
    
    
    
}
