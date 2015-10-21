/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package every;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Dad
 */
public class map implements Serializable {
    
    private mapdata data;
    private ArrayList<unit> units;
    private ArrayList<weapon> weapons;
    
    private Random rand = new Random();
    
    public map( ArrayList<unit> units1 )
    {
        data = new mapdata();
        units = units1;
        weapons = new ArrayList<>();
        
        
        
        
        data.getRooms().add(new room( this, 1 ));
        
        
        
    }
    
    
    public void step(Graphics g) {
        
        
        boolean map_updated = false;
        
        
        int xa = units.get(0).getData().getX();
        int ya = units.get(0).getData().getY();
        
        graphicscase g2d = new graphicscase((Graphics2D) g,-xa + 350,-ya + 350);
        
//        g2d.translate(xa, ya);
        
        
        
        
        
        for ( int c = 0; c < data.getWalls().size(); c++ ) {
            data.getWalls().get(c).step(g2d);
        }
        
        for ( int c = 0; c < units.size(); c++ ) {
            units.get(c).step(g2d);
            
            
            
//            if ( c < units.get(0).getPanel1().getPlayeramount() ) {
//                for ( int d = 0; d < data.getEntrances().size(); d++ ) {
//                    if ( !data.getEntrances().get(d).connected && constants.distance(units.get(c).getData().getX(), units.get(c).getData().getY(), data.getEntrances().get(d).x, data.getEntrances().get(d).y) < 600 ) {
//                        data.getRooms().add(new room( this, 1, data.getEntrances().get(d) ));
//                        data.getEntrances().remove(d);
//                        map_updated = true;
//                        g.setColor(Color.MAGENTA);
//                        g.drawLine(units.get(c).getData().getX(), units.get(c).getData().getY(), data.getEntrances().get(d).x, data.getEntrances().get(d).y);
//                        g.drawString(" " + data.getEntrances().size(), units.get(c).getData().getX() + 50, units.get(c).getData().getY());
//                        
//                    }
//                }
//            }
        }
        
        room temproom;
        
        for ( int c = 0; c < units.get(0).getPanel1().getPlayeramount(); c++ ) {
            temproom = findroomforunit(units.get(c));
            if ( !temproom.hasdownroom ) {
                data.getRooms().add(new room(this, rand.nextInt(6), temproom, 3));
                map_updated = true;
                
                
                int amountofbags = rand.nextInt(3) + 1;
                
               
                room inroom = data.getRooms().get(data.getRooms().size() - 1);
                
                if ( data.getRooms().size() < 5 ) {
                    amountofbags = 0;
                }
                
                
                int terrainxmin = inroom.getX() + 90;
                int terrainxmax = inroom.getX() + 610;
                int terrainymin = inroom.getY() + 90;
                int terrainymax = inroom.getY() + 610;
                
                if ( amountofbags > 0 ) {
                    for ( int d = 0; d < amountofbags; d++ ) {
                        int unitx = terrainxmin + rand.nextInt(terrainxmax - terrainxmin);
                        int unity = terrainymin + rand.nextInt(terrainymax - terrainymin);

                        if ( canmoveinto(unitx, unity, 0) ) {
                            units.add(new bag1(unitx, unity, units.get(0).getPanel1(), units.size()));
                            units.get(units.size() - 1).setMainmap(this);
                        } else {
                            d--;
                            continue;
                        }
                    }
                }
                
            }
            if ( !temproom.hasuproom ) {
                data.getRooms().add(new room(this, rand.nextInt(6), temproom, 1));
                map_updated = true;
                
                int amountofbags = rand.nextInt(3) + 1;
                
                if ( data.getRooms().size() < 5 ) {
                    amountofbags = 0;
                }
                
                room inroom = data.getRooms().get(data.getRooms().size() - 1);
                
                int terrainxmin = inroom.getX() + 90;
                int terrainxmax = inroom.getX() + 610;
                int terrainymin = inroom.getY() + 90;
                int terrainymax = inroom.getY() + 610;
                
                if ( amountofbags > 0 ) {
                    for ( int d = 0; d < amountofbags; d++ ) {
                        int unitx = terrainxmin + rand.nextInt(terrainxmax - terrainxmin);
                        int unity = terrainymin + rand.nextInt(terrainymax - terrainymin);

                        if ( canmoveinto(unitx, unity, 0) ) {
                            units.add(new bag1(unitx, unity, units.get(0).getPanel1(), units.size()));
                            units.get(units.size() - 1).setMainmap(this);
                        } else {
                            d--;
                            continue;
                        }
                    }
                }
            }
            if ( !temproom.hasrightroom ) {
                data.getRooms().add(new room(this, rand.nextInt(6), temproom, 2));
                map_updated = true;
                
                int amountofbags = rand.nextInt(3) + 1;
                
                room inroom = data.getRooms().get(data.getRooms().size() - 1);
                
                if ( data.getRooms().size() < 5 ) {
                    amountofbags = 0;
                }
                
                int terrainxmin = inroom.getX() + 90;
                int terrainxmax = inroom.getX() + 610;
                int terrainymin = inroom.getY() + 90;
                int terrainymax = inroom.getY() + 610;
                
                if ( amountofbags > 0 ) {
                    for ( int d = 0; d < amountofbags; d++ ) {
                        int unitx = terrainxmin + rand.nextInt(terrainxmax - terrainxmin);
                        int unity = terrainymin + rand.nextInt(terrainymax - terrainymin);

                        if ( canmoveinto(unitx, unity, 0) ) {
                            units.add(new bag1(unitx, unity, units.get(0).getPanel1(), units.size()));
                            units.get(units.size() - 1).setMainmap(this);
                        } else {
                            d--;
                            continue;
                        }
                    }
                }
            }
            if ( !temproom.hasleftroom ) {
                data.getRooms().add(new room(this, rand.nextInt(6), temproom, 4));
                map_updated = true;
                
                int amountofbags = rand.nextInt(2) + 1;
                
                room inroom = data.getRooms().get(data.getRooms().size() - 1);
                
                if ( data.getRooms().size() < 5 ) {
                    amountofbags = 0;
                }
                
                int terrainxmin = inroom.getX() + 90;
                int terrainxmax = inroom.getX() + 610;
                int terrainymin = inroom.getY() + 90;
                int terrainymax = inroom.getY() + 610;
                
                if ( amountofbags > 0 ) {
                    for ( int d = 0; d < amountofbags; d++ ) {
                        int unitx = terrainxmin + rand.nextInt(terrainxmax - terrainxmin);
                        int unity = terrainymin + rand.nextInt(terrainymax - terrainymin);

                        if ( canmoveinto(unitx, unity, 0) ) {
                            units.add(new bag1(unitx, unity, units.get(0).getPanel1(), units.size()));
                            units.get(units.size() - 1).setMainmap(this);
                        } else {
                            d--;
                            continue;
                        }
                    }
                }
            }
        }
        
        if ( map_updated ) {
            units.get(0).getPanel1().sendmapdata();
            
        }
        
        g.setColor(Color.CYAN);
        g.fillRect(findroomforunit(units.get(0)).getX() + 350, findroomforunit(units.get(0)).getY() + 350, 30, 30);
        
        
        if ( weapons.size() > 0 ) {
            for ( int c = 0; c < weapons.size(); c++ ) {
                weapons.get(c).step(g2d);
                if ( checkweaphitunit(c) || checkweaphitwall(c) ) {
                    weapons.remove(c);
                    c--;
                }
            }
        }
        
        
        
//        g2d.translate(-xa, -ya);
    }
    
    /**Returns true if a circle centered at x,y with a radius of 25 can fit into that spot on the map.*/
    public boolean canmoveinto( int x, int y, int unitnumber )
    {
        for ( int c = 0; c < data.getWalls().size(); c++ ) {
            if ( Math.abs(data.getWalls().get(c).distancefromwall(x, y)) < 25 ) {
                return false;
            }
            
            
            if ( x > data.getWalls().get(c).getX() && x < data.getWalls().get(c).getX() + data.getWalls().get(c).getWidth() && y > data.getWalls().get(c).getY() && y < data.getWalls().get(c).getY() + data.getWalls().get(c).getHeight() ) {
                return false;
            }
        }
        
        for ( int d = 0; d < units.size(); d++ ) {
            if ( d == unitnumber || units.get(d).getLife() <= 0 ) {
                continue;
            }
            if ( constants.distance(x, y, units.get(d).getData().getX(), units.get(d).getData().getY()) < 50 ) {
                return false;
            }
        }
        
        return true;
    }

    public mapdata getData() {
        return data;
    }

    public void setData(mapdata data) {
        this.data = data;
    }

    public ArrayList<unit> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<unit> units) {
        this.units = units;
    }

    public ArrayList<weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(ArrayList<weapon> weapons) {
        this.weapons = weapons;
    }
    
    public boolean checkweaphitunit( int weapnum )
    {
        
        for ( int c = 0; c < units.size(); c++ ) {
            if ( units.get(c).getLife() > 0 && c != weapons.get(weapnum).getUser() && constants.distance(units.get(c).getData().getX(), units.get(c).getData().getY(), weapons.get(weapnum).getX(), weapons.get(weapnum).getY()) < 35 ) {
                units.get(c).hitit(weapons.get(weapnum));
                return true;
            }
        }
        
        return false;
    }
    
    public boolean checkweaphitwall( int weapnum )
    {
        for ( int c = 0; c < data.getWalls().size(); c++ ) {
            if ( Math.abs(data.getWalls().get(c).distancefromwall(weapons.get(weapnum).getX(), weapons.get(weapnum).getY())) < 10 ) {
                return true;
            }
            
        }
        
        
        
        return false;
    }
    
    
    /**
     @param u unit that the function will search for the current room
     * 
     * returns the room that the given unit is in.
     */
    public room findroomforunit( unit u )
    {
        int x = u.getData().getX();
        int y = u.getData().getY();
        
        for ( int c = 0; c < data.getRooms().size(); c++ ) {
            if ( x > data.getRooms().get(c).getX() && x < data.getRooms().get(c).getX() + 700 && y > data.getRooms().get(c).getY() && y < data.getRooms().get(c).getY() + 700 ) {
                return data.getRooms().get(c);
            }
        }
        
        
        return null;
    }
    
    /**returns the room with the roomlistx and roomlisty given.  returns null if it does not exist.*/
    public room findroomatspot( int listx, int listy )
    {
        for ( int c = 0; c < data.getRooms().size(); c++ ) {
            if ( data.getRooms().get(c).getRoomlistx() == listx && data.getRooms().get(c).getRoomlisty() == listy ) {
                return data.getRooms().get(c);
            }
        }
        
        
        return null;
    }
    
    
    
}
