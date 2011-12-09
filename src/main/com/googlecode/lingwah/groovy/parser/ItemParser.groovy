package com.googlecode.lingwah.groovy.parser

import com.googlecode.lingwah.ParseContext
import com.googlecode.lingwah.ParseResults
import com.googlecode.lingwah.Parser

class ItemParser extends Parser {
    
    private Parser parser

    public ItemParser(Parser parser) {
        this.parser = parser
    }
    
    String getDefaultLabel() {
        return "Item(" + parser.getLabel() + ")"
    }

    void startMatching(ParseContext ctx, int start, ParseResults parseResults) {
    }

    void completeMatching(ParseContext ctx, int start, ParseResults parseResults) {
    }

    public List<Parser> getDependencies() {
        return null
    }
    
}
