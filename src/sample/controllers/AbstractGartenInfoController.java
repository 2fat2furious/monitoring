package sample.controllers;

import sample.data.KinderGarten;

public abstract class AbstractGartenInfoController {
    protected KinderGartenInfoController parent;
    protected KinderGarten selectedKinderGarten;

    public void init(KinderGartenInfoController parent) {
        this.parent = parent;
    }

    abstract void loadData();
}
