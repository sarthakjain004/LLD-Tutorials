// package Behavioural.Iterator;
import java.util.*;

//behavioural design
// rules of conversation between objects
// who is going to do what in a group of objects.

//take example of youtube creator.
/*
// [1] without iterator pattern.
class Video{
    String title;
    public Video(String title){
        this.title = title;
    }
    public String getTitle(){
        return title;
    }
}

class YoutubePlaylist {
    private List<Video> videos = new ArrayList<>();

    public void addVideo(Video video){
        videos.add(video);
    }

    public List<Video> getVideos(){
        return videos;
    }
}
*/
/*
// [2] partially iterator pattern
class Video{
    private String title;
    public Video(String title){
        this.title = title;
    }
    public String getTitle(){
        return title;
    }
}

class YoutubePlaylist {
    private List<Video> videos = new ArrayList<>();

    public void addVideo(Video video){
        videos.add(video);
    }
    public List<Video> getVideos(){
        return videos;
    }
}

interface PlaylistIterator{ // handle the traversal algo.
    // when traversing, what need? the next element.
    boolean hasNext();
    Video next();
}

//now have a concrete class of the traversal implemented in a clas.
// concrete iterator - traversal algo - 1
class YoutubePlaylistIterator implements PlaylistIterator{
    private List<Video> videos;
    private int position;
    public YoutubePlaylistIterator(List<Video> videos){
        this.videos = videos;
        this.position = 0;
    }

    @Override
    public boolean hasNext(){
        return position < videos.size();
    }
    public Video next(){
        return hasNext() ? videos.get(position++) : null;
    }
}
*/



class Video{
    private String title;
    public Video(String title){
        this.title = title;
    }
    public String getTitle(){
        return title;
    }
}

interface Playlist{
    PlaylistIterator createIterator();
    // PlaylistIteration createCopyrightIterator(); // can create more traversal ways.
}

class YoutubePlaylist implements Playlist{
    private List<Video> videos = new ArrayList<>();

    public void addVideo(Video video){
        videos.add(video);
    }

    @Override
    public PlaylistIterator createIterator(){
        return new YoutubePlaylistIterator(videos);
    }
}

interface PlaylistIterator{ // handle the traversal algo.
    // when traversing, what need? the next element.
    boolean hasNext();
    Video next();
}

//now have a concrete class of the traversal implemented in a clas.
// concrete iterator - traversal algo - 1
class YoutubePlaylistIterator implements PlaylistIterator{
    private List<Video> videos;
    private int position;
    public YoutubePlaylistIterator(List<Video> videos){
        this.videos = videos;
        this.position = 0;
    }

    @Override
    public boolean hasNext(){
        return position < videos.size();
    }
    public Video next(){
        return hasNext() ? videos.get(position++) : null;
    }
}
public class Main {
    public static void main(String[] args) {
        /* 
        //[1] without iterator pattern.
        YoutubePlaylist playlist = new YoutubePlaylist();
        playlist.addVideo(new Video("LLD Tutorial"));
        playlist.addVideo(new Video("System Design Basics"));

        for(Video v: playlist.getVideos()){
            System.out.println(v.getTitle());
        }
        // what is the problem here?
        // lots of problems
        // what if later on private videos or copyright videos?
        // cant iterate over those in the client side(Main part).
        // here the client knows too much/ everything such that its able to traverse.
        // the client shouldnt be aware of the traversal alogorithm or the kind of
        // data strcuture using to store the videos.
        // dont want to expose the algo or the data structure the service is using to stiore.
        
        //what is iterator pattern?
        // it is a behavioural design pattern that extends the traversal behaviour of a
        // collection into a separate design pattern. it traverses the elements without exposing the underlying algorithm.
        // it encapsulates all of the traversal details. several iteratirs can go throught the same
        // collection at the same time.
        */


        // now dont want client to implement traversal algo.
        /*
        //[2] partial iterator
        YoutubePlaylist playlist = new YoutubePlaylist();
        playlist.addVideo(new Video("LLD Tutorial"));
        playlist.addVideo(new Video("System Design Basics"));

        PlaylistIterator iterator = new YoutubePlaylistIterator(playlist.getVideos());
        while(iterator.hasNext()){
            System.out.println(iterator.next().getTitle());
        }

        //but not done yet
        // the client is still creating the instance for the iterator. we dont want the client to do it.
        // here the client needs to decide what algo he wants, what the algo wants (playlist.getVideos()).
        // hence add one more layer, iterability of instance.
        // iterable interface like Playlist.
        */

        YoutubePlaylist playlist = new YoutubePlaylist();
        playlist.addVideo(new Video("LLD Tutorial"));
        playlist.addVideo(new Video("System Design Basics"));
        PlaylistIterator iterator = playlist.createIterator(); //now we dont know if its storing a list of videos.
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getTitle());
        }
        // client noe aware of anything except that it has to add the video.

        //when to use it?
        // you want to traverse a collection without exposing its internal structure.
        // you want multiple ways to traverse a collection.
        // you want a unified way to traverse different types of collection.
        // want to decouple iteration logic from collection logic.

        //example where we use it?
        // ArrayList etc, every collection.
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);

        Iterator it = list.iterator();
        boolean doesit = it.hasNext();
        System.out.println(doesit);

        // each queue,etc have iterator.

        //pros
        // hides internal structure of the collection
        // unified way to traverse.
        // supports multiple traversal algorithms
        // SRP and OCP followed

        // cons
        //adds extra classes/interfaces
        // might be overkill for simple DS.
        // external iteration is needed.
    }
}
