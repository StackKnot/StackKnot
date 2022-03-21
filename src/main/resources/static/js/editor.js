$(document).ready(function() {
    let code = $(".codemirror-textarea")[0];
    let editor = CodeMirror.fromTextArea(code,{
        lineNumbers : true,
        theme: "dracula",
        mode: "javascript"
    });
    editor.setSize("700", "450");
})