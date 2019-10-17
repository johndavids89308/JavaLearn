import java.io.File;

public class Main {
    public static void main(String[] args){
        ParsCsv parsCsv = new ParsCsv(new File("data/movementList.csv"));

        try{
            parsCsv.sortCounterparty();
            parsCsv.publicInOut();
        }catch (Exception e){
            System.out.println(e.fillInStackTrace());
        }
    }
}
