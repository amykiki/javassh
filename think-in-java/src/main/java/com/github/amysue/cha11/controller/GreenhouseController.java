package com.github.amysue.cha11.controller;

/**
 * Created by Amysue on 2016/6/22.
 */
public class GreenhouseController {
    public static void main(String[] args) {
        GreenhouseControls gc = new GreenhouseControls();
        gc.addEvent(gc.new Bell(9000));
        Event[] eventList = {
            gc.new ThermostatNight(0),
            gc.new LightOn(2000),
            gc.new LightOff(4000),
            gc.new WaterOn(6000),
            gc.new WaterOff(8000),
            gc.new ThermostatDay(14000),
        };
        gc.addEvent(gc.new Restart(20000, eventList));
        if (args.length == 1) {
            gc.addEvent(new GreenhouseControls.Terminate(Integer.parseInt(args[0])));
        }
        gc.run();
    }
}
