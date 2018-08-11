(defparameter *s* "WHILE x != y DO
   IF x > y THEN x := x-y
   ELSE y := y-x
   FI
OD")
(defparameter *keys* '("IF" "THEN" "WHILE" "DO" "OD" "PRINT" ":=" "-" "ELSE"   "FI" "!=" ">"))
(print (tokenize *s* *keys*))

