package paczka;
import java.io.*;
import java.util.Scanner;

class Generuj_punkty {
    File plik = new File("filizanka.txt");
    File plikv2 = new File("filazankav2.txt");
    Scanner scanner = new Scanner(plik);
    BufferedWriter writer = new BufferedWriter(new FileWriter(plikv2));
    Dzbanek[][] inputPoints = new Dzbanek[4][4];


    public Generuj_punkty() throws IOException {
    }
//dwumian
    static int newton (int n, int k)
    {
        if (k == 0 || k == n)
            return 1;
        else
            return newton(n-1, k-1) + newton(n-1, k);
    }

    static Double berstein(int m, int i, double v){
        return newton(m,i) * Math.pow(v,i) * Math.pow(1-v,m-i);
    }

    public void punkty() throws IOException {
        double x,y,z;
        x=0.0;y=0.0;z=0.0;
        int plaszczyzny = scanner.nextInt();
        writer.write("x, y, z"+System.lineSeparator());
        for(int p=0;p<plaszczyzny;p++){

            for(int j = 0;j < 4;j++){
                for(int k = 0;k < 4;k++){
                    double tymczasowy_x = Double.parseDouble(scanner.next());
                    double tymczasowy_y = Double.parseDouble(scanner.next());
                    double tymczasowy_z = Double.parseDouble(scanner.next());
                    inputPoints[j][k] = new Dzbanek(tymczasowy_x,tymczasowy_y,tymczasowy_z);
                }
            }
            for(double v=0.0;v<=1.0;v+=0.01) {
                for(double w=0.0;w<=1.0;w+=0.01){
                    for (int i=0;i<4;i++){
                        for(int j=0; j<4 ;j++){
                            x += inputPoints[i][j].x * berstein(3,i,w) * berstein(3,j,v);
                            y += inputPoints[i][j].y * berstein(3,i,w) * berstein(3,j,v);
                            z += inputPoints[i][j].z * berstein(3,i,w) * berstein(3,j,v);
                        }
                    }
                    writer.write(x+","+y+","+z+System.lineSeparator());
                    x=0.0;y=0.0;z=0.0;
                }
            }
        }
        writer.close();


    }



}

class Dzbanek {
    double x;
    double y;
    double z;

    public Dzbanek(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    @Override
    public String toString(){
        return "Punkt wygenerowany to: ("+x+","+y+","+z+")";
    }
}
public class Main {

    public static void main(String[] args) throws IOException {
        Generuj_punkty generujpunkty = new Generuj_punkty();
        Scanner scanner = generujpunkty.scanner;
        BufferedWriter writer = generujpunkty.writer;
        generujpunkty.punkty();
    }
}
//Szymon Kudrewicz 80233