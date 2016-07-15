package com.github.amysue.cha11.controller;

/**
 * Created by Amysue on 2016/6/22.
 */
public class GreenhouseControls extends Controller {
    private boolean light = false;
    private boolean water = false;
    private String thermostat = "Day";

    public class LightOn extends Event {
        public LightOn(long delayTime) {
            super(delayTime);
        }
        @Override
        public void action() {
            light = true;
        }

        @Override
        public String toString() {
            return super.toString() + "-Light is on";
        }
    }
    public class LightOff extends Event {
        public LightOff(long delayTime) {
            super(delayTime);
        }
        @Override
        public void action() {
            light = false;
        }

        @Override
        public String toString() {
            return super.toString() + "-Light is off";
        }
    }
    public class WaterOn extends Event {
        public WaterOn(long delayTime) {
            super(delayTime);
        }
        @Override
        public void action() {
            water = true;
        }

        @Override
        public String toString() {
            return super.toString() + "-Greenhouse water is on";
        }
    }
    public class WaterOff extends Event {
        public WaterOff(long delayTime) {
            super(delayTime);
        }
        @Override
        public void action() {
            water = false;
        }

        @Override
        public String toString() {
            return super.toString() + "-Greenhouse water is off";
        }
    }
    public class ThermostatNight extends Event {
        public ThermostatNight(long delayTime) {
            super(delayTime);
        }
        @Override
        public void action() {
            thermostat = "Night";
        }

        @Override
        public String toString() {
            return super.toString() + "-Thermostat on night setting";
        }
    }
    public class ThermostatDay extends Event {
        public ThermostatDay(long delayTime) {
            super(delayTime);
        }
        @Override
        public void action() {
            thermostat = "Day";
        }

        @Override
        public String toString() {
            return super.toString() + "-Thermostat on day setting";
        }
    }

    public class Bell extends Event {
        public Bell(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            addEvent(new Bell(delayTime));
        }

        @Override
        public String toString() {
            return super.toString() + "-Bing!";
        }
    }

    public class Restart extends Event {
        private Event[] eventList;
        public Restart(long delayTime, Event[] eventList) {
            super(delayTime);
            this.eventList = eventList;
            for (Event e : eventList) {
                addEvent(e);
            }
        }

        @Override
        public String toString() {
            return super.toString() + "-Restarting System";
        }

        @Override
        public void action() {
//            System.out.println(listContent());
            for (Event e : eventList) {
                e.start();
                addEvent(e);
            }
            start();;
            addEvent(this);
//            System.out.println(listContent());
        }
    }

    public static class Terminate extends Event {
        public Terminate(long delayTime) {
            super(delayTime);
        }

        @Override
        public String toString() {
            return super.toString() + "-Terminating";
        }

        @Override
        public void action() {
            System.exit(0);
        }
    }
}
