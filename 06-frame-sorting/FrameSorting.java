
import java.util.*;

class Frame {
    int seqNo;
    String data;

    Frame(int seqNo, String data) {
        this.seqNo = seqNo;
        this.data = data;
    }

    public String toString() {
        return "[" + seqNo + ", '" + data + "']";
    }
}

public class FrameSorting {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the message: ");
        String msg = sc.nextLine();

        List<Frame> frames = new ArrayList<>();

        // Divide message into 3-character frames
        for (int i = 0; i < msg.length(); i += 3) {
            frames.add(new Frame(i / 3,
                msg.substring(i, Math.min(i + 3, msg.length()))));
        }

        System.out.println("Frames: " + frames);

        // Shuffle and sort frames
        Collections.shuffle(frames);
        System.out.println("Shuffled frames: " + frames);

        frames.sort((a, b) -> a.seqNo - b.seqNo);
        System.out.println("Sorted frames: " + frames);

        // Reconstruct original message
        StringBuilder result = new StringBuilder();
        for (Frame f : frames)
            result.append(f.data);

        System.out.println("Final message: " + result);

        sc.close();
    }
}
