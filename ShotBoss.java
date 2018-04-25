import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;
import javafx.scene.image.Image;
import javafx.scene.canvas.Canvas;
/**
 *
 * @author Bernardo
 */
public class ShotBoss extends ShotDown{
private Image image;
	public ShotBoss(int px, int py) {
        super(px, py);
				image = Params.getInstance().getTiroBOSS();
    }

    @Override
    public void start(){
        setDirV(1);
        setSpeed(8*Params.AumentaDificult);
    }

		@Override
		public void Draw(GraphicsContext graphicsContext){
			graphicsContext.drawImage(image,getX(),getY(),getLargura(),getAltura());
		}

}
