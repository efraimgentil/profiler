package me.efraimgentil.profiler.test;

import me.efraimgentil.profiler.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TestService {

    @Profile
    public void profileTestNoArgs(){

    }


    @Profile
    public void profileTest(String test, LocalDateTime ldt){

    }

}
