package com.googlecode.lingwah.ext.grammars

import com.googlecode.lingwah.Grammar
import com.googlecode.lingwah.Parser
import com.googlecode.lingwah.ext.parser.GroupParser
import com.googlecode.lingwah.ext.parser.ItemParser

class ItemGroupGrammar extends Grammar {
    
    protected ItemParser I(final Parser parser) {
        return this.Item(parser)
    }
    
    protected ItemParser Item(final Parser parser) {
        return new ItemParser(parser)
    }
    
    protected GroupParser G(final Parser parser) {
        return this.Group(parser)
    }
    
    protected GroupParser Group(final Parser parser) {
        return new GroupParser(parser)
    }
    
}
