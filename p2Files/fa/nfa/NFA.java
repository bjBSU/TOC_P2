package fa.nfa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import fa.State;

/**
 * This class represents a general NFA. It implements the
 * NFAInterface class and handles all functions of an NFA and
 * its mapping.
 * 
 * @author Ashley Day & Brooke Matthews
 */
public class NFA implements NFAInterface {
    LinkedHashSet<Character> sigma;
    LinkedHashSet<NFAState> states;
    NFAState startState;
    LinkedHashSet<NFAState> finalStates;

    /**
     * Constructor
     */
    public NFA() {
        sigma = new LinkedHashSet<>();
        states = new LinkedHashSet<>();
        startState = null;
        finalStates = new LinkedHashSet<>();
    }

    @Override
    public boolean addState(String name) {
        for (NFAState state : states) {
            if (state.getName().equals(name)) {
                return false;
            }
        }

        return states.add(new NFAState(name));
    }

    @Override
    public boolean setFinal(String name) {
        for (NFAState state : states) {
            if (state.getName().equals(name)) {
                state.setFinal(true);
                return finalStates.add(state);
            }
        }
        return false;
    }

    @Override
    public boolean setStart(String name) {
        if (startState != null) { // Change to use NFAState class to handle
            startState.setStart(false);
        }
        for (NFAState state : states) {
            if (state.getName().equals(name)) {
                startState = state;
                state.setStart(true);
                return true;
            }
        }
        return false;
    }

    @Override
    public void addSigma(char symbol) {
        for (Character alnum : sigma) {
            if (alnum.equals(symbol)) {
                return;
            }
        }

        if (symbol != 'e')
            sigma.add(symbol); // Reserve e for epsilon
    }

    // @Override
    // public boolean accepts(String s) {
    // if (startState == null) return false;
    // NFAState currentState = getState(startState);

    // for (int i = 0; i < s.length(); i++) {
    // char symbol = s.charAt(i);
    // if (!sigma.contains(symbol)) return false;
    // NFAState nextState = currentState.getToState(symbol);
    // if (nextState == null) return false;
    // currentState = nextState;
    // }
    // return finalStates.contains(currentState);
    // } Im pretty sure this implementation is closer to how a DFA should be (single
    // state at a time) not an NFA unless I am reading wrong

    @Override
    public boolean accepts(String s) {
        if (startState == null)
            return false;

        Set<NFAState> currentStates = eClosure(startState);

        for (char symbol : s.toCharArray()) {
            if (!sigma.contains(symbol))
                return false; // String check
            Set<NFAState> nextStates = new HashSet<>();

            for (NFAState state : currentStates) { // Get all transitions
                nextStates.addAll(state.getTransitions(symbol));
            }

            currentStates.clear();
            for (NFAState state : nextStates) { // Refill for next character check
                currentStates.addAll(eClosure(state));
            }
        }

        for (NFAState state : currentStates) {
            if (finalStates.contains(state))
                return true; // Check if ended in accept
        }
        return false;
    }

    @Override
    public Set<Character> getSigma() {
        return sigma;
    }

    @Override
    public NFAState getState(String toStates) {
        for (NFAState state : states) {
            if (state.getName().equals(toStates)) {
                return state;
            }
        }
        for (NFAState state : finalStates) {
            if (state.getName().equals(toStates)) {
                return state;
            }
        }
        return null;
    }

    @Override
    public boolean isFinal(String name) {
        for (NFAState state : finalStates) {
            if (state.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isStart(String name) {
        if (name.equals(startState.getName())) {
            return true;
        }
        return false;
    }

    @Override
    // come back to this
    public Set<NFAState> getToState(NFAState from, char onSymb) {
        return from.getTransitions(onSymb);
    }

    @Override
    public Set<NFAState> eClosure(NFAState s) {
        Set<NFAState> closure = new HashSet<>();
        Stack<NFAState> stack = new Stack<>();

        stack.push(s);
        closure.add(s);

        while (!stack.isEmpty()) {
            NFAState state = stack.pop();

            for (NFAState nextState : state.getTransitions('e')) { // 'e' represents epsilon, for all transitions on e
                                                                   // add to stack and hashset and continue path
                if (!closure.contains(nextState)) {
                    closure.add(nextState);
                    stack.push(nextState);
                }
            }
        }

        return closure;
    }

    @Override
    public int maxCopies(String s) {
        if (startState == null)
            return 0;

        Set<NFAState> currentStates = eClosure(startState); // Set up like accept but check if e closure of start or any
                                                            // version of current states is bigger
        int maxCount = currentStates.size();

        for (char symbol : s.toCharArray()) {
            if (!sigma.contains(symbol))
                return 0;
            Set<NFAState> nextStates = new HashSet<>();

            for (NFAState state : currentStates) {
                nextStates.addAll(state.getTransitions(symbol));
            }

            currentStates.clear();
            for (NFAState state : nextStates) {
                currentStates.addAll(eClosure(state));
            }

            maxCount = Math.max(maxCount, currentStates.size());
        }
        return maxCount;
    }

    // @Override
    // public boolean addTransition(String fromState, Set<String> toStates, char
    // onSymb) {
    // NFAState from = getState(fromState);

    // List<NFAState> toStateList = new ArrayList<>();
    // for (String state : toStates) {
    // NFAState to = getState(state);
    // toStateList.add(to);
    // }

    // if (from == null || toStateList == null || !sigma.contains(onSymb)) {
    // return false;
    // }
    // for (NFAState currentState : states) {
    // if (currentState == from) {
    // currentState.toState(onSymb, to);
    // }
    // }
    // return true;
    // }

    @Override
    public boolean addTransition(String fromState, Set<String> toStates, char onSymb) {
        NFAState from = getState(fromState);
        if (from == null || !sigma.contains(onSymb)) {
            return false; // Check from state exists and the symbol is valid
        }

        for (String stateName : toStates) {
            NFAState to = getState(stateName);
            if (to != null) { // Don't add null states
                from.addTransition(onSymb, to);
            }
        }
        return true;
    }

    @Override
    public boolean isDFA() {
        for (NFAState state : states) {
            for (char symbol : sigma) {
                if (state.getTransitions(symbol).size() > 1) { // Check if transitions on symbol ever more than 1
                    return false;
                }
            }
        }
        return true;
    }

    /*
     * ToString method that displays the results of the 6-tuple
     * in a format that supports the test.
     * THIS NEEDS TO BE CHANGED TO WORK FOR A NFA AS OF NOW IS NOT COMPLETE
     */
    @Override
    public String toString() {
        /* NEEDS THE ADDITIONAL COMPONENT */
        StringBuilder sigma_vals = new StringBuilder();
        StringBuilder state_vals = new StringBuilder();
        StringBuilder final_state_vals = new StringBuilder();
        StringBuilder delta_vals = new StringBuilder();

        for (char value : sigma) {
            sigma_vals.append(value).append(" ");
        }
        for (NFAState value : states) {
            state_vals.append(value.getName());
        }
        for (NFAState value : finalStates) {
            final_state_vals.append(value.getName()).append(" ");
        }

        List<Character> inputSymbols = new ArrayList<>(sigma);

        delta_vals.append("\t");
        for (char symbol : inputSymbols) {
            delta_vals.append(symbol).append("\t");
        }
        delta_vals.append("\n");

        for (NFAState state : states) {
            delta_vals.append(state.getName()).append("\t");
            for (char symbol : inputSymbols) {
                NFAState nextState = state.getToState(symbol);
                delta_vals.append((nextState != null ? nextState.getName() : "-")).append("\t");
            }
            delta_vals.append("\n");
        }

        return "Q={" + state_vals + "}\n" +
                "Sigma = {" + sigma_vals.toString().trim() + "}\n" +
                "delta =\n" + delta_vals.toString() +
                "q0 = " + startState + "\n" +
                "F = {" + final_state_vals.toString().trim() + "}\n";
    }

}
