package tower.defense.model;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.LinkedList;

/**
 * Created by tomifor on 18/11/15.
 */
public class SaveScore {
    private LinkedList<Score> scoreList = new LinkedList<Score>();


    public SaveScore(Score s) {
        scoreList.add(s);

    }

    public SaveScore(Score s, File f) {
        scoreList.add(s);

    }


    private int bubble() {
        int count = 0;
        boolean changed = true;
        int i = scoreList.size();
        while (changed && i > 0) {
            changed = false;
            for (int j = 1; j < scoreList.size(); j++) {
                if (scoreList.get(j).getScore() > scoreList.get(j - 1).getScore()) {
                    Score s = scoreList.get(j);
                    scoreList.set(j, scoreList.get(j - 1));
                    scoreList.set(j - 1, s);
                    changed = true;
                }
            }
            count++;
            i--;
        }
        return count;
    }

    //crea lista de strings de cada linea de la tabla
    private LinkedList<String> readFile() {
        LinkedList<String> stringFile = new LinkedList<String>();
        BufferedReader br = null;
        String fileName = "score.txt";
        File file = new File(fileName);

        try {
            if(!file.exists()){
                PrintWriter writer = new PrintWriter(fileName);
            }

            String sCurrentLine;

            br = new BufferedReader(new FileReader("score.txt"));

            while ((sCurrentLine = br.readLine()) != null) {
                stringFile.add(sCurrentLine);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return stringFile;
    }


    public void saveScoreList() throws IOException {
        readFile();
        makeList();
        bubble();

        String fileName = ("score.txt"); //nombre del archivo
        File file = new File(fileName);

        BufferedWriter output = null;
        try {
            if (!file.exists()) {
                PrintWriter writer = new PrintWriter(fileName);
            }

            output = new BufferedWriter(new FileWriter(file));

            for (int i = 0; i < scoreList.size() && i < 10; i++) {
                output.write(scoreList.get(i).toString() + "\n");
            }
        } catch (IOException e){
            e.printStackTrace();
        }finally{
            if(output != null) output.close();
        }
    }

    //hago la lista y
    private void makeList() {
        LinkedList<String> mList = readFile();
        int i = 0;
        for (String s : mList) {
            int score = Integer.valueOf(mList.get(i).substring(0, mList.get(i).indexOf(" ")));
            String name = mList.get(i).substring(mList.get(i).indexOf(" ") + 1);
            scoreList.add(new Score(score, name, "sin fecha"));
            i++;
        }

    }

    private LinkedList<Score> topTenList(){

        LinkedList<Score> topTen = new LinkedList<Score>();
        for (int i=0; i< scoreList.size() && i<10 ; i++){
            topTen.add(scoreList.get(i));
        }
        return topTen;
    }


}