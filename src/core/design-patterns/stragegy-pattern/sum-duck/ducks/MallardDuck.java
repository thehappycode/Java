package sumduck.ducks;

import sumduck.behaviors.*;

public class MallardDuck extends BaseDuck {

  public MallardDuck(){
    flyBehavior = new FlyWithWings();
    quackBehavior = new Quack();
  }

  public MallardDuck(FlyBehavior flyBehavior, QuackBehavior quackBehavior){
      super(flyBehavior, quackBehavior);
  }

  @Override
  public void display(){
    System.out.println("-> display MallardDuck");
  }
}
