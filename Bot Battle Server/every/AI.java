/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package every;

/**
 *
 * @author Dad
 */
public class AI {
    
    protected unit mainunit;
    protected map mainmap;

    public AI(unit mainunit, map mainmap) {
        this.mainunit = mainunit;
        this.mainmap = mainmap;
    }

    public void step()
    {
        
    }
    
    
    public map getMainmap() {
        return mainmap;
    }

    public void setMainmap(map mainmap) {
        this.mainmap = mainmap;
    }

    public unit getMainunit() {
        return mainunit;
    }

    public void setMainunit(unit mainunit) {
        this.mainunit = mainunit;
    }
    
    
    
}
