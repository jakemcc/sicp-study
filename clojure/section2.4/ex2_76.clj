; Exercise 2.76
;
; For each of the below strategies, describe the changes
; that must be made to a system in order to add new types
; or new operations. Which organizaion would be the most
; appropriate for aa system in which new types must be often
; added? Which would be the most appropriate for a system
; in which new operations must often be added?
;
;1) generic operations with explicit dispatch
;   - Hard to add new types, need to go and
;     modify each procedure. Every procedure
;     knows about every type.
;
;2) data-directed style
;   - All types isolated from other types.
;   - Need to write generic procedure which looks up
;     what procedure should actually be called based 
;     on arguments.
;
;3) message-passing style
;   - Need to just add a new object which
;     knows how to handle different messages.
;
; Strategy 1 is a lot of work for adding new types and new operations.
; Strategy 2 you need to install the new type still. Nothing else has to change.
; Strat 3 you only create the new type. You don't need to do anything else.
;
