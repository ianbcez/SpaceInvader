import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;
import javafx.scene.image.Image;
import javafx.scene.canvas.Canvas;

/**
 * Represents the game Gun
 * @author Bernardo Copstein, Rafael Copstein
 */
public class Player extends BasicElement implements KeyboardCtrl{
	private Image image;

    public Player(int px,int py){
        super(px,py);
		image = Params.getInstance().getPlayer();
		setSpeed( 15-(2*Params.AumentaDificult));
    }

    @Override
    public void start() {
        setLimH(20,Params.WINDOW_WIDTH-20);
        setLimV(Params.WINDOW_HEIGHT-100,Params.WINDOW_HEIGHT);
        setLargAlt(32,32);
    }

    @Override
    public void Update() {
        if (jaColidiu()){
            deactivate();

        }
    }

    @Override
    public void OnInput(KeyCode keyCode, boolean isPressed) {
        if (keyCode == KeyCode.LEFT){
            if ( !(getX() < 0) ) setPosX(getX() - getSpeed());
        }
        if (keyCode == KeyCode.RIGHT){
            if ( !(getX() >= getLMaxH() - getLargura()) ) setPosX(getX() + getSpeed());
        }
        if (keyCode == KeyCode.SPACE && isPressed){
            Game.getInstance().addChar(new Shot(getX()+12,getY()-17));
        }
        //if (keyCode == KeyCode.UP) do nothing
        //if (keyCode == KeyCode.DOWN) do nothing
    }

    @Override
    public void Draw(GraphicsContext graphicsContext) {
		graphicsContext.drawImage(image,getX(),getY(),getLargura(),getAltura());
    }
}
