let playersURL = "http://localhost:8090/players/";
let teamsURL = "http://localhost:8090/teams/";
let allPlayers;
let allTeams;


// Get the modal
var modal = document.getElementById("addPlayerModal");

// Get the button that opens the modal
var btn = document.getElementById("createButton");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal
btn.onclick = function() {
    let addbtn = document.getElementById("addPlayerButton");
    let head = document.getElementById("modalHeaderText");
    addbtn.innerHTML = "Create";
    addbtn.setAttribute("onclick", "addPlayer()");
    head.innerHTML = "Add player";

    document.getElementById("playerName").value = "";
    document.getElementById("goalsScored").value = 0;
    document.getElementById("role").value = "";
    document.getElementById("teamName").value = "";

    modal.style.display = "block";
}

span.onclick = function() {
    modal.style.display = "none";
}

//populating the table
async function getPlayers(){
    await fetch(playersURL,{
        method: "GET"
    })
        .then((response) => response.json())
        .then((json) => {
            allPlayers = json;
            let table = document.getElementById('playersTable');
            json.forEach(function (object) {
                let rowNo = table.rows.length - 1;
                let tr = document.createElement('tr');
                tr.innerHTML = '<td>' + object.name + '</td>' +
                    '<td>' + object.goalsScored + '</td>' +
                    '<td>' + object.role + '</td>' +
                    '<td>' + (object.team != null ? object["team"].name : "") + '</td>' +
                    '<button class="button" onclick="openEdit(' + object.id + ','+ rowNo + ')">' + "Edit" + '</button>' +
                    '<button class="buttonRed" onclick="deletePlayer(' + object.id + ')">' + "Delete" + '</button>';
                table.appendChild(tr);
            })
        });
    await fetch(teamsURL, {
        method:"GET"
    })
        .then((response) => response.json())
        .then((json) => {
            allTeams = json;
            let select = document.getElementById("teamName");
            json.forEach(function (object){
                let option = document.createElement("option");
                // Set the value and text of the option
                option.value = object.id;
                option.text = object.name;
                select.appendChild(option);
            })
        })
}

//add a player
async function addPlayer(){

    let nameInput = document.getElementById("playerName");
    let goalsScoredInput = document.getElementById("goalsScored");
    let roleInput = document.getElementById("role");
    let teamInput = document.getElementById("teamName")
    const playerData = {
        name: nameInput.value,
        goalsScored: parseInt(goalsScoredInput.value),
        role: roleInput.value,
        team: (teamInput.value == null? null :  allTeams.find(function(obj) {
            if(obj.id == teamInput.value) return obj;
        }))
    };

    await fetch(playersURL, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(playerData)
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

// delete a player
async function deletePlayer(id){
    await fetch(playersURL + id,{
        method:"DELETE"
    })
        .then((response) =>{
            console.log(response)})
    await refreshTable()
}

function openEdit( id, position){
    modal.style.display = "block";

    let addbtn = document.getElementById("addPlayerButton");
    addbtn.innerHTML = "Edit"
    addbtn.setAttribute("onclick", "editPlayer(" + id + ")");
    document.getElementById("modalHeaderText").innerHTML = "Edit player";

    document.getElementById("playerName").value = allPlayers[position].name;
    document.getElementById("goalsScored").value = allPlayers[position].goalsScored;
    document.getElementById("role").value = allPlayers[position].role;
    document.getElementById("teamName").value = allPlayers[position].team.name;
 }
async function editPlayer( id){

    let nameInput = document.getElementById("playerName");
    let goalsScoredInput = document.getElementById("goalsScored");
    let roleInput = document.getElementById("role");
    let teamInput = document.getElementById("teamName")

    const playerData = {
        name: nameInput.value,
        goalsScored: parseInt(goalsScoredInput.value),
        role: roleInput.value,
        team: teamInput.value
    };
    console.log(playerData)

    await fetch(playersURL + id, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(playerData)
    })
    modal.style.display = "none";
    await refreshTable()
}

async function refreshTable(){
    let table = document.getElementById('playersTable');

    while(table.rows.length !== 1){
        document.getElementById('playersTable').deleteRow(1);
    }

    await getPlayers();
}