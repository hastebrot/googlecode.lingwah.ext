package com.googlecode.lingwah.groovy.parser

import com.googlecode.lingwah.ParseContext
import com.googlecode.lingwah.ParseResults
import com.googlecode.lingwah.Parser

class ItemParser extends NullParser {
    
    public ItemParser(Parser parser) {
        super(parser)
    }
    
    String getDefaultLabel() {
        return "Item(" + this.parser.getLabel() + ")"
    }
    
}
