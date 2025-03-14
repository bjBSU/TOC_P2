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

    /**
     * Adds a transition on the given symbol to the target state
     * @param symbol The symbol triggering the transition
     * @param toState The target state
     * @return true if the transition was added, false if it already existed
     */
    public boolean addTransition(char symbol, NFAState toState) {
        HashSet<NFAState> set = transitions.get(symbol); //Get Transitions on symbol for this State
        if (set == null) {
            set = new HashSet<>();
            transitions.put(symbol, set); // If none make and add this set to symbol
        }
        return set.add(toState);
    }

    /**
     * Returns the set of transitions on the given symbol
     * @param symbol The symbol to retrieve transitions
     * @return A set of states (empty if none exist)
     */
    public HashSet<NFAState> getTransitions(char symbol) {
        HashSet<NFAState> temp = transitions.get(symbol);
        if (temp == null) {
            return new HashSet<>();
        }
        return temp;
    }

    /**
     * Returns all transitions from this state
     * @return A map of transitions
     */
    public HashMap<Character, HashSet<NFAState>> getAllTransitions() {
        return transitions;
    }

    /**
     * Sets if an accepting state
     * @param isFinal true if this is a final state
     */
    public void setFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }
    
    /**
     * Checks if this state is final
     * @return true if it is final
     */
    public boolean isFinal() {
        return isFinal;
    }
    
    /**
     * Sets if the start state
     * @param isStart true if this is the start state
     */
    public void setStart(boolean isStart) {
        this.isStart = isStart;
    }
    
    /**
     * Checks if this state is the start
     * @return true if it is the start state
     */
    public boolean isStart() {
        return isStart;
    }

    @Override
    public String getName(){
        return super.getName();
    }  
}

