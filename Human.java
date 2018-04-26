import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Human extends Player {
    private String name;

    Human(String name) {
        this.name = name;
    }


    @Override
    public String getName() {
        return name;
    }
}

