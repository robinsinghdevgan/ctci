import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class Q16_12 {

  private static final int END = 0;

  private static final Map<String, Integer> TAGS = new HashMap<String, Integer>() {{
    put("family", 1);
    put("person", 2);
    put("firstName", 3);
    put("lastName", 4);
    put("state", 5);
  }};

  private static final class Element {

    public final String tag;
    public final List<Attribute> attributes;
    public final List<Element> children;
    public final String value;

    public Element(final String tag, final List<Attribute> attributes,
                   final List<Element> children, final String value) {
      this.tag = tag;
      this.attributes = attributes;
      this.children = children;
      this.value = value;
    }

    public Element(final String tag, final List<Attribute> attributes,
                   final List<Element> children) {
      this(tag, attributes, children, "");
    }

    public Element(final String value) {
      this("", Collections.<Attribute>emptyList(), Collections.emptyList(), value);
    }

    public String encode() {
      final StringBuilder s = new StringBuilder();
      encode(s);
      return s.toString();
    }

    private void encode(final StringBuilder s) {
      s.append(TAGS.get(tag)).append(' ');
      for (final Attribute attribute : attributes) {
        s.append(TAGS.get(attribute.tag)).append(' ').append(attribute.value).append(' ');
      }
      s.append(END).append(' ');
      for (final Element child : children) {
        child.encode(s);
        s.append(' ');
      }
      if (!value.isEmpty()) {
        s.append(value).append(' ');
      }
      s.append(END);
    }
  }

  private static class Attribute {

    public final String tag;
    public final String value;

    public Attribute(final String tag, final String value) {
      this.tag = tag;
      this.value = value;
    }
  }

  @SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
  public static void main(String[] args) {
    final Element document = new Element("family", asList(
        new Attribute("lastName", "McDowell"),
        new Attribute("state", "CA")), asList(
        new Element("person", asList(new Attribute("firstName", "Gayle")), asList(), "Some Message")
    ));
    System.out.println(document.encode());//, ("1 4 McDowell 5 CA 0 2 3 Gayle 0 Some Message 0 0"));
  }

  //Code copied and modified from: https://github.com/danielnorberg/katas/blob/master/src/main/java/ctci/ch17/Q17_10_XmlEncoding.java
}