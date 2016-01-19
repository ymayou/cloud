$(document).ready(function(){
    $("#btnAddExercise").click(function(){
        var tbody = $("#excerciseTableBody")[0];
        var nbElements = 3;
        var row = tbody.insertRow();
        var time = $("#hour").val() + ":" + $("#minute").val() + ":" + $("#sec").val();
        var inputs = [$("#titleDescription").val(), $("#exerciceDescription").val(), time];
        for (var tmp = 0; tmp < nbElements; tmp++) {
            var cell = row.insertCell(tmp);
            cell.innerHTML = inputs[tmp];
        }
        var cell = row.insertCell(nbElements);
        cell.innerHTML = '<button type="submit" class="btn btn-danger btn-sm"> <span class="glyphicon glyphicon-remove"></span> </button>';
    });
});