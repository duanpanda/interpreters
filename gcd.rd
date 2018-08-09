// Greatest Common Divisor
x := 8; // assign 8 to x
y := 12;
WHILE x != y DO
   IF x > y THEN x := x-y
   ELSE y := y-x
   FI
OD;
PRINT x
