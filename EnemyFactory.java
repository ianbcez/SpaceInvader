import java.util.*;
public class EnemyFactory{
  private static EnemyFactory instance = null;

  private EnemyFactory(){}

    public static EnemyFactory getInstance(){
      if(instance == null){
        instance = new EnemyFactory();
      }
        return instance;
    }

    public BasicElement createInstance(String Tipo,int px , int py){
      if(Tipo.equalsIgnoreCase("Inimigo1")){
        return new Inimigo1(px,py);
      }
      if(Tipo.equalsIgnoreCase("Inimigo_ATIRA")){
        return new Inimigo_ATIRA(px,py);
      }
      if(Tipo.equalsIgnoreCase("Inimigo2")){
        return new Inimigo2(px,py);
      }
      if(Tipo.equalsIgnoreCase("Boss")){
        return new Boss(px,py);
      }
      else { return null;}
    }



}
