let playersURL = "http://localhost:8090/players/";
let teamsURL = "http://localhost:8090/teams/";
let allPlayers;
let allTeams;

async function getData(){
    await fetch(playersURL,{
        method: "GET"
    })
        .then((response) => response.json())
        .then((json) => {
            allPlayers = json;
        });

    await fetch(teamsURL, {
        method:"GET"
    })
        .then((response) => response.json())
        .then((json) => {
            allTeams = json;
        })
}