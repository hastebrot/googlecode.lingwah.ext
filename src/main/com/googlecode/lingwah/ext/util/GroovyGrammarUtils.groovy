package com.googlecode.lingwah.ext.util

import java.lang.reflect.Field

import com.googlecode.lingwah.Grammar
import com.googlecode.lingwah.ParseContext
import com.googlecode.lingwah.ParseResults
import com.googlecode.lingwah.Parser

class GroovyGrammarUtils {
    
    // private MyGrammar() { GroovyGrammarUtil.instructParserLabels(this) }
    // static MyGrammar INST = new MyGrammar()
    static void instructParserLabels(Grammar grammar) {
        def fields = grammar.class.declaredFields
        for (Field field in fields) {
            def fieldValue = grammar."${field.name}"
            if (fieldValue instanceof Parser) {
                ((Parser) fieldValue).setLabel(field.name)
            }
        }
    }
    
    static ParseResults parse(Parser parser, String input) {
        def context = new ParseContext(input)
        //context.trace(parser, false)
        context._trace = false
        return context.getParseResults(parser, 0)
    }

}
