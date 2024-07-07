package com.note_taking_app.note_taking_app.controllers;
import com.note_taking_app.note_taking_app.utilities.MarkdownConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.Map;


@RestController
@RequestMapping("/c")
public class MarkdownController
{
    @PostMapping("/convert")
    public ResponseEntity<String> convertMarkdown(@RequestBody Map<String, String> payload) {
        String markdown = payload.get("markdown");
        if (markdown == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Markdown text is required");
        }

        String html = MarkdownConverter.markdownToHtml(markdown);
        return ResponseEntity.ok(html);
    }

}
