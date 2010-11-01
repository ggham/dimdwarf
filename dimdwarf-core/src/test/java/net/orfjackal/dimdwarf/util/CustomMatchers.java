// Copyright © 2008-2010 Esko Luontola <www.orfjackal.net>
// This software is released under the Apache License 2.0.
// The license text is at http://dimdwarf.sourceforge.net/LICENSE

package net.orfjackal.dimdwarf.util;

import net.orfjackal.dimdwarf.util.matchers.*;
import org.apache.mina.core.buffer.IoBuffer;
import org.hamcrest.Matcher;

import static org.hamcrest.MatcherAssert.assertThat;

public class CustomMatchers {

    public static <T> void assertEventually(EventSink<T> sink, Matcher<? super EventSink<T>> matcher) {
        assertThat(sink, matcher);
    }

    public static void assertEventually(ByteSink sink, Matcher<ByteSink> matcher) {
        assertThat(sink, matcher);
    }

    public static <T> Matcher<EventSink<? super T>> firstEvent(final Matcher<? super T> matcher) {
        return new EventSinkContentMatcher<T>(new ListStartsWithElementMatcher(matcher));
    }

    public static Matcher<ByteSink> startsWithBytes(final IoBuffer expected) {
        return new ByteSinkContentMatcher(new IoBufferStartsWithBytesMatcher(expected));
    }
}