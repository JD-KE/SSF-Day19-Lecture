package sg.edu.nus.iss.d19lecture.model;

import org.springframework.stereotype.Component;

@Component("totebag")
public class ToteBag implements Bag {

    @Override
    public void showBagType() {
        System.out.println("You are carring a tote bag");
    }
    
}
