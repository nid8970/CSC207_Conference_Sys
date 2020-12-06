package views.account;

import controllers.account.AccountController;
import enums.SpeakerMenuEnum;
import gateways.DataManager;
import presenters.account.SpeakerPresenter;
import use_cases.account.AccountManager;
import use_cases.account.ContactManager;
import use_cases.ConversationManager;
import use_cases.event.EventManager;
import views.event.EventView;
import views.message.ConversationView;
import views.message.MessageView;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class SpeakerView {
    private final DataManager dm;
    private final String username;
    private final AccountManager am;
    private final ContactManager fm;
    private final ConversationManager cm;
    private final EventManager em;
    private final AccountController controller;
    private final SpeakerPresenter presenter = new SpeakerPresenter();
    private final Scanner userInput = new Scanner(System.in);

    public SpeakerView(DataManager dm) {
        this.dm = dm;
        this.am = dm.getAccountManager();
        this.fm = dm.getContactManager();
        this.cm = dm.getConversationManager();
        this.em = dm.getEventManager();
        this.username = dm.getUsername();
        this.controller = new AccountController(dm);
    }

    public void viewSpeakerMenu() {
        boolean loggedIn = true;

        presenter.startPrompt();
        presenter.displaySpeakerMenu();

        while (loggedIn) {
            SpeakerMenuEnum enumCommand = SpeakerMenuEnum.fromString(userInput.nextLine());
            switch (enumCommand) {
                // TODO: 12/04/20 Enable exit
//                case EXIT:
//                    loggedIn = false;
//                    break;
                case LOGOUT:
                    loggedIn = false;
                    break;
                case VIEW_ALL_ACCOUNTS:
                    presenter.accountList(controller.getAllAccounts());
                    break;
                case ADD_CONTACT:
                    ContactView contactView = new ContactView(dm);
                    contactView.viewAddFriendMenu();
                    break;
                case REMOVE_CONTACT:
                    contactView = new ContactView(dm);
                    contactView.viewRemoveFriendMenu();
                    break;
                case VIEW_CONTACTS:
                    contactView = new ContactView(dm);
                    contactView.viewFriendList();
                    break;
                case MESSAGE_ATTENDEE:
                case MESSAGE_ALL_AT_TALKS:
                    MessageView messageView = new MessageView(dm);
                    messageView.message(enumCommand);
                    break;
                case VIEW_CONVERSATION:
                    ConversationView conversationView = new ConversationView(dm);
                    conversationView.conversations();
                    break;
                case VIEW_SCHEDULE:
                    EventView eventView = new EventView(dm);
                    eventView.allTalksSchedule();
                    break;
                case VIEW_MENU:
                    presenter.displaySpeakerMenu();
                    break;
                case INVALID:
                    presenter.invalidInputPrompt();
            }
            if (loggedIn) {
                presenter.requestCommandPrompt();
            }
        }
    }
}
