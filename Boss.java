
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;
import javafx.scene.image.Image;
import javafx.scene.canvas.Canvas;
import java.util.*;

/**
 * Represents a set of balls that crosses the screen over and over again
 *
 * @author Bernardo Copstein
 */
public class Boss extends Inimigo2 {
	private int atira;
	private Image image;
	private List<BasicElement> elements;
    private Random gerador = new Random();

	public Boss(int px, int py) {
		super(px, py);
		elements = new ArrayList<>();
		image = Params.getInstance().getBoss();
	}

	@Override
	public void start() {
		setDirH(1);
		setSpeed(4*Params.AumentaDificult);
		atira = gerador.nextInt(100);//GERA TIROS EM TEMPOS RANDOMICOS
		setLargAlt((32 + 8) * 3, 32 + 8 + 32);

		elements.add(new Inimigo_Vida_Boss(getX(), getY()));
		elements.add(new Inimigo_Vida_Boss(getX() + 40, getY()));
		elements.add(new Inimigo_Vida_Boss(getX() + 80, getY()));
		elements.add(new Inimigo_Vida_Boss(getX(), getY() + 40));
		elements.add(new Inimigo_Vida_Boss(getX() + 40, getY() + 40));
		elements.add(new Inimigo_Vida_Boss(getX() + 80, getY() + 40));
	}

	@Override
	public void testaColisao(Character outro){
		if (!(outro instanceof Shot) || (outro instanceof ShotDown)){
			return;
		}else{
			super.testaColisao(outro);
		}

		for(int i=0;i<elements.size();i++){
			elements.get(i).testaColisao(outro);
			if (elements.get(i).jaColidiu()){
				elements.remove(i);
				if (elements.size() == 0){
					Params.CONT_RANK+=10;
					deactivate();
				}
			}
		}
	}

	@Override
	public void Update()
	{
		setPosX(getX() + getDirH() * getSpeed());
		if (jaColidiu())
		{
			deactivate();
		}
		else
		{
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
				setPosY(oldY + 40);
			}
			// Logica do tiro
			atira++;
			if (atira == (110 - Params.AumentaDificult * 10)){//AUMENTA A VELOCIDADE DO TIRO!
				Game.getInstance().addChar(new ShotBoss(getX()+4,getY()+150));
				Game.getInstance().addChar(new ShotBoss(getX()+36,getY()+150));
				Game.getInstance().addChar(new ShotBoss(getX()+68,getY()+150));
				atira = 0;
			}
		}

		for (int i = 0; i < elements.size(); i++) {
			if (elements.get(i).jaColidiu()){
				Params.CONT_RANK += 10;
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
		}
	}


	@Override
	public void Draw(GraphicsContext graphicsContext){
		graphicsContext.drawImage(image,getX(),getY(),getLargura(),getAltura());
	}
}
