import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;
import javafx.scene.image.Image;
import javafx.scene.canvas.Canvas;

/**
 * Represents a simple ball that crosses the screen over and over again
 * @author Bernardo Copstein and Rafael Copstein
 */
public class Inimigo1 extends BasicElement{
    private Image image;

    public Inimigo1(int px,int py){
        super(px,py);
		image = Params.getInstance().getInimigo1();
    }

    @Override
    public void start(){
        setDirH(1);
        setSpeed(5*Params.AumentaDificult);
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
			Params.CONT_RANK++;
            deactivate();
        }else{
            setPosX(getX() + getDirH() * getSpeed());
            // Se chegou no lado direito da tela ...
              if (getX() >= getLMaxH()) {
                // Reposiciona no lado esquerdo e ...
                setPosX(getLMaxH() - 1);
                setDirH(-1);
                int oldY = getY();
                setPosY(oldY + 20);
            } else if (getX() <= getLMinH()) {
                setPosX(getLMinH() + 1);
                setDirH(1);
                int oldY = getY();
                setPosY(oldY + 20);

            }
        }
    }
    public void Draw(GraphicsContext graphicsContext){
        //graphicsContext.setFill(Paint.valueOf("#0000FF"));
        //graphicsContext.fillOval(getX(), getY(), 32, 32);
        graphicsContext.drawImage(image,getX(),getY(),getLargura(),getAltura());
    }
}
