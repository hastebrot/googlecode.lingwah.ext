package com.googlecode.lingwah.ext.parser

import com.googlecode.lingwah.ParseContext
import com.googlecode.lingwah.ParseResults
import com.googlecode.lingwah.Parser

class GroupParser extends NullParser {
    
    public GroupParser(Parser parser) {
        super(parser)
    }
    
    String getDefaultLabel() {
        return "Group(" + this.parser.getLabel() + ")"
    }
    
}
