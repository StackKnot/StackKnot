$(document).ready(function() {
    // CodeMirror.modeURL = "../../static/js/codemirror/mode/%N/%N.js"
    let code = $(".codemirror-textarea")[0];
    let editor = CodeMirror.fromTextArea(code,{
        lineNumbers : true,
        theme: "dracula",
        mode: "javascript"
    });
    editor.setSize("600", "250");
})