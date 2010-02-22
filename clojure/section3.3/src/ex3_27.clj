; Exercise 3.27
;


; Q: Draw an environment diagram to analyze computation of (memo-fib 3)
; A: Not drawing it.

; Q: Explain why memo-fib computes nth Fibonacci number in number of steps
;    proportional to n.
;
; A: Solutions are cached for each call to memo-fib. For example
;    if (memo-fib 3) is calculated, then next time (memo-fib 3) is
;    called the only work done is to look up in a table the key 3,
;    which then returns the solution.
;
;    This caching allows the recursive procedure for finding
;    Fibonacci numbers to run proportinal to n. It does not
;    duplicate any work. This is much better than the non-memoized version.
;
;   Q: Could we have just done (memoize fib)?
;   A: No. Then the recursive calls would not be caching their solutions
;      or looking up in the table to see if the solution has already been
;      calculated for the input.
