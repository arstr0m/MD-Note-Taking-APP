# Note Taking App

Application with MongoDB, Redis, and Spring Boot Integration enables the storage and retrieval of notes and Markdown files in MongoDB. Additionally, it offers functionality for creating and downloading notes seamlessly. Additionally, it includes a wrapper for the LanguageTool API for grammar and spell checking.

## Tech

<div style="display: flex; justify-content: space-around; align-items: center; flex-wrap: wrap;">

![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![JavaScript](https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Redis](https://img.shields.io/badge/redis-%23DD0031.svg?style=for-the-badge&logo=redis&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-%234ea94b.svg?style=for-the-badge&logo=mongodb&logoColor=white)

</div>

# API Reference

## Save Note

`POST /notes/save_note`

## Description

This endpoint allows you to save a new note to the database. You can submit a note with its content in Markdown format and a title. The note will be stored in the database and can be retrieved later.

## Request

### Headers

- `Content-Type: application/json`

### Request Body

The request body should be a JSON object with the following properties:

- `markdown` (string): The content of the note written in Markdown format.
- `title` (string): The title of the note.

**Example Request Body:**

`json`
`{`
` "markdown": "This is a note in **Markdown** format.,`
` "title": "My Note Title"`
`}`

## Markdown Converter

`POST /c/convert`

## Overview

The `/c/convert` endpoint is used to convert Markdown text into HTML. This endpoint processes the provided Markdown content and returns it as an HTML string.

## Request Parameters

The request should be sent with a JSON payload containing the following field:

- `markdown` (string): The Markdown content to be converted to HTML.

### Request Body

**Content-Type:** `application/json`

**Example Request Body:**

`json`
`{`
` `"markdown": "# Hello World\n\nThis is a paragraph` in Markdown."`
`}`

## /n/grammar_check

## Overview

The `/n/grammar_check` endpoint performs grammar and spell-checking on the provided text. It analyzes the text for grammatical errors and returns any issues detected along with suggestions for corrections. IT is a wrapper to API.LANGUAGE.TOOL API

## HTTP Method

- `POST`

## Request URL

- POST `/n/grammar_check`

## Request Parameters

The request should be sent with a JSON payload containing the following field:

- `text` (string): The text to be analyzed for grammar and spelling issues.

### Request Body

**Content-Type:** `application/json`

**Example Request Body:**

json
{
"text": "This is an example text with some grammaticall errors."
}

## POST /u/upload

## Overview

The `/u/upload` endpoint allows for the uploading of files to the server. It accepts file uploads via multipart/form-data, which enables the transfer of files to Database.


## Request Parameters

The request should be sent with a `multipart/form-data` payload. The payload must include a file field.

### Request Body

**Content-Type:** `multipart/form-data`

**Example Request:**

## html
<form id="uploadForm" enctype="multipart/form-data">
  <input type="file" id="fileInput" name="file" />
  <button type="submit">Upload</button>
</form>

## GET `/u/generate_md`
## Overview The `/u/generate_md`
endpoint generates a Markdown (.md) file from the provided text content. It
returns the Markdown file as a downloadable file.

## Query Parameters The request must include the
following query parameters: -
`text` (required): This is the body of the note
`name` (required): This is the name of the file

### Example Request URL
/u/generate_md?text=This%20is%20a%20sample%20Markdown%20content.&name=sample_markdown

