package fa.nfa;

import java.util.HashMap;
import java.util.HashSet;
import fa.State;

/**
 * This class represents a state in the NFA. It extends the abstract
 * State class and manages transitions specific to this specific instance of
 * state.
 * 
 * @author Ashley Day Brooke Matthews
 */
public class NFAState extends State {

    // Map for transitions: symbol -> set of states reachable by that symbol
    private HashMap<Character, HashSet<NFAState>> transitions;
    
    // Flags to mark final and start states
    private boolean isFinal;
    private boolean isStart;

    /**
     * Constructor for NFAState. Initializes the state with a name.
     * @param name The name of the state.
     */
    public NFAState(String name) {
        super(name);
        transitions = new HashMap<>();
        isFinal = false;
        isStart = false;
    }

    
    
}

