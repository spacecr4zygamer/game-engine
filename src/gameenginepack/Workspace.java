package gameenginepack;

import gameenginepack.Game;
import gameenginepack.Instances.Instance;

import java.util.ArrayList;

public class Workspace extends Instance {

    private final Game game;

    //private ArrayList<Instance> Children = new ArrayList<>();

    public Workspace(Game game) {
        this.game = game;

    }

    public void appendChild(Instance Child) {
        this.Children.add(Child);
    }
}