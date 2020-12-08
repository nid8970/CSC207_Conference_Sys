package views.account;

import controllers.account.AccountController;
import enums.SpeakerMenuEnum;
import gateways.*;
import presenters.account.SpeakerPresenter;
import views.event.EventView;
import views.message.ConversationView;
import views.message.MessageTalkAttendeesView;
import views.message.MessageView;

import java.util.Scanner;

public class SpeakerView {
    private final DataManager dm;
    private final AccountController controller;
    private final SpeakerPresenter presenter = new SpeakerPresenter();
    private final Scanner userInput = new Scanner(System.in);

    public SpeakerView(DataManager dm) {
        this.dm = dm;
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
                case MESSAGE:
                    MessageView messageView = new MessageView(dm);
                    messageView.runView();
                case MESSAGE_TALK_ATTENDEES:
                    MessageTalkAttendeesView messageTalkAttendeesView = new MessageTalkAttendeesView(dm);
                    messageTalkAttendeesView.runView();
                    break;
                case VIEW_CONVERSATION:
                    ConversationView conversationView = new ConversationView(dm);
                    conversationView.conversations();
                    break;
                case VIEW_EVENT_SCHEDULE:
                    EventView eventView = new EventView(dm);
                    eventView.allTalksSchedule();
                    break;
                case VIEW_MENU:
                    presenter.displaySpeakerMenu();
                    break;
                case INVALID:
                    presenter.invalidInputPrompt();
            }
            controller.saveData();

            if (loggedIn) {
                presenter.requestCommandPrompt();
            }
        }
    }
}
