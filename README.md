# Julia Interpreter Project 

### By Hannah Duncan, Colleen Hynes, and Mary Le  

The project for this course is the development of an interpreter of a language implemented in Java.  

### The Interpreter Project  

This project consists of developing an interpreter for a minimal form of the Julia language (see JuliaLang.org). This minimal form of Julia has only 1 data type, integer, and the only identifiers are single letters.  

The interpreter will process a Julia program and build some intermediate data structures. These data structures will then be interpreted to execute the program. All tokens in this language are separated by white space. The parsing algorithm should detect any syntacticalor semantic error. The first such error discovered should cause an appropriate error message to be printed, and then the interpreter should terminate. Run-time errors should also be detected with appropriate error messages being printed.  

This project is being completed in three parts:  
- Part 1: Scanner
- Part 2: Parser  
- Part 3: Interpreter  

### Grammar for the language  

__Syntax Analyzer__

```
<program> → functionid() <block> end  

<block> → <statement> | <statement> <block>   

<statement> → <if_statement> | <assignment_statement> | <while_statement> | <print_statement> |<repeat_statement>  

<if_statement> → if <boolean_expression> then <block >else <block> end  

<while_statement> → while <boolean_expression> do <block> end  

<assignment_statement> → id <assignment_operator> <arithmetic_expression>  

<repeat_statement> → repeat <block> until <boolean_expression>  

<print_statement> → print (<arithmetic_expression>)  

<boolean_expression> → <relative_op> <arithmetic_expression> <arithmetic_expression>  

<relative_op> → le_operator | lt_operator | ge_operator |gt_operator | eq_operator | ne_operator  

<arithmetic_expression> → <id> | <literal_integer> | <arithmetic_op>  

<arithmetic_expression>  

<arithmetic_expression>  

<arithmetic_op> → add_operator | sub_operator | mul_operator | div_operator  
```  

__Lexical Analyzer__  

```  
id → letter  

literal_integer → digit literal_integer | digit  

assignment_operator → =  

le_operator → <=  

lt_operator → <  

ge_operator → >=  

gt_operator → >  

eq_operator → = =  

ne_operator → ~=  

add_operator → +  

sub_operator → -  

mul_operator → *  

div_operator → /  
```

This project is for Kennesaw State Universities CS 4308 – Concepts of Programming Languages course. This course is being completed during Fall 2019 and was taught by Deepa Muralidhar.
