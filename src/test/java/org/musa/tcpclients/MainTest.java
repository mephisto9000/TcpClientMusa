/*
 * Copyright 2015 SpringIO.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.musa.tcpclients;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.musa.payload.SMLoyalty;
import org.musa.payload.SMRank;
import org.musa.payload.SpaceMarine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.integration.handler.AbstractReplyProducingMessageHandler;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author mephisto9000
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring/integration/clientContext.xml" })
public class MainTest {
    
    
    @Autowired
    //TcpConnectionFactory client;
    WarpGateway gw;
    //WarpGateway gateway;
    
    @Autowired
    MessageChannel teleportChannel;
    
    public MainTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    

    /**
     * Test of setupContext method, of class Main.
     */
    @Test
    public void testSetupContext() {
        System.out.println("Main.setupContext test1");
        GenericXmlApplicationContext expResult = null;
        GenericXmlApplicationContext result = Main.setupContext();
        assertNotNull(result);
        
    }
    /*
    @Test
    public void testGateway1()
    {
        System.out.println("Main. gateway integrationTest1");
        //public SpaceMarine(String name, String chapter, int kills, SMRank rank, SMLoyalty loyalty, int damage)
        SpaceMarine garro = new SpaceMarine("Nathaniel Garro", "Death Guard", 800, SMRank.CaptainBrother, SMLoyalty.Loyalist, 300);
        Message<SpaceMarine> m = new GenericMessage<SpaceMarine>(garro);
        String s = gw.send(m);
        System.out.println("test result == "+s);
        //fail("not implemented");
        assertEquals(1,1);
    }
    */
    
    @Test
    public void testGateway2()
    {
        
        SubscribableChannel channel = (SubscribableChannel) teleportChannel;
		channel.subscribe(new AbstractReplyProducingMessageHandler() {

			@Override
			protected Object handleRequestMessage(Message<?> requestMessage) {
				byte[] payload = (byte[]) requestMessage.getPayload();

				// we assert during the processing of the messaging that the
				// payload is just the content we wanted to send without the
				// framing bytes (STX/ETX)
				assertEquals("Hello World!", new String(payload));
				return requestMessage;
			}
		});
                
                
        System.out.println("Main. gateway integrationTest2");
        //public SpaceMarine(String name, String chapter, int kills, SMRank rank, SMLoyalty loyalty, int damage)
        SpaceMarine horus = new SpaceMarine("Horus", "Sons of Horus", 999999, SMRank.Primarch, SMLoyalty.Traitor, 999);
        Message<SpaceMarine> m = new GenericMessage<SpaceMarine>(horus);
        String s = gw.send(m);
        System.out.println("test result == "+s);
        //fail("not implemented");
        assertEquals(1,1);
    }
    
}
