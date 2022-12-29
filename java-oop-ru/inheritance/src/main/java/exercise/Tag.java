package exercise;

import java.util.Map;

// BEGIN
public class Tag {
    private final String tagName;
    private final Map<String, String> attributes;

    public Tag(String tagName, Map<String, String> attributes) {
        this.tagName = tagName;
        this.attributes = attributes;
    }

    protected void appendOpenTag(StringBuilder builder) {
        builder.append("<").append(tagName);
        for (var attribute : attributes.entrySet()) {
            builder.append(" ")
                .append(attribute.getKey())
                .append("=\"")
                .append(attribute.getValue())
                .append("\"");
        }
        builder.append(">");
    }

    protected void appendClosedTag(StringBuilder builder) {
        builder.append("</").append(tagName).append(">");
    }
}
// END
