package workshop


import client.TestClient
import spock.lang.Specification

/**
 * Created by mtumilowicz on 2019-07-23.
 */
class Step4_ServerWorkshopTest extends Specification {

    def expectedClientOutput = ["send: xxx", "received: xxx"]

    def "ServerWorkshop"() {
        given:
        def port = 1

        expect:
        expectedClientOutput == extractClientOutputFor(port, new Step4_ServerWorkshop(port))
    }
    
    def extractClientOutputFor(port, server) {
        new Thread({ server.start() }).start()
        Thread.sleep(10)
        new TestClient(port).run()
    }
}
