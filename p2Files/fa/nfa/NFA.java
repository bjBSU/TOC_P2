package fa.nfa;

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
    LinkedHashSet<DFAState> states;
    String startState;
    LinkedHashSet<DFAState> finalStates;

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


        return states.add(new DFAState(name));
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSigma'");
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

    
}

