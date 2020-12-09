package views.message;

import controllers.message.ConversationController;
import enums.ViewEnum;
import exceptions.NoMessagesException;
import exceptions.NonPositiveIntegerException;
import exceptions.not_found.MessageNotFoundException;
import exceptions.not_found.ObjectNotFoundException;
import exceptions.not_found.RecipientNotFoundException;
import exceptions.not_found.UserNotFoundException;
import gateways.DataManager;
import presenters.message.ConversationPresenter;
import use_cases.account.AccountManager;
import use_cases.ConversationManager;
import use_cases.event.EventManager;
import use_cases.account.ContactManager;
import views.View;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class ConversationView implements View {
    private final ConversationPresenter presenter;
    private final ConversationController controller;
    private final Scanner userInput = new Scanner(System.in);

    public ConversationView(ConversationController controller, ConversationPresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    @Override
    public ViewEnum runView() {
        try {
            Set<String> recipients = controller.getAllUserConversationRecipients();
            presenter.conversationsPrompt(recipients);

            presenter.usernamePrompt();
            String recipient = userInput.nextLine();

            presenter.numMessagesPrompt();
            int numMessages = Integer.parseInt(userInput.nextLine());

            presenter.conversationMessages(controller.viewMessagesFrom(recipient, numMessages));
        }
        catch (InputMismatchException e){
            presenter.InputMismatchPrompt();
        }
        catch (NullPointerException e){
            presenter.NullPointerExceptionPrompt();
        }
        catch (NonPositiveIntegerException e){
            presenter.NonPositiveIntegerPrompt();
        } catch (MessageNotFoundException e) {
            presenter.MessageNotFoundPrompt();
        } catch (UserNotFoundException e) {
            presenter.UserNotFoundPrompt();
        } catch (RecipientNotFoundException e) {
            presenter.RecipientNotFoundPrompt();
        } catch (NoMessagesException e) {
            presenter.NoMessagesPrompt();
        }
        return ViewEnum.VOID;
    }
}
