let allGames
let allTeams;
let allResults;
let allStadiums;

let gamesURL = "http://localhost:8090/games/";
let teamsURL = "http://localhost:8090/teams/";
let resultsURL = "http://localhost:8090/results/";
let stadiumsURL = "http://localhost:8090/stadiums/";

// Get the modal
var modal = document.getElementById("addGameModal");

// Get the button that opens the modal
var btn = document.getElementById("createButton");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal
btn.onclick = function() {
    let addbtn = document.getElementById("addGameButton");
    let head = document.getElementById("modalHeaderText");
    addbtn.innerHTML = "Create";
    addbtn.setAttribute("onclick", "addGame()");
    head.innerHTML = "Add game";

    document.getElementById("team1").value = "";
    document.getElementById("team2").value = "";
    document.getElementById("date").value = "";
    document.getElementById("hour").value = "";
    document.getElementById("stadium").value = "";

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
async function getGames(){
    await fetch(gamesURL, {
        method: "GET"
    })
        .then((response) => response.json())
        .then((json) => {
            allGames = json;
            console.log(allGames)
            let table = document.getElementById('gamesTable');

            json.forEach(function (object) {
                let rowNo = table.rows.length - 1;
                let tr = document.createElement('tr');
                tr.innerHTML = '<td>' + object.team1.name + '</td>' +
                    '<td>' + object.team2.name + '</td>' +
                    '<td>' + object.datetime.substring(0, 10) + '</td>' +
                    '<td>' + object.datetime.substring(11, 19) + '</td>' +
                    '<td>' + object.stadium.name + '</td>' +
                    '<td>' + (object.result!= null ? object.result.goalsTeamOne : "-")  + '</td>' +
                    '<td>' + (object.result!= null ? object.result.goalsTeamTwo : "-")  + '</td>' +
                    '<td>' +
                    '<button class="button" onclick="openEdit(' + object.id + ',' + rowNo + ')">Edit</button>' +
                    '<button class="buttonRed" onclick="deleteGame(' + object.id + ')">Delete</button>' + '</td>' +
                    (object.result == null ? '<button class="button" onclick="playGame(' + object.id + ')">Play</button>' : "");

                table.appendChild(tr);
            })
        });

    await fetch(teamsURL, {
        method:"GET"
    })
        .then((response) => response.json())
        .then((json) => {
            allTeams = json;
            let selectTeam1 = document.getElementById("team1");
            let selectTeam2 = document.getElementById("team2");
            json.forEach(function (object){
                let option = document.createElement("option");
                // Set the value and text of the option
                option.value = object.id;
                option.text = object.name;
                selectTeam1.appendChild(option);
            })
            json.forEach(function (object){
                let option = document.createElement("option");
                // Set the value and text of the option
                option.value = object.id;
                option.text = object.name;
                selectTeam2.appendChild(option);
            })
        })

    await fetch(stadiumsURL, {
        method:"GET"
    })
        .then((response) => response.json())
        .then((json) => {
            allStadiums = json;
            let selectStadium = document.getElementById("stadium");
            json.forEach(function (object){
                let option = document.createElement("option");
                // Set the value and text of the option
                option.value = object.id;
                option.text = object.name;
                selectStadium.appendChild(option);
            })
        })
    // await fetch(resultsURL, {
    //     method:"GET"
    // })
    //     .then((response) => response.json())
    //     .then((json) => {
    //         allResults = json;
    //     })
}

// Add a game
async function addGame(){
    let team1Input = document.getElementById("team1");
    let team2Input = document.getElementById("team2");
    let dateInput = document.getElementById("date");
    let hourInput = document.getElementById("hour");
    let stadiumInput = document.getElementById("stadium");

    const gameData = {
        team1: allTeams.find(team => team.id == team1Input.value),
        team2: allTeams.find(team => team.id == team2Input.value),
        datetime: dateInput.value + 'T' + hourInput.value + ':00',
        stadium: allTeams.find(stadium => stadium.id == stadiumInput.value),
        result:null
    };

    console.log(gameData)

    await fetch(gamesURL, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(gameData)
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

// Delete a game
async function deleteGame(id){
    await fetch(gamesURL + id, {
        method: "DELETE"
    })
        .then((response) => {
            console.log(response);
        });

    await refreshTable();
}

// Open the modal to edit a game
function openEdit(id, position){
    modal.style.display = "block";

    let addbtn = document.getElementById("addGameButton");
    addbtn.innerHTML = "Edit";
    addbtn.setAttribute("onclick", "editGame(" + id + ")");
    document.getElementById("modalHeaderText").innerHTML = "Edit game";

    document.getElementById("team1").value = allGames[position].team1.id;
    document.getElementById("team2").value = allGames[position].team2.id;
    document.getElementById("date").value = allGames[position].dateTime;
    document.getElementById("hour").value = allGames[position].dateTime;
    document.getElementById("stadium").value = allGames[position].stadium.id;
}

// Edit a game
async function editGame(id){
    let team1Input = document.getElementById("team1");
    let team2Input = document.getElementById("team2");
    let dateInput = document.getElementById("date");
    let hourInput = document.getElementById("hour");
    let stadiumInput = document.getElementById("stadium");

    let gameData = {
        team1: team1Input.value,
        team2: team2Input.value,
        datetime:dateInput.value + 'T' + hourInput.value + ':00',
        stadium: stadiumInput.value,
    };
    console.log(gameData)

    await fetch(gamesURL + id, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(gameData)
    });

    modal.style.display = "none";
    await refreshTable();
}

async function playGame(id) {
    await fetch(gamesURL + "getResult/" + id, {
        method: "GET"
    })

    await refreshTable();

}

// Refresh the table after CRUD operations
async function refreshTable(){
    let table = document.getElementById('gamesTable');

    while(table.rows.length !== 1){
        document.getElementById('gamesTable').deleteRow(1);
    }

    await getGames();
}