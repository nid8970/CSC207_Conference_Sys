package views.account;

import controllers.account.LoginController;
import enums.AccountTypeEnum;
import gateways.*;
import presenters.account.LoginPresenter;
import use_cases.ConversationManager;
import use_cases.account.AccountManager;
import use_cases.account.ContactManager;
import use_cases.event.EventManager;

import java.util.Scanner;

public class LoginView {
    private final DataManager dm;
    private final LoginController controller;
    private final LoginPresenter presenter = new LoginPresenter();
    private final Scanner userInput = new Scanner(System.in);

    public LoginView(DataManager dm) {
        this.dm = dm;
        this.controller = new LoginController(dm);
    }

    public void runView() {
        presenter.startPrompt();
        presenter.usernamePrompt();
        String username = userInput.nextLine();

        while (!controller.usernameExists(username)) {
            if (username.equals("*")) {
                return;
            }
            presenter.dneUsernamePrompt();
            username = userInput.nextLine();
        }

        presenter.passwordPrompt();
        String password = userInput.nextLine();

        while (!controller.isCorrectPassword(username, password)) {
            if (password.equals("*")) {
                return;
            }
            presenter.incorrectPasswordPrompt();
            password = userInput.nextLine();
        }

        presenter.exitPrompt();

        dm.setUsername(username); // TODO: 12/07/20 Find a more appropriate place for this method call? Would change if we refactor Views to no longer take DataManager argument

        AccountTypeEnum accountTypeEnum = controller.login(username);
        switch (accountTypeEnum) {
            case ORGANIZER:
                OrganizerView organizerView = new OrganizerView(dm);
                organizerView.viewOrganizerMenu();
                break;
            case SPEAKER:
                SpeakerView speakerView = new SpeakerView(dm);
                speakerView.viewSpeakerMenu();
                break;
            case ATTENDEE:
                AttendeeView attendeeView = new AttendeeView(dm);
                attendeeView.viewAttendeeMenu();
                break;
        }
    }
}