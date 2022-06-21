package bridge.solutionThree;

public class BridgeThreeDemo {
    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setClassification("Action");
        movie.setTitle("Encanto");
        movie.setRuntime("2:15");
        movie.setYear("2022");

        Formatter printFormatter = new PrintFormatter();

        Printer moviePrinter = new MoviePrinter(movie);

        String printedMaterial = moviePrinter.print(printFormatter);

        System.out.println(printedMaterial);

        Formatter htmlFormatter = new HtmlFormatter();

        String htmlPrinted = moviePrinter.print(htmlFormatter);

        System.out.println(htmlPrinted);
    }
}
