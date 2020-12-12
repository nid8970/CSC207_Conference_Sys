package views.message;

import controllers.message.ContactController;
import enums.ViewEnum;
import exceptions.not_found.AccountNotFoundException;
import exceptions.not_found.ContactNotFoundException;
import presenters.message.ContactPresenter;
import views.factory.View;

import java.util.Scanner;

/**
 * View responsible for contact removal functionality
 */

public class ContactRemoveView implements View {
    private final ContactController controller;
    private final ContactPresenter presenter;
    private final Scanner userInput = new Scanner(System.in);

    public ContactRemoveView(ContactController controller, ContactPresenter presenter) {
        this.presenter = presenter;
        this.controller = controller;
    }

    public ViewEnum runView() {
        presenter.removeContactHeader();
        presenter.removeContactPrompt();
        String contactToRemove = userInput.nextLine();
        try {
            controller.removeContact(contactToRemove);
            presenter.removeContactSuccessNotification(contactToRemove);
        } catch (ContactNotFoundException e) {
            presenter.contactNotFoundNotification();
            presenter.removeContactFailureNotification();
        } catch (AccountNotFoundException e) {
            presenter.accountNotFoundNotification();
            presenter.removeContactFailureNotification();
        }
        return ViewEnum.VOID;
    }
}
