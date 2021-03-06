// Copyright © 2008-2013 Esko Luontola <www.orfjackal.net>
// This software is released under the Apache License 2.0.
// The license text is at http://dimdwarf.sourceforge.net/LICENSE

package net.orfjackal.dimdwarf.util;

import org.hamcrest.*;

import java.util.*;

public class EventSink<T> extends AsynchronousSink<List<T>> implements SelfDescribing {

    private final List<T> events = new ArrayList<>();

    public EventSink(long timeout) {
        super(timeout);
    }

    public synchronized void append(List<T> event) {
        events.addAll(event);
        notifyAll();
    }

    public synchronized List<T> getContent() {
        return events;
    }

    public void describeTo(Description description) {
        description
                .appendText("events ")
                .appendValueList("[", ", ", "]", getContent());
    }
}
