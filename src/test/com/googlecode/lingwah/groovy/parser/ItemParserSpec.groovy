package com.googlecode.lingwah.groovy.parser

import spock.lang.Specification
import com.googlecode.lingwah.parser.StringParser

class ItemParserSpec extends Specification {

    StringParser stringParser
    ItemParser itemParser
    
    def setup() {
        stringParser = new StringParser("foo")
        itemParser = new ItemParser(stringParser)
    }
        
    def "parser"() {
        expect:
        itemParser.parser == stringParser
    }
    
    def "getDefaultLabel"() {
        expect:
        itemParser.getDefaultLabel() ==~ /Item\(.+\)/
    }
    
}
