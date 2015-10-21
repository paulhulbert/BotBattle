/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package every;

/**
 *
 * @author Dad
 */
public class bag1 extends unit {
    
    public bag1( int x, int y, jpanel1 panel, int playernum )
    {
        super(x,y,5, panel,playernum);
        reloadrate = 5;
        attack = 9;
        
        this.getData().setImagenumber(1);
        
        this.setComputercontrolled(true);
    }

    @Override
    public void setMainmap(map mainmap) {
        super.setMainmap(mainmap);
        this.setAi(new artificial_intelligence.basicai(this, mainmap));
    }
    
    
    
}
