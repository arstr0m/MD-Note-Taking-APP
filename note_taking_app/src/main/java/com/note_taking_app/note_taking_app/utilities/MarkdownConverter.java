package com.note_taking_app.note_taking_app.utilities;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.MutableDataSet;

public class MarkdownConverter
{
    public static String markdownToHtml(String markdown) {
        MutableDataSet options = new MutableDataSet();

        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();

        // Parse the Markdown and render it as HTML
        return renderer.render(parser.parse(markdown));
    }


}
