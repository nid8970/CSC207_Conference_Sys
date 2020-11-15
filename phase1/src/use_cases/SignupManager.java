package use_cases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import entities.Attendee;
import entities.EventTalk;

/**
 * SignupManager adds given Attendee to a given EventTalk.
 *
 * <pre>
 * Use Case SignupManager
 * Responsibilities:
 *      Can add given Attendee to given EventTalk
 *      Can check whether given EventTalk is full or not
 *      Can check whether given Attendee is already in given EventTalk or not
 *
 * Collaborators:
 *      Attendee, EventTalk
 * </pre>
 */
public class SignupManager implements Serializable {
    private HashMap<Integer, ArrayList<String>> signups;

    public SignupManager(HashMap<Integer, ArrayList<String>> signups) {
        this.signups = signups;
    }

    public SignupManager() { this(new HashMap<>()); }

    //------------------------------------------------------------
    // Methods
    //------------------------------------------------------------

    // (NEW!)
    public void addEventKey(Integer id) { signups.put(id, new ArrayList<>()); }

    public void removeEventKey(Integer id) { signups.remove(id); }

    /**
     * Adds given Attendee to Talk.
     * Does nothing if Talk is full or Attendee is already in Talk.
     * @param talk_id given EventTalk id
     * @param attendee given Attendee id
     */
    public void addAttendee(Integer talk_id, String attendee) {
        if (!isFull(talk_id) && !isSignedUp(talk_id, attendee)) { signups.get(talk_id).add(attendee); }
    }

    public void removeAttendee(Integer talk_id, String attendee) {
        if (isSignedUp(talk_id, attendee)) { signups.get(talk_id).remove(attendee); }
    }

    /**
     * Returns the given seat limit of an EventTalk.
     * @return seat limit
     */
    public int getSeatLimit() { return 2; }

    /**
     * Returns whether given EventTalk is full.
     * @param talk_id given EventTalk id
     * @return whether talk is full or not
     */
    public boolean isFull(Integer talk_id) {
        return signups.get(talk_id).size() == getSeatLimit();
    }

    /**
     * Returns whether given EventTalk contains a given Attendee.
     * @param talk_id given EventTalk id
     * @param attendee given Attendee id
     * @return whether talk contains Attendee or not
     */
    public boolean isSignedUp(Integer talk_id, String attendee) {
        return signups.get(talk_id).contains(attendee);
    }
}
