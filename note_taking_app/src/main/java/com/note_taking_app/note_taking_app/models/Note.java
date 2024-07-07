package com.note_taking_app.note_taking_app.models;

import org.springframework.data.annotation.Id;

public class Note {
    @Id
    private String note_id;
    private String title;
    private String markdown;
    private String html;

    public String getNote_id() { return note_id; }
    public void setNote_id(String note_id) { this.note_id = note_id; }

    public String getMarkdown() { return markdown; }
    public void setMarkdown(String markdown) { this.markdown = markdown; }

    public String getHtml() { return html; }
    public void setHtml(String html) { this.html = html; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
