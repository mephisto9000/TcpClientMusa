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
package org.musa.payload;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.lang.enums.EnumUtils;
import org.springframework.core.serializer.Serializer;

/**
 *
 * @author mephisto9000
 */
public class MechanicusTeleportSender implements Serializer<SpaceMarine>{

    public void serialize(SpaceMarine spaceMarine, OutputStream out) throws IOException {
                
                       
                /*
                disassembling the space marine...
                */
                
                byte[] nameParticles = spaceMarine.getName().getBytes();
		out.write(nameParticles);

		byte[] chapterParticles = spaceMarine.getChapter().getBytes();
		out.write(chapterParticles);

		byte[] killsParticles = Integer.toString(spaceMarine.getKills()).getBytes();                
		out.write(killsParticles);
                
                byte[] rankParticles = spaceMarine.getRank().name().getBytes();
                out.write(rankParticles);
                
                byte[] loyaltyParticles = spaceMarine.getLoyalty().name().getBytes();
                out.write(loyaltyParticles);
                
                byte[] statusParticles = spaceMarine.getStatus().name().getBytes();
                out.write(statusParticles);
                
                byte[] damageParticles = Integer.toString(spaceMarine.getDamage()).getBytes();
                out.write(damageParticles);
                               		
		out.flush();
        
     
    }
    
    
    
}
