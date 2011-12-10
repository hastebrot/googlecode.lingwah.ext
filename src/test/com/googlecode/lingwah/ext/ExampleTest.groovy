package com.googlecode.lingwah.ext

import com.googlecode.lingwah.ext.ItemGroupProcessor
import com.googlecode.lingwah.ext.grammars.ItemGroupGrammar
import com.googlecode.lingwah.ext.util.GroovyGrammarUtils

class ExampleGrammar extends ItemGroupGrammar {
    def hash = Group(Group(Item(str("#"))))
    def start = Group(zeroOrMore(hash))

    private ExampleGrammar() { GroovyGrammarUtils.instructParserLabels(this) }
    static ExampleGrammar INST = new ExampleGrammar()
}

def grammar = ExampleGrammar.INST
def processor = new ItemGroupProcessor()

//def results = ParseContext.parse(grammar.start, "##")
def results = GroovyGrammarUtils.parse(grammar.start, "##")
def result = processor.getResult(results.getLongestMatch())
println result // [[[#]], [[#]]]
