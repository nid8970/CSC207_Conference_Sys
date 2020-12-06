package views.event;

import controllers.event.LocationController;
import exceptions.NonPositiveIntegerException;
import exceptions.already_exists.ObjectAlreadyExistsException;
import gateway.DataManager;
import presenters.event.LocationPresenter;
import use_cases.account.AccountManager;
import use_cases.ConversationManager;
import use_cases.event.EventManager;
import use_cases.account.ContactManager;

import java.util.Scanner;

public class LocationView {
    private final DataManager dm;
    private final String username;
    private final AccountManager am;
    private final ContactManager fm;
    private final ConversationManager cm;
    private final EventManager em;
    private final LocationPresenter presenter = new LocationPresenter();
    private final LocationController controller;
    private final Scanner userInput = new Scanner(System.in);

    public LocationView(DataManager dm) {
        this.dm = dm;
        this.am = dm.getAccountManager();
        this.fm = dm.getContactManager();
        this.cm = dm.getConversationManager();
        this.em = dm.getEventManager();
        this.username = dm.getUsername();
        this.controller = new LocationController(dm);
    }

    public void addRoom() {
        presenter.addRoomPrompt();
        String location = userInput.nextLine();
        try {
            controller.addNewLocation(location);
        } catch (NonPositiveIntegerException | ObjectAlreadyExistsException e) {
            e.printStackTrace();
        }
    }

    public void rooms() {
        presenter.displayRooms(controller.getLocations());
    }
}
