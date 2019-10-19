public class Main {
    public static void main(String[] args){

        try{
            HtmlPars pars = new HtmlPars();
            pars.htmlParse("https://lenta.ru/");
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
