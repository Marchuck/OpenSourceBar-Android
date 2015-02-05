package com.polidea.opensourcebar.rxsample.ui

import org.robolectric.Robolectric
import pl.polidea.robospock.RoboSpecification

class MessangerActivitySpec extends RoboSpecification {

    def "activity can be easily built"() {
        when:
        def act = Robolectric.buildActivity(MessengerActivity).create().get()

        then:
        act instanceof MessengerActivity
    }
}
