import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;
import javafx.scene.image.Image;
import javafx.scene.canvas.Canvas;
import java.util.*;
/**
 *
 * @author Bernardo
 */
public class Inimigo_ATIRA extends BasicElement {
    private boolean olhoAberto;
    private int pisca;
    private int atira;
	  private Image image;
    private Random gerador = new Random();
    public Inimigo_ATIRA(int px, int py) {
        super(px, py);
		image = Params.getInstance().getInimigo_Tiro();
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
    public void start() {
        setDirH(1);
        setSpeed(3*Params.AumentaDificult);
        atira = gerador.nextInt(100);//GERA TIROS EM TEMPOS RANDOMICOS
    }

    @Override
    public void Update() {
        if (jaColidiu()) {
			Params.CONT_RANK += 7;
            deactivate();
        } else {
            // Logica dos olhos
            //pisca++;
          //  if (pisca == 30){
      //  /  //      olhoAberto = !olhoAberto;
        //        pisca = 0;
        //    }
            // Logica da posicao
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
            // Logica do tiro
            atira++;
            if (atira == 100){
                Game.getInstance().addChar(new ShotDown(getX()+16,getY()+40));
                atira = 0;
            }
        }
    }

    public void Draw(GraphicsContext graphicsContext) {
        /*graphicsContext.setFill(Paint.valueOf("#0000FF"));
        graphicsContext.fillRect(getX(), getY() + 16, 32, 16);
        graphicsContext.setFill(Paint.valueOf("#FF00FF"));
        graphicsContext.fillOval(getX(), getY(), 16, 16);
        graphicsContext.fillOval(getX() + 16, getY(), 16, 16);
        if (olhoAberto) {
            graphicsContext.setFill(Paint.valueOf("#000000"));
            graphicsContext.fillOval(getX(), getY(), 8, 8);
            graphicsContext.fillOval(getX() + 16, getY(), 8, 8);
        }*/
		graphicsContext.drawImage(image,getX(),getY(),getLargura(),getAltura());
    }
}
