package fa.nfa;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import fa.State;

/**
 * This class represents a general NFA. It implements the
 * NFAInterface class and handles all functions of an NFA and
 * its mapping.
 * 
 * @author Ashley Day & Brooke Matthews
 */
public class NFA implements NFAInterface{
    LinkedHashSet<Character> sigma;
    LinkedHashSet<NFAState> states;
    String startState;
    LinkedHashSet<NFAState> finalStates;

    /**
    *Constructor
    */
    public NFA() {
        sigma = new LinkedHashSet<>();
        states = new LinkedHashSet<>();
        startState = "";
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
                return finalStates.add(state);
            }
        }
        return false;
    }

    @Override
    public boolean setStart(String name) {
        for (NFAState state : states) {
            if (state.getName().equals(name)) {
                startState = name;
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
        sigma.add(symbol);
    }

    @Override
    public boolean accepts(String s) {
        if (startState == null) return false;
        NFAState currentState = getState(startState);

        for (int i = 0; i < s.length(); i++) {
            char symbol = s.charAt(i);
            if (!sigma.contains(symbol)) return false;
            NFAState nextState = currentState.getToState(symbol);
            if (nextState == null) return false;
            currentState = nextState;
        }
        return finalStates.contains(currentState);
    }

    @Override
    public Set<Character> getSigma() {
        return sigma;
    }

    @Override
    public State getState(String name) {
        for (NFAState state : states) {
            if (state.getName().equals(name)) {
                return state;
            }
        }
        for (NFAState state : finalStates) {
            if (state.getName().equals(name)) {
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
        if (name.equals(startState)) {
            return true;
        }
        return false;
    }

    @Override
    public Set<NFAState> getToState(NFAState from, char onSymb) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getToState'");
    }

    @Override
    public Set<NFAState> eClosure(NFAState s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eClosure'");
    }

    @Override
    public int maxCopies(String s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'maxCopies'");
    }

    @Override
    public boolean addTransition(String fromState, Set<String> toStates, char onSymb) {
        NFAState from = getState(fromState);
        NFAState to = getState(toState);

        if (from == null || to == null || !sigma.contains(onSymb)) {
            return false;
        }
        for (NFAState currentState : states) {
            if (currentState == from) {
                currentState.toState(onSymb, to);
            }
        }
        return true;
    }

    @Override
    public boolean isDFA() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isDFA'");
    }

    /*
    * ToString method that displays the results of the 6-tuple
    * in a format taht supports the test.
    */
    @Override
    public String toString(){
        /*NEEDS THE ADDITIONAL COMPONENT*/
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

