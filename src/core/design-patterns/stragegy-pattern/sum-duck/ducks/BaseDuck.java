package sumduck.ducks;

import sumduck.behaviors.FlyBehavior;
import sumduck.behaviors.QuackBehavior;

public abstract class BaseDuck {
  
  protected FlyBehavior flyBehavior;

  protected QuackBehavior quackBehavior;

  public BaseDuck() {

  }

  public BaseDuck(FlyBehavior flyBehavior, QuackBehavior quackBehavior){
    this.flyBehavior = flyBehavior;
    this.quackBehavior =  quackBehavior;
  }

  public void performFly(){
    flyBehavior.fly();
  }

  public void performQuack(){
    quackBehavior.quack();
  }

  public abstract void display();

}
