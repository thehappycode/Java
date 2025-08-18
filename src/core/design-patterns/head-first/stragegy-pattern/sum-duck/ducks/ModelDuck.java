package sumduck.ducks;

import sumduck.behaviors.FlyBehavior;
import sumduck.behaviors.FlyNoWay;
import sumduck.behaviors.MuteQuack;
import sumduck.behaviors.QuackBehavior;

public class ModelDuck  extends BaseDuck {

    public ModelDuck(){
        super();
        flyBehavior = new FlyNoWay();
        quackBehavior = new MuteQuack();
    }

    public ModelDuck(FlyBehavior flyBehavior, QuackBehavior quackBehavior){
        super(flyBehavior, quackBehavior);
    }

    @Override
    public void display() {
        System.out.println("-> display ModelDuck");
    }
}
