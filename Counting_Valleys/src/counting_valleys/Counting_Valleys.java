package counting_valleys;

import java.util.Scanner;
import java.util.Stack;

class Topography {

    int valley;
    int mountain;

    Topography() {
        valley = 0;
        mountain = 0;
    }

    void increaseValley() {
        valley++;
    }

    void increaseMountain() {
        mountain++;
    }

    int getValley() {
        return valley;
    }

    int getMountain() {
        return mountain;
    }
}

public class Counting_Valleys {

    //considers 3 cases
    //when on the sea level count every move as a valley or mountain path
    //when on valley, going downhill increases(push) depth, uphill decreases(pop) depth
    //when on mountain, going downhill decreases(pop) depth, uphill increases(push) depth
    static Topography mapTheHiking(Topography topography, String path) throws RuntimeException {
        Stack distanceToSea = new Stack();
        for (Character step : path.toCharArray()) {
            if (distanceToSea.isEmpty()) {
                switch (step) {
                    case 'D':
                        distanceToSea.push('D');
                        topography.increaseValley();
                        break;
                    case 'U':
                        distanceToSea.push('U');
                        topography.increaseMountain();
                        break;
                    default:
                        throw new RuntimeException("Entry is not within given range!");
                }
            } else {
                if (distanceToSea.peek().equals(step)) {
                    distanceToSea.push(step);
                } else {
                    distanceToSea.pop();
                }
            }
        }
        return topography;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Topography topography = new Topography();
        try {
            mapTheHiking(topography, input.next());
        } catch (RuntimeException e) {
            System.out.println("Something went wrong!");
        }
        System.out.println("Gary hiked through "
                + topography.getValley() + " valley/s and "
                + topography.getMountain() + " mountain/s.");
        input.close();
    }

}
