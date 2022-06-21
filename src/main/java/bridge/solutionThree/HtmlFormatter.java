package bridge.solutionThree;

import java.util.List;

public class HtmlFormatter implements Formatter{
    @Override
    public String format(String header, List<Detail> details) {
        StringBuilder builder = new StringBuilder();
        builder.append("<table>");
        builder.append("<th>");
        builder.append("Classification");
        builder.append("</th>");
        builder.append("<th>");
        builder.append(header);
        builder.append("</th>");

        details.forEach(x -> {
            builder.append("<tr><td>");
            builder.append(x.getLabel());
            builder.append("</td><td>");
            builder.append(x.getValue());
            builder.append("</td></tr>");
        });
        builder.append("<table>");

        return builder.toString();
    }
}
