package com.Backend.Supply_Chain_Management.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Generator {
    public String generateID(int length)
    {
        String Id = "";
        log.info("ID generation started.");
        String generator = "0123456789";
        int Index = 0;
        for( int i = 0; i < length; i++)
        {
             Index = (int)(Math.random()*10);
             Id = Id + generator.charAt(Index);
        }
        log.info("generated ID : {}", Id);
        return Id;
    }

    public String generateEmail(String name)
    {
        return name+"@scm.com";
    }


}
