package com.googlecode.lingwah.groovy.util

import java.lang.reflect.Field

import com.googlecode.lingwah.Grammar
import com.googlecode.lingwah.Parser

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
