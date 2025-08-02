// package Behavioural.Memento;
import java.util.*;

//what problem does it solve
// say resume->changes->new version
// want to revert back
// how? previous state?

/* 
// [1] without memento pattern
class ResumeEditor {
    public String name;
    public String education;
    public String experience;
    public List<String> skills;
}

class ResumeSnapshot{
    public String name;
    public String education;
    public String experience;
    public List<String> skills;
    //exposing my inner details of the class.
    //want to make sure details are hidden. want to follow encapsulation.

    public ResumeSnapshot(ResumeEditor editor) {
        this.name = editor.name;
        this.education = editor.education;
        this.experience = editor.experience;
        this.skills = new ArrayList<>(editor.skills);
    }
    public void restore(ResumeEditor editor) {
        editor.name = this.name;
        editor.education = this.education;
        editor.experience = this.experience;
        editor.skills = new ArrayList<>(this.skills);
    }
}
*/

// [2] with memento pattern
//this is the originator with memento inside.
class ResumeEditor{
    private String name; // all private, not exposing them outside.
    private String education;
    private String experience;
    private List<String> skills;
    
    public void setName(String name) {
        this.name = name;
    }
    public void setEducation(String education) {
        this.education = education;
    }
    public void setExperience(String experience) {
        this.experience = experience;
    }
    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
    public void printResume(){
        System.out.println("Resume:");
        System.out.println("Name: " + name);
        System.out.println("Education: " + education);
        System.out.println("Experience: " + experience);
        System.out.println("Skills: " + skills);
    }
    public Memento save(){
        return new Memento(name, education, experience, List.copyOf(skills));
    }
    public void restore(Memento memento){
        this.name = memento.getName();
        this.education = memento.getEducation();
        this.experience = memento.getExperience();
        this.skills = memento.getSkills();
    }

    //Inner class Memento
    public static class Memento {
        private final String name; // can opt out of this. since name doesnt change
        private final String education;
        private final String experience;
        private final List<String> skills;

        private Memento(String name, String education, String experience, List<String> skills) {
            this.name = name;
            this.education = education;
            this.experience = experience;
            this.skills = skills;
        }
        private String getName() { // all getters are private
            return name;
        }
        private String getEducation() {
            return education;
        }
        private String getExperience() {
            return experience;
        }
        private List<String> getSkills() {
            return skills;
        }
}
}

//caretakes class
class ResumeHistory {
    private Stack<ResumeEditor.Memento> history = new Stack<>();
    public void save(ResumeEditor editor) {
        history.push(editor.save());
    }

    public void undo(ResumeEditor editor) {
        if (!history.isEmpty()) {
            editor.restore(history.pop());
        }
    }
}
public class Main {
    public static void main(String[] args) {
        // 1st state
        // move to 2nd state and want to revert back
        // then need to save the 1st state in some partoicular snapshot.
        //1st state->ResumeSnapshot
        // 2nd state->ResumeSnapshot

        //formal definition of memento pattern
        // BDP, that allows an object to capture its internal state and restore it later without violating encapsulation.
        // it has three components
        // 1. Originator -> the object whose state we want to save. like the resumeEditor whose state we want to save.
        // 2. Memento -> an object which stores the state of the originator
        // 3. Caretaker -> an object that manages the memento and restores the originator's state when needed. the object responsible for saving and restoring the mementos.

        // think of it like a undo/redo mechanism

        // it delegates creating the state snapshots to the actual owner of the state.
        // hence the original class can make the snapshots since it has full access to its own state.

        ResumeEditor editor = new ResumeEditor();
        ResumeHistory history = new ResumeHistory();
        editor.setName("John Doe");
        editor.setEducation("B.Tech in Computer Science");
        editor.setExperience("3 years at XYZ Corp");
        editor.setSkills(Arrays.asList("Java", "Spring", "SQL"));
        editor.printResume();
        history.save(editor); // save the current state

        // 2nd state
        editor.setExperience("5 years at ABC Inc");
        editor.setSkills(Arrays.asList("Java", "Spring", "SQL", "Docker"));
        editor.printResume();

        history.save(editor); // save the new state
        // now we can undo to the previous state
        history.undo(editor);
        System.out.println("After undo:");
        editor.printResume(); // should revert to the 2nd state. 

        history.undo(editor);
        System.out.println("After undo:");
        editor.printResume(); // should revert to the 1st state

        //when to use?
        // need undo/redo functionality
        // want to preserve the encapsulation of the object's state
        // u r handling non trivial state history management

        // pros
        // encapsulation is preserved
        // easy to implement undo/redo functionality
        //clear separation of concerns

        //cons
        // can lead to memory overhead if many states are saved
        // can become complex if the state is large or has many dependencies
        // needs careful management of old mementos.

        
    }
}
