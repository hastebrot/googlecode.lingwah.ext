package com.googlecode.lingwah.ext

import com.googlecode.lingwah.AbstractProcessor
import com.googlecode.lingwah.Match
import com.googlecode.lingwah.ext.parser.GroupParser
import com.googlecode.lingwah.ext.parser.ItemParser

class ItemGroupProcessor extends AbstractProcessor {
    
    void complete(Match node) {
        //println node.parser.defaultLabel
        if (node.parser.class == ItemParser) {
            putResult(node, node.text)
        }
        if (node.parser.class == GroupParser) {
            def matches = this.findMatches(node)
            def result = getResults(matches)
            putResult(node, result)
        }
    }
    
    private List<Match> findMatches(Match root) {
        def matches = []
        def todo = []
        todo.addAll(root.children)
        while (!todo.isEmpty()) {
            Match node = todo.remove(0)
            if (node.parser.class == GroupParser) {
                matches << node
            }
            else if (node.parser.class == ItemParser) {
                matches << node
            }
            else {
                todo.addAll(node.children)
            }
        }
        return matches
    }

    private List<Object> getResults(List<Match> matches) {
        def results = []
        for (match in matches) {
            results << getResult(match)
        }
        return results
    }
    
}
