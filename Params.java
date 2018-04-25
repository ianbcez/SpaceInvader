import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;
import javafx.scene.image.Image;
import javafx.scene.canvas.Canvas;
import java.util.Random;
import javafx.scene.text.Text;

public class Params{
	public static final String WINDOW_TITLE = "SPACE INVADERS";
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;
	public static int CONT_RANK = 0;
	public static int FASES = 1;
	public static int AumentaDificult = 1;
    public static int DiminuiTiroPlayer = 1;


	// Imagens
	private Image TIROC = new Image(getClass().getResourceAsStream("/Graphic/tiro_CANHAO.png"));
	private Image INIMIGO1 = new Image(getClass().getResourceAsStream("/Graphic/InimigoFase1.png"));
	private Image INIMIGO2 = new Image(getClass().getResourceAsStream("/Graphic/InimigoFase2.png"));
	private Image INIMIGO3 = new Image(getClass().getResourceAsStream("/Graphic/InimigoFase3.png"));
	private Image INIMIGO4 = new Image(getClass().getResourceAsStream("/Graphic/InimigoFase4.png"));
	private Image TIROI = new Image(getClass().getResourceAsStream("/Graphic/tiro_INIMIGO.png"));
	private Image TIROB = new Image(getClass().getResourceAsStream("/Graphic/tiro_INIMIGO3.png"));
	private Image INIMIGO2_2 = new Image(getClass().getResourceAsStream("/Graphic/InimigoFase2_2.png"));
	private Image PLAYER = new Image(getClass().getResourceAsStream("/Graphic/Nave.png"));

	private static Params params = null;
	private Random rnd;

	private Params(){
		rnd = new Random();
	}

	public static Params getInstance(){
		if (params == null){
			params = new Params();
		}
		return(params);
	}

	public int nextInt(int lim){
		return(rnd.nextInt(lim));
	}


	//Metodos que devolvem a imagem
	public Image getPlayer()
	{
		return(PLAYER);
	}

	public Image getINIMIGO2_2()
	{
		return(INIMIGO2_2);
	}

	public Image getTiroBOSS()
	{
		return(TIROB);
	}

	public Image getTiroNave()
	{
		return(TIROC);
	}

	public Image getInimigo1()
	{
		return(INIMIGO1);
	}

	public Image getInimigo2()
	{
		return(INIMIGO2);
	}

	public Image getInimigo_Tiro()
	{
		return(INIMIGO3);
	}

	public Image getBoss()
	{
		return(INIMIGO4);
	}

	public Image getTiroInimigo()
	{
		return(TIROI);
	}
}
