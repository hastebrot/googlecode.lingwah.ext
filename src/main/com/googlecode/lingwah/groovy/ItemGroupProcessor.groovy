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
            def matches = this.findAllByClass(node, ItemParser)
            if (matches.size() == 0) {
                matches = this.findAllByClass(node, GroupParser)
            }
            def result = getResults(matches)
            putResult(node, result)
        }
    }
    
    private List<Match> findAllByClass(Match root, Class cls) {
        def matches = []
        def todo = []
        todo.addAll(root.children)
        while (!todo.isEmpty()) {
            Match node = todo.remove(0)
            if (node.parser.class == GroupParser && cls != GroupParser) {
                continue
            }
            if (node.parser.class == cls) {
                matches.add(node)
            }
            if (node.parser.class == GroupParser && cls == GroupParser) {
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
