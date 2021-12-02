package View;

import java.awt.*;

public class BrickView {

    private final Color border;
    private final Color inner;

    public BrickView(Color border, Color inner){
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
