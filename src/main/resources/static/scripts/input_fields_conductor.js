function addField() {
    let container = document.getElementById("systems-container");
    let groups = container.getElementsByClassName("input-group");

    if (groups.length >= 5) return;

    let newField = document.createElement("div");
    newField.classList.add("input-group", "mb-2");

    let input = document.createElement("input");
    input.type = "text";
    input.name = "systems";
    input.classList.add("form-control");
    input.required = true;
    input.placeholder = "System name";

    let removeButton = document.createElement("button");
    removeButton.type = "button";
    removeButton.classList.add("btn", "btn-danger");
    removeButton.innerText = "-";
    removeButton.onclick = function() {
        newField.remove();

        updateAddButtonsState();
    };

    newField.appendChild(input);
    newField.appendChild(removeButton);

    container.appendChild(newField);

    updateAddButtonsState();
}

function updateAddButtonsState() {
    let container = document.getElementById("systems-container");
    let groups = container.getElementsByClassName("input-group");

    // Сначала убираем все "+" кнопки
    for (let group of groups) {
        let addBtn = group.querySelector(".btn-success");
        if (addBtn) addBtn.remove();
    }

    // Если меньше 5 — добавить "+" к последнему
    if (groups.length < 5) {
        let lastGroup = groups[groups.length - 1];

        let addButton = document.createElement("button");
        addButton.type = "button";
        addButton.classList.add("btn", "btn-success");
        addButton.innerText = "+";
        addButton.onclick = addField;

        lastGroup.appendChild(addButton);
    }
}

function removeField(button) {
    button.parentElement.remove();
}

window.onload = function () {
    updateAddButtonsState();
};