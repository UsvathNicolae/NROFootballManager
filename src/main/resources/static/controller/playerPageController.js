let basePlayersURL = "http://localhost:8090/players/";
let baseTeamsURL = "http://localhost:8090/players/";
let allPlayers;
let teams;

//populating the table
async function getPlayers(){
    await fetch(basePlayersURL,{
        method: "GET"
    })
        .then((response) => response.json())
        .then((json) => {
            allPlayers = json;
            var table = document.getElementById('playersTable');
            json.forEach(function (object) {
                console.log(object)
                var tr = document.createElement('tr');
                tr.innerHTML = '<td>' + object.name + '</td>' +
                    '<td>' + object.goalsScored + '</td>' +
                    '<td>' + object.role + '</td>' +
                    '<td>' + object["team"].name + '</td>' +
                    '<button onclick="editPlayer(' + object.id + ')">' + "Edit" + '</button>' +
                    '<button onclick="deletePlayer(' + object.id + ')">' + "Delete" + '</button>';
                table.appendChild(tr);
            })
        });
    await fetch(baseTeamsURL, {
        method:"GET"
    })
        .then((response) => response.json())
        .then((json) => {
            teams = json;
        })
}

//add a player
async function addPlayer(){

    var nameInput = document.getElementById("name");
    var victoriesInput = document.getElementById("victories");
    var drawsInput = document.getElementById("draws");
    var defeatsInput = document.getElementById("defeats");
    var goalsScoredInput = document.getElementById("goalsScored");
    var goalsReceivedInput = document.getElementById("goalsReceived");

    var playerData = {
        name: nameInput.value,
        victories: parseInt(victoriesInput.value),
        draws: parseInt(drawsInput.value),
        defeats: parseInt(defeatsInput.value),
        goalsScored: parseInt(goalsScoredInput.value),
        goalsReceived: parseInt(goalsReceivedInput.value)
    };

    await fetch(basePlayersURL, {
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

    location.reload();
}

// delete a player
async function deletePlayer(id){
    await fetch(basePlayersURL + id,{
        method:"DELETE"
    })
        .then((response) =>{
            console.log(response)})
    location.reload();
}
async function editPlayer( id){
    console.log("It works!" + id);
}

// Get the modal
var modal = document.getElementById("addPlayerModal");

// Get the button that opens the modal
var btn = document.getElementById("createButton");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal
btn.onclick = function() {
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