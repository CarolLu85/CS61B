package capers;

import java.io.File;
import java.io.IOException;
import static capers.Utils.*; //imports all static methods from the Utils class into the current class, allowing them to be used directly without class name qualification.

/** A repository for Capers 
 * @author TODO
 * The structure of a Capers Repository is as follows:
 *
 * .capers/ -- top level folder for all persistent data in your lab12 folder
 *    - dogs/ -- folder containing all of the persistent data for dogs
 *    - story -- file containing the current story
 *
 * TODO: change the above structure if you do something different.
 */
public class CapersRepository {
    /** Current Working Directory. */
    static final File CWD = new File(System.getProperty("user.dir"));

    /** Main metadata folder. the second join function as CWD is a File object */
    static final File CAPERS_FOLDER = join(CWD, ".capers"); // TODO Hint: look at the `join`
                                            //      function in Utils

    /**
     * Does required filesystem operations to allow for persistence.
     * (creates any necessary folders or files)
     * Remember: recommended structure (you do not have to follow):
     *
     * .capers/ -- top level folder for all persistent data in your lab12 folder
     *    - dogs/ -- folder containing all of the persistent data for dogs
     *    - story -- file containing the current story
     */
    public static void setupPersistence() {

        CAPERS_FOLDER.mkdir(); //create capers folder
        File dogFolder = new File(CAPERS_FOLDER,"dogs");
        dogFolder.mkdir();

        File storyFile = new File(CAPERS_FOLDER,"story");
        try
        {
            storyFile.createNewFile();
        }catch (IOException exp){
            throw new IllegalArgumentException(exp.getMessage());
        }
    }

    /**
     * Appends the first non-command argument in args
     * to a file called `story` in the .capers directory.
     * @param text String of the text to be appended to the story
     */
    public static void writeStory(String text) {

        File storyFile = join(CAPERS_FOLDER, "story");
        String existedStories = readContentsAsString(storyFile);
        String newStories = existedStories + text + "/n";
        writeContents(storyFile,newStories);
        String fullStory = readContentsAsString(storyFile);
        System.out.println(fullStory);
    }
    /**
     * Creates and persistently saves a dog using the first
     * three non-command arguments of args (name, breed, age).
     * Also prints out the dog's information using toString().
     */
    public static void makeDog(String name, String breed, int age) {

        Dog newDog = new Dog(name, breed, age);
        newDog.saveDog();
        System.out.println(newDog.toString());
    }

    /**
     * Advances a dog's age persistently and prints out a celebratory message.
     * Also prints out the dog's information using toString().
     * Chooses dog to advance based on the first non-command argument of args.
     * @param name String name of the Dog whose birthday we're celebrating.
     */
    public static void celebrateBirthday(String name) {

        Dog birthDayDog = Dog.fromFile(name);
        birthDayDog.haveBirthday();
        birthDayDog.saveDog();
    }
}
