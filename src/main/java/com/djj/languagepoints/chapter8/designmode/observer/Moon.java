package com.djj.languagepoints.chapter8.designmode.observer;

import java.util.ArrayList;
import java.util.List;

/*
被观察者是月球 Moon

它持有一组 LandingObserver 实例，有东西着陆时会通知这些观察者，还可以增加新的 LandingObserver 实例观测 Moon 对象
 */
public class Moon {

    private final List<LandingObserver> observers = new ArrayList<>();

    public void land(String name) {
        for (LandingObserver observer : observers) {
            observer.observeLanding(name);
        }
    }

    public void startSpying(LandingObserver observer) {
        observers.add(observer);
    }

}
