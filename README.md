# AI-World

Playground for AI-logic in a sandbox like world.

## Idea

Allow AI programs (autonomous agents) to live in a sandbox like world.

Different AI programs can compete with each other.
AI programs can interact with other AI programs
AI programs might be hand written or might evolve by modifying their program.
For example an AI prorgram can start with a Neural Network which has all sensors as input and decides, what do do next.
The AI Program might reproduce itself by training a new NN with different hyper-parameters. 

A goal for the AI programs should be defined. 
E.g. there might be some food which must be eaten to survive.
Other resources like computation time during a tick() must also be taken into account.   
 
## INPUT SENSORS

* look ahead
  - other things like walls, food, other programs, ..

## ACTIONS

* move forward / backward
* turn
* interact with nearby things
* ...

## MEMORY

* some kind of storage 
 
## Status

Currently there is not much implemented, just a small world with five moving and five spinning "things".   

Each thing has a position and a direction into which it "looks", it can move around and turn its direction. 

All things in the World act synchronously during a tick().

Later it could be more like a client/server protocol (REST) to allow other programs (not Java) to create AI programs which live in the world.
Asynchronous communication could solve the problem with calculation time for each tick(). 
Slower AI programs have less ticks() in the same time, then faster AI programs.  


## Files

*AIWorldJava* contains the java sources for AI-World.
*AIWorldDoc* contains additional documentation, ideas, ... about what AI-World might be.


## Further Ideas

Perhaps some parallel worlds could be supported to select the world which evolves best?
Define some productivity goal for the world.

