// TODO: Compiler:  javac -d release *.java behaviors/*.java ducks/*.java
// TODO: Run:       java -cp release sumduck.SumDuck

package sumduck;

import sumduck.behaviors.*;
import sumduck.ducks.BaseDuck;
import sumduck.ducks.MallardDuck;
import sumduck.ducks.ModelDuck;
import sumduck.ducks.RubberDuck;

public class SumDuck {
  public static void main(String[] args){
    System.out.println("-> Welcome to Sum Duck program");
    System.out.println();

    BaseDuck mallardDuck = new MallardDuck();
    mallardDuck.display();
    mallardDuck.performFly();
    mallardDuck.performQuack();

    System.out.println();

    BaseDuck rubberDuck = new RubberDuck();
    rubberDuck.display();
    rubberDuck.performFly();
    rubberDuck.performQuack();

    System.out.println();

    FlyBehavior flyBehavior = new FlyNoWay();
    QuackBehavior quackBehavior = new Squeak();
    BaseDuck modelDuck = new ModelDuck(flyBehavior, quackBehavior);
    modelDuck.display();
    modelDuck.performFly();
    modelDuck.performQuack();
  }
}
