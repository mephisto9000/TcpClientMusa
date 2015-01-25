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

import java.io.IOException;
import java.io.InputStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.musa.payload.SMLoyalty;
import org.musa.payload.SMRank;
import org.musa.payload.SMStatus;
import org.musa.payload.SpaceMarine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.serializer.Deserializer;
import org.springframework.integration.handler.AbstractReplyProducingMessageHandler;
import org.springframework.integration.ip.tcp.connection.AbstractServerConnectionFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author mephisto9000
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring/integration/clientContext_test.xml" })

@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class MainTest {
    
    
    @Autowired
    //TcpConnectionFactory client;
    WarpGateway gw;
    
    
    //@Autowired
    //MessageChannel serverBytes2StringChannel;
    
    @Autowired
    MessageChannel input;
    
    
    
    @Autowired
    AbstractServerConnectionFactory server;
    
    @Autowired
    MessageChannel loop;
    
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
    
    
    @Test
    public void testGateway2()
    {
        
       
                
            server.setDeserializer(new DeserializerImpl()
                    
        );
                
                                       
                
                
        System.out.println("Main. gateway integrationTest2");
                                        
        SpaceMarine horus = new SpaceMarine("Horus", "Sons of Horus", 999999, SMRank.Primarch, SMLoyalty.Traitor, 999);
        
        
        Message<SpaceMarine> m = MessageBuilder.withPayload(horus)        
        .build();
        
        
        String s = gw.send(m);
        System.out.println("test result == "+s);
        
    }

    private class DeserializerImpl implements Deserializer<SpaceMarine> {

        public DeserializerImpl() {
        }

        public SpaceMarine deserialize(InputStream in) throws IOException {
                                                
            System.out.println("*deserializing");
            
                        
            String name = readString(in);
            
            System.out.println("name == "+name);
            
            String chapter = readString(in);
            
            System.out.println("chapter == "+ chapter);
            
            int kills = Integer.parseInt(readString(in));
            
            System.out.println("kills == " + kills);
            
            SMRank rank = SMRank.valueOf(readString(in));
            
            System.out.println("rank == " + rank.name());
            
            SMLoyalty loyalty = SMLoyalty.valueOf(readString(in));
            System.out.println("loyalty == " + loyalty.name());
            
            SMStatus status = SMStatus.valueOf(readString(in));
            System.out.println("status == "+status.name());
            //public SpaceMarine(String name, String chapter, int kills, SMRank rank, SMLoyalty loyalty, int damage)
            int damage = Integer.parseInt(readString(in));
            
            
            System.out.println("damage == " + damage);
            
            SpaceMarine spacemarine = new SpaceMarine(name, chapter, kills, rank, loyalty, damage);
            
            int b = in.available();
            in.skip(b);
            
            
            assertEquals("Horus", name);
            return spacemarine;
        }

        private String readString(InputStream in) throws IOException
    {
        StringBuilder sb   = new StringBuilder();

        int val;
        while(in.available()>0)
        {
            
            
            val = in.read();
            
            if (((char)val)==';' || val == -1)
                break;
            
            sb.append((char)val);
        }
        
        
        return sb.toString();
    }
    }
    
}
