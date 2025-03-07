package fa.nfa;

import java.util.Set;

import fa.State;

/**
 * This class represents a general NFA. It implements the
 * NFAInterface class and handles all functions of an NFA and
 * its mapping.
 * 
 * @author Ashley Day Brooke Matthews
 */
public class NFA implements NFAInterface{

    @Override
    public boolean addState(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addState'");
    }

    @Override
    public boolean setFinal(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setFinal'");
    }

    @Override
    public boolean setStart(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setStart'");
    }

    @Override
    public void addSigma(char symbol) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addSigma'");
    }

    @Override
    public boolean accepts(String s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'accepts'");
    }

    @Override
    public Set<Character> getSigma() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSigma'");
    }

    @Override
    public State getState(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getState'");
    }

    @Override
    public boolean isFinal(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isFinal'");
    }

    @Override
    public boolean isStart(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isStart'");
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addTransition'");
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
    public toString(){
        /*NEEDS THE ADDITIONAL COMPONENT*/
        StringBuilder sigma_vals = new StringBuilder();
        StringBuilder state_vals = new StringBuilder();
        StringBuilder final_state_vals = new StringBuilder();
        StringBuilder delta_vals = new StringBuilder();

        for (char value : sigma) {
            sigma_vals.append(value).append(" ");
        }
        for (DFAState value : states) {
            state_vals.append(value.getName());
        }
        for (DFAState value : finalStates) {
            final_state_vals.append(value.getName()).append(" ");
        }

        List<Character> inputSymbols = new ArrayList<>(sigma);

        delta_vals.append("\t");
        for (char symbol : inputSymbols) {
            delta_vals.append(symbol).append("\t");
        }
        delta_vals.append("\n");

        for (DFAState state : states) {
            delta_vals.append(state.getName()).append("\t");
            for (char symbol : inputSymbols) {
                DFAState nextState = state.getToState(symbol);
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

