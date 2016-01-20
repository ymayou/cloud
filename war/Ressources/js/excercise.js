$(document).ready(function(){
    $("#btnAddExercise").click(function(){
        var tbody = $("#excerciseTableBody")[0];
        var nbElements = 3;
        var row = tbody.insertRow();
        var time = $("#hour").val() + ":" + $("#minute").val() + ":" + $("#sec").val();
        var inputs = [$("#titleDescription").val(), $("#exerciceDescription").val(), time];

        $("#totalTimeValue").text(updateTime(parseInt($("#hour").val()), parseInt($("#minute").val()), parseInt($("#sec").val())));


        // clear inputs
        /*$("#titleDescription").val("");
        $("#exerciceDescription").val("");
        $("#hour").val("");
        $("#minute").val("");
        $("#sec").val("");*/

        // fill table
        for (var tmp = 0; tmp < nbElements; tmp++) {
            var cell = row.insertCell(tmp);
            cell.innerHTML = inputs[tmp];
        }
        var cell = row.insertCell(nbElements);
        cell.innerHTML = '<button type="submit" class="btn btn-danger btn-sm"> <span class="glyphicon glyphicon-remove"></span> </button>';
    });

    $("#btSave").click(function(){
        var title = $("#inputTitle").val();
        var description = $("#inputDescription").val();
        var totalTime = $("#totalTimeValue").text();
        var domain = $("#e1").val();
        var exercises = new Array();
        $("#excerciseTableBody tr").each(function(){
            var data = $(this).find("td");
            if (data.length > 0)
            {
                var exercise = new Array();
                var tmp = 0;
                data.each(function(){
                    if (tmp < 3) // We don' want to push the button in the array
                        exercise[tmp] = $(this).text();
                    tmp++;
                });
                exercises.push(exercise);
            }
        });
        $.post("addPlan",
        {
            cmd: "addPlan",
            data: JSON.stringify({"title": title, "description": description, "domain": domain, "time": totalTime, "exercises": exercises}),
        },
        function(data){
            console.log(data);
        });
    });

    // Form validation
    $("#addPlan").validate({
       rules: {
            hour: {
                required: true,
                number: true,
                min: 0
            },
           minute: {
               required: true,
               number: true,
               min: 0,
               max: 59
           },
           sec: {
               required: true,
               number: true,
               min: 0,
               max: 59
           },
           title: {
               required: true
           },
           titleDescription: {
               required: true
           },
           message: {
               hour: "required",
               minute: "required",
               sec: "required",
               title: "required",
               titleDescription: "required"
           }
       }
    });

    function updateTime(hour, minute, sec)
    {
        var allTime = $("#totalTimeValue").text().split(":");
        allTime[2] = parseInt(allTime[2])+ sec;
        allTime[1] = parseInt(allTime[1])+ minute;
        allTime[0] = parseInt(allTime[0])+ hour;

        if (allTime[2] >= 60)
        {
            allTime[2] = allTime[2] - 60;
            allTime[1] = allTime[1] + 1;
        }
        if (allTime[1] >= 60)
        {
            allTime[1] = allTime[1] - 60;
            allTime[0] = allTime[0] + 1;
        }

        console.log(allTime);
        return allTime[0] + ":" + allTime[1] + ":" + allTime[2];
    }
});