package sample.controllers;

import sample.data.KinderGarten;

public abstract class AbstractTabController<T extends AbstractTabsContainer, Y> implements TabController {
    protected T parent;
    protected Y selectedItem;

    public void init(T parent) {
        this.parent = parent;
    }
}