let allStadiums;
let stadiumsURL = "http://localhost:8090/stadiums/";

// Get the modal
var modal = document.getElementById("addStadiumModal");

// Get the button that opens the modal
var btn = document.getElementById("createButton");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal
btn.onclick = function() {
    let addbtn = document.getElementById("addStadiumButton");
    let head = document.getElementById("modalHeaderText");
    addbtn.innerHTML = "Create";
    addbtn.setAttribute("onclick", "addStadium()");
    head.innerHTML = "Add stadium";

    document.getElementById("stadiumName").value = "";
    document.getElementById("stadiumLocation").value = "";

    document.getElementById("errorMessage").hidden = true;

    modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

// Populating the table
async function getStadiums(){
    await fetch(stadiumsURL, {
        method: "GET"
    })
        .then((response) => response.json())
        .then((json) => {
            allStadiums = json;
            console.log(allStadiums);

            let table = document.getElementById('stadiumsTable');

            json.forEach(function (object) {
                let rowNo = table.rows.length - 1;
                let tr = document.createElement('tr');
                tr.innerHTML = '<td>' + (rowNo + 1) + '</td>' +
                    '<td>' + object.name + '</td>' +
                    '<td>' + object.location + '</td>' +
                    '<td>' +
                    '<button class="button" onclick="openEdit(' + object.id + ','+ rowNo + ')">Edit</button>' +
                    '<button class="buttonRed" onclick="deleteStadium(' + object.id + ')">Delete</button>' +
                    '</td>';
                table.appendChild(tr);
            })
        });
}

// Add a stadium
async function addStadium(){
    let nameInput = document.getElementById("stadiumName");
    let locationInput = document.getElementById("stadiumLocation");

    let error = document.getElementById("errorMessage")
    error.hidden = true;

    if(nameInput.value == null || nameInput.value.trim() === ""){
        error.hidden = false;
        error.innerHTML = "Name field cannot be empty!";
    }else{
        if(locationInput.value == null || locationInput.value.trim() === ""){
            error.hidden = false;
            error.innerHTML = "Location field cannot be empty!";
        }
    }

    if(error.hidden){
        const stadiumData = {
            name: nameInput.value,
            location: locationInput.value
        };

        await fetch(stadiumsURL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(stadiumData)
        })
            .then((response) => {
                console.log(response);
            })
            .catch((error) => {
                console.error(error);
            });

        modal.style.display = "none";
        await refreshTable();
    }

}

// Delete a stadium
async function deleteStadium(id){
    await fetch(stadiumsURL + id, {
        method: "DELETE"
    })
        .then((response) => {
            console.log(response);
        });

    await refreshTable();
}

// Open the modal to edit a stadium
function openEdit(id, position){
    modal.style.display = "block";

    let addbtn = document.getElementById("addStadiumButton");
    addbtn.innerHTML = "Edit";
    addbtn.setAttribute("onclick", "editStadium(" + id + ")");
    document.getElementById("modalHeaderText").innerHTML = "Edit stadium";

    document.getElementById("stadiumName").value = allStadiums[position].name;
    document.getElementById("stadiumLocation").value = allStadiums[position].location;
    document.getElementById("errorMessage").hidden = true;
}

// Edit a stadium
async function editStadium(id){
    let nameInput = document.getElementById("stadiumName");
    let locationInput = document.getElementById("stadiumLocation");
    let error = document.getElementById("errorMessage")
    error.hidden = true;

    if(nameInput.value == null || nameInput.value.trim() === ""){
        error.hidden = false;
        error.innerHTML = "Name field cannot be empty!";
    }else{
        if(locationInput.value == null || locationInput.value.trim() === ""){
            error.hidden = false;
            error.innerHTML = "Location field cannot be empty!";
        }
    }

    if(error.hidden){
        let stadiumData = {
            name: nameInput.value,
            location: locationInput.value
        };

        await fetch(stadiumsURL + id, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(stadiumData)
        });

        modal.style.display = "none";
        await refreshTable();
    }

}

// Refresh the table after CRUD operations
async function refreshTable(){
    let table = document.getElementById('stadiumsTable');

    while(table.rows.length !== 1){
        document.getElementById('stadiumsTable').deleteRow(1);
    }

    await getStadiums();
}