package views.event;

import controllers.event.EventController;
import enums.EventTypeEnum;
import exceptions.InvalidEventTypeException;
import exceptions.InvalidTimeException;
import exceptions.PastTimeException;
import exceptions.conflict.LocationInUseException;
import exceptions.not_found.LocationNotFoundException;
import presenters.event.EventCreationPresenter;
import presenters.event.TimePresenter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EventCreationView {

    private EventController controller;
    private EventCreationPresenter er = new EventCreationPresenter();
    private TimeGetterView tg = new TimeGetterView(new TimePresenter());
    private EventTypeEnum type;
    Scanner userInput = new Scanner(System.in);
    public EventCreationView(EventController ec){
        controller = ec;
    }

    public void runInteraction(){
        er.startPrompt();

        ArrayList<String> usernames = new ArrayList<>();
        er.speakerPrompt();
        boolean finished = false;
        while (!finished) {
            String username = userInput.nextLine();
            usernames.add(username);
            er.moreSpeakersPrompt();
            String input = userInput.nextLine();
            if (!input.equals("1")){finished = true;}
        }

        er.roomPrompt();
        String room = userInput.nextLine();

        er.topicPrompt();
        String topic = userInput.nextLine();

        er.timePrompt();
        boolean valid = false;
        Calendar time = Calendar.getInstance();
        while (!valid){
            try{
                time = tg.collectTimeInfo();
                valid = true;
            } catch (InstantiationException e) {
                er.invalidTimePrompt();
            } catch (InputMismatchException e) {
                er.invalidInputPrompt();
            }
        }

        er.capacityPrompt();
        valid = false;
        while (!valid){
            try{
                int capacity = userInput.nextInt();} catch (Exception e) {
                er.invalidInputPrompt();
            }
        }

        try{
            controller.createEvent(type, topic, time, room, usernames, 2, false);
        } catch (LocationNotFoundException e) {
            er.ErrorMessage("LocationNot");
        } catch (InvalidEventTypeException e) {
            er.ErrorMessage("InvalidEventType");
        } catch (LocationInUseException e) {
            er.ErrorMessage("LocationInUse");
        } catch (PastTimeException e) {
            er.ErrorMessage("PastTime");
        } catch (InvalidTimeException e) {
            er.ErrorMessage("InvalidTime");
        }
    }

}
