package com.googlecode.lingwah.ext

import com.googlecode.lingwah.Grammar
import com.googlecode.lingwah.ParseContext
import com.googlecode.lingwah.ParseResults
import com.googlecode.lingwah.Parser

class CaveatRegexRepetitionGrammar extends Grammar {
    Parser start = rep(regex("a*"))
}

def grammar = new CaveatRegexRepetitionGrammar()
def parser = grammar.start
def input = "aaa"

def context = new ParseContext(input)
def results = new ParseResults(context, parser, 0)

// java.lang.StackOverflowError
parser.startMatching(context, 0, results)
