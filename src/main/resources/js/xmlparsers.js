AJS.toInit(function ($){
    $("#scripts").auiSelect2();


    $.ajax({
        type: "GET",
        url: AJS.contextPath() + "/rest/xmlparsers/1.0/project",
        success: function (data){
            var dataArray = JSON.parse(data);

            var tableBody = document.getElementById("tableBody");
            var projectSelect = document.getElementById("projectSelect");
            var dataListElement = projectSelect.querySelector("datalist");

            dataArray.forEach(function(item) {
                var row = tableBody.insertRow();

                var projectNameCell = row.insertCell(0);
                projectNameCell.innerHTML = item.projectName;

                var bassProjectKeyCell = row.insertCell(1);
                bassProjectKeyCell.innerHTML = item.bassProjectKey;

                var targetProjectKeyCell = row.insertCell(2);
                targetProjectKeyCell.innerHTML = item.targetProjectKey;

                var scriptsCell = row.insertCell(3);
                scriptsCell.innerHTML = item.scripts.join(", ");

                var deleteButtonCell = row.insertCell(4);
                var deleteButton = document.createElement("button");
                deleteButton.innerHTML = "<span class=\"aui-icon aui-icon-small aui-iconfont-trash\" role=\"img\" aria-label=\"Insert meaningful text here for accessibility\" />";
                deleteButtonCell.appendChild(deleteButton);
                deleteButton.onclick = function(e) {
                    e.preventDefault();
                    deleteProject(item.projectName);
                };
                var listElement = document.createElement("aui-option");
                listElement.innerHTML = item.projectName
                dataListElement.appendChild(listElement)

            });
        }
    })

    $('#projectForm').on('aui-valid-submit',function (e){
        e.preventDefault();

        var projectData = {
            projectName: $('#projectName').val(),
            bassProjectKey: $('#bassProjectKey').val(),
            targetProjectKey: $('#targetProjectKey').val(),
            scripts: $('#scripts').val()
        };


        $.ajax({
            type: "PUT",
            url: AJS.contextPath() + "/rest/xmlparsers/1.0/project",
            contentType: "application/json",
            data: JSON.stringify(projectData),
            success: function (data){
                if(data === ""){
                    $('#success-massage .success-message-body').text('Macros to change was not found');
                }else{
                    $('#success-massage .success-message-body').text(data);
                }
                console.log(data)
                $("#success-massage").fadeIn();
            }
        })
    })

    $('#cleaningTask').on('aui-valid-submit',function (e){
        e.preventDefault();


        $.ajax({
            type: "PUT",
            url: AJS.contextPath() + "/rest/xmlparsers/1.0/task",
            data: {
                pageId:$('#pageId').val(),
                projectName:$('#projectSelect').val()
            },
            success: function (data){
                if(data === ""){
                    $('#success-massage .success-message-body').text('Macros to change was not found');
                }else{
                    $('#success-massage .success-message-body').text(data);
                }
                console.log(data)
                $("#success-massage").fadeIn();
            }
        })
    })


})

function deleteProject(projectName){
    var messageElement = document.getElementById("dialog-project")
    messageElement.innerHTML = projectName
    AJS.dialog2("#project-remove-dialog").show();

    AJS.$("#dialog-submit-button-positive").off('click');

    AJS.$("#dialog-submit-button-negative").on('click', function (e) {
        e.preventDefault();
        AJS.dialog2("#project-remove-dialog").hide();
    });

    AJS.$("#dialog-submit-button-positive").on('click', function (e) {
        e.preventDefault();
        $.ajax({
            type: "DELETE",
            url: AJS.contextPath() + "/rest/xmlparsers/1.0/project",
            data: {
                projectName:projectName
            }
        })
        AJS.dialog2("#project-remove-dialog").hide();
    });
}