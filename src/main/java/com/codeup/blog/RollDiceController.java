package com.codeup.blog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RollDiceController {
    @GetMapping("/roll-dice")
    public String showDiceForm() {
        return "rollDice";
    }

//    @GetMapping("/roll-dice/{guess}")
//    public String showGuessForm(@PathVariable Integer guess, Model model) {
//        int dieValue = (int) (Math.ceil(Math.random()*6));
//        String msg = "";
//        model.addAttribute("yourGuess", "");
//        model.addAttribute("dieRoll", "");
//        if (guess != null){
//            msg = guess == dieValue ? "You guessed the correct value" : "You did not guess the right value, sorry!";
//            model.addAttribute("yourGuess", "You guessed: " + guess);
//            model.addAttribute("dieRoll", "The die rolled: " + dieValue);
//        }
//        model.addAttribute("msg",msg);
//        return "rollDice";
//    }


    //Trant's walkthrough example, this is easier to understand.

    @PostMapping("/roll-dice")
    public String showDiceResult(@RequestParam(name = "number") Integer number, Model model) {
        int numberRolled = (int) ((Math.random() * 6) + 1);

        String message = "You selected " + number + " and the number rolled was " + numberRolled + ".";
        if (number.equals(numberRolled)) {
            message += " You Won!";
        } else {
            message += " Oh well... try again!!";
        }
        model.addAttribute("message", message);

        return "rollDice";
    }
}

