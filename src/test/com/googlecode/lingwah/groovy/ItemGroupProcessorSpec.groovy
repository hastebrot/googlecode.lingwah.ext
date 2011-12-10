package com.googlecode.lingwah.groovy

import com.googlecode.lingwah.Parser;
import com.googlecode.lingwah.groovy.grammars.ItemGroupGrammar;
import com.googlecode.lingwah.groovy.util.GroovyGrammarUtils;

import spock.lang.Specification;

class ItemGroupProcessorSpec extends Specification {

    ItemGroupProcessor processor
    
    TestGrammar grammar
    
    def setup() {
        processor = new ItemGroupProcessor()
        grammar = new TestGrammar()
    }
    
    class TestGrammar extends ItemGroupGrammar {
        def a = str("a")
        def b = str("b")
        
        def test1 = Item(a)
        def test2 = Item(rep(a))
        def test3a = seq(Item(a), Item(b))
        def test3b = Group(rep(a))
        def test4 = Group(rep(Item(a)))
        def test5 = Group(rep(Group(Item(a))))
        def test6 = Group(seq(Item(a), Group(rep(Item(b)))))
    }
    
    def "test1"() {
        expect:
        parseAndProcess(grammar.test1, "a") == "a"
    }
    
    def "test2"() {
        expect:
        parseAndProcess(grammar.test2, "aaa") == "aaa"
    }
    
    def "test3a"() {
        expect:
        parseAndProcess(grammar.test3a, "ab") == null
    }
    
    def "test3b"() {
        expect:
        parseAndProcess(grammar.test3b, "aaa") == []
    }
    
    def "test4"() {
        expect:
        parseAndProcess(grammar.test4, "aaa") == ["a", "a", "a"]
    }
    
    def "test5"() {
        expect:
        parseAndProcess(grammar.test5, "aaa") == [["a"], ["a"], ["a"]]
    }
    
    def "test6"() {
        expect:
        parseAndProcess(grammar.test6, "abb") == ["a", ["b", "b"]]
    }
    
    Object parseAndProcess(Parser parser, String input) {
        def results = GroovyGrammarUtils.parse(parser, input)
        def result = processor.getResult(results.getLongestMatch())
        return result
    }
        
}
