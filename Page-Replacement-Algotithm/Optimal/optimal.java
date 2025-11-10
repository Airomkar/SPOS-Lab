import java.io.*;

public class optimal {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter the Number of Frames: ");
        int frames = Integer.parseInt(br.readLine());

        System.out.print("Enter the Length of Reference String: ");
        int ref_len = Integer.parseInt(br.readLine());

        int[] reference = new int[ref_len];
        int[] buffer = new int[frames];        
        int[][] mem_layout = new int[frames][ref_len];

        for (int i = 0; i < frames; i++) buffer[i] = -1;

        System.out.println("Enter the reference string:");
        for (int i = 0; i < ref_len; i++)
            reference[i] = Integer.parseInt(br.readLine());

        int hits = 0, faults = 0;

        for (int i = 0; i < ref_len; i++) {
            int page = reference[i];
            boolean pageHit = false;

            for (int j = 0; j < frames; j++) {
                if (buffer[j] == page) {
                    hits++;
                    pageHit = true;
                    break;
                }
            }

            if (!pageHit) {
                int pos = -1;
                int farthest = -1;

                for (int j = 0; j < frames; j++) {
                    if (buffer[j] == -1) {
                        pos = j;
                        break;
                    }

                    int nextUse = -1;
                    for (int k = i + 1; k < ref_len; k++) {
                        if (buffer[j] == reference[k]) {
                            nextUse = k;
                            break;
                        }
                    }

                    if (nextUse == -1) {
                        pos = j;
                        break;
                    }

                    if (nextUse > farthest) {
                        farthest = nextUse;
                        pos = j;
                    }
                }

                buffer[pos] = page;
                faults++;
            }

            for (int j = 0; j < frames; j++)
                mem_layout[j][i] = buffer[j];
        }

        System.out.println("\nMemory Layout Table:");
        System.out.print("Frame\\Step ");
        for (int i = 0; i < ref_len; i++) System.out.printf("%3d ", reference[i]);
        System.out.println();

        for (int i = 0; i < frames; i++) {
            System.out.print("Frame " + (i + 1) + "   ");
            for (int j = 0; j < ref_len; j++) {
                if (mem_layout[i][j] != -1)
                    System.out.printf("%3d ", mem_layout[i][j]);
                else
                    System.out.print(" -  ");
            }
            System.out.println();
        }

        System.out.println("\nTotal Hits: " + hits);
        System.out.println("Hit Ratio: " + (float) hits / ref_len);
        System.out.println("Total Faults: " + faults);
    }
}
