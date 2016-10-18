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

    $('.scroll-indicator-item').click( function() { 
        var page = $(this).attr('href'); 
        var speed = 1200; 
        $("#navbar").removeClass("in")
        $('html, body').animate( { scrollTop: $(page).offset().top - 50}, speed );
        return false;
    });

    //#to-top button appears after scrolling
    var fixed = false;
    $(document).scroll(function() {
        if ($(this).scrollTop() > 250) {
            if (!fixed) {
                fixed = true;
                // $('#to-top').css({position:'fixed', display:'block'});
                $('#to-top').show("slow", function() {
                    $('#to-top').css({
                        position: 'fixed',
                        display: 'block'
                    });
                });
            }
        } else {
            if (fixed) {
                fixed = false;
                $('#to-top').hide("slow", function() {
                    $('#to-top').css({
                        display: 'none'
                    });
                });
            }
        }
    });

});
