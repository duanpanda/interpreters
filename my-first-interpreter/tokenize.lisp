;;; Sample Usage
;;;
;;; (defparameter *s* "WHILE x != y DO
;;;   IF x > y THEN x := x-y
;;;   ELSE y := y-x
;;;   FI
;;; OD")
;;; (defparameter *keys* '("IF" "THEN" "WHILE" "DO" "OD" "PRINT" ":=" "-" "ELSE"
;;;   "FI" "!=" ">"))
;;; (tokenize *s* *keys*)
;;; =>
;;; ("WHILE" "x" "!=" "y" "DO" "IF" "x" ">" "y" "THEN" "x" ":=" "x" "-" "y"
;;;  "ELSE" "y" ":=" "y" "-" "x" "FI" "OD")

(defun tokenize (s keys)
  (if (null keys)
      (let ((ns (string-trim '(#\Space #\Tab #\Newline) s)))
	(if (= (length ns) 0)
	    nil
	    (list ns)))
      (let* ((k (car keys))
	     (index (search k s)))
	(if index
	    (append (tokenize (subseq s 0 index)
			      (cdr keys))
		    (list k)
		    (tokenize (subseq s (+ index (length k)))
			      keys))
	    (tokenize s (cdr keys))))))
