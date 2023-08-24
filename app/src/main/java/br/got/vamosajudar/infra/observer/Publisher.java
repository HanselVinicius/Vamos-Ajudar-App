package br.got.vamosajudar.infra.observer;

public interface Publisher {


    void notifySubscribers();

    void subscribe(Subscriber sub);



}
