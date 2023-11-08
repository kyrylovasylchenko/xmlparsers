AJS.toInit(function ($){
    $("#scripts").auiSelect2();

    $.ajax({
        type: "GET",
        url: AJS.contextPath() + "/rest/xmlparsers/1.0/project",
        success: function (data){
            console.log(data)
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

})

