package com.epam.mjc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        String[] tempValues = source.split(delimiters.iterator().next());

        List<String> resultList = new ArrayList<>(Arrays.asList(tempValues));

        for (String splitValue: delimiters){
            List<String> tempList = new ArrayList<>();

            for (String stringValue : resultList) {
                String[] listValues = stringValue.split(splitValue);

                for (String value : listValues){
                    if (value.isEmpty())
                        continue;
                    tempList.add(value);
                }
            }
            resultList = tempList;
        }
        return resultList;
    }
}
