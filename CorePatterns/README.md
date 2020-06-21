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


#### 1.2. Factory Method Pattern

    To centralize creation of objects of same kind of classes.

#### 1.3. Builder Pattern

    Separate the construction of a complex object from its representation, allowing the same construction process to 
    create various representations.


#### 1.4. Abstract Factory Pattern

    Provide an interface for creating families of related or dependent objects without specifying their concrete classes.


#### 1.5. Prototype Pattern

    A fully initialized instance to be copied or cloned


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




#### 2.2. Bridge Pattern

    Separates/decouples an object’s abstraction from its implementation.
    With this if the implementation changes, it does not affect abstraction and vice versa.

i.e.
- The switch is the abstraction, and the electronic equipments are the implementations.

#### 2.3. Composite Pattern

    A tree structure of simple and composite objects.

#### 2.4. Decorator Pattern

    Add responsibilities to objects dynamically.
    Adds dynamically stacked behavior thus helping us to change the behavior of the object on runtime.

#### 2.5. Façade Pattern

    A single class that represents an entire subsystem.

#### 2.6. Flyweight Pattern

    A fine-grained instance used for efficient sharing.
    Fly weight pattern is useful where we need to create many objects and all these objects share some kind of common 
    data.

i.e.
- Printing visiting card for all employees in the organization.

#### 2.7. Proxy Pattern

    An object representing another object.

    Proxy fundamentally is a class functioning as an interface, which points towards the actual class which has data.
    This actual data can be a huge image or an object data which is very large and can not be duplicated. So you can 
    create multiple proxies and point towards the huge memory consuming object and perform operations.
    This avoids duplication of the object and thus saving memory.
    Proxies are references, which points towards the actual object.    

The advantages of using proxy are security and avoiding duplicating objects which are of huge sizes.

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

#### 3.2. Memento Pattern

    Capture and restore an object's internal state with out violating encapsulation.

#### 3.3. Interpreter Pattern

    A way to include language elements in a program.

#### 3.4. Iterator Pattern

    Sequentially access the elements of a collection.

#### 3.5. Chain of Responsibility Pattern

    A way of passing a request between a chain of objects.
    Chain of Responsibility is used when we have series of processing which will be handled by a series of handler logic.

#### 3.6. Command Pattern

    Encapsulate a command request as an object.

#### 3.7. State Pattern

    It allows an object to change its behavior when it's state changes.

#### 3.8. Strategy Pattern

    Encapsulates an algorithms inside a class which can be interchanged depending on the usage of the class.

#### 3.9. Observer Pattern

    A way to communicate or notify a change between various objects and its associated or dependent objects.
    
    There are two important concepts in observer pattern ‘Subject’ (Observable) and ‘Observers’ (Observer).
    The subject sends notifications while observers receive notifications if they are registered with the subject.
    You can map this example to publisher and subscriber model.

A class can implement the <code>Observer</code> interface when it wants to be informed of changes in observable objects


#### 3.10. Template Method Pattern

    Defer the exact steps of an algorithm to a subclass.
    
    Template pattern is used in scenarios where we want to create an extendable behaviors in generalization and specialization relationship.

#### 3.11. Visitor Pattern

    Defines a new operation to a class without change.
    
    It allows us to change the class structure with out changing the actual class. It's a way of separating the logic 
    and algorithm from the current data structure.
    

### 4. Similarities and Differences

### 4.1. Visitor vs Strategy

- In strategy, we have one context on which multiple algorithms operate.

- In visitor, we have multiple contexts and for every context we have an algorithm. 
    
### 4.2. Visitor vs Strategy



## Authors

* [Rohtash Lakra](https://github.com/rslakra/DesignPatterns.git)
