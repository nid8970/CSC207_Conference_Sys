package views.event;

import controllers.event.EventController;
import enums.ViewEnum;
import presenters.event.EventPresenter;
import views.View;

import java.util.Scanner;

public class AllTalksScheduleView implements View {
    private final EventController controller;
    private final EventPresenter presenter;

    public AllTalksScheduleView(EventController controller, EventPresenter presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    public ViewEnum runView() {
        presenter.allEventsPrompt();
        presenter.displayEventSchedule(controller.getAllEvents());
        return ViewEnum.VOID;
    }
}
