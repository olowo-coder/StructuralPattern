package bridge.solutionThree;

import java.util.List;

public class PrintFormatter implements Formatter{
    @Override
    public String format(String header, List<Detail> details) {
        StringBuilder builder = new StringBuilder();
        builder.append(header);
        builder.append("\n");

        details.forEach(x -> {
            builder.append(x.getLabel());
            builder.append(":");
            builder.append(x.getValue());
            builder.append("\n");
        });

        return builder.toString();
    }
}
