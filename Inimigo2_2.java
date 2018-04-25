import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;
import javafx.scene.image.Image;
import javafx.scene.canvas.Canvas;

/**
 * Represents a simple ball that crosses the screen over and over again
 * @author Bernardo Copstein and Rafael Copstein
 */
public class Inimigo2_2 extends BasicElement{
	private Image image;

	public Inimigo2_2(int px,int py){
		super(px,py);
		image = Params.getInstance().getINIMIGO2_2();
	}

	@Override
	public void start(){
		setDirH(1);
	}

	 @Override
	public void testaColisao(Character outro){
		// Não verifica colisão de um tiro com outro tiro
		if (!(outro instanceof Shot) || (outro instanceof ShotDown)){
			return;
		}else{
			super.testaColisao(outro);
		}
	}



	@Override
	public void Update(){
		if (jaColidiu()){
			deactivate();
		}else{
			setPosX(getX() + getDirH() * getSpeed());
			// Se chegou no lado direito da tela ...
			if (getX() >= getLMaxH()){
				// Reposiciona no lado esquerdo e ...
				setPosX(getLMinH());
				// Sorteia o passo de avanço [1,5]
				setSpeed(Params.getInstance().nextInt(5)+1);
			}
		}
	}

	public void Draw(GraphicsContext graphicsContext){
		graphicsContext.drawImage(image,getX(),getY(),getLargura(),getAltura());
	}
}
