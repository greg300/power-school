package PowerSchool.src.com.greg.utils;


import java.util.ArrayList;

/**
 * Created by gregorygiovannini on 2/8/16.
 */
public class Searcher
{

    public static ArrayList<Integer> searchForString(ArrayList<? extends Searchable> toSearch, String string)
    {
        // list of matches found
        ArrayList<Integer> matches = new ArrayList<Integer>();
        for(int i = 0; i<toSearch.size();i++)
        {
            // if the current item equals the string to test
            if(toSearch.get(i).getString().equals(string))
            {
                // add the item to the return list
                matches.add(i);
            }
        }
        // returns the indices of the matching objects
        return matches;
    }
}
