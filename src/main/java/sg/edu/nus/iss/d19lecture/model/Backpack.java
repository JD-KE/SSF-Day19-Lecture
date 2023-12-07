package sg.edu.nus.iss.d19lecture.model;

import org.springframework.stereotype.Component;

@Component("backpack")
public class Backpack implements Bag {

    @Override
    public void showBagType() {
        System.out.println("You are carring a backpack");
    }
    
}
