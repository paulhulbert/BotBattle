/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package every;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Dad
 */
public class datapack implements Serializable {
    
    public unitdata unitd;
    public mapdata mapd;
    public unitimage pic;
    public ArrayList<weapon> weaps;
    
    public String name;
    
    public datapack( String type )
    {
        name = type;
    }

    public mapdata getMapd() {
        return mapd;
    }

    public void setMapd(mapdata mapd) {
        this.mapd = mapd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public unitimage getPic() {
        return pic;
    }

    public void setPic(unitimage pic) {
        this.pic = pic;
    }

    public unitdata getUnitd() {
        return unitd;
    }

    public void setUnitd(unitdata unitd) {
        this.unitd = unitd;
    }

    public ArrayList<weapon> getWeaps() {
        return weaps;
    }

    public void setWeaps(ArrayList<weapon> weaps) {
        this.weaps = weaps;
    }
    
    
    
}
