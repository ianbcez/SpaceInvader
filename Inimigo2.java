
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;
import javafx.scene.image.Image;
import javafx.scene.canvas.Canvas;

/**
 * Represents a set of balls that crosses the screen over and over again
 *
 * @author Bernardo Copstein
 */
public class Inimigo2 extends BasicElement {
	private Image image;
    private List<BasicElement> elements;

    public Inimigo2(int px, int py) {
        super(px, py);
        elements = new ArrayList<>();
				image = Params.getInstance().getInimigo2();
    }

    @Override
    public void start() {
        setDirH(1);
        setLargAlt((32 + 8) * 3, 32 + 8 + 32);
				elements.add(new Inimigo2_2(getX(), getY()));
        elements.add(new Inimigo2_2(getX() + 40, getY()));
        elements.add(new Inimigo2_2(getX() + 80, getY()));
  			elements.add(new Inimigo2_2(getX(), getY() + 40));
        elements.add(new Inimigo2_2(getX() + 40, getY() + 40));
        elements.add(new Inimigo2_2(getX() + 80, getY() + 40));
				setSpeed(7*Params.AumentaDificult);
    }

    @Override
    public void testaColisao(Character outro){
        for(int i=0;i<elements.size();i++){
            elements.get(i).testaColisao(outro);
            if (elements.get(i).jaColidiu()){
                elements.remove(i);
                if (elements.size() == 0){
									Params.CONT_RANK+= 3;
                    deactivate();
                }
            }
        }
    }

    @Override
    public void Update() {
        setPosX(getX() + getDirH() * getSpeed());
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).jaColidiu()){
				Params.CONT_RANK += 3;
                elements.get(i).deactivate();
            }else{
                int auxX = elements.get(i).getX();
                elements.get(i).setPosX(auxX + getDirH() * getSpeed());
            }
        }
        if (getX() >= getLMaxH() - getLargura()) {
            //setPosX(getLMaxH() - 1);
            int aux = getDirH();
            setDirH(aux * -1);
            int auxY = getY();
            setPosY(getY() + 20);
            for (int i = 0; i < elements.size(); i++) {
                auxY = elements.get(i).getY()+20;
                elements.get(i).setPosY(auxY);
            }
        } else if (getX() <= getLMinH()) {
            //setPosX(getLMinH() + 1);
            int aux = getDirH();
            setDirH(aux * -1);
            int auxY = getY();
            setPosY(getY() + 20);
            for (int i = 0; i < elements.size(); i++) {
                auxY = elements.get(i).getY()+20;
                elements.get(i).setPosY(auxY);
            }
        }
    }


    @Override
    public void Draw(GraphicsContext graphicsContext){
		graphicsContext.drawImage(image,getX(),getY(),getLargura(),getAltura());
        for(int i=0;i<elements.size();i++){
            elements.get(i).Draw(graphicsContext);
        }
    }
}
