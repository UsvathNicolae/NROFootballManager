let playersURL = "http://localhost:8090/players/";
let teamsURL = "http://localhost:8090/teams/";
let allPlayers;
let allTeams;

async function getData(){
    await fetch(playersURL + "top/" + 5,{
        method: "GET"
    })
        .then((response) => response.json())
        .then((json) => {
            allPlayers = json;
            let table = document.getElementById('playersTable');
            json.forEach(function (object) {
                let rowNo = table.rows.length;
                let tr = document.createElement('tr');
                tr.innerHTML = '<td>' + rowNo + '</td>' +
                    '<td>' + object.name + '</td>' +
                    '<td>' + object.role + '</td>' +
                    '<td>' + object.goalsScored + '</td>';
                table.appendChild(tr);
            })
        });

    await fetch(teamsURL + "top/" + 5, {
        method:"GET"
    })
        .then((response) => response.json())
        .then((json) => {
            allTeams = json;
            let table = document.getElementById('teamsTable');
            json.forEach(function (object) {
                let rowNo = table.rows.length ;
                let tr = document.createElement('tr');
                tr.innerHTML = '<td>' + rowNo  + '</td>' +
                    '<td>' + object.name + '</td>' +
                    '<td>' + object.victories + '</td>' +
                    '<td>' + object.draws + '</td>' +
                    '<td>' + object.defeats + '</td>'+
                    '<td>' + (object.victories * 3 + object.draws) + '</td>';
                table.appendChild(tr);
            })
        });
}