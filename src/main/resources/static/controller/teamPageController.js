let allTeams;
let teamsURL = "http://localhost:8090/teams/";

// Get the modal
var modal = document.getElementById("addTeamModal");

// Get the button that opens the modal
var btn = document.getElementById("createButton");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal
btn.onclick = function() {
    let addbtn = document.getElementById("addTeamButton");
    let head = document.getElementById("modalHeaderText");
    addbtn.innerHTML = "Create";
    addbtn.setAttribute("onclick", "addTeam()");
    head.innerHTML = "Add team";

    document.getElementById("name").value = "";
    document.getElementById("victories").value = 0;
    document.getElementById("draws").value = 0;
    document.getElementById("defeats").value = 0;
    document.getElementById("goalsScored").value = 0;
    document.getElementById("goalsReceived").value = 0;

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

//populating the table
async function getTeams(){
    await fetch(teamsURL,{
        method: "GET"
    })
        .then((response) => response.json())
        .then((json) => {
            allTeams = json;
            console.log(allTeams)

            let table = document.getElementById('teamsTable');

            json.forEach(function (object) {
                let rowNo = table.rows.length - 1;
                let tr = document.createElement('tr');
                tr.innerHTML = '<td>' + object.name + '</td>' +
                    '<td>' + object.victories + '</td>' +
                    '<td>' + object.draws + '</td>' +
                    '<td>' + object.defeats + '</td>'+
                    '<td>' + object.goalsScored + '</td>' +
                    '<td>' + object.goalsReceived + '</td>' +
                    '<td>' + (object.victories * 3 + object.draws) + '</td>' +
                    '<button class="button" onclick="openEdit(' + object.id + ','+ rowNo + ')">' + "Edit" + '</button>' +
                    '<button class="buttonRed" onclick="deleteTeam(' + object.id + ')">' + "Delete" + '</button>';
                table.appendChild(tr);
            })
        });

}

//add a team
async function addTeam(){
    let nameInput = document.getElementById("name");
    let victoriesInput = document.getElementById("victories");
    let drawsInput = document.getElementById("draws");
    let defeatsInput = document.getElementById("defeats");
    let goalsScoredInput = document.getElementById("goalsScored");
    let goalsReceivedInput = document.getElementById("goalsReceived");

    const teamData = {
        name: nameInput.value,
        victories: parseInt(victoriesInput.value),
        draws: parseInt(drawsInput.value),
        defeats: parseInt(defeatsInput.value),
        goalsScored: parseInt(goalsScoredInput.value),
        goalsReceived: parseInt(goalsReceivedInput.value)
    };

    await fetch(teamsURL, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(teamData)
    })
    .then((response) => {
        console.log(response);
    })
    .catch((error) => {
        console.error(error);
    });


    modal.style.display = "none";
    await refreshTable()
}

// delete a team
async function deleteTeam(id){
    await fetch(teamsURL + id,{
        method:"DELETE"
    })
        .then((response) =>{
        console.log(response)})

    await refreshTable()
}
function openEdit( id, position){
    modal.style.display = "block";

    let addbtn = document.getElementById("addTeamButton");
    addbtn.innerHTML = "Edit"
    addbtn.setAttribute("onclick", "editTeam(" + id + ")");
    document.getElementById("modalHeaderText").innerHTML = "Edit team";

    document.getElementById("name").value = allTeams[position].name;
    document.getElementById("victories").value = allTeams[position].victories;
    document.getElementById("draws").value = allTeams[position].draws;
    document.getElementById("defeats").value = allTeams[position].defeats;
    document.getElementById("goalsScored").value = allTeams[position].goalsScored;
    document.getElementById("goalsReceived").value = allTeams[position].goalsReceived;
}

async function editTeam(id){

    let nameInput = document.getElementById("name");
    let victoriesInput = document.getElementById("victories");
    let drawsInput = document.getElementById("draws");
    let defeatsInput = document.getElementById("defeats");
    let goalsScoredInput = document.getElementById("goalsScored");
    let goalsReceivedInput = document.getElementById("goalsReceived");

    let teamData = {
        name: nameInput.value,
        victories: parseInt(victoriesInput.value),
        draws: parseInt(drawsInput.value),
        defeats: parseInt(defeatsInput.value),
        goalsScored: parseInt(goalsScoredInput.value),
        goalsReceived: parseInt(goalsReceivedInput.value)
    };

    await fetch(teamsURL + id, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(teamData)
    })
    modal.style.display = "none";
    await refreshTable()

}

async function refreshTable(){
    let table = document.getElementById('teamsTable');

    while(table.rows.length !== 1){
        document.getElementById('teamsTable').deleteRow(1);
    }

    await getTeams();
}
