package com.github.amysue.cha11.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amysue on 2016/6/22.
 */
public class Controller {
    private List<Event> eventList = new ArrayList<Event>();

    public void addEvent(Event event) {
        eventList.add(event);
    }

    public void run() {
        while (eventList.size() > 0) {
            for (Event e : new ArrayList<Event>(eventList)) {
                if (e.ready()) {
                    System.out.println(e);
                    e.action();;
                    eventList.remove(e);
                }
            }
        }
    }

    public String listContent() {
        StringBuilder sb = new StringBuilder();
        for (Event e : eventList) {
            sb.append(e.getClass().getSimpleName());
            sb.append("-");
        }
        return sb.toString();
    }
}
