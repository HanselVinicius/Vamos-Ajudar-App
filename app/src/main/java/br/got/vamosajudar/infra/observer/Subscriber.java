package br.got.vamosajudar.infra.observer;

public interface Subscriber {


     void update();

     void updateOnError(Exception ex);
}
