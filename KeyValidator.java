// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP-102-112 - 2021T1, Assignment 2
 * Name:Nathan Bridge-Earney
 * Username:bridgenath1
 * ID:300565456
 */

import ecs100.*;
import java.util.Locale;

/**
 * Key:
 * Core:       Method must report whether the key is valid, or
 *             report that it is invalid and give one reason why it is invalid.
 *             To be valid, the key must
 *             - be at least 8 characters and at most 16 characters long,
 *             - not end with the special characters '#' or '$',
 *             - not have a hyphen ('-') character anywhere
 *            
 * Completion: Method should either report that the key is valid, or
 *             report that it is invalid and list ALL the reasons that it is invalid.
 *             To be valid, the key must
 *             - satisfy all of the conditions above AND
 *             - have at least one Upper case character and at least one Lower case character,
 *             - not start with the same character as the first character of the user's name
 *             - contain either a '#' or a '$', but not both.
 * Challenge:  Same as completion, except that to be valid, the key must
 *             - satisfy all of the conditions above AND
 *             - have a mix of numbers and letters
 *             - not contain any "suffix" of the user's name of 2 characters or more in any case.
 *               (eg if name is Peter, it does not contain "er", or "eR" or "ter", or "eTer" or "ETER", or...)
 *
 * Hint.  Look at the documentation in the String class.
 * You will definitely find the length(), endsWith(...), and contains(...) methods to be helpful
 */

public class KeyValidator {

    /**
     * Asks user for key word and then checks if it is a valid key word.
     */
    public void doCore(){
        UI.clearText();
        String key = UI.askString("Key:   ");
        UI.println();
        this.validateKeyCore(key);
    }

    /** CORE
     * Report "Valid" or "Invalid: ...reason...."
     */
    public void validateKeyCore(String key) {
        /*# YOUR CODE HERE */
        int length = key.length();
        int first = key.charAt(0);
        int last = key.charAt(length - 1);
        if (key.length() < 9 || key.length() > 17) {
            UI.println("Invalid: Keys need to be at least 8 characters and at most 16");
        } else if (key.contains("-")) {
            UI.println("Invalid: Cannot include a hyphen");
        } else if (first == 35 || first == 36 || last == 35 || last == 36) {
            UI.println("Invalid: Cannot end or start in # or $");
        }
        else UI.println("Valid");
    }


    /**
     * Asks user for key word and the name and then checks if it is a valid key word.
     */
    public void doCompletion(){
        UI.clearText();
        String key = UI.askString("Key:   ");
        String name = UI.askString("Your name:   ");
        UI.println();
        this.validateKeyCompletion(key, name);
    }
    public void doChallenge(){
        UI.clearText();
        String key = UI.askString("Key:   ");
        String name = UI.askString("Your name:   ");
        UI.println();
        this.validateKeyChallenge(key, name);
    }

    /** COMPLETION
     * Report that the key is valid or report ALL the rules that the key failed.
     */
    public void validateKeyCompletion(String key, String name){
        /*# YOUR CODE HERE */
        int length = key.length();
        int first = key.charAt(0);
        int last = key.charAt(length - 1);
        int firstN = name.charAt(0);
        boolean keyUpper = !key.equals(key.toLowerCase());
        boolean keyLower = !key.equals(key.toUpperCase());
        boolean Valid = true;
        if (key.length() < 9 || key.length() > 17) {
            UI.println("Invalid: Keys need to be at least 8 characters and at most 16");
            Valid = false;
        }
        if (key.contains("-")) {
            UI.println("Invalid: Cannot include a hyphen");
            Valid = false;
        }
        if (first == 35 || first == 36 || last == 35 || last == 36) {
            UI.println("Invalid: Cannot end or start in # or $");
            Valid = false;
        }
        if(first == firstN) {
            UI.println("Invalid: Your password cannot start with the same letter as your name");
            Valid = false;
        }
        if(!keyUpper || !keyLower){
            UI.println("Invalid: You need to have at least one upper case and one lower case letter");
            Valid = false;
        }
        if((key.contains("#") && key.contains("$")) || !(key.contains("#") || key.contains("$"))){
            UI.println("Invalid: You must include either # or $ you can use it more than once but only pick one");
            Valid = false;
        }
        if (Valid)UI.println("Valid Key");
        else UI.println("Invalid Key");

    }

    public void validateKeyChallenge(String key, String name){
        /*# YOUR CODE HERE */
        int length = key.length();
        int first = key.charAt(0);
        int last = key.charAt(length - 1);
        int lengthN = name.length();
        int firstN = name.charAt(0);
        boolean keyUpper = !key.equals(key.toLowerCase());
        boolean keyLower = !key.equals(key.toUpperCase());
        String lastTwoChar = name.substring(lengthN-2);
        String keyCheck = key.toLowerCase();
        lastTwoChar = lastTwoChar.toLowerCase();
        String RegexNumbers = ".*[0-9].*";
        String RegexLetters = ".*[A-Z].*";
        boolean Valid = true;

        if (key.length() < 9 || key.length() > 17) {
            UI.println("Invalid: Keys need to be at least 8 characters and at most 16");
            Valid = false;
        }
        if (key.contains("-")) {
            UI.println("Invalid: Cannot include a hyphen");
            Valid = false;
        }
        if (last == 35 || last == 36) {
            UI.println("Invalid: Cannot end in # or $");
            Valid = false;
        }
        if (first == firstN) {
            UI.println("Invalid: Your password cannot start with the same letter as your name");
            Valid = false;
        }
        if(!keyUpper || !keyLower){
            UI.println("Invalid: You need to have at least one upper case and one lower case letter");
            Valid = false;
        }
        if((key.contains("#") && key.contains("$")) || !(key.contains("#") || key.contains("$"))){
            UI.println("Invalid: You must include either # or $ you can use it more than once but only pick one");
            Valid = false;
        }
        if(keyCheck.contains(lastTwoChar)) {
            UI.println("Invalid: You cannot include any suffix of your name");
            Valid = false;
        }
        if(!key.matches(RegexLetters) & !key.matches(RegexNumbers)){
            Valid = false;
            UI.println("Invalid: You must include both letters and numbers");
        }
        if (Valid){
            UI.println("Valid Key");
        }
        else {
            UI.println("Invalid Key");
        }
    }

    public void setupGUI(){
        UI.initialise();
        UI.addButton("Clear", UI::clearText );
        UI.addButton("Validate Key Core", this::doCore );
        UI.addButton("Validate Key Completion", this::doCompletion );
        UI.addButton("Validate Key Challenge", this::doChallenge);
        UI.addButton("Quit", UI::quit );
        UI.setDivider(1);       // Expand the text area
    }

    public static void main(String[] args){
        KeyValidator kv = new KeyValidator();
        kv.setupGUI();
    }
}
