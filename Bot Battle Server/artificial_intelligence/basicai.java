/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package artificial_intelligence;


import every.map;
import every.unit;
import java.util.Random;

/**
 *
 * @author Dad
 */
public class basicai extends every.AI {
    
    private Random random;

    public basicai(unit mainunit, map mainmap) {
        super(mainunit, mainmap);
        
        random = new Random();
    }
    
    @Override
    public void step()
    {
        mainunit.setMousepressed(true);
        
        int targetx = every.constants.getnearestgoodunit(mainunit, mainmap.getUnits(), mainunit.getPanel1().getPlayeramount()).getData().getX();
        int targety = every.constants.getnearestgoodunit(mainunit, mainmap.getUnits(), mainunit.getPanel1().getPlayeramount()).getData().getY();
        
        
        int mousex = targetx;
        int mousey = targety;
        
        int offsetx = random.nextInt() % 60 - 30;
        int offsety = random.nextInt() % 60 - 30;
        
        mousex += offsetx;
        mousey += offsety;
        
        
        if ( mousex == 9999999 ) {
            mainunit.stopdown();
            mainunit.stopup();
            mainunit.stopleft();
            mainunit.stopright();
            mainunit.setMousepressed(false);
            return;
        }
        
        int difx = 350 - mainunit.getData().getX();
        int dify = 350 - mainunit.getData().getY();
        
        mousex += difx;
        mousey += dify;
        
        mainunit.getData().setMousex(mousex);
        mainunit.getData().setMousey(mousey);
        
        
        move();
        
    }
    
    public void move()
    {
        int quadrant = 0;
        
        
        int targetx = every.constants.getnearestgoodunit(mainunit, mainmap.getUnits(), mainunit.getPanel1().getPlayeramount()).getData().getX();
        int targety = every.constants.getnearestgoodunit(mainunit, mainmap.getUnits(), mainunit.getPanel1().getPlayeramount()).getData().getY();
        
        
        int unitx = mainunit.getData().getX();
        int unity = mainunit.getData().getY();
        
        if ( unitx == targetx ) {
            if ( unity > targety ) {
                mainunit.goup();
                mainunit.stopdown();
            } else {
                mainunit.stopup();
                mainunit.godown();
            }
            
            mainunit.stopleft();
            mainunit.stopright();
            return;
        }
        
        if ( unity == targety ) {
            if ( unitx > targetx ) {
                mainunit.goleft();
                mainunit.stopright();
            } else {
                mainunit.stopleft();
                mainunit.goright();
            }
            
            mainunit.stopup();
            mainunit.stopdown();
            return;
        }
        
        
        if ( unitx > targetx ) {
            
            if ( unity > targety ) {
                quadrant = 3;
            } else {
                quadrant = 2;
            }
            
        } else {
            if ( unity > targety ) {
                quadrant = 4;
            } else {
                quadrant = 1;
            }
        }
        
        if ( quadrant == 1 ) {
            mainunit.goup();
            mainunit.goright();
            mainunit.stopleft();
            mainunit.stopdown();
        }
        
        if ( quadrant == 2 ) {
            mainunit.godown();
            mainunit.goright();
            mainunit.stopleft();
            mainunit.stopup();
        }
        
        if ( quadrant == 3 ) {
            mainunit.godown();
            mainunit.goleft();
            mainunit.stopright();
            mainunit.stopup();
        }
        
        if ( quadrant == 4 ) {
            mainunit.goup();
            mainunit.goleft();
            mainunit.stopright();
            mainunit.stopdown();
        }
        
        
//        if ( unitx > targetx ) {
//            mainunit.stopright();
//            mainunit.goleft();
//        } else {
//            mainunit.goright();
//            mainunit.stopleft();
//        }
//        
//        if ( unity > targety ) {
//            mainunit.stopdown();
//            mainunit.goup();
//        } else {
//            mainunit.godown();
//            mainunit.stopup();
//        }
    }
    
}
