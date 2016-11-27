$(document).ready(function ($, html) {

    $("a.back-button").off("click");


    $(".course-on-nav").bind('change', function () {
        var url = $(this).val(); // get selected value
        if (url) {
            window.location = "admin/courses/" + url + "/edit";
        }
        return false;
    });

});