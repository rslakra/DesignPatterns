# DesignPatterns

This repository contains programs related to design patterns, including core GoF-style examples and related material.

All examples are written in **Java**.

## Repository layout

| Path | Description |
|------|-------------|
| [`pom.xml`](pom.xml) | Maven aggregator (multi-module root) |
| [`CorePatterns/`](CorePatterns/) | Core design pattern samples (`com.rslakra.designpatterns…`) |
| [`J2EEPatterns/`](J2EEPatterns/) | J2EE / servlet / JSP samples (legacy `javax.*`, Java 21) |

## Build

- **JDK** 21 and **Apache Maven** 3.6+ are recommended for the full reactor (**CorePatterns** and **J2EEPatterns** both use Java 21—see each module’s `pom.xml`).
- From the **repository root**, build all modules:

  ```bash
  mvn clean install -Drevision=0.0.1-SNAPSHOT
  ```

  Use any valid `revision` you publish with; the root and child POMs use the `revision` property for the version.

- Optional: [`buildMaven.sh`](buildMaven.sh) sets `revision` from [`version.sh`](version.sh) (Git commit count when available) and runs `mvn clean install` from the **directory where you execute the script** (typically the repo root for the aggregator).

For more detail, see [**CorePatterns/README.md**](CorePatterns/README.md) and [**J2EEPatterns/README.md**](J2EEPatterns/README.md).

## What are design patterns?

Before learning about design patterns, consider one more question.

- What do you mean by a pattern?

    - A pattern is a repeated design or recurring sequence.
    
    OR
    
    - An ordered set of numbers, shapes or other mathematical objects, arranged according to a rule.    

Now let's return to the main question.

As per wikipedia [System Design Patterns](https://en.wikipedia.org/wiki/Software_design_pattern)

```text
In software engineering, a software design pattern is a general, reusable solution to a commonly occurring problem 
within a given context in software design.
```

Design patterns are the solutions for recurring problems in some given context, which are tried and tested.

Design patterns gained popularity in computer science after the book ```Design Patterns: Elements of Reusable Object-Oriented Software``` was published in 1994 by the so-called ```Gang of Four```, which is frequently abbreviated as ```GoF```.

## Classification

Design patterns had originally been categorized into 3 sub-classifications based on the kind of problem they solve.

- Creational
    
    These patterns provide the capability to create objects based on a required criteria and in a controlled way.
    
- Structural

    These patterns are about organizing different classes and objects to form larger structures and provide new 
    functionality.
    
- Behavioral

    These patterns are about identifying common communication patterns between objects and realize these patterns.
    

## Authors

* Rohtash Lakra
