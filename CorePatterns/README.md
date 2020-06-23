# CorePatterns

The ```CorePatterns``` modules provides the information about the core design patterns.

All the examples, used in this repository to be written in ```Java```.

## Core Categories of Design Patterns

The core categories of the design patterns, based on the kinds of problems, they solve are:

### 1. Creational Patterns

- Singleton
- Factory Method
- Builder
- Abstract Factory
- Prototype

#### 1.1. Singleton Pattern

    Ensure a class has only one instance, and provide a global point of access to it.
    
- Breaking singleton property of a class
    - Reflection
    - Serialization
    - Cloning

- Java Singleton Implementations
    - java.lang.Runtime
    - java.awt.Desktop
    
- Applications of Singleton
    - Hardware (Print Spooler)
    - Logger
    - Cache
    - Config Reader    

[Singleton Example](src/com/devamatre/designpatterns/creational/singleton)


#### 1.2. Factory Method Pattern

    To centralize creation of objects of same kind of classes.
    
[Factory Method Example](src/com/devamatre/designpatterns/creational/factory)    


#### 1.3. Builder Pattern

    Separate the construction of a complex object from its representation, allowing the same construction process to 
    create various representations.

[Builder Example](src/com/devamatre/designpatterns/creational/builder)    


#### 1.4. Abstract Factory Pattern

    Provide an interface for creating families of related or dependent objects without specifying their concrete classes.

[Abstract Factory Example](src/com/devamatre/designpatterns/creational/abstractfactory)    


#### 1.5. Prototype Pattern

    A fully initialized instance to be copied or cloned


[Prototype Example](src/com/devamatre/designpatterns/creational/prototype)    


### 2. Structural Patterns

- Adapter
- Bridge
- Composite
- Decorator
- Façade
- Flyweight
- Proxy

#### 2.1. Adapter Pattern

    Adapter allows to communicate with incompatible objects or interfaces and make the objects/interfaces compatible with each other.
    
There are two ways of implementing adapter pattern:
- Using Aggregation (termed as the object adapter pattern), and 
- Using Inheritance (termed as the class adapter pattern)


[Adapter Example](src/com/devamatre/designpatterns/structural/adapter)


#### 2.2. Bridge Pattern

    Separates/decouples an object’s abstraction from its implementation.
    With this if the implementation changes, it does not affect abstraction and vice versa.

i.e.
- The switch is the abstraction, and the electronic equipments are the implementations.

- Adapter vs. Bridge
    
    ```Adapter``` makes things work after they're designed; ```Bridge``` makes them work before they are.

[Bridge Example](src/com/devamatre/designpatterns/structural/bridge)

#### 2.3. Composite Pattern

    A tree structure of simple and composite objects.
    
    
[Composite Example](src/com/devamatre/designpatterns/structural/composite)


#### 2.4. Decorator Pattern

    Add responsibilities to objects dynamically.
    Adds dynamically stacked behavior thus helping us to change the behavior of the object on runtime.
    
[Decorator Example](src/com/devamatre/designpatterns/structural/decorator)
    

#### 2.5. Façade Pattern

    A single class that represents an entire subsystem.
    
[Façade Example](src/com/devamatre/designpatterns/structural/facade)

#### 2.6. Flyweight Pattern

    A fine-grained instance used for efficient sharing.
    Fly weight pattern is useful where we need to create many objects and all these objects share some kind of common 
    data.

i.e.
- Printing visiting card for all employees in the organization.

[Flyweight Example](src/com/devamatre/designpatterns/structural/flyweight)


#### 2.7. Proxy Pattern

    An object representing another object.

    Proxy fundamentally is a class functioning as an interface, which points towards the actual class which has data.
    This actual data can be a huge image or an object data which is very large and can not be duplicated. So you can 
    create multiple proxies and point towards the huge memory consuming object and perform operations.
    This avoids duplication of the object and thus saving memory.
    Proxies are references, which points towards the actual object.    

The advantages of using proxy are security and avoiding duplicating objects which are of huge sizes.

[Proxy Example](src/com/devamatre/designpatterns/structural/proxy)

### 3. Behavioral Patterns

- Mediator
- Memento
- Interpreter
- Iterator
- Chain of Responsibility
- Command
- State
- Strategy
- Observer
- Template Method
- Visitor

#### 3.1. Mediator Pattern

    Defines simplified communication between classes.
    
[Mediator Example](src/com/devamatre/designpatterns/behavioral/mediator)

#### 3.2. Memento Pattern

    Capture and restore an object's internal state with out violating encapsulation.
    
[Memento Example](src/com/devamatre/designpatterns/behavioral/memento)

#### 3.3. Interpreter Pattern

    A way to include language elements in a program.
    
[Interpreter Example](src/com/devamatre/designpatterns/behavioral/interpreter)

#### 3.4. Iterator Pattern

    Sequentially access the elements of a collection.

[Iterator Example](src/com/devamatre/designpatterns/behavioral/iterator)

#### 3.5. Chain of Responsibility Pattern

    A way of passing a request between a chain of objects.
    Chain of Responsibility is used when we have series of processing which will be handled by a series of handler logic.

[Chain of Responsibility Example](src/com/devamatre/designpatterns/behavioral/chainofresponsibility)

#### 3.6. Command Pattern

    Encapsulate a command request as an object.
    
[Command Example](src/com/devamatre/designpatterns/behavioral/command)

#### 3.7. State Pattern

    It allows an object to change its behavior when it's state changes.

[State Example](src/com/devamatre/designpatterns/behavioral/state)

#### 3.8. Strategy Pattern

    Encapsulates an algorithms inside a class which can be interchanged depending on the usage of the class.

[Strategy Example](src/com/devamatre/designpatterns/behavioral/strategy)

#### 3.9. Observer Pattern

    A way to communicate or notify a change between various objects and its associated or dependent objects.
    
    There are two important concepts in observer pattern ‘Subject’ (Observable) and ‘Observers’ (Observer).
    The subject sends notifications while observers receive notifications if they are registered with the subject.
    You can map this example to publisher and subscriber model.

A class can implement the <code>Observer</code> interface when it wants to be informed of changes in observable objects

[Observer Example](src/com/devamatre/designpatterns/behavioral/observer)

#### 3.10. Template Method Pattern

    Defer the exact steps of an algorithm to a subclass.
    
    Template pattern is used in scenarios where we want to create an extendable behaviors in generalization and specialization relationship.

[Template Example](src/com/devamatre/designpatterns/behavioral/template)

#### 3.11. Visitor Pattern

    Defines a new operation to a class without change.
    
    It allows us to change the class structure with out changing the actual class. It's a way of separating the logic 
    and algorithm from the current data structure.

[Visitor Example](src/com/devamatre/designpatterns/behavioral/visitor)

### 4. Similarities and Differences

### 4.1. Visitor vs Strategy

- In strategy pattern, there is only one context on which multiple algorithms operate.

- In visitor pattern, there are multiple contexts and for every context we have an algorithm. 
    
### 4.2. 


### 5. Rules of thumb
- Sometimes creational patterns are competitors: there are cases when either ```Prototype``` or ```Abstract Factory``` 
 could be used profitably. At other times they are complementary: ```Abstract Factory``` might store a set of 
 ```Prototypes``` from which to clone and return product objects, ```Builder``` can use one of the other patterns to 
 implement which components get built. ```Abstract Factory```, ```Builder```, and ```Prototype``` can use ```Singleton``` 
 in their implementation.

- ```Abstract Factory```, ```Builder```, and ```Prototype``` define a factory object that's responsible for knowing and 
 creating the class of product objects, and make it a parameter of the system. ```Abstract Factory``` has the factory 
 object producing objects of several classes. ```Builder``` has the factory object building a complex product 
 incrementally using a correspondingly complex protocol. ```Prototype``` has the factory object (aka prototype) building 
 a product by copying a prototype object.

- ```Abstract Factory``` classes are often implemented with ```Factory Methods```, but they can also be implemented 
 using ```Prototype```.

- ```Abstract Factory``` can be used as an alternative to ```Facade``` to hide platform-specific classes.

- ```Builder``` focuses on constructing a complex object step by step. ```Abstract Factory``` emphasizes a family of 
 product objects (either simple or complex). ```Builder``` returns the product as a final step, but as far as the 
 ```Abstract Factory``` is concerned, the product gets returned immediately.

- ```Builder``` is to creation as ```Strategy``` is to algorithm.

- ```Builder``` often builds a ```Composite```.

- ```Factory Methods``` are usually called within ```Template``` methods.

- ```Factory Method```: creation through inheritance. ```Prototype```: creation through delegation.

- Often, designs start out using ```Factory Method``` (less complicated, more customizable, subclasses proliferate) and 
 evolve toward ```Abstract Factory```, ```Prototype```, or ```Builder``` (more flexible, more complex) as the designer 
 discovers where more flexibility is needed.

- ```Prototype``` doesn't require subclassing, but it does require an Initialize operation. 
 ```Factory Method``` requires subclassing, but doesn't require Initialize.

- Designs that make heavy use of the ```Composite``` and ```Decorator``` patterns often can benefit from 
 ```Prototype``` as well.


## References
- [wikipedia.org](https://en.wikipedia.org/wiki/Singleton_pattern)
- [sourcemaking.com](https://sourcemaking.com/design_patterns)
- [oodesign.com](https://www.oodesign.com/singleton-pattern.html)
- [geeksforgeeks.org](https://www.geeksforgeeks.org/design-patterns-set-1-introduction/?ref=lbp)
- [tutorialspoint.com](https://www.tutorialspoint.com/design_pattern/index.htm)

## Authors

* [Rohtash Lakra](https://github.com/rslakra/DesignPatterns.git)
