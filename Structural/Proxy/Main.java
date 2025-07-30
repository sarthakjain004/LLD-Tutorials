// package Structural.Proxy;
import java.util.*;

/*
// [1] without proxy pattern

//what problem does it solve?
//what is a proxy layer?
// say client access a certain service.
// client has to talk to the layer, layer will talk to service,
// service returns stuff to layer and then layer to client.
// this is proxy layer.
//similar for proxy pattern.

// say video downloader class, accepts URL, downloads video and returns content.

class RealVideoDownloader{
    public String downloadVideo(String videoURL){
        System.out.println("Downloading video from URL: "+videoURL);
        return "Video content from: "+videoURL;
    }
}
*/


interface VideoDownloader{
    String downloadVideo(String videoUrl);
}

//Actual Downloader.
class RealVideoDownloader implements VideoDownloader{

    @Override
    public String downloadVideo(String videoUrl){
        System.out.println("Downloading video from URL: "+videoUrl);
        return "Video content from: "+videoUrl;
    }
}


//make proxy layer.
class CacheVideoDownloader implements VideoDownloader{
    private RealVideoDownloader realDownloader;
    private static Map<String,String> cache = new HashMap<>();
    public CacheVideoDownloader(){
        this.realDownloader = new RealVideoDownloader();
    }

    @Override
    public String downloadVideo(String videoUrl){
        if(cache.containsKey(videoUrl)){
            System.out.println("Returning cached video for: "+videoUrl);
            return cache.get(videoUrl);
        }
        System.out.println("Cache miss. Downloading...");
        String video = realDownloader.downloadVideo(videoUrl);
        cache.put(videoUrl, video);
        return video;
    }
}
public class Main {
    public static void main(String[] args) {
        /*
        // [1] without proxy pattern.
        RealVideoDownloader realVideoDownloader = new RealVideoDownloader();
        realVideoDownloader.downloadVideo("proxy-pattern");

        //say 1 more client which wants to download same videoURL.
        RealVideoDownloader realVideoDownloader2 = new RealVideoDownloader();
        realVideoDownloader2.downloadVideo("proxy-pattern"); // useless.
        // instead of performing the logic again, we can memoize it.
        // return the video downloaded for other client.
        // can cache or memoize like in dp. but if we do that in same class then violates SRP.
        // this is where proxy pattern comes in.

        //layer which takes care of everything.
        // say later on filtering and access limit comes in, create similar layers for that.
        // else in same class, it will get bulky.

        // dont want to overload the servers with different functionalities.
        //what is it?
        // structural design pattern
        // provides a surrogate or placeholder for another object to control access.
        // think of it like a security guard who controls acesss to the real VIP.
        //surrogates/proxy/layer all same.

        //key idea
        // instead of interacting with the real object directly, clients
        // interact with the proxy that acts on behalf of the real object.
        // real life examples-> firewall, filtering, caching, protection, privacy and security.
        */

        VideoDownloader cachedVideoDownloader = new CacheVideoDownloader();
        cachedVideoDownloader.downloadVideo("pattern-url");

        VideoDownloader cachVideoDownloader2 = new CacheVideoDownloader();
        cachVideoDownloader2.downloadVideo("pattern-url");

        //when to use
        // when object creation is expensive and you want to delay the operation.
        // when you want to control access to sensitive operation or add provision.
        // when interacting with remote objects.
        // when you need lazy loading in ur system.
         
        //pros
        // performace optimization
        // access control.
        // lazy initialization.
        // added functionality.
        
        //cons
        //adds complexity.
        //delay in access of real object.
        //maintainence overhead.

    }    
}
