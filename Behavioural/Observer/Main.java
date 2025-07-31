// package Behavioural.Observer;

//Yt, when upload video,
// all users get notif who have bell
// evryone got notified.
// they are all observers.
/*
//[1] without observer pattern
class YoutubeChannel{
    public void uploadNewVideo(String videoTitle){
        System.out.println("Uploading: "+videoTitle);

        //manually notify users
        System.out.println("Sending email to user1@example.com");
        System.out.println("Pusing in-app notif to user3@example.com");
    } //is this method scalable? no
    // why?
    // first not following SRP.
    // its uploading videos as well as sending notification.
    // no option for turn on or off notif to user.
    // this is where observer pattern.
}

//problem it solves-
// we make a change somewhere
// evryone who has subscribed for changes get notified.

//what is observer pattern?
// it is a behavioural design pattern that defines one-to-many dpeendency
// between objects so that when one object changes state, all its dependents are notified
// and updated accordingly.
*/


//observer interface

import java.lang.annotation.Inherited;
import java.util.ArrayList;
import java.util.List;

interface Subscriber{
    void update(String videoTitle);
}

//concrete observer
// directly communicate with user/observer
class EmailSubscriber implements Subscriber{
    private String email;

    public EmailSubscriber(String email){
        this.email = email;
    }
    @Override
    public void update(String videoTitle){
        System.out.println("Email sent to "+email+" for video uploaded on "+videoTitle);
    }
}

class MobileAppSubscriber implements Subscriber{
    private String username;

    public MobileAppSubscriber(String username){
        this.username = username;
    }

    @Override
    public void update(String videoTitle){
        System.out.println("In-app notif for "+username+": New video -"+videoTitle);
    }
}

//we also need option for subscribe, unsubscribe and notify.

//subject interface
interface Channel{
    void subscribe(Subscriber subscriber);
    void unsubscribe(Subscriber subscriber);
    void notifySubscribers(String videoTitle);
}

//conrete Object:
class YoutubeChannel implements Channel{
    private List<Subscriber> subscribers = new ArrayList<>();
    private String channelName;

    public YoutubeChannel(String channelName){
        this.channelName = channelName;
    }

    @Override
    public void subscribe(Subscriber subscriber){
        subscribers.add(subscriber);
    }

    @Override
    public void unsubscribe(Subscriber subscriber){
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(String videoTitle){
        for(Subscriber subscriber: subscribers){
            subscriber.update(videoTitle);
        }
    }

    public void uploadVideo(String videoTitle){
        System.out.println(channelName +" uploaded " +videoTitle);
        notifySubscribers(videoTitle);
    }
}

public class Main {
    public static void main(String[] args) {
        YoutubeChannel tuf = new YoutubeChannel("takeuForward");
        tuf.subscribe(new MobileAppSubscriber("raj"));
        tuf.subscribe(new EmailSubscriber("rahul@gmail.com"));
        tuf.uploadVideo("observer-pattern");

        //when to use it?
        // a change in one ibject should automatically notify others.
        // you want to decouple the subject form the observers. (subject is channel)
        //dynamic subscription/unsubscription

        //when to avoid?
        // you have too many observers(celebrity goes live for 10M followers) -> use event queues, pub-sub systems
        
        //tight control over notification timing is required. ->use message broker to publish events
        //eg billing, notif, seller, delivery for amazon order as there is lot of dependency.
        // at low scale observer pattern is okay.

        // works really well with small number of observers.
        // but to scale, need to move to event driven architecture.


        //pros
        //promotes loose coupling. observers and subjects are decoupled, interact via interface only.
        // open for extension.
        // dynamic subscription.

        //cons
        // unexpected update sequences.
        // like when there are multiple notification that have to be triggered to the observer.
        // performace issues on scale.
        // memory leaks (subscribe, forget to unsubscribe)
        // difficult debugging -> say 1 out of 1 million notif fail
        // tight timing coupling.

        //real life use
        //snapchat snaps -> for smaller groups work, larger may use event driven.
    }
}
