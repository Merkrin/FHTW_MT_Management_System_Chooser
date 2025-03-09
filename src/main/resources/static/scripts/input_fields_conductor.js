function addField() {
    let container = document.getElementById("systems-container");

    let newField = document.createElement("div");
    newField.innerHTML = `<label>System name:<input type="text" name="systems" required"></label>
                            <button type="button" onclick="addField()">+</button>
                            <button type="button" onclick="removeField(this)">-</button>`

    container.appendChild(newField);
}

function removeField(button) {
    button.parentElement.remove();
}
