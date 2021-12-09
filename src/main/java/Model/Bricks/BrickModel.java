package Model.Bricks;

import java.awt.*;


/**
 * Created by filippo on 04/09/16.
 * abstract class for BrickModel
 */
public class BrickModel {

    private final Color border;
    private final Color inner;

    private String name;

    /**
     * class constructor for BrickModel class
     * @param name name of brick
     */
    public BrickModel(String name, Color border, Color inner){

        this.name = name;

        this.border = border;
        this.inner = inner;
    }

    /**
     * method to get brick border color
     * @return brick border color
     */
    public Color getBorderColor(){
        return  border;
    }

    /**
     * method to get brick inner color
     * @return brick inner color
     */
    public Color getInnerColor(){
        return inner;
    }





}





