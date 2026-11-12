import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Frame {
    int seqNo;
    String data;

    Frame(int seqNo, String data) {
        this.seqNo = seqNo;
        this.data = data;
    }

    @Override
    public String toString() {
        return "[" + seqNo + ", '" + data + "']";
    }
}

public class FrameSorting {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the message : ");
        String msg = sc.nextLine();

        List<Frame> frames = new ArrayList<>();
        for (int i = 0; i < msg.length(); i += 3) {
            String chunk = msg.substring(i, Math.min(i + 3, msg.length()));
            frames.add(new Frame(i / 3, chunk));
        }
        System.out.println("Frames : " + frames);

        Collections.shuffle(frames);
        System.out.println("Shuffled frames : " + frames);

        frames.sort(Comparator.comparingInt(f -> f.seqNo));
        System.out.println("Sorted frames : " + frames);

        StringBuilder message = new StringBuilder();
        for (Frame frame : frames) {
            message.append(frame.data);
        }
        System.out.println("Final message : " + message.toString());

        sc.close();
    }
}

/*
OUTPUT:
Enter the message : HelloWorld
Frames : [[0, 'Hel'], [1, 'loW'], [2, 'orl'], [3, 'd']]
Shuffled frames : [[2, 'orl'], [0, 'Hel'], [3, 'd'], [1, 'loW']]
Sorted frames : [[0, 'Hel'], [1, 'loW'], [2, 'orl'], [3, 'd']]
Final message : HelloWorld
*/
