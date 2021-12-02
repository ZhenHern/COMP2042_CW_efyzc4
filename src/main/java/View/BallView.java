package View;

import java.awt.*;

public class BallView {

    private Color border;
    private Color inner;

    public BallView(Color inner, Color border){
        this.setBorder(border);
        this.setInner(inner);
    }


    public Color getBorder() {
        return border;
    }

    public void setBorder(Color border) {
        this.border = border;
    }

    public Color getInner() {
        return inner;
    }

    public void setInner(Color inner) {
        this.inner = inner;
    }
}
