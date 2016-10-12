$(document).ready(function ($, html) {

    $(".course-select").select2({
        ajax: {
            url: "api/course/search",
            processResults: function (data) {
                return {
                    results: data
                };
            }
        }
    });

    $("a.back-button").click(function(){
        parent.history.back();
        return false;
    });

});
