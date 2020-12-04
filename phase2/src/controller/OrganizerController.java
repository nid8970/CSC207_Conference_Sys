package controller;

import exceptions.already_exists.AccountAlreadyExistsException;
import exceptions.not_found.FriendNotFoundException;
import exceptions.not_found.ObjectNotFoundException;
import exceptions.not_found.RecipientNotFoundException;
import exceptions.not_found.UserNotFoundException;
import use_cases.*;

import java.util.*;

import enums.*;
import views.FriendView;
import views.RegistrationView;

public class OrganizerController extends AccountController {
    /**
     * facilitates interaction with organizer upon login
     * @param username organizer username
     * @param am manages data of all accounts in program
     * @param fm manages friendList functionality
     * @param cm manages messaging functionality
     * @param em manages event data
     */
    public OrganizerController(String username, AccountManager am, FriendManager fm, ConversationManager cm, EventManager em) {
        super(username, am, fm, cm, em);
    }

    @Override
    public boolean runInteraction() {
        return false;
    }

//    /**
//     * interacts with the organizer via a menu of options
//     * @return True if organizer wants to terminate the program
//     */
//    public boolean runInteraction(OrganizerCommand enumCommand) {
//        boolean programEnd = false;
//        boolean loggedIn = true;
//        Scanner userInput = new Scanner(System.in);
//
//        while (loggedIn) {
//            switch (enumCommand) {
//                case EXIT:
//                    programEnd = true;
//                    loggedIn = false;
//                    break;
//                case LOGOUT:
//                    loggedIn = false;
//                    break;
//                case NEW_SPEAKER: {
//                    RegistrationView registrationView = new RegistrationView(am, fm, cm, em);
//                    registrationView.accountInfoMenu("2");
//                    break;
//                }
//                case VIEW_ALL_ACCOUNTS:
//                    Set<String> accounts = am.getAccountHashMap().keySet();
//                    presenter.displayAccountList(accounts);
//                    break;
//                case ADD_CONTACT:
//                    FriendView friendView = new FriendView(username, fm);
//                    try {
//                        friendView.viewAddFriendMenu();
//                    } catch (FriendNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                    break;
//                case REMOVE_CONTACT:
//                    friendView = new FriendView(username, fm);
//                    friendView.viewRemoveFriendMenu();
//                    break;
//                case VIEW_CONTACTS:
//                    friendView = new FriendView(username, fm);
//                    friendView.viewFriendList();
//                    break;
//                case MESSAGE_SPEAKER: {
//                    presenter.displayMessagingPrompt("aSpeaker");
//                    String username = userInput.nextLine();
//                    String message = userInput.nextLine();
//                    try {
//                        messageController.messageSpeaker(message, username);
//                    } catch (UserNotFoundException | RecipientNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                    break;
//                }
//                case MESSAGE_ATTENDEE: {
//                    presenter.displayMessagingPrompt("anAttendee");
//                    String username = userInput.nextLine();
//                    String message = userInput.nextLine();
//                    try {
//                        messageController.messageAttendee(message, username);
//                    } catch (UserNotFoundException | RecipientNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                    break;
//                }
//                case MESSAGE_ALL_SPEAKERS: {
//                    presenter.displayMessagingPrompt("allSpeakers");
//                    String message = userInput.nextLine();
//                    messageController.messageAllSpeakers(message);
//                    break;
//                }
//                case MESSAGE_ALL_ATTENDEES: {
//                    presenter.displayMessagingPrompt("allAttendees");
//                    String message = userInput.nextLine();
//                    messageController.messageAllAttendees(message);
//                    break;
//                }
//                case VIEW_CONVERSATION:
//                    try {
//                        Set<String> recipients = cm.getAllUserConversationRecipients(username);
//
//                        if (recipients.isEmpty()) {
//                            presenter.displayConversations("empty", recipients);
//                        } else {
//                            presenter.displayConversations("non_empty", recipients);
//                            String recipient = userInput.nextLine();
//                            int pastMessages = Integer.parseInt(userInput.nextLine());
//                            this.viewMessagesFrom(recipient, pastMessages);
//                        }
//                    } catch (InputMismatchException e) {
//                        this.presenter.displayConversationsErrors("mismatch");
//                    } catch (ObjectNotFoundException e) {
//                        this.presenter.displayConversationsErrors("no_user");
//                    } catch (NullPointerException e) {
//                        this.presenter.displayConversationsErrors("no_conversation");
//                    }
//                    break;
//                case ADD_ROOM:
//                    presenter.displayRoomRegistration();
//                    String location = userInput.nextLine();
//                    addNewLocation(location);
//                    break;
//                case VIEW_ROOMS:
//                    this.seeLocationList();
//                    break;
//                case ADD_EVENT:
//                    try {
//                        presenter.displayEventPrompt("register");
//                        String username = userInput.nextLine();
//                        location = userInput.nextLine();
//                        String topic = userInput.nextLine();
//                        Calendar time = this.collectTimeInfo();
//                        em.addNewEvent(EventType.TALK, topic, time, location, this.username, new ArrayList<>(Collections.singletonList(username)), 2, false);
//                    } catch (Exception e) {
//                        presenter.displayPrompt(e.toString());
//                    }
//                    break;
//                case CANCEL_EVENT:
//                    try {
//                        presenter.displayEventPrompt("cancel");
//                        int id = userInput.nextInt();
//                        userInput.nextLine();
//                        this.cancelTalk(id);
//                    }
//                    catch(Exception e) {
//                        presenter.displayPrompt(e.toString());
//                    }
//                    break;
//                case RESCHEDULE_EVENT:
//                    try {
//                        presenter.displayEventPrompt("reschedule");
//                        int id = userInput.nextInt();
//                        userInput.nextLine();
//                        Calendar newTime = this.collectTimeInfo();
//                        this.rescheduleTalk(id, newTime);
//                    } catch (Exception e) {
//                        presenter.displayPrompt(e.toString());
//                    }
//                    break;
//                case VIEW_SCHEDULE:
//                    this.SeeTalkSchedule();
//                    break;
//                case VIEW_MENU:
//                    presenter.displayOrganizerMenu();
//                    break;
//                default:
//                    presenter.displayPrompt("Invalid input, please try again:\n");
//            }
//            if (loggedIn) {
//                presenter.displayPrompt("Enter another command (1-16). Enter '*' to view the command menu again.");
////                enumCommand = userInput.nextLine();
////
////                validInput = false;
////                while (!validInput) {
////                    for(OrganizerCommand commandEnum: enumCommandList){
////                        if (commandEnum.command.equals(enumCommand)) {
////                            validInput = true;
////                            enumCommand = commandEnum;
////                            break;
////                        }
////                    }
////                    if(!validInput){
////                        presenter.displayPrompt("Invalid input, please try again:\n");
////                        presenter.displayPrompt("Enter another command (1-16). Enter '*' to view the command menu again.");
////                        enumCommand = userInput.nextLine();
////                    }
////                }
//            }
//        }
//        return programEnd;
//    }

//    /**
//     * Helper function that adds a user's username as keys for hashmaps stored in the use cases
//     * @param username specified username
//     */
//    private void addNewSpeakerKeys(String username) {
//        cm.addAccountKey(username);
//        fm.addAccountKey(username);
//        em.addSpeakerKey(username);
//    }

//    /**
//     * Adds a new allowed location where events can take place to the database
//     * @param location location to be added
//     */
//    public void addNewLocation(String location) {
//        try {
//            this.em.addNewLocation(location);
//        } catch (Exception e) {
//            presenter.displayPrompt(e.toString());
//        }
//    }

//    /**
//     * creates a new speaker account with the given information fields
//     * @param username given username
//     * @param password given password
//     * @param firstname given first name
//     * @param lastname given last name
//     */
//    public void createSpeakerAccount(String username, String password, String firstname, String lastname) {
//        try {
//            this.am.addNewSpeaker(username, password, firstname, lastname);
//        } catch (AccountAlreadyExistsException e) {
//            e.printStackTrace();
//        }
//        addNewSpeakerKeys(username);
//    }

//    /**
//     * Registers a new talk into the database with the given information fields
//     * @param time given time
//     * @param topic given topic
//     * @param location given location
//     * @param speaker given speaker username
//     */
//    public void registerNewTalk(Calendar time, String topic, String location, String speaker) {
//        try {
//            Integer newTalkID = eventManager.addNewTalk(topic, time, location, username, speaker);
//        } catch (Exception e) {
//            presenter.displayPrompt(e.toString());
//        }
//    }


//    /**
//     * cancels a talk with the given id
//     * @param id id of talk to cancel
//     */
//    public void cancelTalk(Integer id) {
//        try {
//            this.em.cancelEvent(id);
//        } catch (Exception e) {
//            presenter.displayPrompt(e.toString());
//        }
//    }
//
//    /**
//     * reschedules a talk with the given id to time newTime
//     * @param id talk id
//     * @param newTime time to reschedule talk to
//     */
//    public void rescheduleTalk(Integer id, Calendar newTime) {
//        try {
//            this.em.changeTime(id, newTime);
//        } catch (Exception e) {
//            presenter.displayPrompt(e.toString());
//        }
//    }
//
//    /**
//     * displays the list of all locations currently in the database
//     */
//    public void seeLocationList() {
//        ArrayList<String> locations = this.em.getLocations();
//        presenter.displayPrompt("Locations:\n");
//        for (String location : locations) {
//            presenter.displayPrompt(location);
//        }
//    }
}





