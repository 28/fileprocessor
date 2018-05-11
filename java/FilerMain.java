import java.io.File;
import java.util.regex.Pattern;

import static org.theparanoidtimes.filer.html.assertion.HtmlAssertion.*;

public class FilerMain {

    public static void main(String[] args) {
        String fileLocation = "./test/resources/test.html";
        File testHtml = new File(fileLocation);
        System.out.println(String.format("Testing Java interop with file: %s", testHtml));
        Boolean result;

        System.out.println(String.format("Testing assertNodeContentIsEqual on node: '%s' and argument: %s...", "div#top > header > h1", "HTML5 Test Page"));
        result = assertNodeContentIsEqual(testHtml, "div#top > header > h1", "HTML5 Test Page");
        System.out.println(String.format("Result: %s.", result));
        if (!result)
            throw new AssertionError("Assertion failed on assertNodeContentIsEqual!");

        System.out.println(String.format("Testing assertNthNodeContentIsEqual on node: '%s' and arguments: '%s', '%d'...", "h1", "HTML5 Test Page", 0));
        result = assertNthNodeContentIsEqual(testHtml, "h1", "HTML5 Test Page", 0);
        System.out.println(String.format("Result: %s.", result));
        if (!result)
            throw new AssertionError("Assertion failed on assertNthNodeContentIsEqual!");

        System.out.println(String.format("Testing assertNodeContentMatches with node: '%s' and argument: '%s'...", "div#top > header > p", "[a-zA-Z\\s.]+"));
        result = assertNodeContentMatches(testHtml, "div#top > header > p", Pattern.compile("[a-zA-Z\\s.]+"));
        System.out.println(String.format("Result: %s.", result));
        if (!result)
            throw new AssertionError("Assertion failed on assertNodeContentMatches!");

        System.out.println(String.format("Testing assertNodeAttributeValue with node: '%s' and arguments: '%s', '%s'...", "fieldset#forms__radio > ul", "class", "list list--bare"));
        result = assertNodeAttributeValue(testHtml, "fieldset#forms__radio > ul", "class", "list list--bare");
        System.out.println(String.format("Result: %s.", result));
        if (!result)
            throw new AssertionError("Assertion failed on assertNodeAttributeValue!");

        System.out.println(String.format("Testing assertLinkNameIsEqual with node: '%s' and argument: '%s'...", "article#embedded__progress > footer > p > a", "[Top]"));
        result = assertLinkNameIsEqual(testHtml, "article#embedded__progress > footer > p > a", "[Top]");
        System.out.println(String.format("Result: %s.", result));
        if (!result)
            throw new AssertionError("Assertion failed on assertLinkNameIsEqual!");

        System.out.println(String.format("Testing assertLinkTargetIsEqual with node: '%s' and argument: '%s'...", "article#embedded__progress > footer > p > a", "#top"));
        result = assertLinkTargetIsEqual(testHtml, "article#embedded__progress > footer > p > a", "#top");
        System.out.println(String.format("Result: %s.", result));
        if (!result)
            throw new AssertionError("Assertion failed on assertLinkTargetIsEqual!");

        System.out.println(String.format("Testing assertImageSrcIsEqual with node: '%s' and argument: '%s'...", "article#embedded__images > div > p > img", "http://placekitten.com/480/480"));
        result = assertImageSrcIsEqual(testHtml, "article#embedded__images > div > p > img", "http://placekitten.com/480/480");
        System.out.println(String.format("Result: %s.", result));
        if (!result)
            throw new AssertionError("Assertion failed on assertImageSrcIsEqual!");

        System.out.println(String.format("Testing assertImageSrcAndAltAreEqual with node: '%s' and arguments: '%s', '%s'...", "article#embedded__images > div > p > img", "http://placekitten.com/480/480", "Image alt text"));
        result = assertImageSrcAndAltAreEqual(testHtml, "article#embedded__images > div > p > img", "http://placekitten.com/480/480", "Image alt text");
        System.out.println(String.format("Result: %s.", result));
        if (!result)
            throw new AssertionError("Assertion failed on assertImageSrcAndAltAreEqual!");

        System.out.println(String.format("Testing assertImageAsLinkIsEqual with node: '%s' and arguments: '%s', '%s'...", "p#img-as-link > a", "http://placekitten.com/480/480", "#top"));
        result = assertImageAsLinkIsEqual(testHtml, "p#img-as-link > a", "http://placekitten.com/480/480", "#top");
        System.out.println(String.format("Result: %s.", result));
        if (!result)
            throw new AssertionError("Assertion failed on assertImageAsLinkIsEqual!");

        System.out.println(String.format("Testing assertOnNodeContent with node: '%s' and comparing its content with: '%s'...", "article#text__paragraphs > header > h1", "Paragraphs"));
        result = assertOnNodeContent(testHtml, "article#text__paragraphs > header > h1", content -> content.equals("Paragraphs"));
        System.out.println(String.format("Result: %s.", result));
        if (!result)
            throw new AssertionError("Assertion failed on assertOnNodeContent!");
    }
}
