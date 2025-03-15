# Project 2: Nondeterministic Finite Automaton (NFA)

* **Author:** Ashley Day and Brooke Matthews  
* **Class:** CS361 Section #002  
* **Semester:** Spring 2025  

## Overview

This project implements a **Nondeterministic Finite Automaton (NFA)** following the formal five-tuple definition:  
(Q, Σ, δ, q0, F), where:  
- **Q** is the set of states.  
- **Σ** is the alphabet (set of input symbols).  
- **δ** is the transition function, allowing transitions to multiple states.  
- **q0** is the start state.  
- **F** is the set of accepting or final states.  

The NFA class (`fa.nfa.NFA`) implements the `NFAInterface`, while the `NFAState` class extends `State`. The NFA supports adding states, defining transitions (including epsilon transitions), setting start and final states, and checking whether a given string is accepted.

---

## Reflection

This project helped reinforce concepts related to finite state machines, epsilon transitions, and nondeterminism in automata theory.

### **What Worked Well?**
- Implementing `eClosure` helped in understanding the reachability of states.
- **JUnit testing** was extremely useful in validating the correctness of the automaton and testing as we went. We added additional testing that helped a lot in catching smaller bugs.


### **Challenges & Struggles**
- **Epsilon transitions handling:** Making sure epsilon (`e`) transitions worked as expected, especially when determining `eClosure()`. We ran into some issue with some of our checks in that method in regards to properally handling epsilon and making sure that character was only used for that.
- **Correctly implementing `maxCopies()`:** Ensuring the method tracked how many active copies of the NFA exist at any given time. We had to work through how we were thinking about it versus just mimicking a halted machine.
- **Handling invalid transitions:** Making sure invalid states or symbols were properly rejected by the `addTransition()` function.

### **Design Process & Improvements**
- Writing additional **edge-case tests** for `accepts()`, `eClosure()`, and `maxCopies()` helped uncover issues as we were finishing up this project that were causing less obvious issues when we were writing and trying to work out smaller bugs.

---

## Compiling and Using

### **Compiling the Code (Onyx)**
From the top directory of the project files, compile using:

```
javac -cp .:/usr/share/java/junit.jar ./test/nfa/NFATest.java
```

### **Running the JUnit Tests (Onyx)**
To run the tests, use:

```
java -cp .:/usr/share/java/junit.jar:/usr/share/java/hamcrest/core.jar org.junit.runner.JUnitCore test.nfa.NFATest
```


### **Using the NFA Class**

An NFA is instantiated and configured using the following method calls to define its states, transitions, and acceptance conditions:

```
NFA nfa = new NFA();
nfa.addSigma('0');
nfa.addSigma('1');

nfa.addState("a");
nfa.addState("b");

nfa.setStart("a");
nfa.setFinal("b");

nfa.addTransition("a", Set.of("a"), '0');
nfa.addTransition("a", Set.of("b"), '1');
nfa.addTransition("b", Set.of("a"), 'e');

// Check NFA acceptance
System.out.println(nfa.accepts("101")); // true
System.out.println(nfa.accepts("000")); // false

// Print NFA
System.out.println(nfa);
```
This ensures that Q, Σ, δ, q0, and F are all correctly defined and functional.