package com.example.aggregator.service;

import com.example.aggregator.client.AggregatorRestClient;
import com.example.aggregator.model.Entry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AggregatorService {

    private final AggregatorRestClient aggregatorRestClient;

    public AggregatorService(AggregatorRestClient aggregatorRestClient) {
        this.aggregatorRestClient = aggregatorRestClient;
    }

    public Entry getDefinitionFor(String word) {
        return aggregatorRestClient.getDefinitionFor(word);
    }

    public List<Entry> getWordsThatContainSuccessiveLettersAndStartsWith(String chars) {

        List<Entry> wordsThatStartWith = aggregatorRestClient.getWordsStartingWith(chars);
        List<Entry> wordsThatContainSuccessiveLetters = aggregatorRestClient.getWordsThatContainConsecutiveLetters();

        // stream API version
        List<Entry> common = wordsThatStartWith.stream()
                .filter(wordsThatContainSuccessiveLetters::contains)
                .toList();

        /*List<Entry> common = new ArrayList<>(wordsThatStartWith);
        common.retainAll(wordsThatContainSuccessiveLetters);*/

        return common;

    }

    public List<Entry> getWordsThatContain(String chars) {

        return aggregatorRestClient.getWordsThatContain(chars);

    }

    // rewrite the getAllPalindromes method using loops
    public List<Entry> getAllPalindromes() {

        List<Entry> candidates = new ArrayList<>();

        // Iterate from 'a' to 'z'
        for (char c = 'a'; c <= 'z'; c++) {
            String character = Character.toString(c);

            // Get words starting and ending with the character
            List<Entry> startsWith = aggregatorRestClient.getWordsStartingWith(character);
            List<Entry> endsWith = aggregatorRestClient.getWordsEndingWith(character);

            // Keep entries that exist in both lists
            List<Entry> startsAndEndsWith = new ArrayList<>();
            for (Entry startEntry : startsWith) {
                if (endsWith.contains(startEntry)) {
                    startsAndEndsWith.add(startEntry);
                }
            }

            // Add the valid entries to the candidates list
            candidates.addAll(startsAndEndsWith);
        }

        // Filter palindromes and sort the list
        List<Entry> palindromes = new ArrayList<>();
        for (Entry entry : candidates) {
            String word = entry.getWord();
            String reverse = new StringBuilder(word).reverse().toString();
            if (word.equals(reverse)) {
                palindromes.add(entry);
            }
        }

        // Sort the palindromes alphabetically
        palindromes.sort((e1, e2) -> e1.getWord().compareTo(e2.getWord()));

        return palindromes;
    }

}
