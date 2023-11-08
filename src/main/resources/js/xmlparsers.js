AJS.toInit(function ($){
    $("#scripts").auiSelect2();

    $('#projectForm').on('aui-valid-submit',function (e){
        console.log($('#scripts').val())
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
                $("#success-massage").fadeIn();
            }
        })
    })

})

