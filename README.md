# AI-World

Playground for AI-logic in a sandbox like world.

Currently there is not much implemented, just a small world with five moving and five spinning "things".   

Each thing has a position and a direction into which it "looks", it can move around and turn its direction. 

The goal of the project is to allow things to interact with other things in the world.  
They can modify their behaviour (in some evolutionary way). 

Things have to get resources otherwise they will die.
Things can reproduce themselves.  

All things in the World act synchronously during a tick() 

#TODO

The following issues have to be defines

* API for interaction with world / other nearby things
* Allow programmed Things and self-modifying things
* what kind of resources exist
  + growing Food (competition)?
* calculation time must also be a resource 
* what "programming language" defines the behaviour of the things, it must be (self-)modifiable
* Support parallel worlds?
