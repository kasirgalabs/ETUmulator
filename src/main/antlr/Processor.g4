grammar Processor;

prog
    : (line? EOL)+
    ;

line
    : instruction
    | label
    | data
    ;

instruction
    : arithmetic
    | multiplyAndDivide
    | move
    | shift
    | compare
    | logical
    | branch
    | singleDataMemory
    | stack
    | 'nop'
    ;

arithmetic
    : add
    | adds
    | adc
    | adcs
    | sub
    | subs
    | sbc
    | sbcs
    | rsb
    | rsbs
    | rsc
    | rscs
    ;

multiplyAndDivide
    : mul
    | muls
    | mla
    | mlas
    | mls
    | sdiv
    | udiv
    ;

move
    : mov
    | movs
    | mvn
    | mvns
    | movt
    ;

shift
    : asr
    | asrs
    | lsl
    | lsls
    | lsr
    | lsrs
    | ror
    | rors
    | rrx
    | rrxs
    | rbit
    ;

compare
    : cmp
    | cmn
    ;

logical
    : tst
    | teq
    | and
    | ands
    | eor
    | eors
    | orr
    | orrs
    | orn
    | orns
    | bic
    | bics
    ;

branch
    : b
    | beq
    | bne
    | bcs
    | bhs
    | bcc
    | blo
    | bmi
    | bpl
    | bvs
    | bvc
    | bhi
    | bls
    | bge
    | blt
    | bgt
    | ble
    | bal
    | bl
    ;

singleDataMemory
    : ldr
    | ldrb
    | ldrh
    | str
    | strb
    | strh
    ;

stack
    : push
    | pop
    ;

add
    : 'add' rd COMMA rn COMMA (imm12 | operand2)
    ;

adds
    : 'adds' rd COMMA rn COMMA operand2
    ;

adc
    : 'adc' rd COMMA rn COMMA operand2
    ;

adcs
    : 'adcs' rd COMMA rn COMMA operand2
    ;

sub
    : 'sub' rd COMMA rn COMMA (imm12 | operand2)
    ;

subs
    : 'subs' rd COMMA rn COMMA operand2
    ;

sbc
    : 'sbc' rd COMMA rn COMMA operand2
    ;

sbcs
    : 'sbcs' rd COMMA rn COMMA operand2
    ;

rsb
    : 'rsb' rd COMMA rn COMMA operand2
    ;

rsbs
    : 'rsbs' rd COMMA rn COMMA operand2
    ;

rsc
    : 'rsc' rd COMMA rn COMMA operand2
    ;

rscs
    : 'rscs' rd COMMA rn COMMA operand2
    ;

mul
    : 'mul' rd COMMA rm COMMA rs
    ;

muls
    : 'muls' rd COMMA rm COMMA rs
    ;

mla
    : 'mla' rd COMMA rm COMMA rs COMMA rn
    ;

mlas
    : 'mlas' rd COMMA rm COMMA rs COMMA rn
    ;

mls
    : 'mls' rd COMMA rm COMMA rs COMMA rn
    ;

sdiv
    : 'sdiv' rd COMMA rn COMMA rm
    ;

udiv
    : 'udiv' rd COMMA rn COMMA rm
    ;

mov
    : 'mov' rd COMMA (imm16 | operand2)
    ;

movs
    : 'movs' rd COMMA operand2
    ;

mvn
    : 'mvn' rd COMMA operand2
    ;

mvns
    : 'mvns' rd COMMA operand2
    ;

movt
    : 'movt' rd COMMA imm16
    ;

asr
    : 'asr' rd COMMA rm COMMA (rs | sh)
    ;

asrs
    : 'asrs' rd COMMA rm COMMA (rs | sh)
    ;

lsl
    : 'lsl' rd COMMA rm COMMA (rs | sh)
    ;

lsls
    : 'lsls' rd COMMA rm COMMA (rs | sh)
    ;

lsr
    : 'lsr' rd COMMA rm COMMA (rs | sh)
    ;

lsrs
    : 'lsrs' rd COMMA rm COMMA (rs | sh)
    ;

ror
    : 'ror' rd COMMA rm COMMA (rs | sh)
    ;

rors
    : 'rors' rd COMMA rm COMMA (rs | sh)
    ;

rrx
    : 'rrx' rd COMMA rm
    ;

rrxs
    : 'rrxs' rd COMMA rm
    ;

rbit
    : 'rbit' rd COMMA rm
    ;

cmp
    : 'cmp' rn COMMA operand2
    ;

cmn
    : 'cmn' rn COMMA operand2
    ;

tst
    : 'tst' rn COMMA operand2
    ;

teq
    : 'teq' rn COMMA operand2
    ;

and
    : 'and' rd COMMA rn COMMA operand2
    ;

ands
    : 'ands' rd COMMA rn COMMA operand2
    ;

eor
    : 'eor' rd COMMA rn COMMA operand2
    ;

eors
    : 'eors' rd COMMA rn COMMA operand2
    ;

orr
    : 'orr' rd COMMA rn COMMA operand2
    ;

orrs
    : 'orrs' rd COMMA rn COMMA operand2
    ;

orn
    : 'orn' rd COMMA rn COMMA operand2
    ;

orns
    : 'orns' rd COMMA rn COMMA operand2
    ;

bic
    : 'bic' rd COMMA rn COMMA operand2
    ;

bics
    : 'bics' rd COMMA rn COMMA operand2
    ;

b
    : 'b' DECIMAL
    ;

beq
    : 'beq' DECIMAL
    ;

bne
    : 'bne' DECIMAL
    ;

bcs
    : 'bcs' DECIMAL
    ;

bhs
    : 'bhs' DECIMAL
    ;

bcc
    : 'bcc' DECIMAL
    ;

blo
    : 'blo' DECIMAL
    ;

bmi
    : 'bmi' DECIMAL
    ;

bpl
    : 'bpl' DECIMAL
    ;

bvs
    : 'bvs' DECIMAL
    ;

bvc
    : 'bvc' DECIMAL
    ;

bhi
    : 'bhi' DECIMAL
    ;

bls
    : 'bls' DECIMAL
    ;

bge
    : 'bge' DECIMAL
    ;

blt
    : 'blt' DECIMAL
    ;

bgt
    : 'bgt' DECIMAL
    ;

ble
    : 'ble' DECIMAL
    ;

bal
    : 'bal' DECIMAL
    ;

bl
    : 'bl' (LABEL | DECIMAL)
    ;

ldr
    : 'ldr' rd COMMA (memoryAddress | ASSIGN number)
    ;

ldrb
    : 'ldrb' rd COMMA (memoryAddress | ASSIGN number)
    ;

ldrh
    : 'ldrh' rd COMMA (memoryAddress | ASSIGN number)
    ;

str
    : 'str' rd COMMA memoryAddress
    ;

strb
    : 'strb' rd COMMA memoryAddress
    ;

strh
    : 'strh' rd COMMA memoryAddress
    ;

memoryAddress
    : immediateOffset
    | postIndexedImmediate
    | registerOffset
    | postIndexedRegister
    ;

immediateOffset
    : LBRACK rn (COMMA offset)? RBRACK
    ;

postIndexedImmediate
    : LBRACK rn RBRACK COMMA offset
    ;

registerOffset
    : LBRACK rn COMMA rm (COMMA opsh)? RBRACK
    ;

postIndexedRegister
    : LBRACK rn RBRACK COMMA rm (COMMA opsh)?
    ;

push
    : 'push' regList
    ;

pop
    : 'pop' regList
    ;

regList
    : LBRACE (REGISTER | PC | LR) (COMMA (REGISTER | PC | LR))* RBRACE
    ;

rd
    : REGISTER
    ;

rn
    : REGISTER
    ;

rm
    : REGISTER
    ;

rs
    : REGISTER
    ;

operand2
    : rm
    | registerShiftedByRegister
    | registerShiftedByConstant
    | imm8m
    ;

registerShiftedByRegister
    : rm COMMA shiftOption rs
    ;

registerShiftedByConstant
    : rm COMMA shiftOption number
    ;

shiftOption
    : 'lsl'
    | 'lsr'
    | 'asr'
    | 'ror'
    ;

opsh
    : 'lsl' sh
    ;

sh
    : number
    ;

offset
    : number
    ;

imm16
    : number
    ;

imm12
    : number
    ;

imm8m
    : number
    ;

REGISTER
    : 'r0'
    | 'r1'
    | 'r2'
    | 'r3'
    | 'r4'
    | 'r5'
    | 'r6'
    | 'r7'
    | 'r8'
    | 'r9'
    | 'r10'
    | 'r11'
    | 'r12'
    ;

PC
    : 'pc'
    ;

LR
    : 'lr'
    ;

label
    : LABEL COLON
    ;

LABEL
    : [a-zA-Z_] [a-zA-Z0-9_]+
    ;

data
    : LABEL COLON asciz
    ;

asciz
    : '.asciz' STRING
    ;

STRING
	: DOUBLE_QUOTE CHARACTERS+ DOUBLE_QUOTE
	;

fragment CHARACTERS
	: CHARACTER+
	;

fragment CHARACTER
	: [ a-zA-Z0-9]
	;

number
    : DASH? (DECIMAL | HEX)
    ;

DECIMAL
    : [0-9]+
    ;

HEX
    : '0x' [0-9a-fA-F]+
    ;

DOUBLE_QUOTE
    : '"'
    ;

COLON
    : ':'
    ;

LBRACE
    : '{'
    ;

RBRACE
    : '}'
    ;

ASSIGN
    : '='
    ;

LBRACK
    : '['
    ;

RBRACK
    : ']'
    ;

DASH
    : '#'
    ;

COMMA
    : ','
    ;

COMMENT
    :   '/*' .*? '*/' -> skip
    ;

LINE_COMMENT
    :   '//' ~[\r\n]* -> skip
    ;

WS  :  [ \t\r\u000C]+ -> skip
    ;

EOL
    : '\r'? '\n'
    ;
