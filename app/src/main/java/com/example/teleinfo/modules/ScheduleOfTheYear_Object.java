package com.example.teleinfo.modules;

public class ScheduleOfTheYear_Object {

    public String Event, State, Key;
    public long From, To;

    public ScheduleOfTheYear_Object() {
    }

    public ScheduleOfTheYear_Object(long From, long To, String Event, String State, String Key) {
        this.From = From;
        this.To = To;
        this.Event = Event;
        this.State = State;
        this.Key = Key;
    }
}