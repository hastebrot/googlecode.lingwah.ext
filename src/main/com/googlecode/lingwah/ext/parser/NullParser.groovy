package com.googlecode.lingwah.ext.parser

import com.googlecode.lingwah.Match
import com.googlecode.lingwah.ParseContext
import com.googlecode.lingwah.ParseError
import com.googlecode.lingwah.ParseResults
import com.googlecode.lingwah.Parser

class NullParser extends Parser {
    
    protected Parser parser

    public NullParser(Parser parser) {
        this.parser = parser
    }
    
    String getDefaultLabel() {
        return "Null(" + this.parser.getLabel() + ")"
    }
    
    public List<Parser> getDependencies() {
        return [parser]
    }

    void startMatching(ParseContext ctx, int start, ParseResults parseResults) {
        //this.parser.startMatching(ctx, start, parseResults)
        ctx.doMatch(this.parser, start).addListener(new ParseResults.Listener() {
            void onMatchFound(ParseResults results, Match node) {
                parseResults.addMatch(node)
            }
            void onMatchError(ParseResults results, ParseError parseError) {
                // ignore
            }
        })
    }

    void completeMatching(ParseContext ctx, int start, ParseResults parseResults) {
        this.parser.completeMatching(ctx, start, parseResults)
    }
    
}
