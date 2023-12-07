package sg.edu.nus.iss.d19lecture.model;

import org.springframework.stereotype.Component;

@Component("briefcase")
public class BriefCase implements Bag {

    @Override
    public void showBagType() {
        System.out.println("You are carring a briefcase");
    }
    
}
