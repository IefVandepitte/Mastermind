package com.example;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ief on 28/09/2017.
 */

public class Code extends AbstractList<Pion>{

    @Override
    public boolean remove(Object o) {
        return _pionnen.remove(o);
    }

    protected List<Pion> _pionnen = new ArrayList<>(4);

    public Code() {
    }

    public Code(List<Pion> _pionnen) {
        this._pionnen = _pionnen;
    }

    public Code(Pion p1, Pion p2, Pion p3, Pion p4) {
        _pionnen.add(p1);
        _pionnen.add(p2);
        _pionnen.add(p3);
        _pionnen.add(p4);
    }
    @Override
    public Pion get(int i) {
        return _pionnen.get(i);
    }
    @Override
    public int size() {
        return _pionnen.size();
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Pion p : _pionnen) {
            builder.append(p.getKleur() + " ");
        }
        return builder.toString();
    }
}

