package sumduck.ducks;

import sumduck.behaviors.FlyBehavior;
import sumduck.behaviors.FlyNoWay;
import sumduck.behaviors.MuteQuack;
import sumduck.behaviors.QuackBehavior;

public class RubberDuck extends BaseDuck {

    public RubberDuck(){
        super();
        flyBehavior = new FlyNoWay();
        quackBehavior = new MuteQuack();
    }

    public RubberDuck(FlyBehavior flyBehavior, QuackBehavior quackBehavior){
        super(flyBehavior, quackBehavior);
    }

    @Override
    public void display() {
        System.out.println("-> display RubberDuck");
    }
}
