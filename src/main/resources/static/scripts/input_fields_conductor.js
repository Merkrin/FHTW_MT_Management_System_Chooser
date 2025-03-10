function addField() {
    let container = document.getElementById("systems-container");

    let newField = document.createElement("div");
    newField.classList.add("input-group", "mb-2");

    let input = document.createElement("input");
    input.type = "text";
    input.name = "systems";
    input.classList.add("form-control");
    input.required = true;
    input.placeholder = "System name";

    let addButton = document.createElement("button");
    addButton.type = "button";
    addButton.classList.add("btn", "btn-success");
    addButton.innerText = "+";
    addButton.onclick = addField;

    let removeButton = document.createElement("button");
    removeButton.type = "button";
    removeButton.classList.add("btn", "btn-danger");
    removeButton.innerText = "-";
    removeButton.onclick = function() {
        newField.remove();
    };

    newField.appendChild(input);
    newField.appendChild(addButton);
    newField.appendChild(removeButton);

    container.appendChild(newField);
}

function removeField(button) {
    button.parentElement.remove();
}
