package fa.nfa;

import java.util.HashMap;

import fa.State;
/**
 * This class represents a state in the NFA. It extends the abstract
 * State class and manages transitions specific to this specific instance of
 * state.
 * 
 * @author Ashley Day Brooke Matthews
 */
public class NFAState extends State {

    /**
     * Constructor for DFAState. Initializes the state with a name.
     * 
     * @param name The name of the state.
     */
    public NFAState(String name) {
        super(name);
    }
    
}

