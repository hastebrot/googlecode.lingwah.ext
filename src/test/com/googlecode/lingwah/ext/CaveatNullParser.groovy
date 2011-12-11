package com.googlecode.lingwah.ext

import com.googlecode.lingwah.Grammar
import com.googlecode.lingwah.ParseContext
import com.googlecode.lingwah.ParseResults
import com.googlecode.lingwah.Parser

class CaveatNullParserGrammar extends Grammar {
    Parser token2 = str("b")
    Parser start = seq(token1, token2) // token1 == null
    Parser token1 = str("a")
}

def grammar = new CaveatNullParserGrammar()
def parser = grammar.start
def input = "ab"
println parser.getDependencies() == [grammar.token1, grammar.token2]

def context = new ParseContext(input)
def results = new ParseResults(context, parser, 0)

// java.lang.NullPointerException
parser.startMatching(context, 0, results)
