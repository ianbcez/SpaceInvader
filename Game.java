import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import java.util.List;
import java.util.LinkedList;

/**
 * Handles the game lifecycle and behavior
 * @author Bernardo Copstein and Rafael Copstein
 */
public class Game {
    private static Game game = null;
    private Player player;
    private List<Character> activeChars;

    private Game(){
      activeChars = new LinkedList();
    }

    public static Game getInstance(){
        if (game == null){
            game = new Game();
        }
        return(game);
    }

    public void addChar(Character c){
        activeChars.add(c);
        c.start();
    }

 public void eliminate(Character c){
        activeChars.remove(c);
        if (c == player){
            player = null;
			Main.semCanvas();//OCULTA CANVAS DO GAME
			Main.showInput();//BOTAO E TEXTO PARA GRAVAR NO RANKING
        }
    }



    public void Fase1(){
      for(int i=0; i<17; i++){
         activeChars.add(EnemyFactory.getInstance().createInstance("Inimigo1",50+(i*40),50));
         activeChars.add(EnemyFactory.getInstance().createInstance("Inimigo1",70+(i*40),100));
         activeChars.add(EnemyFactory.getInstance().createInstance("Inimigo1",90+(i*40),150));
         activeChars.add(EnemyFactory.getInstance().createInstance("Inimigo1",110+(i*40),200));
     }
    }
    public void Fase2(){
      Fase1();
      for(int i=0; i<3; i++){
         activeChars.add(EnemyFactory.getInstance().createInstance("Inimigo2",120+(i*150)+(i*55),30+(i*100)));
     }
    }

    public void Fase3(){
        Fase2();
      for(int i=0; i<3; i++){
      activeChars.add(EnemyFactory.getInstance().createInstance("Inimigo_ATIRA",120+(i*100),270-(i*100)));
        }
    }

     public void Fase4(){
        activeChars.add(EnemyFactory.getInstance().createInstance("Boss",150,50));
        for(int i=0; i<3; i++){
      activeChars.add(EnemyFactory.getInstance().createInstance("Inimigo_ATIRA",120+(i*100),270-(i*100)));
        }

    }


    public void Start() {
        // Repositório de personagens
        while(activeChars.size() > 0){
          activeChars.remove(0);//LIMPA A LIST AO INVES DE ALOCAR NOVO ESPACO
        }
        // Adiciona o canhao
        player = new Player(400,550);
        activeChars.add(player);
        if(Params.FASES == 1){ Fase1(); }//Executada so na primera vez que o jogo eh iniciado
        if(Params.FASES == 2){ Fase2(); }// loop de fases ate o canhao morrer
        if(Params.FASES == 3){ Fase3(); }//---
        if(Params.FASES == 4){ Fase4(); }// fim do loop;

        for(Character c:activeChars)
		      {
            c.start();
          }
          if(Params.FASES <= 4){
            Params.FASES++;}
          else{
            Params.FASES = 2;//QUANDO "ZERAR" REINICIA SEMPRE DA FASE 2
            Params.AumentaDificult++;
            if(Params.DiminuiTiroPlayer < 2)Params.DiminuiTiroPlayer = 2;


          }
    }



    public void Update(long currentTime, long deltaTime) {
        for(int i=0;i<activeChars.size();i++){
            Character este = activeChars.get(i);
            este.Update();
            for(int j =0; j<activeChars.size();j++){
                Character outro = activeChars.get(j);
                if ( este != outro){
                    este.testaColisao(outro);
                }
            }
        }
         if(activeChars.size() == 1){
          Start();
        }
    }

    public void OnInput(KeyCode keyCode, boolean isPressed) {
        if (player != null){
            player.OnInput(keyCode, isPressed);
        }
    }

    public void Draw(GraphicsContext graphicsContext) {
        for(Character c:activeChars){
            c.Draw(graphicsContext);
        }
    }
}
