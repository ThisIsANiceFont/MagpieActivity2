/**
 * A program to carry on conversations with a human user.
 * This is the initial version that:  
 * <ul><li>
 *       Uses indexOf to find strings
 * </li><li>
 *          Handles responding to simple words and phrases 
 * </li></ul>
 * This version uses a nested if to handle default responses.
 * @author Laurie White
 * @version April 2012
 */
public class Magpie2
{
    /**
     * Get a default greeting   
     * @return a greeting
     */
    public String getGreeting()
    {
        return "Hello, let's talk.";
    }
    
    /**
     * Gives a response to a user statement
     * 
     * @param statement
     *            the user statement
     * @return a response based on the rules given
     */
    public String getResponse(String statement)
    {
        String response = "";
        if (statement.indexOf("no") >= 0)
        {
            response = "Why so negative?";
        }
        else if (statement.indexOf("mother") >= 0
                || statement.indexOf("father") >= 0
                || statement.indexOf("sister") >= 0
                || statement.indexOf("brother") >= 0)
        {
            response = "Tell me more about your family.";
        }
        //code from part B for pets
        /*else if (statement.indexOf("cat") >= 0
                || statement.indexOf("dog") >= 0)
        {
            response = "Tell me more about your pets.";
        }*/
        //code from part B for teacher name
        /*
        else if (statement.indexOf("Kaehms") >= 0)
        {
            response = "He's really living life to the fullest, huh!";
        }*/
        else if (statement.indexOf("Hello") >= 0)
        {
            response = "Hi! Ask me a question!";
        }
        else if (statement.indexOf("sport") >= 0)
        {
            response = "Amazing! I'm not that good at sports, as a robot";
        }
        else if (statement.indexOf("class") >= 0)
        {
            response = "Wow, that class seems really unique!";
        }
        else if (statement.isEmpty())
        {
            response = "Say something, please.";
        }
        // PLTW 1.1.6 Part C start
        //cat, dog response
        else if (findKeyword(statement, "cat") >= 0
                || findKeyword(statement, "dog") >= 0)
        {
            response = "Tell me more about your pets.";
        }
        //teacher response
        else if (findKeyword(statement, "Kaehms") >= 0)
        {
            response = "He's really living life to the fullest, huh!";
        }
        // PLTW 1.1.6 Part C end
        // PLTW 1.1.6 Part D start
        else if (findKeyword(statement, "I want to", 0) >= 0)
        {
            response = transformIWantToStatement(statement);
        }
    else if (findKeyword(statement, "I want", 0) >= 0)
        {
            response = transformIWantStatement(statement);
        }
        // PLTW 1.1.6 Part D end
        else  // Look for a two word (you <something> me) pattern
        {
            // Look for a two word (you <something> me) pattern
            int psn = findKeyword(statement, "you", 0);

            if (psn >= 0 && findKeyword(statement, "me", psn) >= 0)
            {
                response = transformYouMeStatement(statement);
            }
            else
            {
                response = getRandomResponse();
            }
       }
       return response;
    }

    /**
     * Take a statement with "I want to <something>." and transform it into 
     * "What would it mean to <something>?"
     * @param statement the user statement, assumed to contain "I want to"
     * @return the transformed statement
     */
    private String transformIWantToStatement(String statement)
    {
        //  Remove the final period, if there is one
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        int psn = findKeyword (statement, "I want to", 0);
        String restOfStatement = statement.substring(psn + 9).trim();
        return "What would it mean to " + restOfStatement + "?";
    }
    /**
     * Take a statement with "I want <something>." and transform it into 
     * "Would you really be happy if you had <something>?"
     * @param statement the user statement, assumed to contain "I want to"
     * @return the transformed statement
     */
    private String transformIWantStatement(String statement)
    {
        //  Remove the final period, if there is one
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        int psn = findKeyword (statement, "I want", 0);
        String restOfStatement = statement.substring(psn + 7).trim();
        return "Would you really be happy if you had " + restOfStatement + "?";
    }

    /**
     * Take a statement with "you <something> me" and transform it into 
     * "What makes you think that I <something> you?"
     * Also works if statement is "you <something> me <something else>" and transforms it into
     * "What makes you think that I <something> you <something else>?"
     * @param statement the user statement, assumed to contain "you" followed by "me"
     * @return the transformed statement
     */
    private String transformYouMeStatement(String statement)
    {
        //  Remove the final period, if there is one
        statement = statement.trim();
        String lastChar = statement.substring(statement
                .length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement
                    .length() - 1);
        }
        
        int psnOfYou = findKeyword (statement, "you", 0);
        int psnOfMe = findKeyword (statement, "me", psnOfYou + 3);
        
        String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
        String endOfStatement = " " + statement.substring(psnOfMe+2).trim();
        return "What makes you think that I " + restOfStatement + " you" + endOfStatement + "?" ;
}
    
    /**
     * Pick a default response to use if nothing else fits.
     * @return a non-committal string
     */
    private String getRandomResponse()
    {
        final int NUMBER_OF_RESPONSES = 6;
        double r = Math.random();
        int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
        String response = "";
        
        if (whichResponse == 0)
        {
            response = "Interesting, tell me more.";
        }
        else if (whichResponse == 1)
        {
            response = "Hmmm.";
        }
        else if (whichResponse == 2)
        {
            response = "Do you really think so?";
        }
        else if (whichResponse == 3)
        {
            response = "You don't say.";
        }
        else if (whichResponse == 4)
        {
            response = "That's neat.";
        }
        else if (whichResponse == 5)
        {
            response = "Wow, I didn't know about that.";
        }

        return response;
    }
    
    /**
     * Search for one word in phrase. The search is not case
     * sensitive. This method will check that the given goal
     * is not a substring of a longer string (so, for
     * example, "I know" does not contain "no").
     * 
     * @param statement
     *            the string to search
     * @param goal
     *            the string to search for
     * @param startPos
     *            the character of the string to begin the
     *            search at
     * @return the index of the first occurrence of goal in
     *         statement or -1 if it's not found
     */
    private int findKeyword(String statement, String goal,
    int startPos)
    {
        String phrase = statement.trim();
        // The only change to incorporate the startPos is in
        // the line below
        int psn = phrase.toLowerCase().indexOf(
                goal.toLowerCase(), startPos);

        // Refinement--make sure the goal isn't part of a
        // word
        while (psn >= 0)
        {
            // Find the string of length 1 before and after
            // the word
            String before = " ", after = " ";
            if (psn > 0)
            {
                before = phrase.substring(psn - 1, psn)
                .toLowerCase();
            }
            if (psn + goal.length() < phrase.length())
            {
                after = phrase.substring(
                    psn + goal.length(),
                    psn + goal.length() + 1)
                .toLowerCase();
            }
            
            // If before and after aren't letters, we've
            // found the word
            if (((before.compareTo("a") < 0) || (before
                    .compareTo("z") > 0)) // before is not a
                // letter
            && ((after.compareTo("a") < 0) || (after
                    .compareTo("z") > 0)))
            {
                return psn;
            }

            // The last position didn't work, so let's find
            // the next, if there is one.
            psn = phrase.indexOf(goal.toLowerCase(),
                psn + 1);

        }

        return -1;
    }
    
    /**
     * Search for one word in phrase. The search is not case
     * sensitive. This method will check that the given goal
     * is not a substring of a longer string (so, for
     * example, "I know" does not contain "no"). The search
     * begins at the beginning of the string.
     * 
     * @param statement
     *            the string to search
     * @param goal
     *            the string to search for
     * @return the index of the first occurrence of goal in
     *         statement or -1 if it's not found
     */
    private int findKeyword(String statement, String goal)
    {
        return findKeyword(statement, goal, 0);
    }
}
