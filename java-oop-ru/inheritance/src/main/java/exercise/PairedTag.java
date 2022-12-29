package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag{
    private final String text;
    private final List<Tag> children;

    public PairedTag(String tagName, Map<String, String> attributes, String text, List<Tag> children) {
        super(tagName, attributes);
        this.text = text;
        this.children = children;
    }

    @Override
    public String toString() {
        var result = new StringBuilder();
        appendOpenTag(result);
        result.append(text);
        for (Tag child : children) {
            result.append(child.toString());
        }
        appendClosedTag(result);
        return result.toString();
    }
}
// END
