"use strict";

// WHITE BOARD SECTION
CKEDITOR.replace('editor', {
    height: 260,
    width: 700,
    removeButtons: 'PasteFromWord'
});


function showImage() {
    document.getElementById('solutionJava').style.visibility = 'visible';
    document.getElementById('solutionJS').style.visibility = 'visible';
}

function getNext() {
    let randomQuestion = Math.floor((Math.random() * 2) + 1);
    location.replace('/whiteboard/' + randomQuestion)
}





