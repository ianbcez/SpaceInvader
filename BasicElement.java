import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;

/**
 * Represents the basic game character
 * @author Bernardo Copstein and Rafael Copstein
 */
public abstract class BasicElement implements Character{
    private int posX, posY;
    private int largura, altura;
    private int lminH,lmaxH;
    private int lminV,lmaxV;
    private int speed;
    private boolean active;
    private boolean colidiu;
    private int direction_horizontal, direction_vertical;

    public BasicElement(int startX,int startY){
        posX = startX;
        posY = startY;
        largura = 32;
        altura = 32;
        direction_horizontal = 0;
        direction_vertical = 0;
        active = true;
        colidiu = false;
        speed = 2;
        lminH = 0;
        lmaxH = Params.WINDOW_WIDTH;
        lminV = 0;
        lmaxV = Params.WINDOW_HEIGHT;
    }

    @Override
    public int getX(){
        return(posX);
    }

    @Override
    public int getY(){
        return(posY);
    }

    @Override
    public int getAltura(){
        return(altura);
    }

    @Override
    public int getLargura(){
        return(largura);
    }


    private boolean pontoNoRetangulo(int px,int py,int rx1,int ry1,int rx2,int ry2){
        if ((px>=rx1 && px<=rx2) && (py>=ry1 && py<=ry2)){
            return(true);
        }
        return(false);
    }

    @Override
    public void testaColisao(Character outro){
        if (colidiu){
            return;
        }
        // Monta pontos
        int p1x = this.getX();
        int p1y = this.getY();
        int p2x = p1x+this.getLargura();
        int p2y = p1y+this.getAltura();

        int op1x = outro.getX();
        int op1y = outro.getY();
        int op2x = op1x+outro.getLargura();
        int op2y = op1y+outro.getAltura();

        // Verifica colisão
        if ( pontoNoRetangulo(op1x,op1y,p1x,p1y,p2x,p2y) ||
             pontoNoRetangulo(op2x,op2y,p1x,p1y,p2x,p2y)){
            colidiu = true;
            //outro.setColidiu();
        }
    }

    public int getDirH(){
        return(direction_horizontal);
    }

    public int getDirV(){
        return(direction_vertical);
    }

    public int getLMinH(){
        return(lminH);
    }

    public int getLMaxH(){
        return(lmaxH);
    }

    public int getLMinV(){
        return(lminV);
    }

    public int getLMaxV(){
        return(lmaxV);
    }

    public int getSpeed(){
        return(speed);
    }

    public void setPosX(int p){
        posX = p;
    }

    public void setPosY(int p){
        posY = p;
    }

    public void setLargAlt(int l,int a){
        largura = l;
        altura = a;
    }

    public void setDirH(int dirH){
        direction_horizontal = dirH;
    }

    public void setDirV(int dirV){
        direction_vertical = dirV;
    }

    public void setLimH(int min,int max){
        lminH = min;
        lmaxH = max;
    }

    public void setLimV(int min,int max){
        lminV = min;
        lmaxV = max;
    }

    public void setSpeed(int s){
        speed = s;
    }

    public void deactivate(){
        active = false;
        Game.getInstance().eliminate(this);
    }

    @Override
    public boolean jaColidiu(){
        return(colidiu);
    }

    @Override
    public void setColidiu(){
        colidiu = true;
    }

    @Override
    public boolean isActive(){
        return(active);
    }

    @Override
    public abstract void start();

    @Override
    public abstract void Update();

    @Override
    public abstract void Draw(GraphicsContext graphicsContext);
}
