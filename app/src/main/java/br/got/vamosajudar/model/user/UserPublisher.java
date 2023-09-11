package br.got.vamosajudar.model.user;

import java.util.ArrayList;
import java.util.List;

import br.got.vamosajudar.infra.observer.Publisher;
import br.got.vamosajudar.infra.observer.Subscriber;

public class UserPublisher implements Publisher {

    private final List<Subscriber> subscribers;

    public UserPublisher() {
        this.subscribers = new ArrayList<>();
    }

    @Override
    public void notifySubscribers() {
        subscribers.forEach(Subscriber::update);
    }



    @Override
    public void subscribe(Subscriber sub) {
        this.subscribers.add(sub);
    }

    @Override
    public void notifySubscribersOnError(Exception ex) {
        subscribers.forEach(subscriber -> subscriber.updateOnError(ex));

    }


}
