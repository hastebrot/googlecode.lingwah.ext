package com.googlecode.lingwah.groovy

import com.googlecode.lingwah.AbstractProcessor
import com.googlecode.lingwah.Match
import com.googlecode.lingwah.groovy.parser.GroupParser
import com.googlecode.lingwah.groovy.parser.ItemParser

class ItemGroupProcessor extends AbstractProcessor {
    
    void complete(Match node) {
        //println node.parser.class.simpleName
        if (node.parser.class == ItemParser) {
            putResult(node, node.text)
        }
        if (node.parser.class == GroupParser) {
            def matches = this.findAllItems(node)
            if (matches.size() == 0) {
                matches = this.findAllGroups(node)
            }
            def result = getResults(matches)
            putResult(node, result)
        }
    }
    
    private List<Match> findAllItems(Match root) {
        def matches = []
        def todo = []
        todo.addAll(root.children)
        while (!todo.isEmpty()) {
            Match node = todo.remove(0)
            if (node.parser.class == GroupParser && ItemParser != GroupParser) {
                continue
            }
            if (node.parser.class == ItemParser) {
                matches.add(node)
            }
            if (node.parser.class == GroupParser && ItemParser == GroupParser) {
                continue
            }
            todo.addAll(node.children)
        }
        return matches
    }
    
    private List<Match> findAllGroups(Match root) {
        def matches = []
        def todo = []
        todo.addAll(root.children)
        while (!todo.isEmpty()) {
            Match node = todo.remove(0)
            if (node.parser.class == GroupParser && GroupParser != GroupParser) {
                continue
            }
            if (node.parser.class == GroupParser) {
                matches.add(node)
            }
            if (node.parser.class == GroupParser && GroupParser == GroupParser) {
                continue
            }
            todo.addAll(node.children)
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
