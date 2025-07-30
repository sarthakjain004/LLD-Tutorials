// package Structural.Bridge;

/* 
// [1] without bridge pattern.
//what problem does it solve.
// devices that play videos in certain formats.
interface PlayQuality{
    void play(String title);
}

class WebHDPlayer implements PlayQuality{
    public void play(String title){
        System.out.println("Web Player: Playing " + title + " in HD");
    }
}

class MobileHDPlayer implements PlayQuality{
    public void play(String title){
        System.out.println("Mobile Player: Playing " + title + " in HD");
    }
}

class SmartTVUltraHDPlayer implements PlayQuality {
    public void play(String title) {
        // Smart TV plays in Ultra HD
        System.out.println("Smart TV: Playing " + title + " in ultra HD");
    }
}

//say now 4K is introduced.
class Web4KPlayer implements PlayQuality {
    public void play(String title) {
        // Web player plays in 4K
        System.out.println("Web Player: Playing " + title + " in 4K");
    }
}
//similaary for mobile and smartTV.
// this leads to extra classes
// imagine 8k comes etc.
// the player and quality is tightly coupled.
// small pieces will not be coupled together in structural design pattern.

// this pattern decouples an abstraction from its implementation so that they can very independently.
// in this the webplayer was tightly coupled to HD, 4k, 8k.
// want HD stand alone, Web player stand alone.

// real life -> appliances and remote, each device has its remote
// 16 fans wont have 16 remotes.

//taking example of streaming platform.
*/



//[2] with bridge pattern.
interface VideoQuality{
    void load(String title);
}

class SDQuality implements VideoQuality{
    public void load(String title){
        System.out.println("Streaming " + title + " in SD Quality");
    }
}

class HDQuality implements VideoQuality {
    public void load(String title) {
        System.out.println("Streaming " + title + " in HD Quality");
    }
}

class UltraHDQuality implements VideoQuality {
    public void load(String title) {
        System.out.println("Streaming " + title + " in 4K Ultra HD Quality");
    }
}
// can create 4k easily.

// for player create abstraction logic. since it needs to know what quality its goaing to play on.

abstract class VideoPlayer{
    protected VideoQuality quality;
    public VideoPlayer(VideoQuality quality){
        this.quality = quality;
    }

    public abstract void play(String title);
}

class WebPlayer extends VideoPlayer{
    public WebPlayer(VideoQuality quality){
        super(quality);
    }
    public void play(String title){
        System.out.println("Web Platform");
        quality.load(title);
    }
}

class MobilePlayer extends VideoPlayer {
    public MobilePlayer(VideoQuality quality) {
        super(quality);
    }

    public void play(String title) {
        System.out.println("Mobile Platform:");
        quality.load(title);
    }
}

public class Main {
    public static void main(String[] args) {
        VideoPlayer player1 = new WebPlayer(new HDQuality());
        player1.play("Interstellar");

        // Playing on Mobile with Ultra HD Quality
        VideoPlayer player2 = new MobilePlayer(new UltraHDQuality());
        player2.play("Inception");

        //when to use?
        // you gave two dimensions.
        // you want to evolve independently. keep on adding quality and number of players.
        // you want to avoid class explosion.

        //pros
        // decouples abstraction and implementation, changes in either side do not affect the other.
        // supports OCP.
        // ideal for cross platform development.
        // improves maintainableity and testing.

        // cons
        // increased complexity in small scale systems.
        // can be confused with other patterns.
        // co-ordination needed if separate teams handle it. (in corporate.)

    }
}
